package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Frame.Ventana;

/**
 * Clase para gestionar el menu superior
 */
public class MenuSuperior {
    private JMenuBar menuBar = new JMenuBar();
    private JPanel panelWord;
    private JTextPane textPane;
    private JTabbedPane pestañas;
    private Ventana v;
    private Ajustes ajustes;

    /**
     * Constructor de la clase
     *
     * @param panelWord tipo JPanel
     * @param textPane  tipo JTextPane
     * @param pestañas  tipo JTabbledPane
     * @param ventana   tipo Ventana
     */
    public MenuSuperior(JPanel panelWord, JTextPane textPane, JTabbedPane pestañas, Ventana ventana) {
        this.panelWord = panelWord;
        this.textPane = textPane;
        this.pestañas = pestañas;
        this.v = ventana;

        menuBar.setBounds(0, 0, 1100, 100);
        menuBar.setLayout(null);
        menuBar.setBackground(new Color(30, 144, 255));

        insertarJMenu();
    }

    /**
     * Metodo para insertar los distintos elementos del menu
     */
    private void insertarJMenu() {
        JTextField nombre = new JTextField("Nombre");
        nombre.setMaximumSize(new Dimension(90, 20));
        nombre.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //insertarImagenes(file);
        menuBar.add(nombre);
        Archivo archivo = new Archivo(textPane, nombre, menuBar, pestañas, v, this);
        Fuente fuente = new Fuente(menuBar, textPane, pestañas);

        insertarSeparatos();

        Tamaño tamaño = new Tamaño(menuBar, textPane, pestañas);

        insertarSeparatos();

        ItalicText italic = new ItalicText(menuBar, textPane);
        ColorText color = new ColorText(menuBar, textPane, pestañas);
        ColorTextBackground colorTextBackground = new ColorTextBackground(menuBar, textPane, pestañas);
        insertarSeparatos();
        ajustes = new Ajustes(menuBar, archivo);
    }

    /**
     * Metodo para insertar los separadores
     */
    private void insertarSeparatos() {
        ImageIcon iconoItalic = new ImageIcon("./src/Imagenes/separatos.png");
        JLabel img = new JLabel();
        img.setIcon(new ImageIcon(iconoItalic.getImage().getScaledInstance(15, 15, 0)));
        menuBar.add(img);
    }

    /**
     * Getters y setters
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public Ajustes getAjustes() {
        return ajustes;
    }
}
