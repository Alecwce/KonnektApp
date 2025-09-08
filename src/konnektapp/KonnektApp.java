package konnektapp;

import java.nio.charset.Charset;

public class KonnektApp {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("¡Bienvenido a Konnekt! - La simulación está comenzando...\n");

        // 1. Creación de Usuarios con perfiles más completos
        Usuario sara = new Usuario("sara_dev", "Desarrolladora Java y amante del café.");
        Usuario leo = new Usuario("leo_design", "Diseñador UX/UI. Creando experiencias.");
        Usuario maria = new Usuario("maria_fotos", "Fotógrafa de paisajes y viajes.");
        
        // 2. Usuarios se siguen entre sí (esto generará notificaciones)
        sara.seguir(leo);
        sara.seguir(maria);
        leo.seguir(sara);
        
        // Pausa para simular el paso del tiempo
        Thread.sleep(1000); 

        // 3. Crean publicaciones de diferentes tipos
        Publicacion pubLeo1 = leo.publicar("Revisando los nuevos principios de diseño para 2024.");
        Thread.sleep(1000); 
        Publicacion pubMaria1 = maria.publicar("atardecer.jpg", "Un atardecer increíble en la montaña.");
        Thread.sleep(1000); 
        Publicacion pubSara1 = sara.publicar("Acabo de terminar mi proyecto con POO. ¡Qué satisfacción!");

        // 4. Interactúan con las publicaciones (Likes y Comentarios)
        sara.darLike(pubMaria1); // A Sara le gusta la foto de María
        leo.darLike(pubMaria1);  // A Leo también le gusta
        pubSara1.agregarComentario(leo, "¡Felicidades, Sara! ¡El código limpio es lo mejor!");
        
        // 5. Mostrando los resultados
        
        // Perfiles
        sara.mostrarPerfil();
        leo.mostrarPerfil();
        maria.mostrarPerfil();  
        
        // Feeds
        sara.verFeed(); // Sara debería ver las publicaciones de Leo y María
        leo.verFeed();  // Leo solo debería ver las de Sara
        maria.verFeed(); // El feed de María debería estar vacío

        // Notificaciones
        sara.mostrarNotificaciones(); // Debería tener una notificación del comentario de Leo
        leo.mostrarNotificaciones();  // Debería tener una notificación de que Sara lo sigue
        maria.mostrarNotificaciones(); // Debería tener 2 notificaciones de likes y una de seguidor
        
        System.out.println("Encoding usado por defecto: " + Charset.defaultCharset());
        System.out.println("Texto de prueba: áéíóú ñ ¿ ¡");
    }
}