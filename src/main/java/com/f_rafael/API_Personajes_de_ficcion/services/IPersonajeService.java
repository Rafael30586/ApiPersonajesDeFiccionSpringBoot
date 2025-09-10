package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeConFotoDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeDto;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;

import java.util.List;
import java.util.Optional;

public interface IPersonajeService {
    public Optional<Personaje> encontrarPorId(Long id);
    public List<Personaje> encontrarTodos();
    public void borrarPorId(Long id);
    public Personaje guardar(Personaje personaje);
    public Personaje actualizar(Personaje personaje);
    public List<Personaje> buscarPorNombreCompleto(String nombreCompleto);
    public List<Personaje> buscarPorApodo(String apodo);
    public List<Personaje> buscarPorTituloDeObra(String tituloObra);
    public List<Personaje> buscarPorNombre(String nombre); // Borrar
    public List<Personaje> buscarPorFragmentoNombre(String fragmentoNombre);
    public PersonajeConFotoDto devolverPersonajeConFotos(Long personajeId);
    public List<Personaje> buscarPorFragmentoApodo(String fragmentoApodo);
    public PersonajeDto devolverUnoConSusObras(Long id);
    public List<PersonajeDto> devolverTodosConSusObras();
}
