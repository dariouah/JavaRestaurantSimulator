package com.mycompany.proyecto;

import static java.lang.Thread.sleep;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class Cocina {
    
    private Lock pedido;
    private Condition hayComanda;
    private ArrayList<Comanda> comandas;
    
    //Puestos
    private ArrayList<Puesto> puestos;
    
    //Pedidos e importe totales de esta cocina
    private JTF pt;
    private JTF impt;
    
    //Pedidos e importe de todas las cocinas
    private JTF pf;
    private JTF impf;
    
    //Lista con los id de los clientes cuyas comandas ya se han terminado
    private ArrayList<String> listas;
    
    //Monitor para controlar las paradas
    Parada parada;
        
    public Cocina(ArrayList<Puesto> puestos, JTextField pt, JTextField impt, JTextField pf, JTextField impf, Parada parada)
    {
        pedido=new ReentrantLock();
        hayComanda=pedido.newCondition();
        comandas=new ArrayList<>();
        
        this.puestos=puestos;
        
        this.pt=new JTF(pt);
        this.impt=new JTF(impt);
        
        this.pf=new JTF(pf);
        this.impf=new JTF(impf);
        
        listas=new ArrayList<>();
        
        this.parada=parada;
    }
    
    public Comanda esperarNueva(String id)
    {
        Comanda nueva = null;
        try{
            pedido.lock();
            while(comandas.isEmpty())
            {
                hayComanda.await();
            }
            nueva=comandas.remove(0);
        }catch(Exception e){
        }finally{
            pedido.unlock();
        }
        return nueva;
    }
    
    public Puesto cocinar(Comanda comanda, String id)
    {
        //Locizamos un puesto que no se este usando
        Puesto puesto=null;
        int i=0;
        boolean encontrado=false;
        while (!encontrado)
        {
            puesto=puestos.get(i);
            if (!puestos.get(i).getOcupado())
            {
                encontrado=true;
            }else
            {
                i=(i+1)%puestos.size();
            }
        }
        //Ocupamos el puesto
        puesto.setOcupado(true);
        //Guardamos el numero de productos pendientes
        int nProd=comanda.getnProd();
        //Damos valores a los jtf
        puesto.setCocinero(id);
        puesto.setPedido(comanda.getCliente());
        puesto.setPendientes(Integer.toString(nProd));
        puesto.setCliente(comanda.getCliente());
        
        //Mientras hay productos cocinamos
        int importe=0;
        while (nProd>0)
        {
            try {
                sleep(1000+(int)(1000*Math.random()));
                parada.puedoPasar();
                nProd--;
                puesto.setPendientes(Integer.toString(nProd));
                importe+=4+(int)(5*Math.random());
            } catch (InterruptedException ex) {}
        }
        //Indicamos el coste correspondiente a la comanda
        puesto.setPendientes("PAGANDO "+importe+"€"); 
        puesto.setCoste(importe);
        
        //Avisamos al cliente de que ya tenemos su pedido
        avisar(puesto);
        
        return puesto;
    }
    
    private synchronized void avisar(Puesto puesto)
    {
        listas.add(puesto.getPedido());
        notifyAll();
    }
    
    public void cobrar(Puesto puesto)
    {
        Exchanger<String> e=puesto.getExch();
        String cambio="<Comida>";
        try {
            sleep(1000);
            parada.puedoPasar();
            cambio=e.exchange("<Comida>");
        } catch (InterruptedException ex) {}
        //Actualizamos los pedidos totales de la cocina
        int antes=Integer.parseInt(pt.getJtf());
        pt.setJtf(Integer.toString(antes+1));
        
        //Actualizamos importe total de la cocina
        antes=Integer.parseInt(impt.getJtf());
        impt.setJtf(Integer.toString(antes+Integer.parseInt(cambio)));
        
        //Actualizamos los pedidos totales de todas las cocinas
        antes=Integer.parseInt(pf.getJtf());
        int pedidos=antes+1;
        pf.setJtf(Integer.toString(antes+1));
        
        //Actualizamos importe total de todas las cocinas
        antes=Integer.parseInt(impf.getJtf());
        int importe=antes+Integer.parseInt(cambio);
        impf.setJtf(Integer.toString(importe));
        
        //Limpiamos y dejamos libre el puesto
        puesto.limpiar();
    }
    
    public void añadirNueva(Comanda comanda)
    {
        comandas.add(comanda);
        try
        {
            pedido.lock();
            hayComanda.signal();
        }catch(Exception e){
        }finally{
            pedido.unlock();
        }
    }
    
    public Puesto estaLista(String id)
    {
        contiene(id);
        boolean encontrado=false;
        int i=0;        
        while (!encontrado)
        {
            if (puestos.get(i).getCliente()==id)
            {
                encontrado=true;
            }else
            {
                i=(i+1)%puestos.size();
            }
        }
        return puestos.get(i);
    }
    
    private synchronized void contiene(String id)
    {
        while (!listas.contains(id))
        {
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        listas.remove(id);
    }  
}