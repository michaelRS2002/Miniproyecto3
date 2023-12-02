/*
package Modelo;

import Vista.GUIServidor;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;


/*
 * 
 * @author Michael Ramirez Suriel
 * @author Juan Fernando Calle
 * @author Jose Adrian Marin
 * @author Juan David Pinto
 */
/*
public class LogicaServidor 
{
    static ServerSocket servidor;
    static int contador = 1;
    static ObjectOutputStream salida;
    static ObjectInputStream entrada;
    static Socket conexion;
   // static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    
    static GUIServidor gui;
     public static void ejecutarServidor() {
        gui = new GUIServidor();
        try {
            servidor = new ServerSocket(12345);

            while (true) 
            {
                esperarConexion();
                Thread clienteHandler = new Thread(() -> manejarConexion(conexion));
                clienteHandler.start();
            }
        } catch (IOException ex) {
            System.out.println("Ocurrió un error con el servidor: " + ex.getMessage());
        }
    }
      private static void manejarConexion(Socket clienteSocket) {
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        try {
            gui.mostrarMensaje("Conexión exitosa. Cliente " + contador + " conectado.");

            // Obtener flujos para la comunicación
            salida = new ObjectOutputStream(clienteSocket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(clienteSocket.getInputStream());

            // Procesar la conexión
            procesarConexion(entrada, salida);

        } catch (IOException ex) {
            System.out.println("Error en la comunicación con el cliente: " + ex.getMessage());
        } finally {
            cerrarConexion(salida, entrada, clienteSocket);
            contador++;
        }
    }
    
    
    public static void esperarConexion() throws IOException
    {
        gui.mostrarMensaje("Esperando una conexión........\n");
        conexion = servidor.accept();
        gui.mostrarMensaje("Conexion exitosa.  Cliente "+contador + "conectado.");
    }
    public static void obtenerFlujos() throws IOException
    {
        salida = new ObjectOutputStream(conexion.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(conexion.getInputStream());
        gui.mostrarMensaje("Se obtuvieron flujos para la comunicación");
    }
    
    public static void procesarConexion() throws IOException
    {
        String mensaje = "Conexión exitosa";
        enviarDatos(mensaje);
        gui.habiliarCampo(true);
        do
        {
            try {
                mensaje = (String) entrada.readObject();
                gui.mostrarMensaje("\n"+mensaje);
                
            } catch (ClassNotFoundException ex) {
                gui.mostrarMensaje("se recibió un tipo de dato incorrecto desde el cliente");
            }
            catch(SocketException se)
            {
                mensaje = "CLIENTE>>> TERMINAR";
            }
            
            
        }while(!mensaje.equals("CLIENTE>>> TERMINAR"));
        
    }
    
    public static void enviarDatos(String mensja)
    {
        try {
            salida.writeObject("SERVIDOR>>> "+mensja);
            salida.flush();
            gui.mostrarMensaje("SERVIDOR>>> "+mensja);
        } catch (IOException ioe) {
            gui.mostrarMensaje("\n Error al escribir objeto");
        }
    }
    
    /**
     * cerrar los flujos y el socket que representa al cliente
     */
/*
    public static void cerrarConexion()
    {
        gui.mostrarMensaje("\n Teminamos conexion.");
        gui.habiliarCampo(false);
        
        try {
            salida.close();
            entrada.close();
            conexion.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void actuar(String mensajeDelCampo)
    {
        enviarDatos(mensajeDelCampo);
        gui.limpiarCampo();
    }
}
*/