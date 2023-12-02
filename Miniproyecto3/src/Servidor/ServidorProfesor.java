
/**
 * Clase pendiente de si borrar o no
 */

package Servidor;
import Vista.GUIMiniProyecto3;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ServidorProfesor {
    
    static ServerSocket servidor;
    static int contador = 1;
    static ObjectOutputStream salida;
    static ObjectInputStream entrada;
    static Socket conexion;
    
    static GUIMiniProyecto3 guiS;
    
    public static void ejecutarServidor(){
             //guiS = new GUIMiniProyecto3();
       try {
           servidor = new ServerSocket (1000, 2);
           while (true){
           esperarConexion();
           obtenerFlujo();
           procesarConexion();
           }
       } catch(IOException ex){
       }
       
       
       
        
    
    }
    
    public static void  esperarConexion() throws IOException
            {
    
                guiS.mostrarMensaje("Esperando conexión  \n");
        conexion = servidor.accept(); 
        guiS.mostrarMensaje("Conexión éxitosa, Cliente" +contador + "Conectado");
    }
    
    public static void obtenerFlujo() throws IOException{
    
        salida = new ObjectOutputStream(conexion.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(conexion.getInputStream());
        guiS.mostrarMensaje("Se obtuvieron flujo para la comunicación");
    }
    
    
    
    public static void procesarConexion() throws IOException{
    
        String mensaje = "Conexión éxitosa";
        enviarDatos(mensaje);

    }
    
    public static void enviarDatos(String mensje){
    
        try{
        salida.writeObject("SERVIDOR>>>"+mensje);
        salida.flush();
        guiS.mostrarMensaje("SERVIDO>>>"+mensje);
    } catch(IOException ice){
    guiS.mostrarMensaje("\n Error al escribir objeto");
    }
        
    }
    
}

