package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class DialogoLista extends JDialog {

    private JTextArea txaLista;
    public DialogoLista() {
        //TODOAuto-generated constructor stub

        setTitle("Lista de Información"); // Establecer título del diálogo
        setLayout(new BorderLayout()); // Establecer layout
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Configurar cierre

        txaLista= new JTextArea();
        txaLista.setEditable(false);//hacer el area de texto no editable

        JScrollPane scrollPane = new JScrollPane(txaLista);
        add(scrollPane);

        setSize(600, 300);
        setLocationRelativeTo(null);
    }

    public void agregrarTexto(String texto) {
        txaLista.append(texto + "\n");



    }
}
