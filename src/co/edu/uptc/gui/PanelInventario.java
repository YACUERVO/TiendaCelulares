package co.edu.uptc.gui;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelInventario extends JPanel {

    private JTextArea txInformacion;

    public PanelInventario(Evento evento) {
        //Establecer el laout para añadir componentes
        setLayout(new BorderLayout());

        //Anadir borde con titulo
        setBorder(new TitledBorder("Inventario:"));

        //Parametro oara filas y columnas
        txInformacion= new JTextArea(15,45);

        //Hacer el Jtexto no editable si solo es para mostrar informacion
        txInformacion.setEditable(false);

        //Agregar scrollPane para manejar texto extensos
        JScrollPane scroll = new JScrollPane(txInformacion);

        //Crear boton de cargar
        JButton accion1= new JButton(Evento.CARGAR);
        accion1.addActionListener(evento);
        accion1.setActionCommand(Evento.CARGAR);

        //Añadir componentes al panel
        add(scroll,BorderLayout.CENTER);
        add(accion1,BorderLayout.SOUTH);
    }

    //Metodo para agregar texto
    public void agregarTexto(String texto) {
        txInformacion.append(texto + "\n");
    }

    // Métodopara limpiar texto
    public void limpiarTexto() {
        txInformacion.setText("");
    }
}
