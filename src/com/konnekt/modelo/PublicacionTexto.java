package com.konnekt.modelo;

import com.konnekt.modelo.Publicacion;
import com.konnekt.modelo.Usuario;

/**
 * Representa una publicación que solo contiene texto.
 * Hereda de la clase Publicacion.
 */
public class PublicacionTexto extends Publicacion {
    private String texto;

    public PublicacionTexto(Usuario autor, String texto) {
        // Llama al constructor de la clase padre (Publicacion)
        super(autor);
        this.texto = texto;
    }

    /**
     * Implementación obligatoria del método abstracto de la clase padre.
     * Muestra el contenido específico de esta clase: el texto.
     */
    @Override
    public void mostrarContenidoEspecifico() {
        System.out.println("\"" + this.texto + "\"");
    }
}