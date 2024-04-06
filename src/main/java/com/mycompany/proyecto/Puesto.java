package com.mycompany.proyecto;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;
import javax.swing.JTextField;

public class Puesto {
    
    private JTF cocinero;
    private JTF pedido;
    private JTF pendiente;
    private int coste;
    private Exchanger<String> exch;
    private boolean ocupado;
    private String cliente;
    
    public Puesto(ArrayList<JTextField> campos)
    {
        this.cocinero=new JTF(campos.get(0));
        this.pedido=new JTF(campos.get(1));
        this.pendiente=new JTF(campos.get(2));
        this.coste=0;
        this.exch=new Exchanger();
        ocupado=false;
        cliente="";
    }
    
    public void setOcupado(boolean oc)
    {
        ocupado=oc;
    }
    
    public boolean getOcupado()
    {
        return ocupado;
    }
    
    public void setCocinero(String cocinero)
    {
        this.cocinero.setJtf(cocinero);
    }
    
    public void setPedido(String pedido)
    {
        this.pedido.setJtf(pedido);
    }
    
    public String getPedido()
    {
        return pedido.getJtf();
    }
    
    public void setPendientes(String pendiente)
    {
        this.pendiente.setJtf(pendiente);
    }
    
    public int getCoste()
    {
        return coste;
    }
    
    public void setCoste(int coste)
    {
        this.coste=coste;
    }
    
    public Exchanger<String> getExch()
    {
        return exch;
    }
    
    public String getCliente()
    {
        return cliente;
    }
    
    public void setCliente(String cli)
    {
        this.cliente=cli;
    }
    
    public void limpiar()
    {
        cocinero.setJtf("");
        pedido.setJtf("");
        pendiente.setJtf("");
        coste=0;
        ocupado=false;
        cliente="";
    }
}