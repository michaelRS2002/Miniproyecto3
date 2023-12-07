package Modelo;

import java.io.Serializable;

/**
 *
 * @author nicol
 */
public class Respuesta implements Serializable {
    private int numPregunta;
    private String opcionSeleccionada;

    public Respuesta(int numPregunta, String opcionSeleccionada) {
        this.numPregunta = numPregunta;
        this.opcionSeleccionada = opcionSeleccionada;
    }
    public int getnumPregunta(){
        return numPregunta;
    }
    public String getOpcionSeleccionada(){
        return opcionSeleccionada;
    }
    
}
