package com.konnekt.modelo;

import com.konnekt.modelo.Publicacion;
import com.konnekt.modelo.Usuario;

/**
 * Representa una publicación que contiene una imagen y una descripción.
 * Hereda de la clase Publicacion.
 */
public class PublicacionImagen extends Publicacion {
    private String urlImagen;
    private String descripcion;

    public PublicacionImagen(Usuario autor, String urlImagen, String descripcion) {
        super(autor);
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
    }
    
    /**
     * Implementación obligatoria del método abstracto.
     * Muestra la URL de la imagen y la descripción.
     */
    @Override
    public void mostrarContenidoEspecifico() {
        System.out.println("Imagen: [" + this.urlImagen + "]");
        System.out.println("  " + this.descripcion);
    }
}