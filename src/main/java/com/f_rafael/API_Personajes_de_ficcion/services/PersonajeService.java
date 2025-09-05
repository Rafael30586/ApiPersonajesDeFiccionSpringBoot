package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.repositories.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonajeService implements IPersonajeService {

    private PersonajeRepository repository;

    public PersonajeService(PersonajeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Personaje> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Personaje> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Personaje save(Personaje personaje) {
        return repository.save(personaje);
    }

    @Override
    public Personaje update(Personaje personaje) {
        return this.save(personaje);
    }

    @Override
    public List<Personaje> findPersonajesByNombreCompleto(String nombreCompleto) {
        return repository.findByNombreCompleto(nombreCompleto);
    }

    @Override
    public List<Personaje> findPersonajesByApodo(String apodo) {
        List<Personaje> personajesARetornar = new ArrayList<>();
        List<Personaje> todosLosPersonajes = repository.findAll();
        List<String> apodos;
        boolean guardarPersonaje;

        for (Personaje p : todosLosPersonajes) {

            guardarPersonaje = false;
            apodos = p.getApodo();

            for (String a : apodos) {
                if (apodo.equals(a)) {
                    guardarPersonaje = true;
                }
            }

            if (guardarPersonaje) personajesARetornar.add(p);
        }

        return personajesARetornar;
    }

    @Override
    public List<Personaje> findPersonajesByTituloObra(String tituloObra) {
        List<Personaje> todosLosPersonajes = repository.buscarPersonajesConSusObras();
        List<Personaje> personajesARetornar = new ArrayList<>();
        boolean guaradarPersonaje;
        Set<Obra> obrasDelPersonaje;

        for (Personaje p : todosLosPersonajes) {

            guaradarPersonaje = false;
            obrasDelPersonaje = p.getObras();

            for (Obra o : obrasDelPersonaje) {
                if (tituloObra.equals(o.getTitulo())) guaradarPersonaje = true;
                if (guaradarPersonaje) personajesARetornar.add(p);
            }

        }

        return personajesARetornar;
    }
}
