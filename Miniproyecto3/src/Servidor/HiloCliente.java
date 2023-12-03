package Servidor;

import Modelo.Pregunta;
import Vista.GUIMiniProyecto3;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    private CountDownLatch clientesListos;
    
    private ArrayList<Pregunta> preguntasparaCliente;
    
    
    public HiloCliente(GUIMiniProyecto3 gui, Socket s,CountDownLatch clientesListos)
    {
        this.gui = gui;
        this.socket = s;
        this.clientesListos = clientesListos;
    }
    @Override
    public void run()
    {
        try {
            obtenerFlujos();
            clientesListos.await();
            procesarConexion();
        } catch (IOException ex) {
            System.out.println("error al procesar la comunicacion con el cliente");
        } catch (InterruptedException ex) {
            System.out.println("Hilo Interrumpido");
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
    
    public void enviarDatosString(String mensja)
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
        enviarDatosString(mensaje);
       
        
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
        
    }
  public void recibirPreguntas(ArrayList<Pregunta> preguntas) {
        // Almacena las preguntas recibidas en la lista local
        preguntasparaCliente = preguntas;
        try {
            salida.writeObject(preguntasparaCliente);
            salida.flush();
            gui.mostrarMensaje("SERVIDOR>>> Examen Mandado");
        } catch (IOException ioe) {
            gui.mostrarMensaje("\nError al escribir objeto");
        }
    }

    public ArrayList<Pregunta> getPreguntasRecibidas() {
        return preguntasparaCliente;
    }
    

//fin procesar
    
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

