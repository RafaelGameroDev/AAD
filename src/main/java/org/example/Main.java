package org.example;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        GestorFicheros gestor = new GestorFicheros();

        Path carpeta = Path.of("prueba_ficheros");
        Path archivo = Path.of("prueba_ficheros", "goku.txt");
        Path copia = Path.of("prueba_ficheros", "vegeta.txt");
        Path movido = Path.of("prueba_ficheros", "piccolo.txt");

        try {
            // 1 Crear directorio
            gestor.crearDirectorio(carpeta);
            System.out.println("Directorio creado");

            // 2 Crear fichero
            gestor.crearFichero(archivo);
            System.out.println("Fichero creado");

            // 3 Copiar fichero
            gestor.copiar(archivo, copia);
            System.out.println("Fichero copiado");

            // 4 Mover fichero
            gestor.mover(copia, movido);
            System.out.println("Fichero movido");

            // 5 Listar contenido
            System.out.println("Contenido:");
            for (Path p : gestor.listar(carpeta)) {
                System.out.println(p.getFileName());
            }

            // 6 Buscar fichero
            System.out.println("Resultado de búsqueda:");
            for (Path p : gestor.buscar(carpeta, "goku.txt")) {
                System.out.println(p);
            }

            // 7 Eliminar directorio y contenido
            gestor.eliminar(carpeta);
            System.out.println("Directorio eliminado");

            System.out.println("TODAS LAS PRUEBAS FINALIZADAS");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
