package com.konnekt.modelo;

import com.konnekt.modelo.Usuario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base ABSTRACTA para todas las publicaciones.
 * Define los atributos y comportamientos comunes a cualquier tipo de publicación.
 * No se pueden crear objetos de una clase abstracta directamente.
 */
public abstract class Publicacion {
    
    // Protected permite que las clases hijas accedan directamente a estos atributos.
    protected Usuario autor;
    protected LocalDateTime fechaCreacion;
    protected List<Usuario> likes;
    protected List<Comentario> comentarios;

    public Publicacion(Usuario autor) {
        this.autor = autor;
        this.fechaCreacion = LocalDateTime.now();
        this.likes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    // --- MÉTODOS COMUNES ---

    public void agregarLike(Usuario usuario) {
        if (!likes.contains(usuario)) {
            this.likes.add(usuario);
            // Notificar al autor de la publicación
            String mensaje = "le ha dado 'Me gusta' a tu publicación.";
            Notificacion notif = new Notificacion(TipoNotificacion.NUEVO_LIKE, usuario, mensaje);
            this.autor.recibirNotificacion(notif);
        }
    }

    public void agregarComentario(Usuario autorComentario, String texto) {
        Comentario nuevoComentario = new Comentario(autorComentario, texto);
        this.comentarios.add(nuevoComentario);
        // Notificar al autor de la publicación
        String mensaje = "ha comentado en tu publicación: \"" + texto + "\"";
        Notificacion notif = new Notificacion(TipoNotificacion.NUEVO_COMENTARIO, autorComentario, mensaje);
        this.autor.recibirNotificacion(notif);
    }
    
    /**
     * Muestra la información común de la publicación (autor, fecha, likes, comentarios).
     * Llama a un método abstracto para mostrar el contenido específico.
     */
    public void mostrar() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("\n----------------------------------");
        System.out.println("@" + this.autor.getNombreUsuario() + " (" + this.fechaCreacion.format(formatter) + ")");
        
        // Llama al método que cada subclase debe implementar
        mostrarContenidoEspecifico();

        System.out.println("Likes: " + this.likes.size() + " | Comentarios: " + this.comentarios.size());
        
        if (!this.comentarios.isEmpty()) {
            System.out.println("  Comentarios:");
            for(Comentario c : this.comentarios) {
                c.mostrar();
            }
        }
        System.out.println("----------------------------------");
    }
    
    /**
     * MÉTODO ABSTRACTO: Cada subclase (PublicacionTexto, PublicacionImagen)
     * ESTÁ OBLIGADA a implementar este método a su manera.
     */
    public abstract void mostrarContenidoEspecifico();
    
    // --- GETTERS ---
    public Usuario getAutor() { return autor; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    
    public List<Usuario> getLikes() {
        return likes;
    }
}