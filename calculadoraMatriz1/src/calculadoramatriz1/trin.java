package calculadoramatriz1;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class trin implements ActionListener {

    JFrame winPrinInterMat;
    JMenuBar barraMenu;
    JMenu jMenu1, jMenu2;
    JMenuItem jMenu11, jMenu12, jMenu21, jMenu22, jMenu23, jMenu24, jMenu31, jMenu32;
    Panel jPMatriz1, jPMatriz2, jpEscalar, jPOperaciones;
    JLabel jlNumeroEscalar;
    JTextField jTMatriz1[][], jTMatriz2[][], jtEscalar;
    Choice chMatriz11, chMatriz12, chMatriz21, chMatriz22;
    JButton jbRealizar, jbLimpiar, jbSuma, jbResta, jbMulti, jbMultiMulti;

    public trin() {
        winPrinInterMat = new JFrame("Calculadora De Matriz");

        winPrinInterMat.setLayout(new BorderLayout(2, 2));

        menuPrincipal();
        operaciones();
        matriz1();
        matriz2();

        winPrinInterMat.add(BorderLayout.NORTH, barraMenu);
        winPrinInterMat.add(BorderLayout.EAST, jPOperaciones);
        winPrinInterMat.add(BorderLayout.WEST, jPMatriz1);
        winPrinInterMat.add(BorderLayout.CENTER, jPMatriz2);

        winPrinInterMat.setSize(640, 480);
        winPrinInterMat.setPreferredSize(new Dimension(640, 480));
        winPrinInterMat.setLocation(80, 50);
        winPrinInterMat.setVisible(true);
        winPrinInterMat.setResizable(false);
        winPrinInterMat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void menuPrincipal() {

        //Barra de menu, contiene los menus que se muestran y los submenus
        barraMenu = new JMenuBar();

        //Menus
        jMenu1 = new JMenu("Ventana");

        //Opciones primer menu
        jMenu11 = new JMenuItem("Limpiar");
        jMenu12 = new JMenuItem("Salir");

        //Añadir Item al menu 1        
        jMenu1.add(jMenu11);
        jMenu1.add(jMenu12);

        //Añadiendo menus a la barra de menu
        barraMenu.add(jMenu1);

        //añadiendo eventos de click        
        jMenu12.addActionListener(this);
    }

    public void operaciones() {

        jPOperaciones = new Panel();
        jPOperaciones.setLayout(null);

        jbRealizar = new JButton(new ImageIcon("src/imagenes/realizar.png"));
        jbLimpiar = new JButton(new ImageIcon("src/imagenes/limpiar.png"));
        jbSuma = new JButton(new ImageIcon("src/imagenes/suma.png"));
        jbResta = new JButton(new ImageIcon("src/imagenes/resta.png"));
        jbMulti = new JButton(new ImageIcon("src/imagenes/multiplicacion.png"));
        jbMultiMulti = new JButton(new ImageIcon("src/imagenes/multimulti.png"));

        jbRealizar.setBounds(5, 20, 40, 33);
        jbLimpiar.setBounds(5, 60, 40, 33);
        jbSuma.setBounds(5, 100, 40, 33);
        jbResta.setBounds(5, 140, 40, 33);
        jbMulti.setBounds(5, 180, 40, 33);
        jbMultiMulti.setBounds(5, 220, 40, 33);

        jPOperaciones.add(jbRealizar);
        jPOperaciones.add(jbLimpiar);
        jPOperaciones.add(jbSuma);
        jPOperaciones.add(jbResta);
        jPOperaciones.add(jbMulti);
        jPOperaciones.add(jbMultiMulti);

        jbRealizar.addActionListener(this);
        jbLimpiar.addActionListener(this);
        jbSuma.addActionListener(this);
        jbResta.addActionListener(this);
        jbMulti.addActionListener(this);
        jbMultiMulti.addActionListener(this);

        jPOperaciones.setSize(60, 280);
        jPOperaciones.setVisible(true);
    }

    public void matriz1() {

        jPMatriz1 = new Panel();
        jPMatriz1.setLayout(null);

        int f, c;

        JLabel jlNomMat1 = new JLabel("Matriz 1");
        jlNomMat1.setFont(new Font("Verdana", Font.BOLD, 13));

        chMatriz11 = new Choice();
        chMatriz12 = new Choice();

        chMatriz11.addItem("");
        chMatriz12.addItem("");

        //Ciclo para añadir los numeros a los choice
        for (int i = 0; i < 6; i++) {
            chMatriz11.addItem("" + (i + 1));
            chMatriz12.addItem("" + (i + 1));
        }

        jlNomMat1.setBounds(114, 220, 80, 20);
        chMatriz11.setBounds(105, 255, 35, 20);
        chMatriz12.setBounds(145, 255, 35, 20);

        evenChoice evenCh = new evenChoice();

        chMatriz12.addItemListener(evenCh);

        jPMatriz1.add(jlNomMat1);
        jPMatriz1.add(chMatriz11);
        jPMatriz1.add(chMatriz12);

        jPMatriz1.setSize(290, 280);
        jPMatriz1.setVisible(true);
    }

    public void matriz2() {

        jPMatriz2 = new Panel();
        jPMatriz2.setLayout(null);

        int f, c, setX = 50, setY = 5;

        JLabel jlNomMat2 = new JLabel("Matriz 2");
        jlNomMat2.setFont(new Font("Verdana", Font.BOLD, 13));

        chMatriz21 = new Choice();
        chMatriz22 = new Choice();

        chMatriz21.addItem("");
        chMatriz22.addItem("");

        //Ciclo para añadir los numeros a los choice
        for (int i = 0; i < 6; i++) {
            chMatriz21.addItem("" + (i + 1));
            chMatriz22.addItem("" + (i + 1));
        }

        jTMatriz2 = new JTextField[6][6];

        /*ciclo para añadir el jtextfield bidimensional al panel 2, se uso dos variables
         * setX y setY, las cuales sirven para crear y configurar la posicion en cada iteracion,
         * por cada ciclo de j se suma 35 para dar el salto a la siguiente fila
         */
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                jTMatriz2[i][j] = new JTextField(" ");
                jTMatriz2[i][j].setBounds(setX, setY, 30, 30);
                jTMatriz2[i][j].setHorizontalAlignment(JTextField.RIGHT);
                setX += 35;
            }
            setX = 50;
            setY += 35;
        }

        /*ciclo para añadir los jtextfield al panel 2 y configurar la visibilidad*/
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                jPMatriz2.add(jTMatriz2[i][j]);
                jTMatriz2[i][j].setVisible(false);
            }
        }

        jlNomMat2.setBounds(114, 220, 80, 20);
        chMatriz21.setBounds(105, 255, 35, 20);
        chMatriz22.setBounds(145, 255, 35, 20);

        evenChoice evenCh = new evenChoice();

        chMatriz22.addItemListener(evenCh);

        jPMatriz2.add(jlNomMat2);
        jPMatriz2.add(chMatriz21);
        jPMatriz2.add(chMatriz22);

        jPMatriz2.setSize(290, 280);
        jPMatriz2.setVisible(true);
    }

    public void generarMat1(int f, int c) {
        //Metodo para posicionar cuadros de texto de la primera matriz

        int setX = 50, setY = 5;

        jTMatriz1 = new JTextField[f][c];

        /*ciclo para añadir el jtextfield bidimensional al panel 1, se uso dos variables
         * setX y setY, las cuales sirven para crear y configurar la posicion en cada iteracion,
         * por cada ciclo de j se suma 35 para dar el salto a la siguiente fila
         */
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                jTMatriz1[i][j] = new JTextField("");
                jTMatriz1[i][j].setBounds(setX, setY, 30, 30);
                jTMatriz1[i][j].setHorizontalAlignment(JTextField.RIGHT);
                setX += 35;
            }
            setX = 50;
            setY += 35;
        }

        /*ciclo para añadir los jtextfield al panel 2 y configurar la visibilidad*/
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                jPMatriz1.add(jTMatriz1[i][j]);
                jTMatriz1[i][j].setVisible(false);
            }
        }

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                jTMatriz1[i][j].setVisible(true);
            }
        }
        winPrinInterMat.pack();
    }

    public void generarMat2(int f, int c) {
        //Metodo para posicionar cuadros de texto de la segunda matriz

        int setX = 50, setY = 5;

        jTMatriz2 = new JTextField[f][c];

        /*ciclo para añadir el jtextfield bidimensional al panel 1, se uso dos variables
         * setX y setY, las cuales sirven para crear y configurar la posicion en cada iteracion,
         * por cada ciclo de j se suma 35 para dar el salto a la siguiente fila
         */
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                jTMatriz2[i][j] = new JTextField("");
                jTMatriz2[i][j].setBounds(setX, setY, 30, 30);
                jTMatriz2[i][j].setHorizontalAlignment(JTextField.RIGHT);
                setX += 35;
            }
            setX = 50;
            setY += 35;
        }

        /*ciclo para añadir los jtextfield al panel 2 y configurar la visibilidad*/
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                jPMatriz2.add(jTMatriz2[i][j]);
                jTMatriz2[i][j].setVisible(false);
            }
        }

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                jTMatriz2[i][j].setVisible(true);
            }
        }
        winPrinInterMat.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*Usamos el getSource para saber si el evento fue hecho de determinado componente
         * para esto lo comparamos con el == y el nombre, en cada bloque se ejecuta lo que 
         * deseemos que haga la aplicacion deacuerdo al componente "seleccionado"
         */
        int temp[][];
        
        if (e.getSource() == jMenu11) {

        } else if (e.getSource() == jMenu12) {
            System.exit(0);
        } else if (e.getSource() == jbSuma) { //cuando se presiona el boton de la suma
            if (chMatriz11.getSelectedItem().equals("") || chMatriz12.getSelectedItem().equals("")
                    || chMatriz21.getSelectedItem().equals("") || chMatriz22.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Para Sumar dos matrices se deben seleccionar\n"
                        + "valores en ambas matrices.");

            } else { //cuando se han seleccionado valores en los choice
                if ((chMatriz11.getSelectedIndex() == chMatriz21.getSelectedIndex())
                        && (chMatriz12.getSelectedIndex() == chMatriz22.getSelectedIndex())) {
                    
                    int [][] res = {{2,3,3}, {3,2,3}};
                    ventanaRespuesta mostrarRespuesta = new ventanaRespuesta(res);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "no se puede realizar la suma");
                }
            }
        } else if (e.getSource() == jbResta) {
            if (chMatriz11.getSelectedItem().equals("") || chMatriz12.getSelectedItem().equals("")
                    || chMatriz21.getSelectedItem().equals("") || chMatriz22.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Para Restar dos matrices se deben seleccionar\n"
                        + "valores en ambas matrices.");

            } else { //cuando se han seleccionado valores en los choice
                if ((chMatriz11.getSelectedIndex() == chMatriz21.getSelectedIndex())
                        && (chMatriz12.getSelectedIndex() == chMatriz22.getSelectedIndex())) {
                    
                    int [][] res = {{2,3,3}, {3,2,3}};
                    ventanaRespuesta mostrarRespuesta = new ventanaRespuesta(res);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede realizar la resta");
                }
            }
        } else if (e.getSource() == jbMulti) {//evento boton para realizar la operacion

        } else if (e.getSource() == jbMultiMulti) {

        }
    }

    public class ventanaRespuesta { //clase que contiene la ventana para mostrar los resultados

        public ventanaRespuesta(int[][] ar) {//constructor, recibe como parametro el arreglo a mostrar

            JFrame ventanaRespuesta = new JFrame("RESPUESTA");
            ventanaRespuesta.setLocation(80, 140);

            JTextArea jtA = new JTextArea(10, 10);

            String ax = ""; //variable para guardar lo del arreglo a mostrar

            //ciclo para mostrar el arreglo que se recibe...
            for (int i = 0; i < ar.length; i++) {
                for (int j = 0; j < ar[i].length; j++) {
                    ax += ar[i][j] + "    ";
                }
                ax += "\n";
            }

            jtA.setText(ax);

            ventanaRespuesta.add(jtA);

            ventanaRespuesta.setSize(200, 150);
            ventanaRespuesta.setResizable(false);
            ventanaRespuesta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaRespuesta.setVisible(true);
        }
    }

    public class evenChoice implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            int f, c;

            if (e.getSource() == chMatriz12) {
                if (chMatriz11.getSelectedIndex() != 0 && chMatriz12.getSelectedIndex() != 0) {
                    f = Integer.parseInt(chMatriz11.getSelectedItem());
                    c = Integer.parseInt(chMatriz12.getSelectedItem());

                    if (jTMatriz1 != null) {
                        for (int i = 0; i < jTMatriz1.length; i++) {
                            for (int j = 0; j < jTMatriz1[0].length; j++) {
                                jTMatriz1[i][j].setVisible(false);
                            }
                        }
                        jTMatriz1 = null;
                        generarMat1(f, c);
                    } else {
                        generarMat1(f, c);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ambos choice tienen que tener un valor!");
                }
            } else if (e.getSource() == chMatriz22) {
                if (chMatriz21.getSelectedIndex() != 0 && chMatriz22.getSelectedIndex() != 0) {
                    f = Integer.parseInt(chMatriz21.getSelectedItem());
                    c = Integer.parseInt(chMatriz22.getSelectedItem());

                    if (jTMatriz2 != null) {
                        for (int i = 0; i < jTMatriz2.length; i++) {
                            for (int j = 0; j < jTMatriz2[0].length; j++) {
                                jTMatriz2[i][j].setVisible(false);
                            }
                        }
                        jTMatriz2 = null;
                        generarMat2(f, c);
                    } else {
                        generarMat2(f, c);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ambos choice tienen que tener un valor!");
                }
            }
        }

    }
}