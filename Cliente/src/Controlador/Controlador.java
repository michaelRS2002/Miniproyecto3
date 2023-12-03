package Controlador;

import Modelo.Cliente;
import Vista.GUICliente;

/**
 *
 * @author nicol
 */
public class Controlador {
    private GUICliente gui;
    private Cliente cli;
    
    public Controlador() {
        this.gui = new GUICliente();
        this.gui.EstablecerControlador(this);
        this.cli = new Cliente(gui);
    }
    
}
