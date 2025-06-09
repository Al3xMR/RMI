package servidor;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import matriz.Matriz;
import interfaz.RemoteCode;

public class Servidor extends UnicastRemoteObject implements RemoteCode {
    
    private static final long serialVersionUID = 1235467890L;
    private static final int PUERTO = 3232;

    public Servidor() throws RemoteException {}

    public static void main(String[] args) throws Exception {
        (new Servidor()).iniciarServidor();        
    }

    public void iniciarServidor() {
        try {
            String dirIP = (InetAddress.getLocalHost()).toString();
            System.out.println("Servidor iniciado en: " + dirIP + ": " + PUERTO);
            
            Registry registry = LocateRegistry.createRegistry(PUERTO);
            registry.rebind("Servidor", this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Matriz invertirMatriz(Matriz matriz) throws RemoteException {
        int n = matriz.getFilas();
        if (n != matriz.getColumnas()) {
            throw new RemoteException("La matriz debe ser cuadrada para ser invertible");
        }

        Matriz matrizExtendida = new Matriz(n, 2 * n);

        // Crear matriz aumentada [matriz | identidad]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizExtendida.set(i, j, matriz.get(i, j));
            }
            for (int j = n; j < 2 * n; j++) {
                matrizExtendida.set(i, j, (j - n == i) ? 1.0 : 0.0);
            }
        }

        // Aplicar Gauss-Jordan
        for (int i = 0; i < n; i++) {
            double pivote = matrizExtendida.get(i, i);
            if (pivote == 0) throw new RemoteException("Matriz singular, no invertible");

            for (int j = 0; j < 2 * n; j++) {
                matrizExtendida.set(i, j, matrizExtendida.get(i, j) / pivote);
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matrizExtendida.get(k, i);
                    for (int j = 0; j < 2 * n; j++) {
                        matrizExtendida.set(k, j, matrizExtendida.get(k, j) - factor * matrizExtendida.get(i, j));
                    }
                }
            }
        }

        // Extraer la matriz inversa (parte derecha)
        Matriz inversa = new Matriz(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inversa.set(i, j, matrizExtendida.get(i, j + n));
            }
        }

        return inversa;
    }
}