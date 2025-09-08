package com.f_rafael.API_Personajes_de_ficcion.repositories;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {

    Optional<Especie> findByNombre(String nombre);

    @Query("SELECT e FROM Especie e WHERE nombre LIKE :fragmentoNombre")
    List<Especie> encontrarPorFragmentoNombre(@Param("fragmentoNombre") String fragmentoNombre);
}
