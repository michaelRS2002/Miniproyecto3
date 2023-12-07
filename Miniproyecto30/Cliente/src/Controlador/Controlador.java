package Controlador;

import Modelo.Cliente;
import Modelo.LogicaCliente;
import Modelo.Pregunta;
import Modelo.Respuesta;
import Vista.GUICliente;
import Modelo.Timer;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nicol
 */
public class Controlador {
    private GUICliente gui;
    private LogicaCliente LOG;
    private Cliente cli;
    private Timer time;
    String [] listaPreguntas=null;
  
    
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
    public void enviarRespuestas(int id,String opc){
        ArrayList<Respuesta> resp = new ArrayList<>();
        Respuesta respuesta=new Respuesta(id, opc);
        resp.add(respuesta);
        try {
            cli.enviarArrayList(resp);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
public void enPantalla() {
    try (BufferedReader br = new BufferedReader(new FileReader("src\\Controlador\\Examen.txt"))) {
        String linea;
        
        int contadorPregunta = 0;
        int contadorLineas = 1;

        while ((linea = br.readLine()) != null) {
            if (linea.equals("/////////////////////////////////////////////")) {
                System.out.println("Terminé pregunta");
                contadorPregunta++;
                contadorLineas = 1;
                continue;
            }

            contadorLineas++;
        }
        System.out.println(contadorPregunta);
        armarArrayString(contadorPregunta);
    } catch (IOException ex) {
        // Manejar la excepción
        ex.printStackTrace();
    }
    gui.revalidate();
}

    public void armarArrayString(int contadorPregunta) {
        listaPreguntas = new String[contadorPregunta];
        for(int i=0;i<contadorPregunta;i++){
            listaPreguntas[i]= Integer.toString(i+1);
        }
    }
    public String[] getListaPreg(){
        return listaPreguntas;
    }
    public void MostrarEnPantalla(int indice) {
    try (BufferedReader br = new BufferedReader(new FileReader("src\\Controlador\\Examen.txt"))) {
        String linea;
        
        int contadorPregunta = 0;
        int contadorLineas = 1;

        while ((linea = br.readLine()) != null) {
            if (linea.equals("/////////////////////////////////////////////")) {
                System.out.println("Terminé pregunta");
                if (contadorPregunta == indice){
                    break;
                }else{
                contadorPregunta++;
                contadorLineas = 1;
                continue;
                }
            }

            switch (contadorLineas) {
                case 2:
                    gui.setLEnunP(linea);
                    System.out.println(linea);
                case 4:
                    gui.set1(linea);
                case 5:
                    gui.set2(linea);
                case 6:
                    gui.set3(linea);
                case 7:
                    gui.set4(linea);
                case 9:
                    // Aquí deberías establecer la respuesta correcta en tu interfaz gráfica
                    //gui.Correcta(linea);
                    System.out.println(linea);
            }

            contadorLineas++;
        }
        System.out.println(contadorPregunta);
       
    } catch (IOException ex) {
        // Manejar la excepción
        ex.printStackTrace();
    }
    gui.revalidate();
}
    public void PonerPreguntaNum(int pregunta){
        //int numPregunta= gui.indice();
        MostrarEnPantalla(pregunta);
    }

}


