// Archivo: src/com/konnekt/controlador/KonnektApp.java
package com.konnekt.controlador;

import com.konnekt.modelo.Publicacion;
import com.konnekt.modelo.Usuario;
import com.konnekt.vista.VistaConsola;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase Controladora Principal: Inicia la aplicación y maneja el flujo principal.
 * Contiene el bucle del menú y responde a las acciones del usuario,
 * coordinando la Vista y el Servicio (Konnekt).
 */
public class KonnektApp {

    private Konnekt konnekt;
    private VistaConsola vista;

    public KonnektApp() {
        this.konnekt = new Konnekt();
        this.vista = new VistaConsola();
    }

    public void iniciar() {
        vista.mostrarMensaje("¡Bienvenido a Konnekt! - La red social interactiva.");
        buclePrincipal();
        vista.mostrarMensaje("Gracias por usar Konnekt. ¡Hasta pronto!");
    }

    private void buclePrincipal() {
        int opcion;
        do {
            opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    manejarInicioSesion();
                    break;
                case 2:
                    manejarRegistro();
                    break;
                case 0:
                    break; // Salir
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);
    }
    
    private void bucleUsuario() {
        int opcion;
        do {
            opcion = vista.mostrarMenuUsuario(konnekt.getUsuarioActual().getNombreUsuario());
            switch (opcion) {
                case 1: // Ver Feed
                    verFeed();
                    break;
                case 2: // Crear Publicación
                    crearPublicacion();
                    break;
                case 3: // Ver Perfil
                    vista.mostrarPerfil(konnekt.getUsuarioActual());
                    break;
                case 4: // Ver Notificaciones
                    vista.mostrarNotificaciones(konnekt.getUsuarioActual().getNotificaciones());
                    break;
                case 5: // Buscar y Seguir
                    buscarYSeguirUsuario();
                    break;
                case 0: // Cerrar Sesión
                    konnekt.cerrarSesion();
                    vista.mostrarMensaje("Sesión cerrada.");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void manejarInicioSesion() {
        String nombreUsuario = vista.leerEntrada("Introduce tu nombre de usuario: ");
        if (konnekt.iniciarSesion(nombreUsuario)) {
            vista.mostrarMensaje("¡Bienvenido de vuelta, @" + nombreUsuario + "!");
            bucleUsuario(); // Entra al menú del usuario logueado
        } else {
            vista.mostrarMensaje("Error: Usuario no encontrado.");
        }
    }

    private void manejarRegistro() {
        String nombreUsuario = vista.leerEntrada("Elige un nombre de usuario: ");
        String biografia = vista.leerEntrada("Escribe una breve biografía: ");
        if (konnekt.registrarUsuario(nombreUsuario, biografia)) {
            vista.mostrarMensaje("¡Usuario @" + nombreUsuario + " registrado con éxito! Ahora puedes iniciar sesión.");
        } else {
            vista.mostrarMensaje("Error: Ese nombre de usuario ya existe.");
        }
    }
    
    private void verFeed() {
        List<Publicacion> feed = konnekt.getUsuarioActual().getSeguidos().stream()
                .flatMap(seguido -> seguido.getPublicaciones().stream())
                .sorted(Comparator.comparing(Publicacion::getFechaCreacion).reversed())
                .collect(Collectors.toList());
        vista.mostrarFeed(feed);
    }
    
    private void crearPublicacion() {
        String texto = vista.leerEntrada("Escribe tu publicación: ");
        konnekt.getUsuarioActual().publicar(texto);
        vista.mostrarMensaje("Publicación creada con éxito.");
    }
    
    private void buscarYSeguirUsuario() {
        vista.mostrarUsuarios(konnekt.getUsuarios());
        String nombre = vista.leerEntrada("Escribe el nombre del usuario que quieres seguir: ");
        
        Optional<Usuario> usuarioASeguir = konnekt.buscarUsuarioPorNombre(nombre);
        
        if (usuarioASeguir.isPresent()) {
            konnekt.getUsuarioActual().seguir(usuarioASeguir.get());
            vista.mostrarMensaje("Ahora sigues a @" + nombre);
        } else {
            vista.mostrarMensaje("Usuario no encontrado.");
        }
    }

    public static void main(String[] args) {
        KonnektApp aplicacion = new KonnektApp();
        aplicacion.iniciar();
    }
}