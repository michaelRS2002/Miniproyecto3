
package Modelo;

import java.awt.event.ActionEvent;

/**
 *Logica del timer de examen
 * @author usuario
 */
public class Timer 
{
        private javax.swing.Timer mytimer;
        private int tiempoDeExamen;
        public void iniciarTimer(int tiempoExamen)
        {
            mytimer = new javax.swing.Timer(1000, (ActionEvent e) -> {
            if (tiempoDeExamen > 1) {
                tiempoDeExamen--;
            } else {
                // Cuando tiempoDeExamen llega a 0, detener el temporizador
                mytimer.stop(); //Funcion por realizar
                   }
            });
        }
        
        

       
}
