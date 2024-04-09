package Frame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase para gestionar los eventos del teclado
 */
public class VersionAnterior {
    private JTextPane textPane;
    private JTextPane textPaneSeguiente;
    private JTabbedPane pestañas;

    /**
     * Constructor de la case
     *
     * @param textPane tipo JTextPane
     * @param pestañas
     */
    public VersionAnterior(JTextPane textPane, JTabbedPane pestañas) {
        this.textPane = textPane;
        this.textPaneSeguiente = new JTextPane();
        this.pestañas = pestañas;
        insertarBucle();
    }

    /**
     * Metodo para gestionar los atagjos del teclado
     */
    private void insertarBucle() {
        volverAtras();
        pestañas.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JPanel panel = (JPanel) pestañas.getSelectedComponent();
                Component[] components = panel.getComponents();
                for (Component component : components) {
                    if (component instanceof JTextPane) {
                        textPane = (JTextPane) component;
                        volverAtras();
                    }
                }
            }
        });
    }

    /**
     * Metodo para crear eventos de teclado
     */
    public void volverAtras() {
        textPane.addKeyListener(new KeyAdapter() {
            boolean isKeyPressed = false;

            // Acciones al presionar y mantener presionada una tecla
            @Override
            public void keyPressed(KeyEvent ea) {
                StyledDocument doc = textPane.getStyledDocument();

                if (ea.getKeyCode() == KeyEvent.VK_CONTROL) {
                    isKeyPressed = true;
                }

                if (isKeyPressed && ea.getKeyCode() == KeyEvent.VK_Z) {
                    String text = textPane.getText();
                    if (!text.equalsIgnoreCase("")) {
                        int tamaño = text.length() - 1;
                        textPane.setText(text.substring(0, tamaño));
                        textPaneSeguiente = textPane;
                    }
                }
            }
        });
    }
}
