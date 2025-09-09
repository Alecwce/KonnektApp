package com.konnekt.modelo;

import com.konnekt.modelo.Usuario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa una notificación para un usuario, generada por una acción
 * de otro usuario (like, comentario, seguir).
 */
public class Notificacion {
    private TipoNotificacion tipo;
    private Usuario usuarioOrigen; // Quién generó la notificación
    private LocalDateTime fecha;
    private String mensaje;

    public Notificacion(TipoNotificacion tipo, Usuario usuarioOrigen, String mensaje) {
        this.tipo = tipo;
        this.usuarioOrigen = usuarioOrigen;
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }
    
    public void mostrar() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("  [" + fecha.format(formatter) + "] " +
                           usuarioOrigen.getNombreUsuario() + " " + this.mensaje);
    }
    
    public Usuario getUsuarioOrigen() {
        return usuarioOrigen;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getMensaje() {
        return mensaje;
    }
}