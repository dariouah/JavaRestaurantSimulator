package com.mycompany.proyecto;

public class Parada {
    
    private boolean cerrado;
    private Log log;
    
    public Parada()
    {
        cerrado=false;
        log=Log.getInstance();
    }
    
    public synchronized void puedoPasar()
    {
        while(cerrado){
            try
            {
                wait();
            }catch(InterruptedException e){}
        }
    }
    
    public synchronized void cerrar()
    {
        //Si estaba en marcha y se para lo representamos en el log
        if (!cerrado){log.escribir("Se ha parado la simulacion");}
        cerrado=true;
        
    }
    
    public synchronized void abrir()
    {
        //Si estaba parada y se reanuda lo representamos en el log
        if (cerrado){log.escribir("Se ha reanudado la simulacion");}
        cerrado=false;
        notifyAll();
    }
}