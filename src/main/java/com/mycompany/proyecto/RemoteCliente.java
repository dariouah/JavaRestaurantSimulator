package com.mycompany.proyecto;

import static java.lang.Thread.sleep;
import java.rmi.Naming;
import java.util.ArrayList;
import javax.swing.JTextField;

public class RemoteCliente {

    public static void main(String[] args) {
        
        //RMI cliente
        InterfazComun obj= null;
        
        boolean encontrado=false;
        
        //Bucle hasta que se inicie el servidor
        while (!encontrado)
        {
            try {
                obj=(InterfazComun) Naming.lookup("//localhost/ObjetoC");
                encontrado=true;
            }catch (Exception e){}
        }
        
        PantallaCliente ps = new PantallaCliente();
        ps.setVisible(true);
        
        //Tomamos los jtextfield del jframe
        ArrayList<JTextField> textFields = ps.getJTF();
        
        //textFields.get(2).setText("hola");
        
        //Los guardamos en sus correspondientes variables
        JTextField fuera = textFields.get(0);
        JTextField dentro = textFields.get(1);
        JTextField pedidos = textFields.get(2);
        JTextField importe = textFields.get(3);
           
        
        try
        {
            while(true)
            {
                sleep(500);
                //Actualizamos constantemente los valores
                fuera.setText(String.valueOf(obj.getFuera()));
                dentro.setText(String.valueOf(obj.getDentro()));
                pedidos.setText(String.valueOf(obj.getPedidos()));
                importe.setText(String.valueOf(obj.getImporte()));
            }
        }
        catch (Exception e){
            //System.out.println("Se ha desconectado el servidor");
        }
    }
}