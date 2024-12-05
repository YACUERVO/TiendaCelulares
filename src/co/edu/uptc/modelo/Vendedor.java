package co.edu.uptc.modelo;

public class Vendedor {
    private String nombres;
    private String documentoIdentidad;
    private TipoDocumento tipoDocumento;
    private String numeroCuentaBancaria;
    private TipoCuentaBancaria tipoCuentaBancaria;
    private String codigoUnicoVenta;

    // Constructor


    public Vendedor(String codigoVendedor, String nombre, String cuentaBancaria,
                    TipoDocumento tipoDocumento, String numeroDocumento,
                    TipoCuentaBancaria tipoCuentaBancaria) {
        this.codigoUnicoVenta = codigoVendedor;
        this.nombres = nombre;
        this.numeroCuentaBancaria = cuentaBancaria;
        this.tipoDocumento = tipoDocumento;
        this.documentoIdentidad = numeroDocumento;
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    // Métodopara generar código único de venta (simple implementación)
    private String generarCodigoUnicoVenta() {
        return "VTA-" + System.currentTimeMillis();
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroCuentaBancaria() {
        return numeroCuentaBancaria;
    }

    public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    }

    public TipoCuentaBancaria getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(TipoCuentaBancaria tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public String getCodigoUnicoVenta() {
        return codigoUnicoVenta;
    }

    public void setCodigoUnicoVenta(String codigoUnicoVenta) {
        this.codigoUnicoVenta = codigoUnicoVenta;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s %s - Código Venta: %s",
                nombres, tipoDocumento, documentoIdentidad, codigoUnicoVenta);
    }
}