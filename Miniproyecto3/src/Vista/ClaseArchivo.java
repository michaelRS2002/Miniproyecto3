package Vista;

import java.io.*;
//import java.util.Scanner;
/**
 *
 * @author Asus Michael
 */
public class ClaseArchivo {
    

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
                fw.append("-----------------------------------------------------\n");
            }
        } catch (IOException ex) {
            System.out.println("no encontró el archivo a escribir");
        } 
    }
 /*   
    public static void leerScanner()
    {
        try {
            Scanner sc = new Scanner(new File("src\\prueba.txt"));
            while(sc.hasNext())
            {
                System.out.println(sc.nextInt());
                
            }
        } catch (FileNotFoundException ex) {
            System.out.println("no encontré el archivo");
        }
    }
    public static void main(String[] args) 
    {
        leerScanner();
        //escribirEnArchivo();
        System.out.println("esto es lo del FileReader");
        FileReader fr = null;
        try {
            File miArchivo = new File("src\\prueba.txt");
            fr = new FileReader (miArchivo);
            BufferedReader br = new BufferedReader(fr);
            /*int num;
            while((num = fr.read())!= -1){
                System.out.println((char)num);
            }
            
            String linea;
            while((linea=br.readLine())!=null){
                System.out.println(linea);
                System.out.println("voy a imprimir las palabras");
                String[] pal = linea.split(" ");
                for(int j = 0; j < pal.length; j++)
                    System.out.print(pal[j]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No encontré el archivo");
        } catch (IOException ex) {
            System.out.println("no pude leer la línea");
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                System.out.println("tuve problemas cerrando el flujo");
            }
        }

        
    }*/
}