package Vista;

/*
 *
 * @author invitado
 */
import java.awt.*;
import java.util.HashSet;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUIMiniProyecto3 extends JFrame {
   
    JLabel lNom, lImagenMenu;
    JTextField jtPresen;
    JPanel pGeneral, pMenu;
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
        
        
    }
}

