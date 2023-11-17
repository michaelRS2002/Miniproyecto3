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
   
    JLabel lNom, lImagenMenu, lCrearExamen;
    JTextField jtPresen;
    JPanel pGeneral, pMenu, pGeneral2;
    JMenuBar barra;
    JMenuItem inicio, crearExamen, verExamenes, verInformes, realizarExamen;
   
    
    public GUIMiniProyecto3()
    {
        setTitle("Examenes Conectados");
        setSize(640,540);
        crearGUI1();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void crearGUI1()
    {
        //Panel Menu
        barra = new JMenuBar();
        inicio = new JMenuItem("Inicio");
        inicio.setEnabled(false);
        inicio.setHorizontalAlignment(JMenuItem.CENTER);
        crearExamen = new JMenuItem("Crear Examen");
        crearExamen.setHorizontalAlignment(JMenuItem.CENTER);
        verExamenes = new JMenuItem("Ver Examenes");
        verExamenes.setHorizontalAlignment(JMenuItem.CENTER);
        verInformes = new JMenuItem("Ver informes");
        verInformes.setHorizontalAlignment(JMenuItem.CENTER);
        realizarExamen = new JMenuItem("Realizar Examen");
        realizarExamen.setHorizontalAlignment(JMenuItem.CENTER);
        
        lNom = new JLabel("<html>Bienvenido a Examenes Conectados<br>"
                          +"tu plataforma para hacer examenes <br>"
                          +"en grupos!!");
        pGeneral = new JPanel(new GridLayout(2,1));
        jtPresen = new JTextField();
        lImagenMenu = new JLabel();
        lImagenMenu.setIcon(new ImageIcon("src/Images/imagenMenu.png"));
        lImagenMenu.setHorizontalAlignment(JLabel.CENTER);
        
        
        
        lNom.setHorizontalAlignment(SwingConstants.CENTER);
        
        Font forte = new Font("Comic Sans", Font.PLAIN, 20);
        lNom.setFont(forte);
        
        
               
        //ubicacion de los elementos de la presentacion del programa
        pGeneral.add(lNom);
        pGeneral.add(lImagenMenu);
        barra.add(inicio);
        barra.add(crearExamen);
        barra.add(verExamenes);
        barra.add(verInformes);
        barra.add(realizarExamen);
        setJMenuBar(barra);
        pGeneral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 3)));
        
        //Organizar ventana
        add(pGeneral, BorderLayout.CENTER); 
        //add(barra, BorderLayout.NORTH);
        
        
    
    ClaseManejadoraEventos ev = new ClaseManejadoraEventos();
    inicio.addActionListener(ev);
    crearExamen.addActionListener(ev);
    
    }
    
    public void crearGUI2()
    {
        
        pGeneral2 = new JPanel(new BorderLayout());
        lCrearExamen = new JLabel("Crear examenes");
        Font forte = new Font("Comic Sans", Font.ROMAN_BASELINE, 20);
        lCrearExamen.setFont(forte);
        lCrearExamen.setHorizontalAlignment(JLabel.CENTER);

        
        pGeneral2.add(lCrearExamen, BorderLayout.NORTH);
        add(pGeneral2);
        revalidate();
        inicio.setEnabled(true);
    
    }
    class ClaseManejadoraEventos implements ActionListener
    {

            @Override
            public void actionPerformed(ActionEvent e)
            {      

                if (e.getSource() == inicio)
                {            
                    pGeneral2.setVisible(false);
                    pGeneral.setVisible(true);
                    
             
                }
                else if (e.getSource() == crearExamen)
                {
                    pGeneral.setVisible(false);
                    crearGUI2();
                }
    
            }
    }
}

