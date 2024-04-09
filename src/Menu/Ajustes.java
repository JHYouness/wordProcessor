package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Clase para gestionar los elementos del boton ajustes
 */
public class Ajustes implements ActionListener {
    private JMenu ajustes;
    private JMenuItem rojo;
    private JMenuItem azul;
    private JMenuItem verde;
    private JMenuItem amarillo;
    private JMenuItem naranja;
    private JMenuItem morado;
    private int[] a;
    private int[] r;
    private int[] b;
    private Archivo archivo;
    private JMenu desacGif;
    private JMenuItem ayuda;
    private JMenu personalizar = new JMenu("Color pestañas");
    private JFrame frame = new JFrame();

    /**
     * Constructor de la clase
     *
     * @param menuBar tipo JMenuBar
     * @param archivo tipo Archivo
     */
    public Ajustes(JMenuBar menuBar, Archivo archivo) {
        this.archivo = archivo;

        this.a = new int[7];
        this.r = new int[7];
        this.b = new int[7];

        this.ajustes = new JMenu("Ajuestes");
        this.ajustes.setMaximumSize(new Dimension(30, 30));
        this.ajustes.setLayout(null);
        this.ajustes.setLocation(0, 500);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setIconImage(new ImageIcon("./src/Imagenes/iconoPrincipal.png").getImage());

        insertarImagenDescarga();
        menuBar.add(ajustes);
        isnertarBotonAyuda();
        insertarBotonesPersonalizar();
        insertarBotonDesaGif();
        insertarImagenes();
    }

    /**
     * Metodo para insertar las imagenes
     */
    private void insertarImagenes() {
        ImageIcon icono = new ImageIcon("./src/Imagenes/ayuda.png");
        ayuda.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, 0)));

        ImageIcon icono1 = new ImageIcon("./src/Imagenes/multiPestañas.png");
        personalizar.setIcon(new ImageIcon(icono1.getImage().getScaledInstance(20, 20, 0)));


        ImageIcon icono2 = new ImageIcon("./src/Imagenes/acDe.png");
        desacGif.setIcon(new ImageIcon(icono2.getImage().getScaledInstance(20, 20, 0)));
    }

    /**
     * Metodo para gestionar el boton ayuda
     */
    private void isnertarBotonAyuda() {
        ayuda = new JMenuItem("Ayuda");

        ayuda.addActionListener(e -> {

            JOptionPane.showMessageDialog(frame, "Para crear una ventana nueva, primero se debe personalizar el color " +
                    "de las ventanas" +
                    "\n\n" +
                    "Los atajos tradicionales para copia, pegar, cortar y volver atras son Ctrl + z/x/c/v/a" +
                    "\n\n" +
                    "                        World Swing 2024.1 (Community Edition)" +
                    "\n                        Construido en Java - 2024/01/01" +
                    "\n                        Programado por Youness El Jylaly","Ayuda",JOptionPane.INFORMATION_MESSAGE);
        });
        ajustes.add(ayuda);
    }

    /**
     * Metodo para crear los JMenuItem activar/desactivar y gestionar los eventos del componente activar
     */
    private void insertarBotonDesaGif() {
        desacGif = new JMenu("Activar/Desactivar la bienvenida inicial");
        JMenuItem activar = new JMenuItem("Activar");
        JMenuItem desactivar = new JMenuItem("Desactivar");

        activar.addActionListener(e -> {
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter("./gifIncial.txt"), true);
                printWriter.write("no");
                printWriter.flush();
                printWriter.close();

                JOptionPane.showMessageDialog(frame, "¡EL GIF SE A ACTIVADO!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        desactivar.addActionListener(e -> {
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter("./gifIncial.txt"), true);
                printWriter.write("si");
                printWriter.flush();
                printWriter.close();
                JOptionPane.showMessageDialog(frame, "¡EL GIF SE A DESACTIVADO!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        desacGif.add(activar);
        desacGif.add(desactivar);
        ajustes.add(desacGif);
    }

    /**
     * Metodo para insertar la imagen principal
     */
    private void insertarImagenDescarga() {
        ImageIcon icono = new ImageIcon("./src/Imagenes/ajustes.png");
        ajustes.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, 0)));
    }

    /**
     * Metodo para crear y gestionar los botones
     */
    private void insertarBotonesPersonalizar() {
        rojo = new JMenuItem("Rojo");
        azul = new JMenuItem("Azul");
        verde = new JMenuItem("Verde");
        amarillo = new JMenuItem("Amarillo");
        naranja = new JMenuItem("Naranja");
        morado = new JMenuItem("Morado");

        rojo.setBackground(Color.red);
        azul.setBackground(new java.awt.Color(52, 152, 219));
        verde.setBackground(Color.green);
        amarillo.setBackground(Color.yellow);
        naranja.setBackground(Color.orange);
        morado.setBackground(new java.awt.Color(142, 68, 173));

        rojo.addActionListener(archivo);
        azul.addActionListener(archivo);
        verde.addActionListener(archivo);
        amarillo.addActionListener(archivo);
        naranja.addActionListener(archivo);
        morado.addActionListener(archivo);

        rojo.addActionListener(this);
        azul.addActionListener(this);
        verde.addActionListener(this);
        amarillo.addActionListener(this);
        naranja.addActionListener(this);
        morado.addActionListener(this);

        personalizar.add(rojo);
        personalizar.add(azul);
        personalizar.add(verde);
        personalizar.add(amarillo);
        personalizar.add(naranja);
        personalizar.add(morado);
        ajustes.add(personalizar);
    }

    /**
     * Metodo para rellenar los vectores de colores adecuados segun el evento
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rojo) {
            a = new int[]{241, 236, 231, 203, 176, 148, 120, 1};
            r = new int[]{148, 112, 76, 67, 58, 49, 40, 2};
            b = new int[]{138, 99, 60, 53, 46, 38, 31, 3};
        } else if (e.getSource() == azul) {
            a = new int[]{133, 93, 52, 46, 40, 33, 27, 1};
            r = new int[]{193, 173, 152, 134, 116, 97, 79, 3};
            b = new int[]{233, 226, 219, 193, 166, 140, 114, 4};
        } else if (e.getSource() == verde) {
            a = new int[]{130, 88, 46, 40, 35, 29, 24, 1};
            r = new int[]{224, 214, 204, 180, 155, 131, 106, 3};
            b = new int[]{170, 141, 113, 99, 86, 72, 72, 4};
        } else if (e.getSource() == amarillo) {
            a = new int[]{247, 244, 241, 212, 183, 154, 1};
            r = new int[]{220, 208, 196, 172, 149, 125, 3};
            b = new int[]{111, 63, 15, 13, 11, 10, 4};
        } else if (e.getSource() == naranja) {
            a = new int[]{248, 245, 243, 214, 185, 156, 1};
            r = new int[]{196, 176, 156, 137, 119, 100, 3};
            b = new int[]{113, 65, 18, 16, 14, 12, 4};
        } else if (e.getSource() == morado) {
            a = new int[]{187, 165, 142, 125, 108, 91, 3};
            r = new int[]{143, 105, 68, 60, 52, 44, 1};
            b = new int[]{206, 189, 173, 152, 131, 111, 4};
        }
    }

    /**
     * Getters y setters
     */
    public int[] getB() {
        return b;
    }

    public int[] getR() {
        return r;
    }

    public int[] getA() {
        return a;
    }

    public JMenuItem getRojo() {
        return rojo;
    }

    public JMenuItem getAzul() {
        return azul;
    }

    public JMenuItem getVerde() {
        return verde;
    }

    public JMenuItem getAmarillo() {
        return amarillo;
    }

    public JMenuItem getNaranja() {
        return naranja;
    }

    public JMenuItem getMorado() {
        return morado;
    }
}
