package Servidor;

import Modelo.Pregunta;
import Modelo.Respuesta;
import Vista.GUIMiniProyecto3;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase utilizada para manejar la comunicación con un cliente
 */
public class HiloCliente extends Thread {
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket socket;
    private MulticastSocket multicastSocket; // Nuevo: para multicast
    private GUIMiniProyecto3 gui;
    private CountDownLatch clientesListos;
    private ArrayList<Pregunta> preguntasParaCliente;
    private int contador;
    private InetAddress multicastGroup;
    private int multicastPort;

    public HiloCliente(GUIMiniProyecto3 gui, Socket s, CountDownLatch clientesListos, int contador, InetAddress multicastGroup, int multicastPort) {
        this.gui = gui;
        this.socket = s;
        this.clientesListos = clientesListos;
        this.contador = contador;
        this.multicastGroup = multicastGroup;
        this.multicastPort = multicastPort;
        this.preguntasParaCliente = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            obtenerFlujos();
            //esperarCompas(contador);
            unirseGrupoMulticast();
            clientesListos.await();
            procesarConexion();
        } catch (IOException ex) {
            gui.mostrarMensaje("Error al procesar la comunicación con el cliente");
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void obtenerFlujos() throws IOException {
        salida = new ObjectOutputStream(socket.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(socket.getInputStream());
        gui.mostrarMensaje("Se obtuvieron flujos para la comunicación");
    }

    // Nueva función para unirse al grupo multicast
    private void unirseGrupoMulticast() throws IOException {
        multicastSocket = new MulticastSocket(multicastPort);
        multicastSocket.joinGroup(multicastGroup);
    }

    // Nueva función para enviar datos al grupo multicast
    public void enviarDatosMulticast(String mensaje) {
        try {
            byte[] buffer = mensaje.getBytes();
            multicastSocket.send(new DatagramPacket(buffer, buffer.length, multicastGroup, multicastPort));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarDatosString(String mensaje) {
        try {
            salida.writeObject("SERVIDOR>>> " + mensaje);
            salida.flush();
            gui.mostrarMensaje("SERVIDOR>>> " + mensaje);

            // Nuevo: enviar también al grupo multicast
            //enviarDatosMulticast(mensaje);
        } catch (IOException ioe) {
            gui.mostrarMensaje("\nError al escribir objeto");
        }
    }
     public void procesarConexion() throws IOException {
        String mensaje = "EMPIECE";
        //enviarDatosString(mensaje);

        do {
            try {
                mensaje = (String) entrada.readObject();
                gui.mostrarMensaje("\nDESDE EL CLIENTE: " + mensaje);
                ObjectInputStream entradaA = new ObjectInputStream(socket.getInputStream());
                // Leer el objeto serializado
                Object objetoRecibido = entradaA.readObject();

                // Verificar el tipo del objeto
                if (objetoRecibido instanceof ArrayList<?>) {
                    @SuppressWarnings("unchecked")
                    ArrayList<Respuesta> listaRespuestas = (ArrayList<Respuesta>) objetoRecibido;
                    System.out.println("LLEGO respuesta");
                }
            } catch (ClassNotFoundException ex) {
                gui.mostrarMensaje("Se recibió un tipo de dato incorrecto desde el cliente");
            } catch (SocketException se) {
                mensaje = "TERMINAR";
            }

        } while (!mensaje.equals("TERMINAR"));
    }

    public void esperarCompas(int resta) throws IOException {
        String mensajeEspera = "Espere por favor faltan: " + (3 - resta) + " compañeros por ingresar\n";
        String mensajeEspera1 = "Espere por favor falta: " + (3 - resta) + " compañero por ingresar\n";
        String listo = "Grupo Completo\nMuchos Éxitos.\n";

        switch (3 - resta) {
            case 0:
                enviarDatosString(listo);
                
                break;
            case 1:
                salida.writeObject(mensajeEspera1);
                salida.flush();
                break;
            default:
                salida.writeObject(mensajeEspera);
                salida.flush();
                break;
        }
    }

    public void cerrarConexion() {
        gui.mostrarMensaje("\nTerminamos conexión.");
        try {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
