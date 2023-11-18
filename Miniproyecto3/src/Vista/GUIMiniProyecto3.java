package Vista;

/*
 *
 * @author invitado
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUIMiniProyecto3 extends JFrame {
   
    JLabel lNom, lImagenMenu, lCrearExamen, lR1, lR2, lR3, lR4, lEnunP;
    JTextField jtPresen;
    JPanel pGeneral, pMenu, pCExamen;
    JMenuBar barra;
    JMenuItem inicio, crearExamen, verExamenes, verInformes, realizarExamen;
    JButton jbGuardarP, jbTerminarCE;
   
    
    public GUIMiniProyecto3()
    {
        setTitle("Examenes Conectados");
        setSize(580,500);
        crearGUIP();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    
    //No modificar esta, ya funciona bien x,D
    public void crearGUIPresen()
    {
        
        lNom = new JLabel("<html><div style='text-align: center;'>"
                          +"<html>Bienvenido a Examenes Conectados<br>"
                          +"tu plataforma para hacer examenes <br>"
                          +"en grupos!!</div></html>");
        
        lImagenMenu = new JLabel();
        lImagenMenu.setSize(20, 30);
        lImagenMenu.setIcon(new ImageIcon("src/Images/Image1.png"));
        lImagenMenu.setHorizontalAlignment(JLabel.CENTER);
        
        //Panel con Grid con el fin de organizar el texto y la imagen de la presentacion
        pGeneral = new JPanel(new GridLayout(2,1));
        
              
        lNom.setHorizontalAlignment(SwingConstants.CENTER);
        
        Font forte = new Font("Comic Sans", Font.BOLD, 25);
        lNom.setFont(forte);
        lNom.setForeground(Color.RED);
                     
        //ubicacion de los elementos de la presentacion del programa
        pGeneral.add(lNom);
        pGeneral.add(lImagenMenu);
        pGeneral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3)));
        
        //Organizar ventana
        add(pGeneral, BorderLayout.CENTER); 
        //add(barra, BorderLayout.NORTH);
              
        /**Metodo que usa un ActionLstener con el fin de dar 5 segs a la GUI de presentacion
         * se llama al metodo dipose() para cerrar la GUI, e instantaneamente iniciar la otra
         * que coniene el programa en su totalidad
         */ 
        Timer CuentaAtras = new Timer(3000, (ActionEvent e) -> {
            dispose();
            crearGUIP();
        });
        
        CuentaAtras.start();            
    }
    
    //Esta como tal ya es la GUI principal del programa
    public void crearGUIP()
    {
        
        //Panel Menu
        barra = new JMenuBar();
        crearExamen = new JMenuItem("Crear Examen");
        crearExamen.setHorizontalAlignment(JMenuItem.CENTER);
        verExamenes = new JMenuItem("Ver Examenes");
        verExamenes.setHorizontalAlignment(JMenuItem.CENTER);
        verInformes = new JMenuItem("Ver informes");
        verInformes.setHorizontalAlignment(JMenuItem.CENTER);
        realizarExamen = new JMenuItem("Realizar Examen");
        realizarExamen.setHorizontalAlignment(JMenuItem.CENTER);
       
        barra.add(crearExamen);
        barra.add(verExamenes);
        barra.add(verInformes);
        barra.add(realizarExamen);
        setJMenuBar(barra);
                        
        ClaseManejadoraEventos ev = new ClaseManejadoraEventos();
        //inicio.addActionListener(ev);
        crearExamen.addActionListener(ev);
        verExamenes.addActionListener(ev);
        verInformes.addActionListener(ev);
        realizarExamen.addActionListener(ev);
     
    }
    
    public void crearGUI2(){
        
        //JLabel para las respuestas "Estos son los que hacen el return"
        JPanel jppregYenun,jpbotones;
        jppregYenun = new JPanel(new GridLayout(6,1,2,2));
        jpbotones = new JPanel();
        jpbotones.setLayout(new BoxLayout(jpbotones, BoxLayout.Y_AXIS));
        lR1 = new JLabel("R1"); //EDITAR 
        lR2 = new JLabel("R2"); //EDITAR
        lR3 = new JLabel("R3"); //EDITAR
        lR4 = new JLabel("R4"); //EDITAR
        
        jbGuardarP = new JButton("<html>Guardar"
                               + "Pregunta<br>");
        jbGuardarP.setPreferredSize(new Dimension(50,50));
        
        jbTerminarCE = new JButton("<html>Terminar"
                                 + "Examen<br>");
        jbTerminarCE.setPreferredSize(new Dimension(60,70));
        
        lCrearExamen = new JLabel("Crear examenes");
        Font forte = new Font("Comic Sans", Font.ROMAN_BASELINE, 20);
        lCrearExamen.setFont(forte);
        lCrearExamen.setHorizontalAlignment(JLabel.CENTER);
        pCExamen = new JPanel(new GridLayout(1,2));
        JTextField NPregunta = new JTextField("");
        JLabel EnunPN = new JLabel("Enunciado Pregunta N°: " + NPregunta);
        lEnunP = new JLabel("Aqui se supone que va la pregunta"); //EDITAR
        
        /**
         * Aqui se supone es donde van los JLabel de las preguntas "uno que contenga el numero y
         * otro que contenga como tal la pregunta con el fin de quede bien organizado"
         */
        JLabel uno = new JLabel("1. " + lR1);
        JLabel dos = new JLabel("2. " + lR2);
        JLabel tres = new JLabel("3. " + lR3);
        JLabel cuat = new JLabel("4. " + lR4);
        
        jppregYenun.add(EnunPN);
        jppregYenun.add(lEnunP); 
        jppregYenun.add(uno);
        jppregYenun.add(dos); 
        jppregYenun.add(tres); 
        jppregYenun.add(cuat);
        
        jpbotones.add(Box.createVerticalStrut(160));
        jpbotones.add(jbGuardarP);
        jpbotones.add(jbTerminarCE);
         
        //pCExamen.add(lCrearExamen, BorderLayout.NORTH);
        add(lCrearExamen, BorderLayout.NORTH);
        add(jppregYenun, BorderLayout.CENTER);
        add(jpbotones, BorderLayout.EAST);
        revalidate();
        
    }
    
    public void crearGUI3(){
        
    }
    
    //Clase manejadora de Eventos
    class ClaseManejadoraEventos implements ActionListener
    {
        
            @Override
            public void actionPerformed(ActionEvent e)
            {      

                if (e.getSource() == crearExamen)
                {
                    //pCExamen.setVisible(true);
                    crearExamen.setEnabled(false);
                    crearGUI2();
                }
    
            }
    }
}

