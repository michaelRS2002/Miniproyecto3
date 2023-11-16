package Vista;

/*
 *
 * @author invitado
 */
import java.awt.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUIMiniProyecto3 extends JFrame {
   
    JLabel lNom;
    JTextField jtPresen;
    JPanel pGeneral;
    JMenuItem inicio;
    
    public GUIMiniProyecto3()
    {
        setTitle("Examenes Conectados");
        setSize(500,500);
        crearGUI1();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void crearGUI1()
    {
        //Panel Menu
        inicio= new JMenuItem("Inicio");
        
        lNom = new JLabel("<html>Bienvenido a Examenes Conectados<br>"
                          +"tu plataforma para hacer examenes <br>"
                          +"en grupos!!");
        pGeneral = new JPanel(new GridLayout(2,1));
        jtPresen = new JTextField();
        
        lNom.setHorizontalAlignment(SwingConstants.CENTER);
        
        Font forte = new Font("Comic Sans", Font.PLAIN, 20);
        lNom.setFont(forte);
        
        
               
        //ubicacion de los elementos de la presentacion del programa
        pGeneral.add(lNom);
        pGeneral.add(jtPresen);
        
        pGeneral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 3)));
        
        //Organizar ventana
        add(pGeneral);               
    }
}

