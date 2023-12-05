
package Modelo;

import Vista.GUICliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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
    private GUICliente gui;
/*
    Método principal para poder iniciar la conexion
    */
public Cliente(GUICliente gui)
{
        this.gui = gui;
        try
        {
            conectarAlServidor();
            obtenerFlujos();
            esperarCompas();
            procesarConexion();
        }catch(IOException ioe){
            gui.mostrarMensajes("error en la conexion con el cliente");
        }finally{
            cerrarConexion();
        }
    }
/*
    Su funcionalidad es conectarse al servidor
*/
public void conectarAlServidor() throws IOException
    {
        gui.mostrarMensajes("Intentando establecer conexion....\n");
        cliente = new Socket("127.0.0.1", 12345);
        gui.mostrarMensajes("Conectado a: "+cliente.getInetAddress()+"\n");
    }

 /*public void recibirPreguntas() throws IOException, ClassNotFoundException {
        Object receivedObject = entrada.readObject();
        if (receivedObject instanceof ArrayList<Pregunta> receivedList) {
            ArrayList<Pregunta> arrayList = (ArrayList<Pregunta>) receivedObject;
            if (!receivedList.isEmpty() && receivedList.get(0) instanceof Pregunta) {
                // Si el objeto recibido es un ArrayList de Pregunta
                ArrayList<Pregunta> preguntas = (ArrayList<Pregunta>) receivedList;
               
                for (Pregunta pregunta : preguntas) {
                    System.out.println(pregunta);
                }
              
            } else {
                System.out.println("El objeto recibido no es un ArrayList de Pregunta.");
            }
        } else {
            System.out.println("Se esperaba recibir un ArrayList.");
        }
    }

 * 
 * 
 * 
 * Método para obtener y enviar datos
 */
public void obtenerFlujos() throws IOException
    {
        salida = new ObjectOutputStream(cliente.getOutputStream());
        //Mandar los flujos
        salida.flush();
        entrada = new ObjectInputStream(cliente.getInputStream());
        gui.mostrarMensajes("Se obtuvieron los flujos E/S\n");
        
    }
 public void esperarCompas() throws IOException {
      String mensaje = "Conexión exitosa al examen\n";
      String mensajeEstado= "";
      String nom = gui.getNomUsuario();
      gui.mostrarMensajes(mensaje);
      do{
      try {
            mensaje = (String) entrada.readObject();
                if(mensaje.equals("SERVIDOR>>> EMPIECE")){
                    gui.crearGUI5();
                    if(gui.getJClienteS() != null){
                        gui.BoolGuiServer(true);
                    }
                    gui.revalidate();
                    //gui.BoolGuiServer(false);
                }
                //mensajeEstado = (String) entrada.readObject();
                //gui.mostrarMensajes(nom+mensajeEstado);
            }catch (ClassNotFoundException ex) {
                //mostrarMensaje("error tipo de dato incorrecto");
            }
      }while (!mensaje.equals("SERVIDOR>>> EMPIECE")) ;
         
     }
    /**
    * Método para leer los mensajes recibidos
    * @throws IOException 
    */
    public void procesarConexion() throws IOException
    {
        String mensaje ="";
        do {
        try {
            Object objetoRecibido = entrada.readObject();

            if (objetoRecibido instanceof ArrayList<?>) {
                // Verificar si es un ArrayList
                ArrayList<?> listaRecibida = (ArrayList<?>) objetoRecibido;

                // Verificar el tipo de elementos en el ArrayList
                if (listaRecibida.get(0) instanceof Pregunta && !listaRecibida.isEmpty()) {
                    // Si el objeto recibido es un ArrayList de String
                    ArrayList<Pregunta> listaPreguntas = (ArrayList<Pregunta>) listaRecibida;

                    // Procesar la lista de cadenas como desees
                    for (Pregunta pregunta : listaPreguntas) {
                            System.out.println("Número de pregunta: " + pregunta.GetNumeroPregunta());
                            System.out.println("Enunciado: " + pregunta.GetEnunciado());    
                        }
                } else {
                    System.out.println("El objeto recibido no es un ArrayList de String.");
                }
            } else {
                System.out.println("Se esperaba recibir un ArrayList.");
            }
        } catch (ClassNotFoundException ex) {
            gui.mostrarMensajes("Error: tipo de dato incorrecto");
        }
    } while (!mensaje.equals("TERMINAR"));
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


