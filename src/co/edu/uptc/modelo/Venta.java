package co.edu.uptc.modelo;

import java.util.List;

public class Venta {
    private String codigoVenta;
    private List<Producto> productos;  // Lista de productos vendidos
    private int cantidadTotal;

    public Venta(String codigoVenta, List<Producto> productos) {
        this.codigoVenta = codigoVenta;
        this.productos = productos;
        this.cantidadTotal = productos.size();  // Suponiendo que cada producto es uno en una venta
    }

    // Getters y Setters
    public String getCodigoVenta() {
        return codigoVenta;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public double calcularTotalVenta() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecioBase();
        }
        return total;
    }

    @Override
    public String toString() {
        // Construir una representación legible de los productos
        StringBuilder productosStr = new StringBuilder();
        for (Producto producto : productos) {
            productosStr.append(producto.toString()).append(", ");
        }

        // Eliminar la última coma y espacio
        if (productosStr.length() > 0) {
            productosStr.setLength(productosStr.length() - 2);
        }

        // Formatear la salida
        return String.format("Venta { Código Venta: %s, Productos: [%s], Cantidad Total: %d }",
                codigoVenta, productosStr.toString(), cantidadTotal);
}
}