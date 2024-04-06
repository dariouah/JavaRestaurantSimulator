package com.mycompany.proyecto;

import java.util.ArrayList;
import javax.swing.JTextField;

public class ListaCocineros {
    
    ArrayList<Cocinero> lista;
    JTextField tf;
    
    public ListaCocineros(JTextField tf)
    {
        lista=new ArrayList<>();
        this.tf=tf;
    }
    
    public synchronized void meter(Cocinero c)
    {
        lista.add(c);
        imprimir();
    }
    
    public synchronized void sacar(Cocinero c)
    {
        lista.remove(c);
        imprimir();
    }
    
    public void imprimir()
    {
        String contenido="";
        for(int i=0; i<lista.size(); i++)
        {
           contenido=contenido+lista.get(i).getMiId()+" ";
        }
        tf.setText(contenido);
    }
}