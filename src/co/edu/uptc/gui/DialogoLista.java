package co.edu.uptc.gui;

import javax.swing.*;

public class DialogoLista extends JDialog {

    private JTextArea txaLista;
    public DialogoLista() {
        //TODOAuto-generated constructor stub

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
