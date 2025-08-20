package com.literalura;

import com.literalura.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private CatalogoService catalogoService;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== LiterAlura (Consola) =====");
            System.out.println("1) Buscar y guardar libro por título (API Gutendex)");
            System.out.println("2) Consultar libros (persistidos)");
            System.out.println("3) Consultar autores (persistidos)");
            System.out.println("4) Listar libros por idioma");
            System.out.println("5) Listar autores vivos en un año");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título a buscar: ");
                    String titulo = sc.nextLine();
                    catalogoService.buscarYGuardarPorTitulo(titulo);
                }
                case 2 -> catalogoService.listarLibros();
                case 3 -> catalogoService.listarAutores();
                case 4 -> {
                    System.out.print("Idioma (ej: en, es, fr, pt): ");
                    String idioma = sc.nextLine();
                    catalogoService.listarLibrosPorIdioma(idioma);
                }
                case 5 -> {
                    System.out.print("Año (YYYY): ");
                    int year = Integer.parseInt(sc.nextLine());
                    catalogoService.listarAutoresVivosEnAnio(year);
                }
                case 0 -> System.out.println("Hasta luego 👋");
                default -> System.out.println("Opción inválida");
            }
        }
    }
}
