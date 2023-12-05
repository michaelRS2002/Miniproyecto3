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
    private int contador;
    
    
    public HiloCliente(GUIMiniProyecto3 gui, Socket s,CountDownLatch clientesListos, int contador)
    {
        this.gui = gui;
        this.socket = s;
        this.clientesListos = clientesListos;
        this.contador= contador;
    }
    @Override
    public void run()
    {
        try {
            obtenerFlujos();
            esperarcompas(contador);
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
        String mensaje = "EMPIECE";
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
        try {
            salida.writeObject(preguntas);
            salida.flush();
            gui.mostrarMensaje("SERVIDOR>>> Examen Mandado");
        } catch (IOException ioe) {
            gui.mostrarMensaje("\nError al escribir objeto");
        }
    }

    public ArrayList<Pregunta> getPreguntasRecibidas() {
        return preguntasparaCliente;
    }
    public void esperarcompas(int resta) throws IOException{
        String mensajeEspera = "Espere por favor faltan:"+Integer.toString(3-resta)+"compañeros por ingresar\n";
        String mensajeEspera1 = "Espere por favor falta: "+Integer.toString(3-resta)+"compañero por ingresar\n";
        String listo = "Grupo Completo\nMuchos Exitos.\n";
        switch (3-resta) {
            case 0:
                salida.writeObject(listo);
                salida.flush();
               
            case 1:
                salida.writeObject(mensajeEspera1);
                salida.flush();
            default:
                salida.writeObject(mensajeEspera);
                salida.flush();
        }
        
    }
    

//fin procesar
    
    /**
     * cerrar los flujos y el socket que representa al cliente
     */    

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

