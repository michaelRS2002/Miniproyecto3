package Servidor;

import Vista.GUIMiniProyecto3;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.CountDownLatch;


/*
 * Esta Clase contiene tiene la Clase que define metodos y atributos de la clase
 * que van a ser llamados desde la logica del servidor
 * @author Michael Ramirez Suriel
 * @author Juan Fernando Calle
 * @author Jose Adrian Marin
 * @author Juan David Pinto
 */
public class HiloCliente extends Thread
{
    ObjectOutputStream salida;
    ObjectInputStream entrada;
    Socket socket;
    GUIMiniProyecto3 gui;
    
    
    public HiloCliente(GUIMiniProyecto3 gui, Socket s)
    {
        this.gui = gui;
        this.socket = s;
    }
    @Override
    public void run()
    {
        try {
            obtenerFlujos();
            procesarConexion();
        } catch (IOException ex) {
            System.out.println("error al procesar la comunicacion con el cliente");
        }finally {
            cerrarConexion();
        }
    }
    
    public void obtenerFlujos() throws IOException
    {
        salida = new ObjectOutputStream(socket.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(socket.getInputStream());
        gui.mostrarMensaje("Se obtuvieron flujos para la comunicación");
    }
    
    public void enviarDatos(String mensja)
    {
        try {
            salida.writeObject("SERVIDOR>>> "+mensja);
            salida.flush();
            gui.mostrarMensaje("SERVIDOR>>> "+mensja);
        } catch (IOException ioe) {
            gui.mostrarMensaje("\n Error al escribir objeto");
        }
    }
    
    public void procesarConexion() throws IOException
    {
        String mensaje = "Conexión exitosa";
        //double resultado;
        enviarDatos(mensaje);
        do
        {
            try {
                mensaje = (String) entrada.readObject();
                gui.mostrarMensaje("\nDESDE EL CLIENTE: "+mensaje);
                
              /*  if(operar.procesarEntrada(mensaje) == true)
                {
                    resultado = operar.operar();
                    gui.mostrarMensaje(""+resultado);
                    enviarDatos(""+resultado);
                }*/
                
            } catch (ClassNotFoundException ex) {
                gui.mostrarMensaje("se recibió un tipo de dato incorrecto desde el cliente");
            }
            catch(SocketException se)
            {
                mensaje = "TERMINAR";
            }
            
            
        }while(!mensaje.equals("TERMINAR"));
        
    }//fin procesar
    
    /**
     * cerrar los flujos y el socket que representa al cliente
     */
    public void cerrarConexion()
    {
        gui.mostrarMensaje("\n Teminamos conexion.");
        try {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}

