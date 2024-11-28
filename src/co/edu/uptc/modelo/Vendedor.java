package co.edu.uptc.modelo;

public class Vendedor {
    private String nombres;
    private String apellidos;
    private String numeroCelular;
    private String documentoIdentidad;
    private TipoDocumento tipoDocumento;
    private String numeroCuentaBancaria;
    private TipoCuentaBancaria tipoCuentaBancaria;
    private String codigoUnicoVenta;

    // Constructor
    public Vendedor(String nombres, String apellidos, String numeroCelular,
                    String documentoIdentidad, TipoDocumento tipoDocumento,
                    String numeroCuentaBancaria, TipoCuentaBancaria tipoCuentaBancaria) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroCelular = numeroCelular;
        this.documentoIdentidad = documentoIdentidad;
        this.tipoDocumento = tipoDocumento;
        this.numeroCuentaBancaria = numeroCuentaBancaria;
        this.tipoCuentaBancaria = tipoCuentaBancaria;
        this.codigoUnicoVenta = generarCodigoUnicoVenta();
    }

    // Métodopara generar código único de venta (simple implementación)
    private String generarCodigoUnicoVenta() {
        return "VTA-" + System.currentTimeMillis();
    }

    // Getters y setters (omitidos por brevedad)

    @Override
    public String toString() {
        return String.format("%s %s - %s %s - Código Venta: %s",
                nombres, apellidos, tipoDocumento, documentoIdentidad, codigoUnicoVenta);
    }
}