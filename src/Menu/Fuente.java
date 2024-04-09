package Menu;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import java.awt.*;

/**
 * Clase para gestionar la fuente del texto
 */
public class Fuente {
    JMenuBar menuBar;
    JTextPane textPane;
    JMenu fuente;
    JTabbedPane pestañas;
    CaretListener caretListener2;
    JTextPane textPaneCopia;

    /**
     * Constructor de la clase
     *
     * @param menuBar  itpo JMenuBar
     * @param textPane tipo JTextPane
     * @param pestañas tipo JTabbedPane
     */
    public Fuente(JMenuBar menuBar, JTextPane textPane, JTabbedPane pestañas) {
        this.textPane = textPane;
        this.menuBar = menuBar;
        this.pestañas = pestañas;
        this.fuente = new JMenu("Fuente");
        textPaneCopia = textPane;
        insertarFuente();
        cambiarFuente();
    }

    /**
     * Metodo para conocer la pestaña en la que se encuentra el usuario
     */
    private void cambiarFuente() {
        textoSeleccionado(true);
        pestañas.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JPanel panel = (JPanel) pestañas.getSelectedComponent();
                JTextPane textPane2 = null;
                Component[] components = panel.getComponents();
                for (Component component : components) {
                    if (component instanceof JTextPane) {
                        textPane2 = (JTextPane) component;
                        textPane = textPane2;
                        break;
                    }
                }
                textoSeleccionado(false);
            }
        });
    }

    /**
     * Metodo para saber el estilo del texto seleccionado
     *
     * @param tipo tipo boolean
     */
    private void textoSeleccionado(boolean tipo) {
        CaretListener caretListener = new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                if (tipo)
                    textPane = textPaneCopia;

                int dot = e.getDot();
                int mark = e.getMark();

                StyledDocument doc = textPane.getStyledDocument();

                int inicio = textPane.getSelectionStart();

                if (dot != mark) {
                    AttributeSet conjuntoAtributos = doc.getCharacterElement(inicio).getAttributes();
                    String font = StyleConstants.getFontFamily(conjuntoAtributos);

                    fuente.setText(font);
                    fuente.setFont(new Font(font, Font.PLAIN, 14));
                    fuente.setForeground(Color.black);
                }
            }
        };
        textPane.addCaretListener(caretListener);
    }

    /**
     * Metodo para insertar la fuente
     */
    private void insertarFuente() {
        fuente.setBounds(100, 10, 40, 30);
        fuente.setForeground(Color.black);
        menuBar.add(fuente);
        insertarComponentes(fuente);
    }

    /**
     * Metodo para insertar los evento y comoponentes
     *
     * @param fuente tipo JMenu
     */
    private void insertarComponentes(JMenu fuente) {
        JMenuItem arial = new JMenuItem("Arial");
        arial.setBackground(Color.white);
        Font miFuente = new Font("Arial", Font.PLAIN, 14);
        arial.setFont(miFuente);
        //ActionListen
        arial.addActionListener(new StyledEditorKit.FontFamilyAction("", "Arial"));

        JMenuItem roman = new JMenuItem("Times New Roman");
        roman.setBackground(Color.white);
        roman.setForeground(Color.BLACK);
        miFuente = new Font("Times New Roman", Font.PLAIN, 14);
        roman.setFont(miFuente);
        //ActionListen
        roman.addActionListener(new StyledEditorKit.FontFamilyAction("", "Times new Roman"));

        JMenuItem impact = new JMenuItem("Impact");
        impact.setForeground(Color.black);
        impact.setBackground(Color.white);
        miFuente = new Font("Impact", Font.PLAIN, 14);
        impact.setFont(miFuente);
        //ActionListen
        impact.addActionListener(new StyledEditorKit.FontFamilyAction("", "Impact"));

        fuente.add(arial);
        fuente.add(roman);
        fuente.add(impact);
    }
}
