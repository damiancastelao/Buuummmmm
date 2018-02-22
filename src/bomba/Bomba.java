package bomba;

import java.awt.Toolkit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 * Bomba que explota a la de tres, mientras gritamos Ahhhhhhh
 * @author damian
 */
public class Bomba {
    // Nos sirve para lanzar una tarea periodicamente y con retardo
    Timer timer;
    // un artibuto de la clase principal
    String ruido="Buuummm";

    public static void main(String[] args) throws InterruptedException {
        //Mensaje inicial
        System.out.println("Enciendo la mecha...");
        // Para ejecutar el constructor 
        new Bomba();
        // Proceso dentro del hilo principal
        // Grito Ahhhh cada 200msg
        for (int i=0;i<15;i++) {
            System.out.println("Ahhhh");
            Thread.sleep(200);
        }
    }
    /**
     * Constructor de la clase principal
     */
    public Bomba() {
        //creamos un Timer
        timer = new Timer();
        //con el Timer ejecutamos la tarea TicTac, con un retardo de 0sg y repetimos cada 1sg
        timer.schedule(new TicTac(), 0, 1000);
    }

    /**
     * Inner class para la tarea en segundo plano (hilo)
     * Extiende de TimerTask para poder ejecutarlo desde el Timer
     */
    public class TicTac extends TimerTask {
        //Un atributo de esta clase, la cuenta atras
        Integer numBeeps = 3;
        // para poder usar el altavoz del ordenador
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        /**
         * Método que se ejecuta cuando lanzamos la tarea con el Timer
         * Es un método definido en la clase TimerTask que sobrescribimos
         */
        @Override
        public void run() {
            if (numBeeps > 0) {
                // muestro los beep, la cuenta atras y la fecha
                System.out.println("Beep! " + numBeeps + " " + new Date());
                // descuento uno para la cuenta atras
                numBeeps--;
            } else {
                //Hacemos sonar el altavoz
                toolkit.beep();
                // la cuenta atras es cero, explota la bomba
                // utilizamos un tributo de la clase principal en la inner class
                System.out.println(ruido);
                //Terminamos la tarea, matamos el hilo
                //El objeto timer es de la clase principal
                timer.cancel();
                timer.purge();
            }      
        }
    }
}
