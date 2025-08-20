package com.literalura.service;

import com.literalura.dto.AutorDTO;
import com.literalura.dto.GutendexResponse;
import com.literalura.dto.LibroDTO;
import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CatalogoService {

    private final GutendexClient client;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public CatalogoService(GutendexClient client, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.client = client;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public void buscarYGuardarPorTitulo(String titulo) {
        GutendexResponse response = client.buscarPorTitulo(titulo);
        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            System.out.println("No se encontraron resultados para: " + titulo);
            return;
        }
        for (LibroDTO l : response.getResults()) {
            String idioma = (l.getLanguages() != null && !l.getLanguages().isEmpty()) ? l.getLanguages().get(0) : null;
            String tituloLibro = l.getTitle();

            // Evitar duplicados por título (simplificación).
            if (libroRepository.findByTituloIgnoreCase(tituloLibro).isPresent()) {
                System.out.println("Ya existe: " + tituloLibro);
                continue;
            }

            Libro libro = new Libro(tituloLibro, idioma, l.getDownloadCount());
            // Autores
            if (l.getAuthors() != null) {
                for (AutorDTO a : l.getAuthors()) {
                    if (a.getName() == null || a.getName().isBlank()) continue;
                    Autor autor = autorRepository.findByNombreIgnoreCase(a.getName())
                            .orElseGet(() -> autorRepository.save(new Autor(a.getName(), a.getBirth_year(), a.getDeath_year())));
                    libro.getAutores().add(autor);
                }
            }
            libroRepository.save(libro);
            System.out.println("Guardado: " + libro);
        }
    }

    public void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros persistidos aún.");
            return;
        }
        libros.forEach(l -> System.out.println("• " + l));
    }

    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores persistidos aún.");
            return;
        }
        autores.forEach(a -> System.out.println("• " + a));
    }

    public void listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByIdiomaIgnoreCase(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros en el idioma '" + idioma + "'.");
            return;
        }
        libros.forEach(l -> System.out.println("• " + l));
    }

    public void listarAutoresVivosEnAnio(int year) {
        List<Autor> autores = autorRepository.findAutoresVivosEn(year);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en " + year);
            return;
        }
        autores.forEach(a -> System.out.println("• " + a));
    }
}
