package Modelo;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class Logica {
    public Logica(){
    }
    public static  ArrayList<String> obtenerArchivosTxt(String directorio) {
        ArrayList<String> archivos = new ArrayList<>();
        File folder = new File(directorio);
        File[] files = folder.listFiles();
        
        if (files != null) {
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                archivos.add(file.getName());
            }
        }
        }
        return archivos;
    }
}
