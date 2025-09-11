package com.f_rafael.API_Personajes_de_ficcion.repositories;

import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
/*
    @Query(value = "SELECT ",nativeQuery = true)
    List<Personaje> buscarPersonajesConSusObras();
  */
    @Query(value = "SELECT p FROM Personaje p LEFT JOIN FETCH p.obras")
    List<Personaje> buscarTodosConSusObras();

    List<Personaje> findByNombreCompleto(String nombreCompleto);

    @Query("SELECT p FROM Personaje p WHERE p.nombreCompleto LIKE %:fragmentoNombre%")
    List<Personaje> buscarPorFragmentoNombre(@Param("fragmentoNombre") String fragmentoNombre);

    @Query("SELECT p FROM Personaje p WHERE p.apodo LIKE %:fragmentoApodo%")
    List<Personaje> buscarPorFragmentoApodo(@Param("fragmentoApodo") String fragmentoApodo);

    @Query("SELECT p FROM Personaje p LEFT JOIN FETCH p.obras WHERE p.id = :id")
    Optional<Personaje> devolverUnoConSusObras(@Param("id") Long id);

    @Query("SELECT p FROM Personaje p LEFT JOIN FETCH p.especie WHERE p.especie.nombre = :especie")
    List<Personaje> devolverPorEspecie(@Param("especie") String especie);

}
