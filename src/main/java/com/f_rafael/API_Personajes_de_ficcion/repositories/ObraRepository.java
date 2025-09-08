package com.f_rafael.API_Personajes_de_ficcion.repositories;

import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

    @Query("SELECT o FROM Obra o WHERE titulo = :titulo")
    public List<Obra> encontrarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT o FROM Obra o WHERE o.titulo LIKE :titulo")
    public List<Obra> encontrarPorFragmentoTitulo(@Param("fragmentoTitulo") String fragmentoTitulo);

    @Query("SELECT o.id, o.titulo, o.fechaLanzamiento, o.clasificacion, o.personajes FROM Obra o WHERE o.id = :id")
    public Optional<Obra> encontrarObraYSusPersonajes(@Param("id") Long id);
}
