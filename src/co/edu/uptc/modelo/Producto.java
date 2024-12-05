package co.edu.uptc.modelo;

public class Producto {
    private String codigo;
    private double precioProducto;

    public Producto(String codigo, double precioProducto) {
        this.codigo = codigo;
        this.precioProducto = precioProducto;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public double getPrecioBase() {
        return precioProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", precioBase=" + precioProducto +
                '}';
    }
}