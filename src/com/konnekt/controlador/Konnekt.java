// Archivo: src/com/konnekt/controlador/Konnekt.java
package com.konnekt.controlador;

import com.konnekt.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase de Servicio (o "Engine"): Mantiene el estado de la aplicación.
 * Gestiona la lista de todos los usuarios y la sesión del usuario actual.
 * Es el intermediario entre el controlador y los modelos.
 */
public class Konnekt {
    
    private List<Usuario> usuarios;
    private Usuario usuarioActual; // null si nadie ha iniciado sesión

    public Konnekt() {
        this.usuarios = new ArrayList<>();
        this.usuarioActual = null;
        // Opcional: Crear usuarios de prueba para no empezar de cero
        this.usuarios.add(new Usuario("admin", "El admin de Konnekt."));
        this.usuarios.add(new Usuario("testuser", "Un usuario de prueba."));
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Intenta iniciar sesión con un nombre de usuario.
     * @param nombreUsuario El nombre del usuario a loguear.
     * @return true si el login fue exitoso, false en caso contrario.
     */
    public boolean iniciarSesion(String nombreUsuario) {
        Optional<Usuario> usuarioEncontrado = usuarios.stream()
                .filter(u -> u.getNombreUsuario().equalsIgnoreCase(nombreUsuario))
                .findFirst();

        if (usuarioEncontrado.isPresent()) {
            this.usuarioActual = usuarioEncontrado.get();
            return true;
        }
        return false;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    /**
     * Registra un nuevo usuario si el nombre no está ya en uso.
     * @param nombreUsuario El nombre para el nuevo usuario.
     * @param biografia La biografía del nuevo usuario.
     * @return true si el registro fue exitoso, false si el nombre ya existe.
     */
    public boolean registrarUsuario(String nombreUsuario, String biografia) {
        boolean yaExiste = usuarios.stream()
                .anyMatch(u -> u.getNombreUsuario().equalsIgnoreCase(nombreUsuario));
        
        if (yaExiste) {
            return false; // El nombre de usuario ya está en uso.
        }
        
        Usuario nuevoUsuario = new Usuario(nombreUsuario, biografia);
        this.usuarios.add(nuevoUsuario);
        return true;
    }
    
    public Optional<Usuario> buscarUsuarioPorNombre(String nombre) {
        return usuarios.stream()
                .filter(u -> u.getNombreUsuario().equalsIgnoreCase(nombre))
                .findFirst();
    }
}