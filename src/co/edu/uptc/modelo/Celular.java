package co.edu.uptc.modelo;

public class Celular {
    private String marca;
    private String linea;
    private String codigo;
    private double precioBase;
    private int cantidadDisponible;

    public Celular(String marca, String linea, String codigo, double precioBase, int cantidadDisponible) {
        this.marca = marca;
        this.linea = linea;
        this.codigo = codigo;
        this.precioBase = precioBase;
        this.cantidadDisponible = cantidadDisponible;
    }

    // Getters y setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return String.format("%s %s (Código: %s) - Precio: $%.2f - Disponibles: %d",
                marca, linea, codigo, precioBase, cantidadDisponible);
    }
}