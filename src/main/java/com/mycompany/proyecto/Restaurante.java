package com.mycompany.proyecto;

import static java.lang.Thread.sleep;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Restaurante extends UnicastRemoteObject implements InterfazComun{
    
    //Listas para actualizar los clientes
    private ListaClientes fuera;
    private ListaClientes dentro;
    
    //Controlar maquina de pedidos
    private Semaphore cola;
    private Lock maquina;
    
    //Cocinas
    private Cocina cEsp;
    private Cocina cAsi;
    private Cocina cMex;
        
    //Lista de cocineros descansando
    private ListaCocineros cocinerosD;
    
    //Monitor para controlar la parada
    Parada parada;
    
    //RMI
    private Restaurante obj;
    
    //Pedidos e importe total
    JTextField pedidos;
    JTextField importe;
    
    //Boton de parar y reanudar
    private JButton parar;
    private JButton reanudar;
        
    public Restaurante(ListaClientes fuera, ListaClientes dentro, JTextField pedidos, JTextField importe, JButton parar, JButton reanudar) throws RemoteException
    {
        this.fuera=fuera;
        this.dentro=dentro;
        this.pedidos=pedidos;
        this.importe=importe;
        this.parar=parar;
        this.reanudar=reanudar;
    }
    
    public Restaurante(ArrayList<JTextField> jtf, Cocina cEsp, Cocina cMex, Cocina cAsi, ListaCocineros cocinerosD, Parada parada, JButton parar, JButton reanudar) throws RemoteException
    {
        fuera=new ListaClientes(jtf.get(0));
        dentro=new ListaClientes(jtf.get(1));
        
        cola=new Semaphore(20);
        maquina=new ReentrantLock();
        
        this.cEsp=cEsp;
        this.cMex=cMex;
        this.cAsi=cAsi;
        
        this.cocinerosD=cocinerosD;
        
        this.parada=parada;
        
        this.pedidos=jtf.get(33);
        this.importe=jtf.get(34);
        
        this.parar=parar;
        this.reanudar=reanudar;
        
        try{
            obj = new Restaurante(fuera,dentro,pedidos,importe,parar,reanudar); 
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost/ObjetoC", obj);
            
        }catch(Exception e){
            System.out.println("doy error");
        }
    }
    
    public void esperarCola(Cliente c) throws InterruptedException
    {
        fuera.meter(c);
        
        cola.acquire();
        //Estamos dentro si no nos quedamos en la cola de afuera
        fuera.sacar(c);
        dentro.meter(c);
        //Cola (dentro) para usar la maquina
        maquina.lock();
    }
    
    public Cocina usarMaquina(Cliente c) throws InterruptedException
    {
        //Hacemos la comanda con la maquina de pedidos
        int tipo=(int) (3*Math.random());
        int nProd=5+(int)(6*Math.random());
        Comanda comanda=new Comanda(tipo,nProd,c.getMiId());
        sleep(2000+(int)(1000*Math.random()));
        parada.puedoPasar();
        //Una vez usada la maquina, ya esta lista la comanda
        //Tomamos la cocina correspondiente
        Cocina cocina=null;
        switch(tipo)
        {
            case 0:
                cocina=cEsp;
                break;
            case 1:
                cocina=cMex;
                break;
            case 2:
                cocina=cAsi;
                break;
            default:
                cocina=cEsp;
                break;
        }
        
        cocina.añadirNueva(comanda);
        //Salimos de la cola
        maquina.unlock();
        dentro.sacar(c);
        cola.release(); 
        
        return cocina;
    }
    
    public Puesto esperarComida(Cliente c, Cocina cocina) throws InterruptedException
    {        
        //Tomamos el puesto que corresponde con nuestra comanda
        Puesto puesto=cocina.estaLista(c.getMiId());
        return puesto;
    }
    
    public void pagar(Cliente c, Puesto puesto)
    {
        String cambio=Integer.toString(puesto.getCoste());
        Exchanger<String> e=puesto.getExch();
        try {
            cambio=e.exchange(cambio);
        } catch (InterruptedException ex) {}
    }
    
    public void descansar(Cocinero c)
    {
        //Cocinero descansa 10s
        try {
            cocinerosD.meter(c);
            sleep(10000);
            parada.puedoPasar();
        } catch (InterruptedException ex) {
        } finally {cocinerosD.sacar(c);}
    }

    
    //Metodos remotos
    public int getFuera() throws RemoteException {
        return fuera.getContador();
    }

    public int getDentro() throws RemoteException {
        return dentro.getContador();
    }

    public String getPedidos() throws RemoteException {
        return pedidos.getText();
    }

    public String getImporte() throws RemoteException {
        return importe.getText();
    }
    
    public void pulsarParar() throws RemoteException {
        parar.doClick();
    }
    public void pulsarReanudar() throws RemoteException {
        reanudar.doClick();
    }
}