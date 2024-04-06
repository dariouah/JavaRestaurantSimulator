package com.mycompany.proyecto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazComun extends Remote{
    
    public int getFuera() throws RemoteException;
    public int getDentro() throws RemoteException;
    public String getPedidos() throws RemoteException;
    public String getImporte() throws RemoteException;
    public void pulsarParar() throws RemoteException;
    public void pulsarReanudar() throws RemoteException;
}