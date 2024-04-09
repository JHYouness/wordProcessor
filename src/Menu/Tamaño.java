package Menu;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase para gestionar el tamaño del texto selecionado
 */
public class Tamaño {
    private JMenuBar menuBar;
    private JMenuItem menos = new JMenuItem("-");
    private JMenuItem mas = new JMenuItem("+");
    private JMenu tamaño;
    private int tamañoMas;
    private int tamañoMenos;
    private int tamañoTextoSeleccionado;
    private int inicio;
    private JTextPane textPane;
    private JTabbedPane pestañas;
    private JTextPane textPaneCopia;
    private int pr;

    /**
     * Constructor de la clase
     *
     * @param menuBar  tipo JMenuBar
     * @param textPane tipo JTextPane
     * @param pestañas tipo JTabbedPane
     */
    public Tamaño(JMenuBar menuBar, JTextPane textPane, JTabbedPane pestañas) {
        this.menuBar = menuBar;
        this.textPane = textPane;
        this.pestañas = pestañas;

        textPaneCopia = textPane;
        tamaño = new JMenu("Tamaño");
        pr = 0;
        tamañoMas = 12;
        tamañoMenos = 12;
        tamañoTextoSeleccionado = -1;
        inicio = 0;
        insertarTamaño();
    }

    /**
     * Metodo para crear los JMenuItems con diferente tamaños (12-24)
     */
    private void insertarTamaño() {
        final boolean[] encontrado = {false};

        tamaño.setForeground(Color.black);
        tamaño.setMaximumSize(new Dimension(60, 20));
        tamaño.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        menos.setMaximumSize(new Dimension(20, 20));
        mas.setMaximumSize(new Dimension(20, 20));

        mas.setBackground(new Color(30, 144, 255));
        menos.setBackground(new Color(30, 144, 255));
        insertaImagenesMasMenos();

        menuBar.add(menos);
        menuBar.add(tamaño);
        menuBar.add(mas);

        pestañas.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                boolean encontrado = false;
                JPanel panel = (JPanel) pestañas.getSelectedComponent();
                Component[] components = panel.getComponents();
                for (Component component : components) {
                    if (component instanceof JTextPane) {
                        textPane = (JTextPane) component;
                        encontrado = true;

                        CaretListener caretListener = new CaretListener() {
                            public void caretUpdate(CaretEvent e) {
                                int dot = e.getDot();
                                int mark = e.getMark();

                                StyledDocument doc = textPane.getStyledDocument();

                                inicio = textPane.getSelectionStart();

                                if (dot != mark) {
                                    AttributeSet conjuntoAtributos = doc.getCharacterElement(inicio).getAttributes();
                                    tamañoTextoSeleccionado = StyleConstants.getFontSize(conjuntoAtributos);
                                    cambiarTamañoVisual(tamañoTextoSeleccionado);
                                }
                            }
                        };

                        textPane.addCaretListener(caretListener);
                        break;
                    }
                }

                if (!encontrado) {
                    textPane = textPaneCopia;
                }
            }
        });

        insertarComponentes(true);
        insertarActionListenerMasMenos();

    }

    /**
     * Metodo para insertar las imagenes correspondientes
     */
    private void insertaImagenesMasMenos() {
        ImageIcon icono = new ImageIcon("./src/Imagenes/mas.png");
        mas.setIcon(new ImageIcon(icono.getImage().getScaledInstance(15, 15, 0)));
        mas.setLocation(0, 0);

        icono = new ImageIcon("./src/Imagenes/menos.png");
        menos.setIcon(new ImageIcon(icono.getImage().getScaledInstance(10, 5, 0)));
        menos.setLocation(0, 0);
    }

    /**
     * Metodo para gestionar los eventos
     */
    private void insertarActionListenerMasMenos() {
        mas.addActionListener(e -> {

            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet attrs = new SimpleAttributeSet();

            if (textPane.getSelectedText() != null) {
                inicio = textPane.getSelectionStart();
                AttributeSet conjuntoAtributos = doc.getCharacterElement(inicio).getAttributes();
                tamañoTextoSeleccionado = StyleConstants.getFontSize(conjuntoAtributos);

                tamañoMas = tamañoTextoSeleccionado;
                tamañoMas++;

                cambiarTamañoVisual(tamañoMas);

                StyleConstants.setFontSize(attrs, tamañoMas);
                doc.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), attrs, false);
            }
        });

        menos.addActionListener(e -> {
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet attrs = new SimpleAttributeSet();

            if (textPane.getSelectedText() != null) {
                inicio = textPane.getSelectionStart();
                AttributeSet conjuntoAtributos = doc.getCharacterElement(inicio).getAttributes();
                tamañoTextoSeleccionado = StyleConstants.getFontSize(conjuntoAtributos);

                tamañoMenos = tamañoTextoSeleccionado;
                tamañoMenos--;
                cambiarTamañoVisual(tamañoMenos);
                StyleConstants.setFontSize(attrs, tamañoMenos);
                doc.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), attrs, false);
            }
        });
    }

    /**
     * Metodo para cambiar el texto del JMenu segun el texto seleccionado
     *
     * @param tamañoNumero
     */
    private void cambiarTamañoVisual(int tamañoNumero) {
        tamaño.setText("<html><div align='center'>" + tamañoNumero + "</div></html>");
    }

    /**
     * Metodo para insertar los componentes
     *
     * @param tipo tipo boolean
     */
    private void insertarComponentes(boolean tipo) {
        String[] vector = {"8", "9", "10", "11", "12", "14", "18", "24"};
        JMenuItem[] numeros = new JMenuItem[vector.length];

        for (int i = 0; i < numeros.length; i++) {
            JMenuItem numero = new JMenuItem(vector[i]);
            numeros[i] = numero;

            numeros[i].setBackground(Color.white);

            CaretListener caretListener = new CaretListener() {
                public void caretUpdate(CaretEvent e) {
                    int dot = e.getDot();
                    int mark = e.getMark();

                    StyledDocument doc = textPane.getStyledDocument();

                    inicio = textPane.getSelectionStart();

                    if (dot != mark) {
                        AttributeSet conjuntoAtributos = doc.getCharacterElement(inicio).getAttributes();
                        tamañoTextoSeleccionado = StyleConstants.getFontSize(conjuntoAtributos);
                        cambiarTamañoVisual(tamañoTextoSeleccionado);
                    }
                }
            };

            numeros[i].setForeground(Color.black);

            int tamañoEntero = Integer.parseInt(vector[i]);
            int finalI = i;

            StyledEditorKit.FontSizeAction styledEditorKit = new StyledEditorKit.FontSizeAction("cambiarTmaño", tamañoEntero);
            numero.addActionListener(styledEditorKit);
            numero.addActionListener(e -> {
                cambiarTamañoVisual(tamañoEntero);
            });
            textPane.addCaretListener(caretListener);
            if (tipo) {
                tamaño.add(numeros[i]);
            }
        }
    }
}
