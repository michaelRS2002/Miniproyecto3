package Controlador;

import Modelo.ClaseArchivo;
import Vista.GUIMiniProyecto3;
import Modelo.Logica;
import java.util.ArrayList;

public class Controlador {
    private GUIMiniProyecto3 gui;
    private ClaseArchivo arc;
    private Logica logic;

    public Controlador() {
        this.gui = new GUIMiniProyecto3();
        this.gui.setControlador(this);
        this.arc = new ClaseArchivo();
        this.logic= new Logica();
    }
    public void iniciar() {
        // Puedes llamar a este método desde tu main para iniciar la GUI
        gui = new GUIMiniProyecto3();
    }

    public void crearExamen() {
        // Debes obtener el tiempo del examen desde tu GUI, asumiendo que tienes un método getTiempoExam() en tu GUI
        String nom = gui.getNomExam();
        String tiempoExamen = gui.getTiempoExam();
        // Llama al método del modelo para crear el archivo de examen
        ClaseArchivo.crearArchivoExamen(nom, tiempoExamen);
    }
    public void GuardarPreguntas(){
        String nom = gui.getNomExam();
        String numPregunta = gui.getNumPregunta();
        String enunciado = gui.getEnunciado();
        String R1 = gui.getR1();
        String R2 = gui.getR2();
        String R3 = gui.getR3();
        String R4 = gui.getR4();
        String Correcta = gui.getCorrecta();
        
        ClaseArchivo.escribirPregunta(nom, numPregunta, enunciado, R1, R2, R3, R4, Correcta);
    }
    public String[] TraerExamenes(){
        ArrayList<String> archivos = Logica.obtenerArchivosTxt("src\\Examenes");
        archivos.toArray(new String[]);
        return archivos;
    }
}

