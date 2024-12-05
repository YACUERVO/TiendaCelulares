package co.edu.uptc.gui;


import co.edu.uptc.modelo.*;
import co.edu.uptc.negocio.CargadorVentas;
import co.edu.uptc.negocio.GestorVentas;
import co.edu.uptc.modelo.TipoDocumento;

import java.awt.*;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame {

    private PanelInventario info;
    private PanelVentas infoVentas;
    private PanelBotones botones;
    private PanelPersona persona;

    private CatalogoCelulares catalogoCelulares;
    private GestorVentas gestorVentas;

    public VentanaPrincipal(){

        //Iniciando catalogo de celulares
        catalogoCelulares = new CatalogoCelulares();

        //Iniciamos el gestor de ventas
        gestorVentas = new GestorVentas();


        // Configuraciones básicas del JFrame
        setTitle("Mi Tienda");
        setSize(1500,600);

        // Crear evento único para todos los paneles
        Evento evento= new Evento(this);

        // Inicializar paneles
        info= new PanelInventario(evento);
        infoVentas= new PanelVentas(evento);
        botones = new PanelBotones(evento);
        persona = new PanelPersona(evento);

        // Configurar layout y añadir paneles
        setLayout(new BorderLayout());
        add(info,BorderLayout.WEST);
        add(persona,BorderLayout.CENTER);
        add(infoVentas,BorderLayout.EAST);
        add(botones,BorderLayout.SOUTH);




    }
    public static void main(String[] args) {
        // Usar SwingUtilities para manejar el thread de Swing
        java.awt.EventQueue.invokeLater(() -> {
            VentanaPrincipal nueva = new VentanaPrincipal();
            nueva.setVisible(true);
        });
    }

    public void cargarInfoInventario() {
        // Mostrar lista de celulares en el inventario
        List<Celular> celulares = catalogoCelulares.getCelulares();

        // Limpiar panel anterior
        info.limpiarTexto();

        // Agregar encabezado
        info.agregarTexto("INVENTARIO DE CELULARES");
        info.agregarTexto("-----------------------------");

        // Mostrar cada celular
        for (Celular celular : celulares) {
            info.agregarTexto(celular.toString());
        }
    }

    public void cargarInfoVentas() {

        // Crear una instancia de CargadorVentas con el catálogo actual
        CargadorVentas cargadorVentas = new CargadorVentas(catalogoCelulares);

        // Cargar las ventas basadas en el catálogo de celulares
        List<Venta> ventas = cargadorVentas.cargarVentas();

        // Limpiar el panel anterior
        infoVentas.limpiarTexto();

        // Agregar encabezado
        infoVentas.agregarTexto("LISTA DE VENTAS");
        infoVentas.agregarTexto("-----------------------------");

        // Mostrar cada venta
        for (Venta venta : ventas) {
            infoVentas.agregarTexto("Código de Venta: " + venta.getCodigoVenta());
            infoVentas.agregarTexto("Productos Vendidos:");

            double totalVenta = 0;
            double totalIVA = 0;
            double totalComision = 0;

            Map<String, Integer> productosCantidad = new HashMap<>();

            // Calcular cantidad total por producto y mostrar detalles
            for (Producto producto : venta.getProductos()) {
                String codigoProducto = producto.getCodigo();
                productosCantidad.put(codigoProducto, productosCantidad.getOrDefault(codigoProducto, 0) + 1);
            }

            // Mostrar productos con su cantidad, precio base, IVA, comisión y precio de venta
            for (Map.Entry<String, Integer> entry : productosCantidad.entrySet()) {
                String codigoProducto = entry.getKey();
                int cantidad = entry.getValue();
                Celular celular = catalogoCelulares.buscarCelularPorCodigo(codigoProducto);

                if (celular != null) {
                    double precioBase = celular.getPrecioBase();

                    // Calcular IVA
                    double porcentajeIVA = precioBase > 600000 ? 0.19 : 0.05;
                    double iva = precioBase * porcentajeIVA;

                    // Calcular comisión
                    double comision = (precioBase *cantidad) * 0.05;

                    // Calcular precio de venta
                    double precioVenta = precioBase + iva;

                    // Calcular totales
                    totalIVA += iva * cantidad;
                    totalComision += comision * cantidad;
                    totalVenta += precioVenta * cantidad;

                    // Mostrar detalles del producto
                    infoVentas.agregarTexto("  - " + celular.getMarca() + " " + celular.getLinea() +
                            " (Código: " + codigoProducto + "), Cantidad: " + cantidad);
                    infoVentas.agregarTexto("    Precio Base: " + precioBase);
                    infoVentas.agregarTexto("    IVA (" + (porcentajeIVA * 100) + "%): " + iva);
                    infoVentas.agregarTexto("    Comisión (5%): " + comision);
                    infoVentas.agregarTexto("    Precio de Venta: " + precioVenta);
                } else {
                    infoVentas.agregarTexto("  - Producto no encontrado (Código: " + codigoProducto + ")");
                }
            }

            // Mostrar el total de la venta con IVA y comisiones
            infoVentas.agregarTexto("Total IVA: " + totalIVA);
            infoVentas.agregarTexto("Total Comisiones: " + totalComision);
            infoVentas.agregarTexto("Total de la Venta (con IVA): " + totalVenta);
            infoVentas.agregarTexto("-----------------------------");
        }
    }

    public void generarInformeInventario() {
        // Formato contable para los números
        NumberFormat formatoContable = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        // Variables para el cálculo del reporte
        int numeroTotalCelulares = 0;
        double totalPrecioBase = 0;
        double totalPrecioVenta = 0;
        double totalImpuestosAPagar = 0;
        double totalComisionesVenta = 0;
        double totalGanancias = 0;

        // Recorrer el inventario de celulares para calcular los totales
        for (Celular celular : catalogoCelulares.getCelulares()) {
            int cantidad = celular.getCantidadDisponible(); // Cantidad en inventario
            double precioBase = celular.getPrecioBase();
            double porcentajeIVA = (precioBase*1.25) > 600000 ? 0.19 : 0.05;
            double iva = (precioBase*1.25) * porcentajeIVA;
            double precioVenta = (precioBase*1.25) + iva;
            double comision = precioBase * 0.05;
            double ganancia = ((precioBase * 1.25)*cantidad) - (precioBase*cantidad) - (precioBase * cantidad * 0.05);

            // Actualizar totales
            numeroTotalCelulares += cantidad;
            totalPrecioBase += precioBase * cantidad;
            totalPrecioVenta += precioVenta * cantidad;
            totalImpuestosAPagar += iva * cantidad;
            totalComisionesVenta += comision * cantidad;
            totalGanancias += ganancia;
        }

        // Crear el reporte y mostrarlo en un cuadro de diálogo
        String reporte = "REPORTE DE INVENTARIO\n" +
                "---------------------------------------------\n" +
                String.format("%-25s: %d\n", "Número Total de Celulares", numeroTotalCelulares) +
                String.format("%-25s: %s\n", "Total Precio Base", formatoContable.format(totalPrecioBase)) +
                String.format("%-25s: %s\n", "Total Precio Venta", formatoContable.format(totalPrecioVenta)) +
                String.format("%-25s: %s\n", "Total Impuestos a Pagar", formatoContable.format(totalImpuestosAPagar)) +
                String.format("%-25s: %s\n", "Total Comisiones Venta", formatoContable.format(totalComisionesVenta)) +
                String.format("%-25s: %s\n", "Total Ganancias", formatoContable.format(totalGanancias));

        JOptionPane.showMessageDialog(this, reporte, "Reporte de Inventario", JOptionPane.INFORMATION_MESSAGE);
    }
    public void generarInformeVentasPorVendedor() {
        // Formato contable para los números
        NumberFormat formatoContable = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        // Crear una instancia de CargadorVentas con el catálogo actual
        CargadorVentas cargadorVentas = new CargadorVentas(catalogoCelulares);

        // Cargar las ventas basadas en el catálogo de celulares
        List<Venta> ventas = cargadorVentas.cargarVentas();

        // Mapa para almacenar las ventas por cada vendedor
        Map<String, Vendedor> vendedores = new HashMap<>();

        // Datos ficticios de vendedores para asociar
        vendedores.put("VEN001", new Vendedor("VEN001", "XXX", "CC-1", TipoDocumento.CC, "0", TipoCuentaBancaria.AHORROS));
        vendedores.put("VEN010", new Vendedor("VEN010", "YYY", "CC-2", TipoDocumento.CC, "2", TipoCuentaBancaria.AHORROS));
        vendedores.put("VEN011", new Vendedor("VEN011", "ZZZ", "CC-3", TipoDocumento.CC, "3", TipoCuentaBancaria.AHORROS));


        // Mapas para almacenar los totales por vendedor
        Map<String, Double> totalComisionesPorVendedor = new HashMap<>();
        Map<String, Integer> totalCelularesVendidosPorVendedor = new HashMap<>();

        // Procesar cada venta para calcular comisiones y total de celulares vendidos
        for (Venta venta : ventas) {
            String codigoVenta = venta.getCodigoVenta();
            double totalComisionVenta = 0;
            int totalCelularesVenta = 0;

            Map<String, Integer> productosCantidad = new HashMap<>();

            // Calcular cantidad total por producto en la venta
            for (Producto producto : venta.getProductos()) {
                String codigoProducto = producto.getCodigo();
                productosCantidad.put(codigoProducto, productosCantidad.getOrDefault(codigoProducto, 0) + 1);
            }

            // Calcular comisiones y total de celulares vendidos
            for (Map.Entry<String, Integer> entry : productosCantidad.entrySet()) {
                String codigoProducto = entry.getKey();
                int cantidad = entry.getValue();
                Celular celular = catalogoCelulares.buscarCelularPorCodigo(codigoProducto);

                if (celular != null) {
                    double precioBase = celular.getPrecioBase();
                    double comision = (precioBase * cantidad) * 0.05;  // Comisión del 5% sobre cada producto
                    totalComisionVenta += comision;
                    totalCelularesVenta += cantidad;
                }
            }

            // Actualizar totales por vendedor
            totalComisionesPorVendedor.put(codigoVenta, totalComisionesPorVendedor.getOrDefault(codigoVenta, 0.0) + totalComisionVenta);
            totalCelularesVendidosPorVendedor.put(codigoVenta, totalCelularesVendidosPorVendedor.getOrDefault(codigoVenta, 0) + totalCelularesVenta);
        }

        // Crear el reporte
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE VENTAS POR VENDEDOR\n")
                .append("----------------------------------------------------\n")
                .append(String.format("%-10s %-10s %-10s %-20s %-20s\n", "Código", "Doc.", "Nombre", "Total Comisión", "Total Celulares Vendidos"));

        for (String codigoVendedor : totalComisionesPorVendedor.keySet()) {
            double totalComision = totalComisionesPorVendedor.get(codigoVendedor);
            int totalCelulares = totalCelularesVendidosPorVendedor.get(codigoVendedor);

            Vendedor vendedor = vendedores.get(codigoVendedor);

            if (vendedor != null) {
                reporte.append(String.format("%-10s %-10s %-10s %-20s %-20d\n",
                        codigoVendedor,
                        vendedor.getDocumentoIdentidad(),
                        vendedor.getNombres(),
                        formatoContable.format(totalComision),
                        totalCelulares));
            } else {
                reporte.append(String.format("%-10s %-10s %-10s %-20s %-20d\n",
                        codigoVendedor, "N/A", "N/A", formatoContable.format(totalComision), totalCelulares));
            }
        }

        // Mostrar el reporte en un cuadro de diálogo
        JOptionPane.showMessageDialog(this, reporte.toString(), "Reporte de Ventas por Vendedor", JOptionPane.INFORMATION_MESSAGE);
    }

    public void generarReporteMasVendidos() {
        // Crear una instancia de CargadorVentas con el catálogo actual
        CargadorVentas cargadorVentas = new CargadorVentas(catalogoCelulares);

        // Cargar las ventas basadas en el catálogo de celulares
        List<Venta> ventas = cargadorVentas.cargarVentas();

        // Mapas para acumular el valor total de ventas por marca y por línea
        Map<String, Double> ventasPorMarca = new HashMap<>();
        Map<String, Double> ventasPorLinea = new HashMap<>();

        // Recorrer todas las ventas y acumular el valor total vendido por marca y línea
        for (Venta venta : ventas) {
            for (Producto producto : venta.getProductos()) {
                String codigoProducto = producto.getCodigo();
                Celular celular = catalogoCelulares.buscarCelularPorCodigo(codigoProducto);

                if (celular != null) {
                    double precioBase = celular.getPrecioBase();

                    double porcentajeIVA = (precioBase*1.25) > 600000 ? 0.19 : 0.05;
                    double iva = (precioBase*1.25) * porcentajeIVA;
                    double precioVenta = (precioBase*1.25) + iva;

                    // Acumular el valor total de ventas por marca
                    String marca = celular.getMarca();
                    ventasPorMarca.put(marca, ventasPorMarca.getOrDefault(marca, 0.0) + precioVenta);

                    // Acumular el valor total de ventas por línea
                    String linea = celular.getLinea();
                    ventasPorLinea.put(linea, ventasPorLinea.getOrDefault(linea, 0.0) + precioVenta);
                }
            }
        }

        // Encontrar la marca más vendida en términos de valor
        String marcaMasVendida = null;
        double totalVentasMarca = 0.0;
        for (Map.Entry<String, Double> entry : ventasPorMarca.entrySet()) {
            if (entry.getValue() > totalVentasMarca) {
                marcaMasVendida = entry.getKey();
                totalVentasMarca = entry.getValue();
            }
        }

        // Encontrar la línea más vendida en términos de valor
        String lineaMasVendida = null;
        double totalVentasLinea = 0.0;
        for (Map.Entry<String, Double> entry : ventasPorLinea.entrySet()) {
            if (entry.getValue() > totalVentasLinea) {
                lineaMasVendida = entry.getKey();
                totalVentasLinea = entry.getValue();
            }
        }

        // Formatear los valores como moneda
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        // Mostrar el reporte
        String reporte = "REPORTE DE CELULARES MÁS VENDIDOS\n" +
                "---------------------------------------------\n" +
                "Marca más vendida: " + (marcaMasVendida != null ? marcaMasVendida : "N/A") + "\n" +
                "Total Ventas Marca: " + formatoMoneda.format(totalVentasMarca) + "\n" +
                "Línea más vendida: " + (lineaMasVendida != null ? lineaMasVendida : "N/A") + "\n" +
                "Total Ventas Línea: " + formatoMoneda.format(totalVentasLinea);

        // Mostrar el reporte en un cuadro de diálogo
        JOptionPane.showMessageDialog(this, reporte, "Reporte de Más Vendidos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void generarReporteImpuestos() {
        // Crear una instancia de CargadorVentas con el catálogo actual
        CargadorVentas cargadorVentas = new CargadorVentas(catalogoCelulares);

        // Cargar las ventas basadas en el catálogo de celulares
        List<Venta> ventas = cargadorVentas.cargarVentas();

        // Variables para acumular las bases gravables y los impuestos
        double totalBaseGravable5 = 0.0;
        double totalImpuesto5 = 0.0;
        double totalBaseGravable19 = 0.0;
        double totalImpuesto19 = 0.0;

        // Recorrer todas las ventas y acumular las bases gravables y los impuestos
        for (Venta venta : ventas) {
            for (Producto producto : venta.getProductos()) {
                String codigoProducto = producto.getCodigo();
                Celular celular = catalogoCelulares.buscarCelularPorCodigo(codigoProducto);

                if (celular != null) {
                    double precioBase = celular.getPrecioBase();

                    // Calcular IVA
                    double porcentajeIVA = (precioBase*1.25) > 600000 ? 0.19 : 0.05;
                    double iva = (precioBase*1.25) * porcentajeIVA;

                    // Acumular la base gravable e impuesto correspondiente
                    if (porcentajeIVA == 0.05) {
                        totalBaseGravable5 += precioBase*1.25;
                        totalImpuesto5 += iva;
                    } else {
                        totalBaseGravable19 += precioBase*1.25;
                        totalImpuesto19 += iva;
                    }
                }
            }
        }

        // Calcular sumatorias de totales
        double totalBasesGrabables = totalBaseGravable5 + totalBaseGravable19;
        double totalImpuestos = totalImpuesto5 + totalImpuesto19;

        // Formatear los valores como moneda
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        // Crear el reporte
        String reporte = "REPORTE DE IMPUESTOS\n" +
                "---------------------------------------------\n" +
                String.format("%-30s: %s\t%s\n", "Impuesto del 5%  ", formatoMoneda.format(totalBaseGravable5), formatoMoneda.format(totalImpuesto5)) +
                String.format("%-30s: %s\t%s\n", "Impuesto del 19% ", formatoMoneda.format(totalBaseGravable19), formatoMoneda.format(totalImpuesto19)) +
                "---------------------------------------------\n" +
                String.format("%-30s: %s\t%s\n", "Sumatoria de Totales", formatoMoneda.format(totalBasesGrabables), formatoMoneda.format(totalImpuestos));

        // Mostrar el reporte en un cuadro de diálogo
        JOptionPane.showMessageDialog(this, reporte, "Reporte de Impuestos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void salir() {
        // Mostrar diálogo de confirmación antes de salir
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea salir?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            dispose(); // Cerrar solo esta ventana
            // System.exit(0); // Opcional: cerrar completamente la aplicación
        }
    }
}

