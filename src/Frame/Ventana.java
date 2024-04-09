package Frame;

import Menu.MenuSuperior;

import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Clase para crear los diferentes componentes del programa
 */
public class Ventana extends JFrame {
    private JPanel bienvenidaPanel = new JPanel();
    private JPanel panelWord = new JPanel();
    private JTextPane textArea;
    private JTabbedPane pestañas = new JTabbedPane();
    private JLabel etiqueta3 = new JLabel();

    /**
     * Constructor de la clase
     */
    public Ventana() {
        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.textArea = new JTextPane();

        ImageIcon icono = new ImageIcon("./src/Imagenes/iconoPrincipal.png");
        this.setIconImage(new ImageIcon(icono.getImage().getScaledInstance(30, 30, 0)).getImage());

        insertarGif();
        this.setVisible(true);
        insertarElementos();
        this.setVisible(true);
    }

    /**
     * Metodo para crear los elementos
     */
    private void insertarElementos() {
        insertarWord();
        JTextPane textPane = insertarTextPane();

        panelWord.setVisible(true);

        pestañas.setBackground(new java.awt.Color(174, 214, 241));
        pestañas.setForeground(new java.awt.Color(21, 67, 96));
        pestañas.addTab("Nombre", panelWord);
        insertarMenu(textPane);
        add(pestañas);
        VersionAnterior versionAnterior = new VersionAnterior(textPane, pestañas);
    }

    /**
     * Metodo para inserta los elementos del menu superior
     *
     * @param textPane tipo JTextPane
     */
    private void insertarMenu(JTextPane textPane) {
        MenuSuperior menu = new MenuSuperior(panelWord, textPane, pestañas, this);
        JMenuBar menuBar = menu.getMenuBar();

        JPanel panelMenu = new JPanel();
        panelMenu.setBounds(0, 200, 1100, 50);
        panelMenu.setBackground(Color.BLACK);
        panelMenu.add(menuBar);
        setJMenuBar(menuBar);
    }

    /**
     * Metodo para insertar el JTextPane y el JScrollPane en el JPanel
     *
     * @return textArea tipo JTextPane
     */
    public JTextPane insertarTextPane() {
        textArea = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelWord.setLayout(new BorderLayout());
        panelWord.add(scrollPane, BorderLayout.CENTER);

        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textArea.setForeground(Color.black);
        textArea.setBackground(Color.white);

        return textArea;
    }

    /**
     * Metodo para insertar el panel del world en la ventana
     */
    public void insertarWord() {
        try {
            //Leo el fichero 'gifIncial.txt' para saber si el usuario a activado o desactivado el gif
            //(importante que el contenedio del fichero por defecto sea un 'no')
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./gifIncial.txt"));
            String text = bufferedReader.readLine();

            if (text.equalsIgnoreCase("no")) {
                //Los milisegundos que tarda el gif para posteriormente cambiar de panel
                try {
                    Thread.sleep(4500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Hago el panel de bienvenida invisible
        bienvenidaPanel.setVisible(false);

        ImageIcon imagen2 = new ImageIcon("./src/Gifs/YounessCEO.gif");
        etiqueta3.setBounds(500, 0, 500, 500);
        etiqueta3.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(400, 400, 0)));
        etiqueta3.setVisible(false);
        panelWord.add(etiqueta3);

        //Creo el panel world
        panelWord.setBounds(0, 0, 1100, 700);
        panelWord.repaint();
        panelWord.setLayout(null);
    }

    /**
     * Metodo para insertar el gif iniucial
     */
    public void insertarGif() {
        //Creo el panel de bienvenida
        bienvenidaPanel.setBounds(0, 0, 1100, 700);
        ImageIcon imagen2 = new ImageIcon("./src/Gifs/bienvenida.gif");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setBounds(0, 0, 1100, 700);
        etiqueta2.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(1100, 700, 0)));
        etiqueta2.setVisible(true);

        bienvenidaPanel.add(etiqueta2);
        bienvenidaPanel.setVisible(true);
        add(bienvenidaPanel);
    }

    /**
     * Getters y setters
     */
    public JTextPane getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextPane textArea) {
        this.textArea = textArea;
    }

    public JPanel getBienvenidaPanel() {
        return bienvenidaPanel;
    }

    public void setBienvenidaPanel(JPanel bienvenidaPanel) {
        this.bienvenidaPanel = bienvenidaPanel;
    }

    public JPanel getPanelWord() {
        return panelWord;
    }

    public void setPanelWord(JPanel panelWord) {
        this.panelWord = panelWord;
    }

    public JTabbedPane getPestañas() {
        return pestañas;
    }
}
