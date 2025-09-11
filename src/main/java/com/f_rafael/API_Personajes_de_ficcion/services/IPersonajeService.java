package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeConFotoDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeDto;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;

import java.util.List;
import java.util.Optional;

public interface IPersonajeService {
    public Optional<Personaje> encontrarPorId(Long id);
    public List<Personaje> encontrarTodos();
    public PersonajeDto borrarPorId(Long id);
    public PersonajeDto guardar(Personaje personaje);
    public PersonajeDto actualizar(Personaje personaje);
    public List<PersonajeDto> buscarPorNombreCompleto(String nombreCompleto);
    public List<PersonajeDto> buscarPorApodo(String apodo);
    public List<PersonajeDto> buscarPorTituloDeObra(String tituloObra);
    public List<PersonajeDto> buscarPorNombre(String nombre); // Borrar
    public List<PersonajeDto> buscarPorFragmentoNombre(String fragmentoNombre);
    public PersonajeConFotoDto devolverPersonajeConFotos(Long personajeId);
    public List<PersonajeDto> buscarPorFragmentoApodo(String fragmentoApodo);
    public PersonajeDto devolverUnoConSusObras(Long id);
    public List<PersonajeDto> devolverTodosConSusObras();
    public List<PersonajeDto> devolverPorEspecie(String especie);
}
