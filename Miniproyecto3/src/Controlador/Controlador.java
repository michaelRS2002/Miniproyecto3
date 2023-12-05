//ide 20
package Controlador;

import Modelo.ClaseArchivo;
import Modelo.Logica;

//import Modelo.LogicaServidor;
import Modelo.Pregunta;
import Servidor.ConexionServidor;
import Vista.GUIMiniProyecto3;
import java.util.ArrayList;

/**
 * Clase del controlador en el modelo MVC
 * @author usuario
 */
public class Controlador {
    private GUIMiniProyecto3 gui;
    private ClaseArchivo arc;
    private Logica logic;
    private ConexionServidor Ser;
    
/**
 * Herencia de la aplicacion completa en sus partes
 */
    public Controlador() {
        this.gui = new GUIMiniProyecto3();
        this.gui.EstablecerControlador(this);
        this.arc = new ClaseArchivo();
        this.logic = new Logica();
        this.Ser = new ConexionServidor(gui,12345);
        
    }
    
    
/**
 * Logica de la creacion del examen
 */
    public void crearExamen() {
        // Debes obtener el tiempo del examen desde tu GUI, asumiendo que tienes un método getTiempoExam() en tu GUI
        String nom = gui.getNomExam();
        String tiempoExamen = gui.getTiempoExam();
        // Llama al método del modelo para crear el archivo de examen
        ClaseArchivo.crearArchivoExamen(nom, tiempoExamen);
    }
    /**
     * Logica de guardar las preguntas
     */
    public void GuardarPreguntas(){
        String nom = gui.getNomExam();
        String numPregunta = gui.getNumPregunta();
        String enunciado = gui.getEnunciado();
        String R1 = gui.getR1();
        String R2 = gui.getR2();
        String R3 = gui.getR3();
        String R4 = gui.getR4();
        String Correcta = gui.getCorrecta();
        
        arc.escribirPregunta(nom, numPregunta, enunciado, R1, R2, R3, R4, Correcta);
    }
    public String[] TraerExamenes(){
        ArrayList<String> archivos = logic.obtenerArchivosTxt("src/Examenes");
        String[] archivosArray = archivos.toArray(String[]::new);
        System.out.println(archivosArray[0]);
        return archivosArray;
    }

    public void RecuperarPreguntas(String ExamenA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
    
    
    
    
}
    
