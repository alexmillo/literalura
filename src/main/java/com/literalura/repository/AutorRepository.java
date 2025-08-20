package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE (:year IS NULL OR (a.nacimiento IS NULL OR a.nacimiento <= :year) " +
            "AND (a.fallecimiento IS NULL OR a.fallecimiento >= :year))")
    List<Autor> findAutoresVivosEn(Integer year);
}
