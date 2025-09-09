// Archivo: src/com/konnekt/controlador/KonnektApp.java
package com.konnekt.controlador;


/*import com.konnekt.vista.VistaConsola;*/
import com.konnekt.vista.LoginFrame;
import javax.swing.UIManager;

/**
 * Clase Controladora Principal: Inicia la aplicación y maneja el flujo principal.
 * Contiene el bucle del menú y responde a las acciones del usuario,
 * coordinando la Vista y el Servicio (Konnekt).
 */
public class KonnektApp {

    public static void main(String[] args) {
        // Es una buena práctica establecer un "Look and Feel" para que se vea mejor
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo establecer el Look and Feel del sistema.");
        }
        
        // Creamos la instancia central de nuestro servicio
        Konnekt konnekt = new Konnekt();
        
        // Iniciamos la aplicación mostrando la ventana de Login
        // Es buena práctica iniciar las GUIs de Swing de esta manera (en el Event Dispatch Thread)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame(konnekt).setVisible(true);
            }
        });
    }
}