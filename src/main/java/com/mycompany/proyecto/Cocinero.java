package com.mycompany.proyecto;

public class Cocinero extends Thread{
    
    private String id;
    private int tipo;
    private Restaurante restaurante;
    private Cocina cocina;
    private String estado;
    private Log log;
    
    public Cocinero(String id, int tipo, Restaurante restaurante, Cocina cocina)
    {
        this.id=id;
        this.tipo=tipo;
        this.restaurante=restaurante;
        this.cocina=cocina;
        estado="DISPONIBLE";
        this.log=Log.getInstance();
    }
    
    public void run()
    {
        log.escribir("Cocinero "+id+" esta ejecutandose");
        while(true)
        {
            for (int i=0;i<10;i++)
            {
                log.escribir("Cocinero "+id+" esta DISPONIBLE");
                log.escribir("Cocinero "+id+" esta esperando nueva comanda");
                Comanda nueva=cocina.esperarNueva(this.id);
                estado="COCINANDO";
                log.escribir("Cocinero "+id+" esta COCINANDO");
                Puesto puesto=cocina.cocinar(nueva,this.id);
                log.escribir("Cocinero "+id+" cobra el pedido");
                cocina.cobrar(puesto);
                estado="DISPONIBLE";
            }
            estado="DESCANSANDO";
            log.escribir("Cocinero "+id+" esta DESCANSANDO");
            restaurante.descansar(this);
        }
    }
    
    public String getMiId()
    {
        return id;
    }
    
}