package Controlador;

import Modelo.Cliente;
import Vista.GUICliente;
import Modelo.Timer;


/**
 *
 * @author nicol
 */
public class Controlador {
    private GUICliente gui;
    private Cliente cli;
    private Timer time;
  
    
    public Controlador() {
        this.gui = new GUICliente();
        this.gui.EstablecerControlador(this);
        this.cli = new Cliente(gui);
        this.time = new Timer();
    }
    public void iniciarContador() 
    {
        //time.iniciarTimer();
    }
    
}
