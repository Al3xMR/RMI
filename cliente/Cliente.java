package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import matriz.Matriz;
import interfaz.RemoteCode;

public class Cliente {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 3232;

        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress, port);
            RemoteCode remoteCode = (RemoteCode) registry.lookup("Servidor");

            double[][] datosMatriz = {
                {1, 2, 3},
                {0, 1, 4},
                {5, 6, 0}
            };
            
            Matriz matriz = new Matriz(datosMatriz);
            Matriz matrizInvertida = remoteCode.invertirMatriz(matriz);

            System.out.println("Conexión exitosa al servidor en " + serverAddress + ":" + port);

            System.out.println("Matriz original:");
            System.out.println(matriz);

            System.out.println("Matriz invertida:");
            System.out.println(matrizInvertida);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}