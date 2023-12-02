package Modelo;

/**
 *
 * @author nicol
 */
public class Pregunta {
    private int numero;
    private String enunciado;
    private String respuestaA;
    private String respuestaB;
    private String respuestaC;
    private String respuestaD;
    private String correcta;
    /**
     * constructor de una Pregunta que recibe 6 parámetros
     * @param numero
     * @param enunciado
     * @param respuestaA
     * @param respuestaB
     * @param respuestaC
     * @param respuestaD
     * @param correcta
     */
    public Pregunta(int numero, String enunciado, String respuestaA, String respuestaB, String respuestaC, String respuestaD, String correcta)
    {
            this.numero = numero;
            this.enunciado = enunciado;
            this.respuestaA = respuestaA;
            this.respuestaB = respuestaB;
            this.respuestaC = respuestaC;
            this.respuestaD = respuestaD;
            this.correcta = correcta;
    }
    public int GetNumeroPregunta(){
        return numero;
    }
    public String GetEnunciado(){
        return enunciado;
    }
    public String GetRespuestaA(){
        return respuestaA;
    }
    public String GetRespuestaB(){
        return respuestaB;
    }
    public String GetRespuestaC(){
        return respuestaC;
    }
    public String GetRespuestaD(){
        return respuestaD;
    }
    public String GetCorrecta(){
        return correcta;
    }
    
}
    
    
    

