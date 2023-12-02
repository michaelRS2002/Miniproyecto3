package Servidor;

import Modelo.ClaseArchivo;
import Modelo.Pregunta;
import Servidor.HiloCliente;
import Vista.GUIMiniProyecto3;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ConexionServidor extends Thread {
    private ServerSocket servidor;
    private GUIMiniProyecto3 gui;
    private ClaseArchivo arc;
    private int contador = 0;
    private CountDownLatch clientesListos;
    private HiloCliente[] clientes;

    public ConexionServidor(GUIMiniProyecto3 gui, int port) {
        this.gui = gui;
        this.clientes = new HiloCliente[3];
        clientesListos= new CountDownLatch(3);

        try {
            this.gui.mostrarMensaje("Conectando por el puerto " + port + ".\nEspere por favor...............\n");
            servidor = new ServerSocket(port);
            this.gui.mostrarMensaje("Servidor Examenes conectados iniciado " + servidor +"\n");
            start();
        } catch (IOException ioe) {
            System.out.println("Error al crear ServerSocket");
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
                int restantes = 3-contador;
                if (restantes !=0){
                    gui.mostrarMensaje("Faltan "+restantes+" estudiantes por acceder.\n");
                }else{
                    gui.mostrarMensaje("Empezando Examen.\n");
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
            HiloCliente cliente = new HiloCliente(gui, socket,clientesListos);
            clientes[contador - 1] = cliente;
            cliente.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mostrarPreguntas() 
    {
        // Obtener preguntas desde tu lógica (asumamos que las preguntas son objetos Pregunta)
        ArrayList<Pregunta> preguntas = arc.PreguntasExamenes(gui.getExamenMandar());

        // Enviar preguntas a cada cliente
        for (int i = 0; i < 3; i++) {
            if (clientes[i] != null) {
                clientes[i].recibirPreguntas(preguntas);
            }
        }
    }

    
}


