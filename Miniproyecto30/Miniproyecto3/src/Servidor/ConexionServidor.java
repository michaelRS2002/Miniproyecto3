package Servidor;

import Modelo.ClaseArchivo;
import Modelo.Pregunta;
import Vista.GUIMiniProyecto3;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Clase utilizada para crear el servidor y permitir a los usuarios conectarse
 * @author Michael
 */
public class ConexionServidor extends Thread {
    private ServerSocket servidor;
    private GUIMiniProyecto3 gui;
    private ClaseArchivo arc;
    private int contador = 0;
    private CountDownLatch clientesListos;
    private HiloCliente[] clientes;
    private MulticastSocket multicastSocket;
    private InetAddress multicastGroup;
    private static final int MULTICAST_PORT = 9876;

    public ConexionServidor(GUIMiniProyecto3 gui, int port) {
        this.gui = gui;
        this.clientes = new HiloCliente[3];
        clientesListos = new CountDownLatch(3);

        try {
            this.gui.mostrarMensaje("Conectando por el puerto " + port + ".\nEspere por favor...............\n");
            servidor = new ServerSocket(port, 3);
            multicastSocket = new MulticastSocket();
            multicastGroup = InetAddress.getByName("230.0.0.0");

            this.gui.mostrarMensaje("Servidor Examenes conectados iniciado " + servidor + "\n");
            start();
        } catch (IOException ioe) {
            System.out.println("Error al crear ServerSocket o MulticastSocket");
        }
    }

    @Override
    public void run() {
        try {
            while (contador < 3) {
                gui.mostrarMensaje("Esperando un cliente........\n");
                Socket clienteSocket = servidor.accept();
                adicionarCliente(clienteSocket);
                clientesListos.countDown();
                gui.mostrarMensaje("Conexion exitosa. Cliente " + contador + " conectado.\n");
                int restantes = 3 - contador;
                if (restantes != 0) {
                    gui.mostrarMensaje("Faltan " + restantes + " estudiantes por acceder.\n");
                } else {
                    gui.mostrarMensaje("Empezando Examen.\n");
                    //notificarInicioExamen(); // Notificar inicio del examen cuando se conectan los 3 clientes
                    mostrarPreguntas();
                }
            }
            
        } catch (IOException ex) {
            System.out.println("Error al aceptar clientes");
        }
    }

    private void adicionarCliente(Socket socket) {
        try {
            contador++;
            gui.mostrarMensaje("Cliente No " + contador + " conectado!");
            HiloCliente cliente = new HiloCliente(gui, socket, clientesListos, contador, multicastGroup, MULTICAST_PORT);
            clientes[contador - 1] = cliente;
            cliente.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notificarInicioExamen() {
        enviarMensajeMulticast("EMPIECE");
    }

    public void enviarMensajeMulticast(String mensaje) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(mensaje);
            objectOutputStream.flush();
            objectOutputStream.close();
            byte[] buffer = mensaje.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastGroup, MULTICAST_PORT);
            multicastSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void enviarPreguntasMulticast(ArrayList<Pregunta> preguntas) {
    try {
        //enviarMensajeMulticast("PREGUNTAS");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(preguntas);
        oos.flush();
        oos.close();
        byte[] serializedPreguntas = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(serializedPreguntas, serializedPreguntas.length, multicastGroup, MULTICAST_PORT);
        multicastSocket.send(packet);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void mostrarPreguntas() {
        // Obtener preguntas desde tu lógica (asumamos que las preguntas son objetos Pregunta)
        ArrayList<Pregunta> preguntas = arc.PreguntasExamenes(gui.getExamenMandar());
        enviarPreguntasMulticast(preguntas);
        
    }/*
    
    */
}
