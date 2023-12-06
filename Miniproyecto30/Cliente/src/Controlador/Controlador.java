package Controlador;

import Modelo.Cliente;
import Modelo.Pregunta;
import Vista.GUICliente;
import Modelo.Timer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    public void iniciarCliente() 
    {
        this.cli = new Cliente(gui);
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
    public void escribirEnunciados(){
        ArrayList<Pregunta> preguntas = cli.getLista();
        File examen = new File("src\\Controlador\\Examen.txt");
        
        if (examen.exists()) {
            System.out.println("El archivo ya existe.");
            return; 
        }  
            try (FileWriter fw = new FileWriter("src\\Controlador\\Examen.txt",true)) {
                // Escribe en el archivo
                for(int i=1; i < preguntas.size();i++){
                    Pregunta pregunta = preguntas.get(i);
                fw.append("Pregunta N° "+i+"\n");
                fw.append(pregunta.GetEnunciado()+"\n");
                fw.append("-------Opciones----------------\n");
                fw.append(pregunta.GetRespuestaA()+"\n");
                fw.append(pregunta.GetRespuestaB()+"\n");
                fw.append(pregunta.GetRespuestaC()+"\n");
                fw.append(pregunta.GetRespuestaD()+"\n");
                fw.append("-------Correcta----------------\n");
                fw.append(pregunta.GetCorrecta()+"\n");
                fw.append("/////////////////////////////////////////////\n");
            }
        } catch (IOException ex) {
            System.out.println("no encontró el archivo a escribir");
        } 
    }
}


