package Servidor;

import Servidor.HiloCliente;
import Vista.GUIMiniProyecto3;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionServidor extends Thread {
    private ServerSocket servidor;
    private GUIMiniProyecto3 gui;
    private int contador = 0;
    private HiloCliente[] clientes;

    public ConexionServidor(GUIMiniProyecto3 gui, int port) {
        this.gui = gui;
        this.clientes = new HiloCliente[3];

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
                gui.mostrarMensaje("Conexion exitosa. Cliente " + contador + " conectado.\n");
                int restantes = 3-contador;
                if (restantes !=0){
                    gui.mostrarMensaje("Faltan "+restantes+" estudiantes por acceder.\n");
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
            HiloCliente cliente = new HiloCliente(gui, socket);
            clientes[contador - 1] = cliente;
            cliente.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


