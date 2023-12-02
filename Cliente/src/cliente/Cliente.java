
package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *  Clase hecha para poder conectar al usuario con el servidor de la app
 * @author invitado
 */
public class Cliente {
    
    //Representación del cliente
    Socket cliente;
    //Salida de datos
    ObjectOutputStream salida;
    //Entrada de datos
    ObjectInputStream entrada;
    
/*
    Método principal para poder iniciar la conexion
    */
public void ejecutarCliente()
    {
        try
        {
            conectarAlServidor();
            obtenerFlujos();
            procesarConexion();
        }catch(IOException ioe){
            //mostrarMensaje("error en la conexion con el cliente");
        }finally{
            cerrarConexion();
        }
    }
/*
    Su funcionalidad es conectarse al servidor
*/
public void conectarAlServidor() throws IOException
    {
        //mostrarMensaje("Intentando establecer conexion.....");
        cliente = new Socket("127.0.0.1", 12345);
        //mostrarMensaje("Conectado a: "+cliente.getInetAddress());
    }
/**
 * Método para obtener y enviar datos
 */
public void obtenerFlujos() throws IOException
    {
        salida = new ObjectOutputStream(cliente.getOutputStream());
        //Mandar los flujos
        salida.flush();
        entrada = new ObjectInputStream(cliente.getInputStream());
        //mostrarMensaje("Se obtuvieron los flujos E/S");
    }
/**
 * Método para leer los mensajes recibidos
 * @throws IOException 
 */
public void procesarConexion() throws IOException
    {
        String mensaje = "";
        //campoIntroducir.setEnabled(true);
        do
        {
            try {
                mensaje = (String) entrada.readObject();
                //mostrarMensaje("\n"+mensaje);
            } catch (ClassNotFoundException ex) {
                //mostrarMensaje("error tipo de dato incorrecto");
            }
            
        }while(!mensaje.equals("SERVIDOR>>> TERMINAR"));
    }
    /**
     * Su funciån es que al terminar la conexion cerras los flujos 
     */
    public void cerrarConexion()
    {
        //mostrarMensaje("cerrando conexion...");
        try {
            salida.close();
            entrada.close();
            cliente.close();
            //campoIntroducir.setEnabled(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Envio de datos al servidor
     * @param mensaje 
     */
    public void enviarDatos(String mensaje)
    {
        try {
            salida.writeObject("CLIENTE>>> "+mensaje);
            salida.flush();
            //mostrarMensaje("CLIENTE>>> "+mensaje);
        } catch (IOException ex) {
            //mostrarMensaje("Error al mandar datos al servidor");
        }
        
    }
   
}


