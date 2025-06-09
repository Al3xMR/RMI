package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

import matriz.Matriz;

public interface RemoteCode extends Remote {
    //public int sumar(int a, int b) throws RemoteException;
    Matriz invertirMatriz(Matriz matriz) throws RemoteException;
}