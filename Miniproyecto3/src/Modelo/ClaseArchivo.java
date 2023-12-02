/**
 * Clase pendiente a documentacion
 */


package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.Scanner;
/**
 *
 * @author Asus Michael
 */
public class ClaseArchivo {
    private static ArrayList<Pregunta> preguntas;
    

    public static void crearArchivoExamen(String nomExamen,String Tiempo) {
        File examen = new File("src\\Examenes\\" + nomExamen + ".txt");
        
        if (examen.exists()) {
            System.out.println("El archivo ya existe.");
            return; 
        }  
        try{
            // Escribe en el archivo
            try (FileWriter fw = new FileWriter(examen, true)) {
                // Escribe en el archivo
                fw.append(nomExamen+"\n");
                fw.append(Tiempo+"\n");
                fw.append("-----------------------------------------------------\n");
            }
        System.out.println("Archivo "+ nomExamen+ " creado exitosamente.");
        }catch (IOException ex) {
            System.out.println("Error al Escribir en el archivo de examen.");
        }
    }
    
    public static void escribirPregunta(String nomExamen,String numPregunta,String E,String R1,String R2,String R3,String R4,String Correcta)
    {
        try {
            try (FileWriter fw = new FileWriter("src\\Examenes\\" + nomExamen + ".txt",true)) {
                // Escribe en el archivo
                fw.append("Pregunta N° "+numPregunta+"\n");
                fw.append(E+"\n");
                fw.append("-------Opciones----------------\n");
                fw.append(R1+"\n");
                fw.append(R2+"\n");
                fw.append(R3+"\n");
                fw.append(R4+"\n");
                fw.append("-------Correcta----------------\n");
                fw.append(Correcta+"\n");
                fw.append("/////////////////////////////////////////////\n");
            }
        } catch (IOException ex) {
            System.out.println("no encontró el archivo a escribir");
        } 
    }
    
   
    
    public static ArrayList<Pregunta> PreguntasExamenes(String nomExamen)
    {
        
        FileReader fr = null;
        preguntas = new ArrayList<>();
        String Tiempo =null;
        int numPregunta=1;
        String linea= null;
        String Enunciado = null;
        String RespuestaA = null;
        String RespuestaB = null;
        String RespuestaC = null;
        String RespuestaD = null;
        String Correcta = null;
        
   try {
        fr = new FileReader("src\\Examenes\\" + nomExamen + ".txt");
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        Tiempo = br.readLine();
        br.readLine();
        int contadorLineas = 1;
        while ((linea = br.readLine()) != null) {
            if (linea.equals("/////////////////////////////////////////////")){
               System.out.println("Terminé pregunta");
               Pregunta preg = new Pregunta(numPregunta,Enunciado,RespuestaA,RespuestaB,RespuestaC,RespuestaD,Correcta);
               preguntas.add(preg);
               contadorLineas = 1;   
               numPregunta++;
               continue; 
            }
            if (contadorLineas == 2) {
                    Enunciado = linea;
                    System.out.println("Enunciado: "+Enunciado);
                }
            if (contadorLineas == 4) {
                    RespuestaA = linea;
                    System.out.println("OpcionA "+ RespuestaA);
                }
            if (contadorLineas == 5) {
                    RespuestaB = linea;
                    System.out.println("Opcion B: "+RespuestaB);
                }
            if (contadorLineas == 6) {
                    RespuestaC = linea;
                    System.out.println("Opcion C: "+RespuestaC);
                }
            if (contadorLineas == 7) {
                    RespuestaD = linea;
                    System.out.println("Opcion D: "+RespuestaD);
                }
            if (contadorLineas == 9) {
                    Correcta = linea;
                    System.out.println("Correcta "+Correcta);
                }

                contadorLineas++;
            }
        }//System.out.println("termine ultima linea ->"+line);
        catch (FileNotFoundException ex) {
            System.out.println("Archivo " + nomExamen + " no encontrado");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar el FileReader");
            }
        }
        return preguntas;
    }
    
}   

            
