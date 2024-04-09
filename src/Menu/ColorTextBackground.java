package Menu;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para crear y gestionar los eventos del subraydao
 */
public class ColorTextBackground {
    private JMenuItem color;
    private JMenuBar menuBar;
    private JTextPane textPane;
    private JTabbedPane pestañas;
    private JTextPane textPaneCopia;

    /**
     * Constructor de la clase
     *
     * @param menuBar  tipo JMenuBar
     * @param textPane tipo JtextPane
     * @param pestañas tipo JTabbedPane
     */
    public ColorTextBackground(JMenuBar menuBar, JTextPane textPane, JTabbedPane pestañas) {
        this.menuBar = menuBar;
        this.textPane = textPane;
        this.pestañas = pestañas;
        this.textPaneCopia = textPane;
        this.color = new JMenuItem();

        insertarColor();
        pestañasDiferentes();
    }

    /**
     * Metodo para reconocer la pestaña en la que el usuario se encuentre
     */
    private void pestañasDiferentes() {
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
     * Metodo para crear el JMenuItem y gestionar los eventos
     */
    private void insertarColor() {
        color.setMaximumSize(new Dimension(30, 20));
        color.setBackground(new Color(30, 144, 255));
        color.setForeground(Color.BLACK);
        color.setBorderPainted(false);
        insertarImagen();

        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textPane.getSelectedText();
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(new ImageIcon("./src/Imagenes/iconoPrincipal.png").getImage());
                if (text == null) {
                    JOptionPane.showMessageDialog(frame, "¡NO HAY TEXTO SELECCIONADO!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    Color colorBackGround = JColorChooser.showDialog(frame, "Elegir Color", Color.BLUE);
                    if (colorBackGround != null) {
                        cambiarColor(textPane, colorBackGround);
                    }
                }
            }
        });

        menuBar.add(color);
    }


    /**
     * Metodo para cambiar el color del texto seleccoinado
     *
     * @param textPane tipo JPanel
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
                StyleConstants.setBackground(attrs, color);
                textPane.getStyledDocument().setCharacterAttributes(selectionStart, selectionEnd - selectionStart, attrs, false);
                break;
            }
        }
    }

    /**
     * Metodo para insertar el icono
     *
     * @return
     */
    private ImageIcon insertarImagen() {
        ImageIcon icono = new ImageIcon("./src/Imagenes/rotulador.png");
        color.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, 0)));
        color.setLocation(0, 0);
        return icono;
    }

}
