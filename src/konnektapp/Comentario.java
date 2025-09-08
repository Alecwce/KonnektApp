package konnektapp;

/**
 * Representa un comentario en una publicación.
 */
public class Comentario {
    private Usuario autor;
    private String texto;

    public Comentario(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
    }

    public void mostrar() {
        System.out.println("    - @" + this.autor.getNombreUsuario() + ": " + this.texto);
    }
}