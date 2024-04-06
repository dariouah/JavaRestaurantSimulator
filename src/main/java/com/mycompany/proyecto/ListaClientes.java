package com.mycompany.proyecto;

import java.util.ArrayList;
import javax.swing.JTextField;

public class ListaClientes {
    
    private ArrayList<Cliente> lista;
    private JTextField tf;
    private int contador;
    
    public ListaClientes(JTextField tf)
    {
        lista=new ArrayList<>();
        this.tf=tf;
    }
    
    public synchronized void meter(Cliente h)
    {
        lista.add(h);
        contador++;
        imprimir();
    }
    
    public synchronized void sacar(Cliente h)
    {
        lista.remove(h);
        contador--;
        imprimir();
    }
    
    public int getContador()
    {
        return this.contador;
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