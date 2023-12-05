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
    
    public void exponerPreguntas(){
    ArrayList<Pregunta> lista = cli.getLista();
    int indice = gui.indice(); // Suponiendo que gui.indice() devuelve el índice seleccionado en tu GUI

    // Verificar si el índice está dentro del rango válido
    if (indice >= 0 && indice < lista.size()) {
        Pregunta preguntaSeleccionada = lista.get(indice);

        // Configurar la interfaz para mostrar detalles de la pregunta seleccionada
        // Por ejemplo, para mostrar el enunciado en un campo de texto en la interfaz
        gui.setLEnunP(preguntaSeleccionada.GetEnunciado());
        gui.set1(preguntaSeleccionada.GetRespuestaA());
        gui.set2(preguntaSeleccionada.GetRespuestaB());
        gui.set3(preguntaSeleccionada.GetRespuestaC());
        gui.set4(preguntaSeleccionada.GetRespuestaD());
    } else {
        // Manejar el caso en que el índice no sea válido
        System.out.println("Índice fuera de rango");
    }
   }

}
