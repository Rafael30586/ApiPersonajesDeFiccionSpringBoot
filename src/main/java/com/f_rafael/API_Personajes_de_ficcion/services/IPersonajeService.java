package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;

import java.util.List;
import java.util.Optional;

public interface IPersonajeService {
    public Optional<Personaje> findById(Long id);
    public List<Personaje> findAll();
    public void deleteById(Long id);
    public Personaje save(Personaje personaje);
    public Personaje update(Personaje personaje);
    public List<Personaje> findPersonajesByNombreCompleto(String nombreCompleto);
    public List<Personaje> findPersonajesByApodo(String apodo);
    public List<Personaje> findPersonajesByTituloObra(String tituloObra);
}
