package com.f_rafael.API_Personajes_de_ficcion.repositories;

import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
/*
    @Query(value = "SELECT ",nativeQuery = true)
    List<Personaje> buscarPersonajesConSusObras();
  */
    @Query(value = "SELECT p.nombreCompleto, p.apodo, p.obras, p.especie FROM Personaje p")
    List<Personaje> buscarPersonajesConSusObras();

    List<Personaje> findByNombreCompleto(String nombreCompleto);
}
