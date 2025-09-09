package com.konnekt.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Clase central que representa a un usuario en Konnekt.
 * Gestiona perfil, seguidores, seguidos, publicaciones, notificaciones y el feed.
 */
public class Usuario {

    private String nombreUsuario;
    private String biografia;
    private LocalDateTime fechaRegistro;
    
    private List<Usuario> seguidos;
    private List<Usuario> seguidores;
    private List<Publicacion> publicaciones;
    private List<Notificacion> notificaciones;

    public Usuario(String nombreUsuario, String biografia) {
        this.nombreUsuario = nombreUsuario;
        this.biografia = biografia;
        this.fechaRegistro = LocalDateTime.now();
        
        this.seguidos = new ArrayList<>();
        this.seguidores = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
    }

    // --- MÉTODOS DE ACCIÓN ---
    public void seguir(Usuario otroUsuario) {
        if (this != otroUsuario && !seguidos.contains(otroUsuario)) {
            this.seguidos.add(otroUsuario);
            otroUsuario.agregarSeguidor(this);
            
            // Notificar al usuario seguido
            String mensaje = "ha comenzado a seguirte.";
            Notificacion notif = new Notificacion(TipoNotificacion.NUEVO_SEGUIDOR, this, mensaje);
            otroUsuario.recibirNotificacion(notif);
        }
    }

    private void agregarSeguidor(Usuario seguidor) {
        this.seguidores.add(seguidor);
    }
    
    public Publicacion publicar(String texto) {
        Publicacion nuevaPublicacion = new PublicacionTexto(this, texto);
        this.publicaciones.add(nuevaPublicacion);
        return nuevaPublicacion;
    }
    
    public Publicacion publicar(String urlImagen, String descripcion) {
        Publicacion nuevaPublicacion = new PublicacionImagen(this, urlImagen, descripcion);
        this.publicaciones.add(nuevaPublicacion);
        return nuevaPublicacion;
    }

    public void darLike(Publicacion publicacion) {
        publicacion.agregarLike(this);
    }
    
    public void recibirNotificacion(Notificacion notificacion) {
        this.notificaciones.add(notificacion);
    }

    // --- MÉTODOS DE VISUALIZACIÓN ---
    public void mostrarPerfil() {
        System.out.println("\n=========================================");
        System.out.println("PERFIL DE @" + this.nombreUsuario);
        System.out.println("'" + this.biografia + "'");
        System.out.println("Miembro desde: " + this.fechaRegistro.toLocalDate());
        System.out.println("-----------------------------------------");
        System.out.println("Sigue a: " + this.seguidos.size() + " | Seguidores: " + this.seguidores.size());
        System.out.println("Publicaciones: " + this.publicaciones.size());
        System.out.println("=========================================");
    }
    
    public void verFeed() {
        System.out.println("\n<<<<<<<<<<<< FEED DE " + this.nombreUsuario + " >>>>>>>>>>>>");
        
        List<Publicacion> feed = new ArrayList<>();
        // Recolectar publicaciones de todos los usuarios seguidos
        for (Usuario seguido : this.seguidos) {
            feed.addAll(seguido.getPublicaciones());
        }
        
        // Ordenar el feed por fecha de creación (de más reciente a más antiguo)
        feed.sort(Comparator.comparing(Publicacion::getFechaCreacion).reversed());
        
        if (feed.isEmpty()) {
            System.out.println("Tu feed está vacío. ¡Sigue a alguien para ver sus publicaciones!");
        } else {
            for (Publicacion p : feed) {
                p.mostrar();
            }
        }
        System.out.println("<<<<<<<<<< FIN DEL FEED DE " + this.nombreUsuario + " >>>>>>>>>>\n");
    }
    
    public void mostrarNotificaciones() {
        System.out.println("\n🔔 NOTIFICACIONES DE " + this.nombreUsuario + " 🔔");
        if(this.notificaciones.isEmpty()){
            System.out.println("No tienes notificaciones nuevas.");
        } else {
            // Mostrar de la más reciente a la más antigua
            for(int i = this.notificaciones.size() - 1; i >= 0; i--){
                this.notificaciones.get(i).mostrar();
            }
        }
        System.out.println("-----------------------------------------\n");
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }
    
    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }
    
    public List<Usuario> getSeguidos() {
        return seguidos;
    }
    
}