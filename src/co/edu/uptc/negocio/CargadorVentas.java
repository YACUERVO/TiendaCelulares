package co.edu.uptc.negocio;

import co.edu.uptc.modelo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargadorVentas {

    private CatalogoCelulares catalogo;

    public CargadorVentas(CatalogoCelulares catalogo) {
        this.catalogo = catalogo;
    }

    public List<Venta> cargarVentas() {
        // Mapa de ventas: Código de venta -> Lista de productos con cantidad
        Map<String, List<Producto>> ventasMap = new HashMap<>();

        // Agregar todas las ventas con código y cantidad
        agregarProductoVenta(ventasMap, "VEN001", "MOP", 3);
        agregarProductoVenta(ventasMap, "VEN001", "MOS", 4);
        agregarProductoVenta(ventasMap, "VEN001", "MG6", 6);
        agregarProductoVenta(ventasMap, "VEN001", "MG7", 4);
        agregarProductoVenta(ventasMap, "VEN001", "HM20P", 3);
        agregarProductoVenta(ventasMap, "VEN001", "HY9P", 1);
        agregarProductoVenta(ventasMap, "VEN001", "HP30", 4);
        agregarProductoVenta(ventasMap, "VEN001", "HP30L", 3);
        agregarProductoVenta(ventasMap, "VEN001", "SGA40", 4);
        agregarProductoVenta(ventasMap, "VEN001", "SGA50", 6);
        agregarProductoVenta(ventasMap, "VEN001", "SGA70", 9);
        agregarProductoVenta(ventasMap, "VEN001", "SGA80", 1);

        agregarProductoVenta(ventasMap, "VEN010", "MOP", 4);
        agregarProductoVenta(ventasMap, "VEN010", "MOS", 5);
        agregarProductoVenta(ventasMap, "VEN010", "MG6", 7);
        agregarProductoVenta(ventasMap, "VEN010", "MG7", 5);
        agregarProductoVenta(ventasMap, "VEN010", "HM20P", 4);
        agregarProductoVenta(ventasMap, "VEN010", "HY9P", 2);
        agregarProductoVenta(ventasMap, "VEN010", "HP30", 5);
        agregarProductoVenta(ventasMap, "VEN010", "HP30L", 13);
        agregarProductoVenta(ventasMap, "VEN010", "SGA40", 5);
        agregarProductoVenta(ventasMap, "VEN010", "SGA50", 8);
        agregarProductoVenta(ventasMap, "VEN010", "SGA70", 9);
        agregarProductoVenta(ventasMap, "VEN010", "SGA80", 11);

        agregarProductoVenta(ventasMap, "VEN011", "MOP", 4);
        agregarProductoVenta(ventasMap, "VEN011", "MOS", 6);
        agregarProductoVenta(ventasMap, "VEN011", "MG6", 4);
        agregarProductoVenta(ventasMap, "VEN011", "MG7", 6);
        agregarProductoVenta(ventasMap, "VEN011", "HM20P", 9);
        agregarProductoVenta(ventasMap, "VEN011", "HY9P", 6);
        agregarProductoVenta(ventasMap, "VEN011", "HP30", 8);
        agregarProductoVenta(ventasMap, "VEN011", "HP30L", 4);
        agregarProductoVenta(ventasMap, "VEN011", "SGA40", 15);
        agregarProductoVenta(ventasMap, "VEN011", "SGA50", 14);
        agregarProductoVenta(ventasMap, "VEN011", "SGA70", 10);
        agregarProductoVenta(ventasMap, "VEN011", "SGA80", 6);

        // Convertir el mapa en una lista de ventas
        List<Venta> ventas = new ArrayList<>();
        for (Map.Entry<String, List<Producto>> entry : ventasMap.entrySet()) {
            ventas.add(new Venta(entry.getKey(), entry.getValue()));
        }

        return ventas;
    }

    public List<Venta> cargarVentasConVendedores(List<Vendedor> vendedores, Map<String, Map<String, Integer>> tablaVentas) {
        List<Venta> ventas = new ArrayList<>();

        for (Vendedor vendedor : vendedores) {
            String codigoVenta = vendedor.getCodigoUnicoVenta();
            Map<String, Integer> productosVendidos = tablaVentas.getOrDefault(codigoVenta, new HashMap<>());

            List<Producto> productosVenta = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : productosVendidos.entrySet()) {
                String codigoProducto = entry.getKey();
                int cantidadVendida = entry.getValue();

                Celular celular = catalogo.buscarCelularPorCodigo(codigoProducto);
                if (celular != null && celular.getCantidadDisponible() >= cantidadVendida) {
                    celular.setCantidadDisponible(celular.getCantidadDisponible() - cantidadVendida);
                    productosVenta.add(new Producto(codigoProducto, celular.getPrecioBase()));
                }
            }

            ventas.add(new Venta(codigoVenta, productosVenta));
        }

        return ventas;
    }






    private void agregarProductoVenta(Map<String, List<Producto>> ventasMap, String codigoVenta, String codigoProducto, int cantidad) {
        Celular celular = catalogo.buscarCelularPorCodigo(codigoProducto);

        if (celular != null) {
            // Obtener la lista de productos para esa venta, o crear una nueva si no existe
            ventasMap.putIfAbsent(codigoVenta, new ArrayList<>());
            List<Producto> productos = ventasMap.get(codigoVenta);

            // Agregar el producto con la cantidad especificada
            for (int i = 0; i < cantidad; i++) {
                productos.add(new Producto(celular.getCodigo(), celular.getPrecioBase()));
            }
        } else {
            System.out.println("Advertencia: No se encontró el producto con código " + codigoProducto);
        }
    }
}
