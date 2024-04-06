package com.mycompany.proyecto;

import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Proyecto {

    public static void main(String[] args) {
        //Iniciamos jFrame
        PantallaR pr=new PantallaR();
        pr.setVisible(true);
        Parada parada=pr.getParada();
        
        //Tomamos todos los jFrame
        ArrayList<JTextField> datos=pr.getJTF();
        
        //Tomamos los botones
        ArrayList<JButton> botones=pr.getJButton();
        
        //Creamos listas para gestionar los cocineros en descanso
        ListaCocineros cocinerosD=new ListaCocineros(datos.get(32));
        
        
        //Creamos cada puesto de la cocina española
        ArrayList<Puesto> puestos=new ArrayList<>();
        for (int i=0; i<3; i++)
        {
            ArrayList<JTextField> campos=new ArrayList<>();
            for (int j=0; j<3; j++)
            {
                campos.add(datos.get(i*3+j+2));
            }
            Puesto p=new Puesto(campos);
            puestos.add(p);
        }
        //Creamos la cocina española
        Cocina cEsp=new Cocina(puestos, datos.get(11), datos.get(12), datos.get(33), datos.get(34), parada);
        
        //Creamos cada puesto de la cocina asiatica
        puestos=new ArrayList<>();
        for (int i=0; i<2; i++)
        {
            ArrayList<JTextField> campos=new ArrayList<>();
            for (int j=0; j<3; j++)
            {
                campos.add(datos.get(i*3+j+13));
            }
            Puesto p=new Puesto(campos);
            puestos.add(p);
        }
        //Creamos la cocina asiatica
        Cocina cMex=new Cocina(puestos, datos.get(19), datos.get(20), datos.get(33), datos.get(34), parada);
        
        
        //Creamos cada puesto de la cocina mexicana
        puestos=new ArrayList<>();
        for (int i=0; i<3; i++)
        {
            ArrayList<JTextField> campos=new ArrayList<>();
            for (int j=0; j<3; j++)
            {
                campos.add(datos.get(i*3+j+21));
            }
            Puesto p=new Puesto(campos);
            puestos.add(p);
        }
        //Creamos la cocina mexicana
        Cocina cAsi=new Cocina(puestos, datos.get(30), datos.get(31), datos.get(33), datos.get(34), parada);
        
        //Creamos el restaurante
        Restaurante r=null;
        try {
            r = new Restaurante(datos, cEsp, cMex, cAsi, cocinerosD, parada, botones.get(0), botones.get(1));
        } catch (RemoteException ex) {
            System.out.println("No se ha podido registrar correctamente");
        }
        
        //Creamos a los cocineros
        for (int i=1; i<4; i++)
        {
            Cocinero c=new Cocinero("CO"+i, 0, r, cEsp);
            c.start();
        }
        
        for (int i=4; i<6; i++)
        {
            Cocinero c=new Cocinero("CO"+i, 1, r, cMex);
            c.start();
        }
        
        for (int i=6; i<9; i++)
        {
            Cocinero c=new Cocinero("CO"+i, 2, r, cAsi);
            c.start();
        }

        //Creamos los clientes
        for (int i=0;i<20000;i++)
        {
            try {
                //Esperamos de 0,5 a 6 segundos para generar el siguiente cliente
                esperarSig();
                parada.puedoPasar();
            } catch (InterruptedException ex) {}
            Cliente c=new Cliente("CL"+i, r);
            c.start();
        }
    }
    
    private static void esperarSig() throws InterruptedException 
    {
        sleep(500+(int)(500*Math.random()));
    }
}