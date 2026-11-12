
package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {

    // Crear directorios
    public void crearDirectorio(Path ruta) throws IOException {
        Files.createDirectories(ruta);
    }

    // Crear ficheros
    public void crearFichero(Path ruta) throws IOException {
        if (ruta.getParent() != null) {
            Files.createDirectories(ruta.getParent());
        }
        Files.createFile(ruta);
    }

    // Copiar ficheros
    public void copiar(Path origen, Path destino) throws IOException {
        Files.copy(origen, destino);
    }

    // Mover ficheros
    public void mover(Path origen, Path destino) throws IOException {
        Files.move(origen, destino);
    }

    // Listar archivos y directorios
    public List<Path> listar(Path directorio) throws IOException {
        List<Path> archivos = new ArrayList<>();

        try (var contenido = Files.list(directorio)) {
            contenido.forEach(archivos::add);
        }

        return archivos;
    }

    // Buscar archivos por nombre
    public List<Path> buscar(Path directorio, String nombre)
            throws IOException {

        List<Path> resultados = new ArrayList<>();

        try (var rutas = Files.walk(directorio)) {
            rutas.filter(Files::isRegularFile)
                    .filter(ruta -> ruta.getFileName().toString()
                            .equals(nombre))
                    .forEach(resultados::add);
        }

        return resultados;
    }

    // Eliminar archivos y directorios recursivamente
    public void eliminar(Path ruta) throws IOException {

        if (Files.notExists(ruta)) {
            return;
        }

        Files.walkFileTree(ruta, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(
                    Path fichero, BasicFileAttributes attrs)
                    throws IOException {

                Files.delete(fichero);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(
                    Path directorio, IOException error)
                    throws IOException {

                if (error != null) {
                    throw error;
                }

                Files.delete(directorio);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
