package Menu;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Frame.*;

/**
 * Clase para gestionar los elementos del boton archivo
 */
public class Archivo implements ActionListener {
    private int contadorPestañas;
    private JMenu archivo;
    private JTextPane textPane;
    private JTextField nombre;
    private ArrayList<JMenuItem> vectorJMenuItemDescargar;
    private JTabbedPane pestañas;
    private Ventana v;
    private static LinkedList<Nuevo> listaPestañas = new LinkedList<>();
    private JMenuItem nuevo = new JMenuItem("Nuevo");
    private static ArrayList<JTabbedPane> listaJTabbedPane = new ArrayList<>();
    private JMenuItem abrir;
    private int contadorColores;
    private MenuSuperior menuSuperior;
    private JMenu cerrar;
    private ArrayList<JMenuItem> listaPestañasAcerrar = new ArrayList<>();
    private JFrame frame = new JFrame();

    /**
     * Constructor de la clase
     *
     * @param textPane     tipo JTextPane
     * @param nombre       tipo String
     * @param menuBar      tipo JMenuBar
     * @param pestañas     tipo JTabbedPane
     * @param v            tipo Ventana
     * @param menuSuperior tipo MenuSuperior
     */
    public Archivo(JTextPane textPane, JTextField nombre, JMenuBar menuBar, JTabbedPane pestañas, Ventana v, MenuSuperior menuSuperior) {
        this.textPane = textPane;
        this.nombre = nombre;
        this.pestañas = pestañas;
        this.v = v;
        this.vectorJMenuItemDescargar = new ArrayList<>();
        this.contadorPestañas = 0;
        this.menuSuperior = menuSuperior;
        this.cerrar = new JMenu("Cerrar");

        this.archivo = new JMenu("Archivo");
        this.abrir = new JMenuItem("Abrir");
        this.archivo.setForeground(Color.black);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setIconImage(new ImageIcon("./src/Imagenes/iconoPrincipal.png").getImage());

        insertarImageneJMenuItems();

        crearJItems();
        menuBar.add(archivo);
    }

    /**
     * Metodo para insertar las imagenes adecuadas
     */
    private void insertarImageneJMenuItems() {
        ImageIcon icono = new ImageIcon("./src/Imagenes/abrir.png");
        abrir.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, 0)));

        ImageIcon icono1 = new ImageIcon("./src/Imagenes/cerrar.png");
        cerrar.setIcon(new ImageIcon(icono1.getImage().getScaledInstance(20, 20, 0)));

        ImageIcon icono2 = new ImageIcon("./src/Imagenes/nuevo.png");
        nuevo.setIcon(new ImageIcon(icono2.getImage().getScaledInstance(20, 20, 0)));
    }

    /**
     * Metodo para crear los los JMenuItems y añadirles eventos
     */
    private void crearJItems() {
        JMenu descarga = new JMenu("Descargar");
        descarga.setBackground(Color.white);
        insertarImagenDescarga(descarga);
        insertarDescarga(descarga);

        abrir.addActionListener(this);
        nuevo.addActionListener(this);
        nombre.addActionListener(this);

        archivo.add(abrir);
        archivo.add(cerrar);
        archivo.add(nuevo);
        archivo.add(descarga);

    }

    /**
     * Metodo para gestionar los eventos del JMenuItem 'descarga'
     *
     * @param descarga tipo JMenu
     */
    private void insertarDescarga(JMenu descarga) {
        JMenuItem txt = new JMenuItem("Texto plano (.txt)");
        txt.addActionListener(e -> {
            descargar(".txt");
        });
        JMenuItem odt = new JMenuItem("Formato de documento Abierto (.odt)");
        odt.addActionListener(e -> {
            descargar(".odt");
        });
        JMenuItem pdf = new JMenuItem("PDF Documento (.pdf)");
        pdf.addActionListener(e -> {
            descargar(".pdf");
        });
        listaJTabbedPane.add(pestañas);
        vectorJMenuItemDescargar.add(txt);
        vectorJMenuItemDescargar.add(odt);
        vectorJMenuItemDescargar.add(pdf);

        descarga.add(txt);
        descarga.add(odt);
        descarga.add(pdf);
    }

    /**
     * Metodo para insertar la imagen del JMenu descarga
     *
     * @param descarga tipo JMenu
     */
    private void insertarImagenDescarga(JMenu descarga) {
        ImageIcon icono = new ImageIcon("./src/Imagenes/descarga.png");
        descarga.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, 0)));
    }

    /**
     * Metodo para escrbir en el fichero
     *
     * @param file tip File
     */
    private void imprimirContenido(File file) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            printWriter.write(textPane.getText());
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo mostrar el menu con los directorios locales y gestionar la respuesta del cliente
     *
     * @param formato tipo String
     */
    private void descargar(String formato) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Mostrar el diálogo de selección de directorio
        int resultado = fileChooser.showSaveDialog(frame);

        // Verificar si el usuario ha seleccionado un directorio
        String directorioSeleccionado = null;
        if (resultado == JFileChooser.APPROVE_OPTION) {
            // Obtener el directorio seleccionado por el usuario
            directorioSeleccionado = fileChooser.getSelectedFile().getAbsolutePath();
            File fichero = new File(directorioSeleccionado, nombre.getText() + formato);
            int remprazar = 0;

            if (fichero.exists()) {
                remprazar = JOptionPane.showConfirmDialog(frame, "Ya existe un fichero con el mismo nombre, ¿Deseas remplazarlo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (remprazar == 0) imprimirContenido(fichero);
            } else imprimirContenido(fichero);
        }
    }

    /**
     * Metodo para gestionar los eventos
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Ajustes ajustes = menuSuperior.getAjustes();
        JMenuItem rojo = ajustes.getRojo();
        JMenuItem azul = ajustes.getAzul();
        JMenuItem verde = ajustes.getVerde();
        JMenuItem amarrilo = ajustes.getAmarillo();
        JMenuItem naranja = ajustes.getNaranja();
        JMenuItem morado = ajustes.getMorado();

        // Si cambio de color restauro el contador de colores para que el degradado empiece de cero
        if (e.getSource() == rojo || e.getSource() == azul || e.getSource() == verde || e.getSource() == amarrilo ||
                e.getSource() == naranja || e.getSource() == morado) {
            contadorColores = 0;
        } else if (e.getSource() == nuevo) {
            int[] a = ajustes.getA();
            int[] r = ajustes.getR();
            int[] b = ajustes.getB();

            if (a[0] == 0) {
                String[] opciones = {"Ayuda"};
                int option = JOptionPane.showOptionDialog(frame, "¡ERROR! ¡NO HAY COLOR SELECCIONADO!", "Advertencia", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, opciones, opciones);
                if (option == 0) {
                    JOptionPane.showMessageDialog(frame, "NO OLVIDES PERSONALIZAR EL COLOR DE LAS PESTAÑAS, (DESDE EL BOTON DE AJUSTES)", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                Nuevo nuevoFichero = new Nuevo(pestañas, textPane);
                listaPestañas.add(nuevoFichero);

                for (int i = 0; i < listaPestañas.size(); i++) {
                    listaPestañas.get(i).setId((i + 1));
                }
                contadorPestañas++;
                if (contadorColores == (a.length - 1)) {
                    contadorColores = 1;
                }

                pestañas.setBackgroundAt(contadorPestañas, new java.awt.Color(a[contadorColores], r[contadorColores], b[contadorColores]));
                contadorColores++;
                JMenuItem elementoAcerrar = new JMenuItem(contadorPestañas + "");
                cerrar.add(elementoAcerrar);
                listaPestañasAcerrar.add(elementoAcerrar);
                elementoAcerrar.addActionListener(c -> {
                    int numEntero = Integer.parseInt(elementoAcerrar.getText());
                    pestañas.remove(numEntero);
                    contadorPestañas--;
                    elementoAcerrar.setVisible(false);
                    listaPestañasAcerrar.remove(elementoAcerrar);
                    if (numEntero == listaPestañas.size()) {
                        listaPestañas.removeLast();
                    } else {
                        listaPestañas.remove(numEntero);
                    }
                    for (int i = 0; i < listaPestañasAcerrar.size(); i++) {
                        listaPestañasAcerrar.get(i).setText((i + 1) + "");
                    }
                    for (int i = 0; i < listaPestañas.size(); i++) {
                        listaPestañas.get(i).setId((i + 1));
                    }
                });
            }
        } else if (e.getSource() == abrir) {
            abrirMetodo();
        } else {
            JPanel panel = (JPanel) pestañas.getSelectedComponent();
            if (!listaPestañas.isEmpty()) {
                for (Nuevo n : listaPestañas) {
                    if (n.getPanelNuevo() == panel) {
                        pestañas.setTitleAt(n.getId(), nombre.getText());
                    }
                }
            }

            //Este for lo ultilizo para saber en que psetaña se encuentra el usuario, lo veras en varios puntos del programa
            JPanel panelFalso = (JPanel) pestañas.getSelectedComponent();
            boolean encontrado = false;
            Component[] components = panelFalso.getComponents();
            for (Component component : components) {
                if (component instanceof JTextPane) {
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                pestañas.setTitleAt(0, nombre.getText());
            } else {
                encontrado = false;
            }
        }
    }

    /**
     * Metodo para gestionar el evento del JMenu 'abrir'
     */
    private void abrirMetodo() {
        JFileChooser fileChooser = new JFileChooser();

        // Mostrar el cuadro de diálogo para seleccionar el archivo
        int seleccion = fileChooser.showOpenDialog(frame);


        String directorioSeleccionado = null;
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // Obtener el directorio seleccionado por el usuario
            File file = fileChooser.getSelectedFile();

            try {
                // Crear un FileReader y BufferedReader para leer el archivo
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // Leer el contenido del archivo línea por línea
                String linea;
                StringBuilder contenido = new StringBuilder();
                while ((linea = bufferedReader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }

                pestañas.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        JPanel panel = (JPanel) pestañas.getSelectedComponent();
                        JTextPane textPane2 = null;
                        boolean encontrado = false;
                        Component[] components = panel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JTextPane) {
                                textPane2 = (JTextPane) component;
                                textPane = textPane2;
                                break;
                            }
                        }
                    }
                });

                textPane.setText(contenido.toString());

                // Cerrar el BufferedReader y FileReader
                bufferedReader.close();
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
