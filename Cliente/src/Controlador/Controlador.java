package Controlador;

import Modelo.Cliente;
import Modelo.Pregunta;
import Vista.GUICliente;
import Modelo.Timer;
import java.util.ArrayList;


/**
 *
 * @author nicol
 */
public class Controlador {
    private GUICliente gui;
    private Cliente cli;
    private Timer time;
  
    
    public Controlador() {
        this.gui = new GUICliente();
        this.gui.EstablecerControlador(this);
        this.cli = new Cliente(gui);
        this.time = new Timer();
    }
    public void iniciarContador() 
    {
        //time.iniciarTimer();
    }
    
 public void exponerPreguntas(ArrayList<Pregunta> lista) {
    int indice = gui.indice(); // Suponiendo que gui.indice() devuelve el índice seleccionado en tu GUI

    if (!lista.isEmpty()) {
        if (indice >= 0 && indice < lista.size()) {
            Pregunta preguntaSeleccionada = lista.get(indice);

            // Configurar la interfaz para mostrar detalles de la pregunta seleccionada
            // Por ejemplo, para mostrar el enunciado en un campo de texto en la interfaz
            setLEnunP(preguntaSeleccionada.GetEnunciado());
            set1(preguntaSeleccionada.GetRespuestaA());
            set2(preguntaSeleccionada.GetRespuestaB());
            set3(preguntaSeleccionada.GetRespuestaC());
            set4(preguntaSeleccionada.GetRespuestaD());
        } else {
            setLEnunP("El índice está fuera de rango.");
            // Si el índice está fuera del rango, mostrar un mensaje de error en la interfaz
        }
    } else {
        setLEnunP("No hay preguntas disponibles.");
        // Si la lista de preguntas está vacía, mostrar un mensaje en la interfaz
    }
}

}
