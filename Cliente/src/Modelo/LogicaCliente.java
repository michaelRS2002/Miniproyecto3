/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LogicaCliente {
    public ArrayList<Pregunta> leerPreguntasDesdeArchivo(String nombreExamen) {
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        String directorio = "src/Examenes/"; // Directorio donde están los archivos de examen

        try (BufferedReader br = new BufferedReader(new FileReader(directorio + nombreExamen + ".txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Suponiendo que cada línea contiene los datos de una pregunta separados por un delimitador como ";"
                String[] datosPregunta = linea.split(";"); // Cambiar el delimitador según la estructura de tu archivo

                // Se parsean los datos de la línea a los tipos correctos
                int numero = Integer.parseInt(datosPregunta[0]);
                String enunciado = datosPregunta[1];
                String respuestaA = datosPregunta[2];
                String respuestaB = datosPregunta[3];
                String respuestaC = datosPregunta[4];
                String respuestaD = datosPregunta[5];
                String correcta = datosPregunta[6];

                // Se crea un objeto Pregunta con los datos obtenidos y se añade a la lista
                Pregunta pregunta = new Pregunta(numero, enunciado, respuestaA, respuestaB, respuestaC, respuestaD, correcta);
                preguntas.add(pregunta);
            }
        } catch (IOException | NumberFormatException e) {
            // Manejo de excepciones (por ejemplo, mostrar un mensaje de error)
            e.printStackTrace();
        }

        return preguntas;
    }
    
    
  
    
}
