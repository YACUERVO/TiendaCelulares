package co.edu.uptc.gui;


import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelVentas extends JPanel {

    private JTextArea txInformacion;

    public PanelVentas(Evento evento) {
        // Establecer el layout antes de añadir componentes
        setLayout(new BorderLayout());

        // Añadir borde con título
        setBorder(new TitledBorder("Linea Texto de Ventas:"));
        // Corregir dimensiones del JTextArea
        txInformacion = new JTextArea(10, 50);

        // Hacer el JTextArea no editable si solo es para mostrar información
        txInformacion.setEditable(false);

        // Agregar ScrollPane para manejar texto extenso
        JScrollPane scrollPane = new JScrollPane(txInformacion);

        // Crear botón de cargar ventas
        JButton accion1= new JButton(Evento.CARGAR_VENTAS);
        accion1.addActionListener(evento);
        accion1.setActionCommand(Evento.CARGAR_VENTAS);



        // Añadir componentes al panel
        add(scrollPane, BorderLayout.CENTER);
        add(accion1, BorderLayout.SOUTH);
    }

    public void agregarTexto(String texto) {
        txInformacion.append(texto + "\n");
    }

    // Métodopara limpiar texto
    public void limpiarTexto() {
        txInformacion.setText("");
    }
}