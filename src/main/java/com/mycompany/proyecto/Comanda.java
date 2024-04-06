package com.mycompany.proyecto;

public class Comanda {
    
    private int tipo;
    private int nProd;
    private String cliente;
    
    
    public Comanda (int tipo, int nProd, String cliente)
    {
        this.tipo=tipo;
        this.nProd=nProd;
        this.cliente=cliente;
    }
    
    public int getTipo()
    {
        return tipo;
    }
    
    public int getnProd()
    {
        return nProd;
    }
    
    public String getCliente()
    {
        return cliente;
    }
}