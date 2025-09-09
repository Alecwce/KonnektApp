// Archivo: src/com/konnekt/vista/VistaConsola.java
package com.konnekt.vista;

import com.konnekt.modelo.*;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Vista: se encarga de toda la interacción con el usuario.
 * Imprime menús y resultados, y lee las entradas del teclado.
 * No contiene lógica de negocio, solo de presentación.
 */
public class VistaConsola {

    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public String leerEntrada(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL DE KONNEKT =====");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrar Nuevo Usuario");
        System.out.println("0. Salir de la Aplicación");
        try {
            return Integer.parseInt(leerEntrada("Elige una opción: "));
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }
    
    public int mostrarMenuUsuario(String nombreUsuario) {
        System.out.println("\n===== MENÚ DE @" + nombreUsuario + " =====");
        System.out.println("1. Ver mi Feed");
        System.out.println("2. Crear una Publicación");
        System.out.println("3. Ver mi Perfil");
        System.out.println("4. Ver mis Notificaciones");
        System.out.println("5. Buscar y Seguir a un Usuario");
        System.out.println("0. Cerrar Sesión");
        try {
            return Integer.parseInt(leerEntrada("Elige una opción: "));
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }
    
    public void mostrarPerfil(Usuario usuario) {
        usuario.mostrarPerfil(); // Reutilizamos el método del modelo por simplicidad
    }
    
    public void mostrarFeed(List<Publicacion> feed) {
         if (feed.isEmpty()) {
            System.out.println("Tu feed está vacío. ¡Sigue a alguien para ver sus publicaciones!");
        } else {
            System.out.println("\n<<<<<<<<<<<< TU FEED >>>>>>>>>>>>");
            for (Publicacion p : feed) {
                p.mostrar();
            }
            System.out.println("<<<<<<<<<< FIN DEL FEED >>>>>>>>>>\n");
        }
    }
    
    public void mostrarNotificaciones(List<Notificacion> notificaciones) {
        System.out.println("\n🔔 TUS NOTIFICACIONES 🔔");
        if(notificaciones.isEmpty()){
            System.out.println("No tienes notificaciones nuevas.");
        } else {
            // Mostrar de la más reciente a la más antigua
            for(int i = notificaciones.size() - 1; i >= 0; i--){
                notificaciones.get(i).mostrar();
            }
        }
        System.out.println("-----------------------------------------\n");
    }
    
    public void mostrarUsuarios(List<Usuario> usuarios) {
        System.out.println("\n--- Usuarios Registrados ---");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". @" + usuarios.get(i).getNombreUsuario());
        }
    }
}