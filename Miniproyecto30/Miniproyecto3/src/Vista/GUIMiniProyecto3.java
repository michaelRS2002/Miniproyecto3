package Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controlador.Controlador;

/*
 * Esta Clase contiene toda la Interfaz gráfica-Servidor
 * @author Michael Ramirez Suriel
 * @author Juan Fernando Calle
 * @author Jose Adrian Marin
 * @author Juan David Pinto
 */

public class GUIMiniProyecto3 extends JFrame {
    //Barra de Opciones en la aplicación
    JMenuBar barra;
    JMenuItem crearExamen, verExamenes, verInformes, realizarExamen;
    
    //Gui Inicio
    
    
    //Gui Crear Examenes
    JLabel lCrearExamen,EnunPN,uno,dos,tres,cuatro,cinco;
    JTextField NPregunta,jR1, jR2, jR3, jR4,jRespuesta;
    JTextArea areaEnunciado;
    JScrollPane scrollPaneCrear, jspInf;
    JButton jbGuardarP, jbTerminarCE;
    int num_pregunta= 0;
    
    
    //Gui Ver Examenes
    JComboBox<String> jcbSelecExam;
    JPanel jpExam, jpExamTA;
    JTextArea jtaExam;
    JScrollPane jspExam;
    
    //Gui Ver Informes
    JComboBox<String> jcbSelecInformes;
    JPanel jpInf, jpInfTA;
    JTextArea jtaInf;
    
    //Gui Realizar Examen
    JLabel lNumPregunta,lEnunp,lEnunciado,lR1, lR2, lR3, lR4;
    
    
    JLabel lNom, lImagenMenu;
    JTextField jtPresen;
    
    //Gui Servidor 
    JTextField campoIntroducir;
    JTextArea areaPantalla = new JTextArea(25, 20);
    JScrollPane barrasServidor = new JScrollPane (areaPantalla);
    JPanel pGeneral, pMenu, JCrearExamenes,pCExamen,JServidor;
    
    
    JButton  jbCancelar,jbObtener;
    
    String nombreExamen, tiempoExamen,ExamenARealizar;
    private Controlador control;
    
    /**
     * Constructor de la GUI
     */
    public GUIMiniProyecto3()
    {
        setTitle("Examenes Conectados");
        setSize(580,500);
        crearGUIMenu();//Metodo de la GUI principal
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void EstablecerControlador(Controlador control) {
        this.control = control;
    }
    /**
     * GUI principal que contiene el menu
     */
    public void crearGUIMenu()
    {
        crearGUIPresen();
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
    /**
     * Logica de bienvenida al usuario
     */
    public void crearGUIPresen()
    {
        
        lNom = new JLabel("<html><div style='text-align: center;'>"
                          +"<html>Bienvenido a Examenes Conectados<br>"
                          +"tu plataforma para hacer examenes <br>"
                          +"en grupos!!</div></html>");
        
        lImagenMenu = new JLabel();
        lImagenMenu.setSize(20, 30);
        lImagenMenu.setIcon(new ImageIcon("src/Images/imagenMenu.png"));
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
    }
    /**
     * Creación de examenes
     */
    public void crearGUIcrearExamenes(){
        
        panelNomExamen();
        num_pregunta++;
        //Paneles Para Crear Examanes
        JPanel JPregRes,JEnunciadoN,JEnunciado,JRespuestas, jpbotones, jNRespuestas;
        //Panel Para Identificar Funcion en toda la gui
        JCrearExamenes = new JPanel(new BorderLayout());
        //Panel Enunciado-Pregunta
        JPregRes = new JPanel(new BorderLayout());
        //Panel Enunciado Norte
        JEnunciadoN= new JPanel(new BorderLayout());
        //Panel Enunciado Text
        JEnunciado= new JPanel(new BorderLayout());
        //Panel Respuestas
        JRespuestas = new JPanel(new GridLayout(5,1,5,5));
        //Panel numero respuestas
        jNRespuestas = new JPanel(new GridLayout(5,1,5,5));
        //Panel Botones
        jpbotones = new JPanel();
        jpbotones.setPreferredSize(new Dimension(100,100));
        //jpbotones.setLayout(new BoxLayout(jpbotones, BoxLayout.Y_AXIS));
        //Atributos Panel Enunciado
        EnunPN = new JLabel("Enunciado Pregunta N°: ");
        
        NPregunta= new JTextField();
        NPregunta.setText(Integer.toString(num_pregunta));
        
        areaEnunciado = new JTextArea(2, 10); 

        // Agregar el JTextArea a un JScrollPane para permitir el desplazamiento
        scrollPaneCrear = new JScrollPane(areaEnunciado);
        
        //lEnunP = new JLabel("Aqui se supone que va la pregunta"); //EDITAR
        /**
         * Aqui se supone es donde van los JLabel de las preguntas "uno que contenga el numero y
         * otro que contenga como tal la pregunta con el fin de quede bien organizado"
         */
        uno = new JLabel("A ");
        uno.setHorizontalAlignment(JLabel.CENTER);
        dos = new JLabel("B ");
        dos.setHorizontalAlignment(JLabel.CENTER);
        tres = new JLabel("C ");
        tres.setHorizontalAlignment(JLabel.CENTER);
        cuatro = new JLabel("D ");
        cuatro.setHorizontalAlignment(JLabel.CENTER);
        cinco = new JLabel("Correcta");
        cinco.setHorizontalAlignment(JLabel.CENTER);
        
        //JTextField  para las respuestas "Estos son los que hacen el return"
        jR1 = new JTextField(""); //EDITAR
        jR2 = new JTextField(""); //EDITAR
        jR3 = new JTextField(""); //EDITAR
        jR4 = new JTextField(""); //EDITAR
        jRespuesta = new JTextField("");//EDITAR
        
        //JButton
        jbGuardarP = new JButton("<html><div style='text-align: center;'>"
                               + "<html>Guardar"
                               + "<br>Pregunta");
        jbGuardarP.setPreferredSize(new Dimension(100,100));
        
        jbTerminarCE = new JButton("<html><div style='text-align: center;'>"
                                    + "<html>Terminar"
                                    + "<br>Examen");
        jbTerminarCE.setPreferredSize(new Dimension(100,100));
        
        lCrearExamen = new JLabel("Crear examenes");
        Font forte = new Font("Comic Sans", Font.ROMAN_BASELINE, 20);
        lCrearExamen.setFont(forte);
        lCrearExamen.setHorizontalAlignment(JLabel.CENTER);
        
        //Añadir Elementos Panel Enunciado-Respuestas
        //Panel Enunciado Norte
        JEnunciadoN.add(EnunPN,BorderLayout.CENTER);
        JEnunciadoN.add(NPregunta,BorderLayout.EAST);
        
        //Panel Enunciado
        JEnunciado.add(JEnunciadoN,BorderLayout.NORTH);
        JEnunciado.add(scrollPaneCrear,BorderLayout.CENTER);
        
        //Panel Respuestas
        JRespuestas.add(jR1);
        JRespuestas.add(jR2);
        JRespuestas.add(jR3);
        JRespuestas.add(jR4);
        JRespuestas.add(jRespuesta);
        
        //Panel NRespuesta
        jNRespuestas.add(uno);
        jNRespuestas.add(dos);
        jNRespuestas.add(tres);
        jNRespuestas.add(cuatro);
        jNRespuestas.add(cinco);
        
        JPregRes.add(JEnunciado,BorderLayout.NORTH);
        JPregRes.add(JRespuestas,BorderLayout.CENTER);
        JPregRes.add(jNRespuestas, BorderLayout.WEST);
        JPregRes.setBorder(new EmptyBorder(5,5,5,5));
        
        jpbotones.add(Box.createVerticalStrut(220));
        jpbotones.add(jbGuardarP);
        jpbotones.add(jbTerminarCE);
        jpbotones.setBorder(new EmptyBorder(0,0,0,10));
         
        JCrearExamenes.add(lCrearExamen, BorderLayout.NORTH);
        JCrearExamenes.add(JPregRes, BorderLayout.CENTER);
        JCrearExamenes.add(jpbotones, BorderLayout.EAST);
        add(JCrearExamenes);
        revalidate();
        ClaseManejadoraEventos eva = new ClaseManejadoraEventos();
        jbGuardarP.addActionListener(eva);
        
    }
    public void verExamenes()
    {    
         /**
         * Arraylist de los elementos del comboBox
         * Pd: Esta es la que va a recibir los
         * examenes creados
         */ 
        String[] examenes = {"Exam #1","Exam #2","Exam #3","..."};
        
        //Elementos de la GUIinformes
        
        jcbSelecExam = new JComboBox<>(examenes);
        jcbSelecExam.setPreferredSize(new Dimension(80,40));
        
        jtaExam = new JTextArea();
        jtaExam.setLineWrap(true);
        jtaExam.setWrapStyleWord(true);
        
        jspExam = new JScrollPane(jtaExam);
        
        JLabel selecE = new JLabel("<html><div style='text-align: center;'>"
                                  +"<html>Seleccione el "
                                  +"<br>examen a ver");
        
        //Creacion de los paneles
        jpExamTA = new JPanel();
        jpExamTA.setBorder(new EmptyBorder(10,5,5,5));
        jpExamTA.setPreferredSize(new Dimension(100,50));
        jpExam = new JPanel(new GridLayout(1,1));
        jpExam.setBorder(new EmptyBorder(10,10,10,10));
        
        jpExamTA.add(selecE,BorderLayout.NORTH); jpExamTA.add(jcbSelecExam); 
        jpExam.add(jspExam);
        
        add(jpExamTA, BorderLayout.WEST);
        add(jpExam, BorderLayout.CENTER);
        revalidate();
        
    }
    
    public void borrarTextos(){
        areaEnunciado.setText("");
        jR1.setText("");
        jR2.setText("");
        jR3.setText("");
        jR4.setText("");
        jRespuesta.setText("");
    }
    
    public void panelNomExamen(){
        // Crear un cuadro de diálogo para obtener el nombre del examen y el tiempo
    JTextField nombreExamenField = new JTextField();
    JTextField tiempoExamenField = new JTextField();

    Object[] message = {
            "Nombre del examen:", nombreExamenField,
            "Tiempo de examen:", tiempoExamenField
    };

    int option = JOptionPane.showConfirmDialog(null, message, "Nombre-Tiempo del examen", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        nombreExamen = nombreExamenField.getText();
        tiempoExamen = tiempoExamenField.getText();
        control.crearExamen();
  

        // Aquí puedes utilizar los valores obtenidos (nombreExamen y tiempoExamen)
        // para lo que necesites en tu aplicación.
    }
    }
    /**
     * Logica para realizar e lexamen
     */
    public void ExamenARealizar() {
        // Crear un modelo para la lista desplegable
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(control.TraerExamenes());

        // Crear un panel de entrada con la lista desplegable
        JComboBox<String> comboBox = new JComboBox<>(modelo);
        Object[] message = {
                "Seleccione un archivo .txt:", comboBox
        };
        // Mostrar el panel de confirmación
        int options = JOptionPane.showConfirmDialog(null, message, "Preparado para lanzar el examen?", JOptionPane.OK_CANCEL_OPTION);

        // Verificar la respuesta del usuario
        if (options == JOptionPane.OK_OPTION) {
            // Obtener el archivo seleccionado
            String archivoSeleccionado = (String) comboBox.getSelectedItem();
            System.out.println("Archivo seleccionado: " + archivoSeleccionado);
            int posPunto = archivoSeleccionado.lastIndexOf(".");

        if (posPunto > 0) {
            ExamenARealizar = archivoSeleccionado.substring(0, posPunto);
            System.out.println("Nombre del archivo sin extensión: " + ExamenARealizar);
        } else {
            System.out.println("La extensión del archivo no se encontró.");
        }
            // Aquí puedes realizar acciones adicionales según la selección del usuario
        } else {
            System.out.println("El usuario canceló la operación.");
            // Puedes realizar acciones adicionales en caso de cancelación
        }
    }
    
    public void verInformes(){
        
         /**
         * Arraylist de los elementos del comboBox
         * Pd: Esta es la que va a recibir los informes
         * de los examenes realizados
         */ 
        String[] informes = {"inf 1","inf 2","inf 3","..."};
        
        //Elementos de la GUIinformes
        jcbSelecInformes = new JComboBox<>(informes);
        jcbSelecInformes.setPreferredSize(new Dimension(80,40));
        
        jtaInf = new JTextArea();
        jtaInf.setLineWrap(true);
        jtaInf.setWrapStyleWord(true);
        
        jspInf = new JScrollPane(jtaInf);
        
        JLabel selec = new JLabel("<html><div style='text-align: center;'>"
                                  +"<html>Seleccione el "
                                  +"<br>informe a ver");
        
        //Creacion de los paneles
        jpInfTA = new JPanel();
        jpInfTA.setBorder(new EmptyBorder(10,5,5,5));
        jpInfTA.setPreferredSize(new Dimension(100,50));
        jpInf = new JPanel(new GridLayout(1,1));
        jpInf.setBorder(new EmptyBorder(10,10,10,10));
        
        jpInfTA.add(selec,BorderLayout.NORTH); jpInfTA.add(jcbSelecInformes); 
        jpInf.add(jspInf);
        
        add(jpInfTA, BorderLayout.WEST);
        add(jpInf, BorderLayout.CENTER);
        revalidate();        
    }
    
    public void creaGuiServer()
    {
     JServidor = new JPanel(new BorderLayout());
     campoIntroducir = new JTextField();
     campoIntroducir.setEnabled(false);
     JServidor.add(campoIntroducir, BorderLayout.SOUTH);
     JServidor.add(barrasServidor, BorderLayout.CENTER);
     add(JServidor);
     revalidate();
     
     ClaseManejadoraEventos ev = new ClaseManejadoraEventos();
     campoIntroducir.addActionListener(ev);
    }
    
 
    
    /**
     * Método para obtener el nombre de usuarios
     */
    public void nombreUsuarios()
    {
        JOptionPane mensajeEmergente;
        mensajeEmergente = new JOptionPane();
        mensajeEmergente.showInputDialog(null, "Nombre del usuario", "Examenes conectados", JOptionPane.QUESTION_MESSAGE);
   
       if (mensajeEmergente.YES_OPTION == 0)
               {
                   //crearGUI5();
     
               }
       if (mensajeEmergente.CANCEL_OPTION == 0)
       {
           
       }
    
    }
    
   
    
    public void TraerPreguntas(String ExamenA){
      //  this.ExamenARealizar=ExamenARealizar;
        control.RecuperarPreguntas(ExamenA);
        
    }
    public void Servidor(JPanel ServidorGui){
        JServidor = new JPanel();
        JServidor.add(ServidorGui);
        add(JServidor);
    }
    /**
     * Pendiente a borrar o ver que se hace
     */
    public void barraConexion()
        {
            JFrame jfConexion;
            JTextArea textArea;
              jfConexion = new JFrame("Conexion Usuarios");
              jfConexion.setSize(320,320);
              jfConexion.setLocation(1044, 200);
              jfConexion.setVisible(true);
              //Area para saber los usuarios conectados
             
              textArea = new JTextArea();
              textArea.setEditable(false);
              textArea.setName("Usuarios");
              jfConexion.add(textArea);
    
            
        }
    /**
     * Metodos set
     * @param Opc 
     */
    public void setNumPregunta(int Opc){
        lNumPregunta.setText(Integer.toString(Opc));
    }
    public void setEnun(String Opc){
        lEnunp.setText(Opc);
    }
    public void setLabelR1(String Opc){
        lR1.setText(Opc);
    }
    public void setLabelR2(String Opc){
        lR2.setText(Opc);
    }
    public void setLabelR3(String Opc){
        lR3.setText(Opc);
    }
    public void setLabelR4(String Opc){
        lR4.setText(Opc);
    }
    /**
     * Metodos get
     * @return 
     */
    public String getExamenMandar(){
        return ExamenARealizar;
    }
    
    public String getNomExam(){
        return nombreExamen;
    }
    public String getTiempoExam(){
        return tiempoExamen;
    }
    public String getNumPregunta(){
        return Integer.toString(num_pregunta);
    }
    public String getEnunciado(){
        return areaEnunciado.getText();
    }
    public String getR1(){
        return jR1.getText() ;
    }
    public String getR2(){
        return jR2.getText();
    }
    public String getR3(){
        return jR3.getText();
    }
    public String getR4(){
        return jR4.getText();
    }
    public String getCorrecta(){
        return jRespuesta.getText();
    }
    
    /**
     * Clase manejadora de eventos
     */
    class ClaseManejadoraEventos implements ActionListener
    {
        
            @Override
            public void actionPerformed(ActionEvent e)
            {      

                if (e.getSource() == crearExamen)
                {
                    crearExamen.setEnabled(false);
                    verExamenes.setEnabled(true);
                    verInformes.setEnabled(true);
                    realizarExamen.setEnabled(true);
                    crearGUIcrearExamenes();
                }
                 if (e.getSource() == verExamenes)
                {
                    crearExamen.setEnabled(true);
                    verExamenes.setEnabled(false);
                    verInformes.setEnabled(true);
                    realizarExamen.setEnabled(true);
                    verExamenes();
                }
                if(e.getSource() == verInformes)
                {
                    crearExamen.setEnabled(true);
                    verExamenes.setEnabled(true);
                    verInformes.setEnabled(false);
                    realizarExamen.setEnabled(true);

                    verInformes();
                }
                if (e.getSource() == realizarExamen)
                {
                    crearExamen.setEnabled(false);
                    verExamenes.setEnabled(false);
                    verInformes.setEnabled(false);
                    realizarExamen.setEnabled(false);
                    control.EjecutarServidor();
                    if (JCrearExamenes != null) {
                        JCrearExamenes.setVisible(false);
                    }
                    ExamenARealizar();
                    pGeneral.setVisible(false);
                    creaGuiServer();
                    //nombreUsuarios();
                   // server.creaGui()  ;
                }
                if (e.getSource()== jbGuardarP)
                {
                    control.GuardarPreguntas();
                    num_pregunta++;
                    NPregunta.setText(Integer.toString(num_pregunta));
                    borrarTextos();
                }
            }
    }
    // Mostrar un mensaje de error si no se conecta el usuario debidamente 

public void mostrarMensaje(String mensaje){
    

areaPantalla.append (mensaje);
}
}

