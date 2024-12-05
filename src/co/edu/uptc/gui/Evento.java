package co.edu.uptc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evento implements ActionListener {

    public static final String VENTAS="VENTAS";
    public static final String STOCK="STOCK";
    public static final String MAS_VENDIDO="+ VENDIDO";
    public static final String IMPUESTOS="IMPUESTOS";
    public static final String CARGAR="Cargar Inverntario";
    public static final String CARGAR_PERSONAS="Cargar Persona";
    public static final String CARGAR_VENTAS="Cargar Ventas";
    public static final String SALIR="SALIR";


    private VentanaPrincipal ventana;

    public Evento(VentanaPrincipal ventanaPrincipal) {
        this.ventana= ventanaPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String evento= e.getActionCommand();

        //TODOreto del dia llamar a los demas metodos

        switch (evento) {
            case CARGAR :
                ventana.cargarInfoInventario();
                break;
            case VENTAS :
                ventana.generarInformeVentasPorVendedor();
                break;
            case CARGAR_VENTAS :
                ventana.cargarInfoVentas();
                break;
            case STOCK:
                ventana.generarInformeInventario();
                break;
            case SALIR:
                ventana.salir();
                break;
            case MAS_VENDIDO:
                ventana.generarReporteMasVendidos();
                break;
            case IMPUESTOS:
                ventana.generarReporteImpuestos();
                break;
            default:
                System.out.println("No se puede ejecutar este evento "+ evento);
        }




    }

}

