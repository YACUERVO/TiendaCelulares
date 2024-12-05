package co.edu.uptc.negocio;

import co.edu.uptc.modelo.*;

import java.util.ArrayList;

public class GestorVentas {

    private ArrayList<Venta> ventas;

    public GestorVentas() {
        ventas = new ArrayList<>();
    }

    // Registrar una nueva venta
    public void registrarVenta(Venta venta) {
        ventas.add(venta);
    }

    // Calcular el total de todas las ventas registradas
    public double calcularTotalVentas() {
        double total = 0;
        for (Venta venta : ventas) {
            total += venta.calcularTotalVenta();
        }
        return total;
    }

    // Obtener todas las ventas
    public ArrayList<Venta> obtenerVentas() {
        return ventas;
    }
}
