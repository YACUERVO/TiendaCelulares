package co.edu.uptc.modelo;

public enum TipoCuentaBancaria {
    AHORROS("Cuenta de Ahorros"),
    CORRIENTE("Cuenta Corriente");

    private final String descripcion;

    TipoCuentaBancaria(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}