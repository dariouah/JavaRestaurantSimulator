package com.mycompany.proyecto;

public class Cliente extends Thread{
    
    private String id;
    private Restaurante rest;
    private Log log;
    
    public Cliente(String id, Restaurante rest)
    {
        this.id=id;
        this.rest=rest;
        this.log=Log.getInstance();
    }
    
    public void run()
    {
        try{
            log.escribir("Cliente "+id+" esta ejecutandose");
            log.escribir("Cliente "+id+" esta esperando la cola");
            rest.esperarCola(this);
            log.escribir("Cliente "+id+" esta usando maquina de pedidos");
            Cocina cocina=rest.usarMaquina(this);
            log.escribir("Cliente "+id+" esta esperando la comdia");
            Puesto puesto=rest.esperarComida(this,cocina);
            log.escribir("Cliente "+id+" esta pagando");
            rest.pagar(this,puesto);
            log.escribir("Cliente "+id+" ha terminado su ejecucion");
        }catch(Exception e){}
    }
    
    public String getMiId()
    {
        return id;
    }
    
}