package Modelo;

import Vista.GUICliente;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase hecha para conectar al usuario con el servidor de la app
 * @author invitado
 */
public class Cliente {

    // Representación del cliente
    private Socket cliente;
    // Salida de datos
    private ObjectOutputStream salida;
    // Entrada de datos
    private ObjectInputStream entrada;
    private GUICliente gui;
    private ArrayList<Pregunta> lista;
    private MulticastSocket multicastSocket; // Nuevo: para multicast
    private InetAddress multicastGroup;
    private int multicastPort;

    /*
    Método principal para iniciar la conexión
    */
    public Cliente(GUICliente gui) {
        this.gui = gui;
        try {
            conectarAlServidor();
            obtenerFlujos();
            //esperarCompas();
            lista = recibirDatosMulticast();
            escribirEnunciados(lista);
            procesarConexion();
        } catch (IOException ioe) {
            gui.mostrarMensajes("Error en la conexión con el cliente");
        } finally {
            cerrarConexion();
        }
    }

    /*
    Su funcionalidad es conectarse al servidor
    */
    public void conectarAlServidor() throws IOException {
        gui.mostrarMensajes("Intentando establecer conexión...\n");
        cliente = new Socket("127.0.0.1", 12345);
        gui.mostrarMensajes("Conectado a: " + cliente.getInetAddress() + "\n");
    }

    public void obtenerFlujos() throws IOException {
        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(cliente.getInputStream());
        gui.mostrarMensajes("Se obtuvieron los flujos E/S\n");
        System.out.println("epa");
        unirseGrupoMulticast();
    }
    private void unirseGrupoMulticast() throws IOException {
        multicastGroup = InetAddress.getByName("230.0.0.0"); // Dirección IP del grupo multicast
        multicastPort = 9876; // Puerto del grupo multicast
        multicastSocket = new MulticastSocket(multicastPort);
        multicastSocket.joinGroup(multicastGroup);
    }
    public ArrayList<Pregunta> recibirDatosMulticast() {
    int contador = 1;
    ArrayList<Pregunta> preguntas = null;

    do {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("Holaaa me ejecuté");
            multicastSocket.receive(packet);
            ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
            ObjectInputStream ois = new ObjectInputStream(bis);
            System.out.println("Holaaa me ejecuté");

            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
            preguntas = (ArrayList<Pregunta>) obj;
            } else {
            System.out.println("El objeto recibido no es de tipo ArrayList<Pregunta>.");
            }
            // Puedes agregar algún código adicional si es necesario antes de salir del bucle
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        } while (preguntas == null); // Continuar hasta que se reciban las preguntas

    return preguntas;
}


    /*
    Método para recibir datos multicast
    */
    
    public void esperarCompas() throws IOException {
        String mensaje = "Conexión exitosa al examen\n";
        gui.mostrarMensajes(mensaje);
        do {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("Holaaa me ejecuté321");
                multicastSocket.receive(packet);
                ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
                ObjectInputStream ois = new ObjectInputStream(bis);
                mensaje = (String) ois.readObject();
                if (mensaje.equals("EMPIECE")) {
                    gui.crearGUI5();
                    if (gui.getJClienteS() != null) {
                        gui.BoolGuiServer(true);
                    }
                    gui.revalidate();
                    System.out.println("ASI ME VOYY");
                    
                }
            } catch (ClassNotFoundException ex) {
                gui.mostrarMensajes("Error: tipo de dato incorrecto");
            }
        } while (!mensaje.equals("SERVIDOR>>> EMPIECE"));
        System.out.println("ASI ME VOYY");
    }

    /**
     * Método para leer los mensajes recibidos
     *
     * @throws IOException
     */
    public void procesarConexion() throws IOException {
        String mensaje = "";
        escribirEnunciados(recibirDatosMulticast());
        System.out.println("Epaaa");
        gui.mostrarMensajes("Holaaaa");
        
        do {
            try {
                mensaje = (String) entrada.readObject();
                
                
            } catch (ClassNotFoundException ex) {
                gui.mostrarMensajes("Error: tipo de dato incorrecto");
            }
        } while (!mensaje.equals("TERMINAR"));
    }

    public ArrayList<Pregunta> getLista() {
        return lista;
    }

    public void cerrarConexion() {
        try {
            salida.close();
            entrada.close();
            cliente.close();
        } catch (IOException ex) {
            gui.mostrarMensajes("Error al cerrar la conexión");
            ex.printStackTrace();
        }
    }

    public void enviarDatos(String mensaje) {
        try {
            salida.writeObject("CLIENTE>>> " + mensaje);
            salida.flush();
        } catch (IOException ex) {
            gui.mostrarMensajes("Error al mandar datos al servidor");
            ex.printStackTrace();
        }
    }

    public void escribirEnunciados(ArrayList<Pregunta> lis) {
        ArrayList<Pregunta> preguntas = lis;   
        

        File examen = new File("src\\Controlador\\Examen.txt");
        gui.crearGUI5();

        if (examen.exists()) {
            System.out.println("El archivo ya existe.");
            return;
        }
        try (FileWriter fw = new FileWriter("src\\Controlador\\Examen.txt", true)) {
            // Escribe en el archivo
            for (int i = 1; i < preguntas.size(); i++) {
                Pregunta pregunta = preguntas.get(i);
                fw.append("Pregunta N° " + i + "\n");
                fw.append(pregunta.GetEnunciado() + "\n");
                fw.append("-------Opciones----------------\n");
                fw.append(pregunta.GetRespuestaA() + "\n");
                fw.append(pregunta.GetRespuestaB() + "\n");
                fw.append(pregunta.GetRespuestaC() + "\n");
                fw.append(pregunta.GetRespuestaD() + "\n");
                fw.append("-------Correcta----------------\n");
                fw.append(pregunta.GetCorrecta() + "\n");
                fw.append("/////////////////////////////////////////////\n");
            }
        } catch (IOException ex) {
            System.out.println("No encontró el archivo a escribir");
        }
    }
}
