package Controlador;

import Vista.ClaseArchivo;
import Vista.GUIMiniProyecto3;

public class Controlador {
    private GUIMiniProyecto3 gui;
    private ClaseArchivo arc;

    public Controlador() {
        this.gui = new GUIMiniProyecto3();
        this.gui.setControlador(this);
        this.arc = new ClaseArchivo();
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
}
