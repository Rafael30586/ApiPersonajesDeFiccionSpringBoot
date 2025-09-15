package com.f_rafael.API_Personajes_de_ficcion.repositories;

import com.f_rafael.API_Personajes_de_ficcion.models.ClasificacionObra;
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

    @Query("SELECT o FROM Obra o WHERE o.titulo LIKE %:fragmentoTitulo%")
    public List<Obra> encontrarPorFragmentoTitulo(@Param("fragmentoTitulo") String fragmentoTitulo);

    @Query("SELECT o FROM Obra o LEFT JOIN FETCH o.personajes WHERE o.id = :id")
    public Optional<Obra> encontrarUnaYSusPersonajes(@Param("id") Long id);

    @Query("SELECT o FROM Obra o LEFT JOIN FETCH o.personajes")
    public List<Obra> buscarTodasConSusPersonajes();

    @Query("SELECT o FROM Obra o WHERE YEAR(o.fechaLanzamiento) BETWEEN :desde AND :hasta")
    public List<Obra> buscarPorPeriodo(@Param("desde") int desde, @Param("hasta") int hasta);

    @Query("SELECT o FROM Obra o WHERE o.clasificacion = :clasificacion")
    public List<Obra> buscarPorClasificacion(@Param("clasificacion") ClasificacionObra clasificacion);
}
