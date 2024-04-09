package Menu;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Clase para gestionar el texto en italic
 */
public class ItalicText {
    private JMenuBar menuBar;
    private JTextPane textPane;
    private AtomicBoolean italicActivado = new AtomicBoolean(false);

    /**
     * Constructor de la clase
     *
     * @param menuBar  tipo JMenuBar
     * @param textPane tipo JTextPane
     */
    public ItalicText(JMenuBar menuBar, JTextPane textPane) {
        this.menuBar = menuBar;
        this.textPane = textPane;

        insertarComponentes();
    }

    /**
     * Metodo para insertar los componentes
     */
    private void insertarComponentes() {
        JMenuItem italic = new JMenuItem();
        italic.setFont(new Font("Italic", Font.ITALIC, 14));
        italic.setMaximumSize(new Dimension(25, 25));
        italic.setForeground(Color.black);
        italic.setLocation(0, -10);
        italic.setBackground(new Color(30, 144, 255));

        JMenuItem cursiva = new JMenuItem();
        cursiva.setHorizontalTextPosition(0);
        cursiva.setMaximumSize(new Dimension(25, 25));
        cursiva.setFont(new Font("Bold", Font.BOLD, 14));
        cursiva.setForeground(Color.BLACK);
        cursiva.setBackground(new Color(30, 144, 255));

        insertarImagenes(italic, cursiva);

        italic.addActionListener(new StyledEditorKit.ItalicAction());

        cursiva.addActionListener(new StyledEditorKit.BoldAction());

        menuBar.add(italic);
        menuBar.add(cursiva);
    }

    /**
     * Metodo para insertar las imagenes
     *
     * @param italic  tipo JMenuItem
     * @param cursiva tipo JMenuItem
     */
    private void insertarImagenes(JMenuItem italic, JMenuItem cursiva) {
        ImageIcon iconoBold = new ImageIcon("./src/Imagenes/bold.png");
        cursiva.setIcon(new ImageIcon(iconoBold.getImage().getScaledInstance(15, 15, 0)));
        cursiva.setLocation(0, 0);

        ImageIcon iconoItalic = new ImageIcon("./src/Imagenes/italic.png");
        italic.setIcon(new ImageIcon(iconoItalic.getImage().getScaledInstance(15, 15, 0)));
        italic.setLocation(0, 0);
    }
}
