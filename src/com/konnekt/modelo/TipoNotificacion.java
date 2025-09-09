package com.konnekt.modelo;

/**
 * Define los tipos de notificaciones que un usuario puede recibir.
 * Usar un Enum es una buena práctica porque previene errores de tipeo
 * y hace el código más legible y seguro que usar Strings.
 */
public enum TipoNotificacion {
    NUEVO_SEGUIDOR,
    NUEVO_LIKE,
    NUEVO_COMENTARIO
}