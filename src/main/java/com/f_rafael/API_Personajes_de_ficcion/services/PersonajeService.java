package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeConFotoDto;
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
    public Optional<Personaje> encontrarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Personaje> encontrarTodos() {
        return repository.findAll();
    }

    @Override
    public void borrarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Personaje guardar(Personaje personaje) {
        return repository.save(personaje);
    }

    @Override
    public Personaje actualizar(Personaje personaje) {
        return this.guardar(personaje);
    }

    @Override
    public List<Personaje> buscarPorNombreCompleto(String nombreCompleto) {
        return repository.findByNombreCompleto(nombreCompleto);
    }

    @Override
    public List<Personaje> buscarPorApodo(String apodo) {
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
    public List<Personaje> buscarPorTituloDeObra(String tituloObra) {
        List<Personaje> todosLosPersonajes = repository.buscarPersonajesConSusObras();
        List<Personaje> personajesARetornar = new ArrayList<>();
        boolean guaradarPersonaje;
        Set<Obra> obrasDelPersonaje;

        for (Personaje p : todosLosPersonajes) {

            guaradarPersonaje = false;
            obrasDelPersonaje = p.getObras();

            for (Obra o : obrasDelPersonaje) {
                if (tituloObra.equalsIgnoreCase(o.getTitulo())) guaradarPersonaje = true;
            }

            if (guaradarPersonaje) personajesARetornar.add(p);

        }

        return personajesARetornar;
    }

    @Override
    public List<Personaje> buscarPorNombre(String nombre) {
        List<Personaje> todosLosPersonajes = repository.findAll();
        List<Personaje> personajesARetornar = new ArrayList<>();
        String[] arrayNombreCompleto;
        boolean nombrePresente;

        for(Personaje p : todosLosPersonajes){

            nombrePresente = false;
            arrayNombreCompleto = p.getNombreCompleto().split(" ");

            for (String n : arrayNombreCompleto){

                if(nombre.equalsIgnoreCase(n)){
                    nombrePresente = true;
                }

            }
            if(nombrePresente) personajesARetornar.add(p);
        }
        return personajesARetornar;
    }

    @Override
    public List<Personaje> buscarPorFragmentoNombre(String fragmentoNombre) {
        return repository.buscarPersonajesPorFragmentoNombre(fragmentoNombre);
    }

    @Override
    public PersonajeConFotoDto devolverPersonajeConFotos(Long personajeId) {
        Optional<Personaje> personajeOptional = encontrarPorId(personajeId);
        PersonajeConFotoDto personajeConFotos = new PersonajeConFotoDto();

        if(personajeOptional.isPresent()){
            personajeConFotos.setNombreCompleto(personajeOptional.get().getNombreCompleto());
            personajeConFotos.setApodos(personajeOptional.get().getApodo());
            personajeConFotos.setUrl_imagenes(personajeOptional.get().getUrlImagenes());

            return personajeConFotos;
        }else{
            return new PersonajeConFotoDto(null,null,null);
        }

    }

    @Override
    public List<Personaje> buscarPorFragmentoApodo(String fragmentoApodo) {
        return repository.buscarPersonajesPorFragmentoApodo(fragmentoApodo);
    }

    @Override
    public Optional<Personaje> devolverPersonajeConSusObras(Long id) {
        return repository.devolverPersonajeConSusObras(id);
    }

}
