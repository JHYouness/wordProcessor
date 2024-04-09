package Menu;

import Frame.Ventana;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para crear nuevas pestañas
 */
public class Nuevo {
    private JTabbedPane pestañas;
    private static int contador = 0;
    private int id;
    private JPanel panelNuevo = new JPanel();

    /**
     * Constructor de la clase
     *
     * @param pestañas tipo JTabbedPane
     * @param textPane tipo JTextPane
     */
    public Nuevo(JTabbedPane pestañas, JTextPane textPane) {
        this.pestañas = pestañas;
        contador++;
        id = contador;
        crearPestañas();
    }

    /**
     * Metodo para crear la pestaña
     */
    private void crearPestañas() {
        JTextPane textArea = insertarTextArea();
        panelNuevo.add(textArea);
        pestañas.addTab("Nombre", panelNuevo);
    }

    /**
     * Metodo para insertar los componentes que pertencen al JPanel
     *
     * @return textPane1 tipo JTextPane
     */
    private JTextPane insertarTextArea() {
        JTextPane textPane1 = new JTextPane();
        textPane1.setText("");
        JScrollPane scrollPane = new JScrollPane(textPane1);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelNuevo.setLayout(new BorderLayout());
        panelNuevo.add(scrollPane, BorderLayout.CENTER);

        textPane1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textPane1.setForeground(Color.black);
        textPane1.setBackground(Color.white);

        return textPane1;
    }

    /**
     * Getters y setters
     */
    public JPanel getPanelNuevo() {
        return panelNuevo;
    }

    public int getId() {
        return id;
    }

    public JTabbedPane getPestañas() {
        return pestañas;
    }

    public void setId(int id) {
        this.id = id;
    }
}
