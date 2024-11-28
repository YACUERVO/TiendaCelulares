package co.edu.uptc.gui;


import co.edu.uptc.modelo.CatalogoCelulares;
import co.edu.uptc.modelo.Celular;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame {

    private PanelInventario info;
    private PanelVentas infoVentas;
    private PanelBotones botones;
    private PanelPersona persona;

    private CatalogoCelulares catalogoCelulares;

    public VentanaPrincipal(){

        //Iniciando catalogo de celulares
        catalogoCelulares = new CatalogoCelulares();

        // Configuraciones básicas del JFrame
        setTitle("Mi Tienda");
        setSize(1100,600);

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
        //info.limpiarTexto();

        // Agregar encabezado
        info.agregarTexto("INVENTARIO DE CELULARES");
        info.agregarTexto("-----------------------------");

        // Mostrar cada celular
        for (Celular celular : celulares) {
            info.agregarTexto(celular.toString());
        }
    }

    public void cargarInfoVentas() {
        JOptionPane.showMessageDialog(this, "Cargar contenido de ventas");
        String datosVentas = infoVentas.obtenerDatos();
        //TODOImplementar lógica para procesar los datos de ventas
        System.out.println("Datos de Ventas: " + datosVentas);
    }

    public void generarInformeInventario() {
        JOptionPane.showMessageDialog(this, "Crear infome de inventario" );
        DialogoLista nuevo= new DialogoLista();
        String encabezado="# celulares | Total Precio base |"
                + "Total Precio de venta |"
                + "Total de Impuestos a pagar |"
                + "Total comisiones de venta |"
                + "Total ganancias ";
        nuevo.agregrarTexto(encabezado);
        nuevo.setVisible(true);
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

