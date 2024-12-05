package co.edu.uptc.gui;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelPersona extends JPanel {

    private JTextArea txInformacion;

    public PanelPersona(Evento evento) {

        //Estable el layou ante de añadir componentes
        setLayout(new BorderLayout());

        //añadir borde con titui
        setBorder(new TitledBorder("Informacion Personas:"));
        txInformacion= new JTextArea(15,40);
        txInformacion.setEditable(false);

        //Agregar scroolPane para manjar texto largo
        JScrollPane scroll = new JScrollPane(txInformacion);

        //Crear boton
        JButton accion1= new JButton(Evento.CARGAR_PERSONAS);
        accion1.addActionListener(evento);
        accion1.setActionCommand(Evento.CARGAR_PERSONAS);

        // Añadir componentes al panel
        add(scroll, BorderLayout.CENTER);
        add(accion1, BorderLayout.SOUTH);
    }

    public String obtenerDatos() {
        return txInformacion.getText();
    }
    // Métodopara limpiar el contenido del JTextArea
    public void limpiarTexto() {
        txInformacion.setText("");
    }
}
