package Menu;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Clase para gestionar el color del texto
 */
public class ColorText {
    private JMenuBar menuBar;
    private JTextPane textPane;
    private JTabbedPane pestañas;
    private JMenuItem colores = new JMenuItem();
    JTextPane textPaneCopia;

    /**
     * Constructor de la clase
     *
     * @param menuBar  tipo JMenuBar
     * @param textPane tipo JTextPane
     * @param pestañas tipo JTabbedPane
     */
    public ColorText(JMenuBar menuBar, JTextPane textPane, JTabbedPane pestañas) {
        this.menuBar = menuBar;
        this.textPane = textPane;
        this.pestañas = pestañas;
        this.textPaneCopia = textPane;

        insertarColor();
        ventanasDiferentes();
    }

    /**
     * Metodo para saber en que pestaña se encuentra el usuario
     */
    private void ventanasDiferentes() {
        //Para saber en que pestaña se encuentra el usuario
        pestañas.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                boolean encontrado = false;
                JPanel panel = (JPanel) pestañas.getSelectedComponent();
                JTextPane textPane2 = null;
                Component[] components = panel.getComponents();
                for (Component component : components) {
                    if (component instanceof JTextPane) {
                        textPane2 = (JTextPane) component;
                        textPane = textPane2;
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    textPane = textPaneCopia;
                }
            }
        });
    }

    /**
     * Metodo gestionar el aspecto del JMenuItem 'colores'
     */
    private void insertarColor() {
        insertarImagen(colores);
        colores.setMaximumSize(new Dimension(30, 20));
        colores.setBackground(new Color(30, 144, 255));
        colores.setForeground(Color.BLACK);
        colores.setBorderPainted(false);

        añadirActionListener();

        menuBar.add(colores);
    }

    /**
     * Metodo para gestionar los eventos del JMenuItem 'colores'
     */
    private void añadirActionListener() {
        colores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textPane.getSelectedText();
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(new ImageIcon("./src/Imagenes/iconoPrincipal.png").getImage());
                if (text != null) {
                    Color color = JColorChooser.showDialog(frame, "Elegir Color", Color.BLUE);
                    if (color != null) {
                        cambiarColor(textPane, color);
                        colores.addActionListener(new StyledEditorKit.ForegroundAction("", color));
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "¡NO HAY TEXTO SELECCIONADO!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     * Metodo para inserta la imagen
     *
     * @param colores tipo JMenuItem
     * @return icono tipo ImageIcon
     */
    private ImageIcon insertarImagen(JMenuItem colores) {
        ImageIcon icono = new ImageIcon("./src/Imagenes/colores.png");
        colores.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, 0)));
        colores.setLocation(0, 0);
        return icono;
    }

    /**
     * Metodo para cambiar del color el texto seleccionado
     *
     * @param textPane tipo JTextPane
     * @param color    tipo Color
     */
    private static void cambiarColor(JTextPane textPane, Color color) {
        Highlighter.Highlight[] highlights = textPane.getHighlighter().getHighlights();
        int selectionStart = textPane.getSelectionStart();
        int selectionEnd = textPane.getSelectionEnd();

        for (Highlighter.Highlight highlight : highlights) {
            int highlightStart = highlight.getStartOffset();
            int highlightEnd = highlight.getEndOffset();
            if (highlightStart <= selectionStart && highlightEnd >= selectionEnd) {
                SimpleAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setForeground(attrs, color);
                textPane.getStyledDocument().setCharacterAttributes(selectionStart, selectionEnd - selectionStart, attrs, false);
                break;
            }
        }
    }
}
