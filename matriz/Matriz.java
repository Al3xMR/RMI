package matriz;

import java.io.Serializable;

public class Matriz implements Serializable {
    private static final long serialVersionUID = 1L;
    private double[][] datos;
    private int filas;
    private int columnas;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new double[filas][columnas];
    }

    public Matriz(double[][] datos) {
        this.filas = datos.length;
        this.columnas = datos[0].length;
        this.datos = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            System.arraycopy(datos[i], 0, this.datos[i], 0, columnas);
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public double get(int fila, int columna) {
        return datos[fila][columna];
    }

    public void set(int fila, int columna, double valor) {
        datos[fila][columna] = valor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                sb.append(datos[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}