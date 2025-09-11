package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeConFotoDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeDto;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.repositories.PersonajeRepository;
import com.f_rafael.API_Personajes_de_ficcion.utils.Transform;
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
    public PersonajeDto borrarPorId(Long id) {
        PersonajeDto personajeARetornar;
        if(this.encontrarPorId(id).isPresent()){
            personajeARetornar = Transform.transformarEnPersonajeDto(repository.devolverUnoConSusObras(id).get());
            repository.deleteById(id);
        }else{
            personajeARetornar = new PersonajeDto(-9999999L,"Personaje no encontrado",null,null,null,null);
        }
        return personajeARetornar;
    }

    @Override
    public PersonajeDto guardar(Personaje personaje) {
        Personaje personajeARetornar;

        repository.save(personaje);
        if(repository.findById(personaje.getId()).isPresent()){
            personajeARetornar = repository.devolverUnoConSusObras(personaje.getId()).get();
        }else{
            return new PersonajeDto(-99999L,"Error en el guardado",null,null,null,null);
        }
        return Transform.transformarEnPersonajeDto(personajeARetornar);
    }

    @Override
    public PersonajeDto actualizar(Personaje personaje) {
        return this.guardar(personaje);
    }

    @Override
    public List<PersonajeDto> buscarPorNombreCompleto(String nombreCompleto) {
        return Transform.transformarEnPersonajeDtos(repository.findByNombreCompleto(nombreCompleto));
    }

    @Override
    public List<PersonajeDto> buscarPorApodo(String apodo) {
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

        return Transform.transformarEnPersonajeDtos(personajesARetornar);
    }

    @Override
    public List<PersonajeDto> buscarPorTituloDeObra(String tituloObra) {
        List<Personaje> todosLosPersonajes = repository.buscarTodosConSusObras();
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

        return Transform.transformarEnPersonajeDtos(personajesARetornar);
    }

    @Override
    public List<PersonajeDto> buscarPorNombre(String nombre) {
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
        return Transform.transformarEnPersonajeDtos(personajesARetornar);
    }

    @Override
    public List<PersonajeDto> buscarPorFragmentoNombre(String fragmentoNombre) {
        return Transform.transformarEnPersonajeDtos(repository.buscarPorFragmentoNombre(fragmentoNombre));
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
    public List<PersonajeDto> buscarPorFragmentoApodo(String fragmentoApodo) {
        return Transform.transformarEnPersonajeDtos(repository.buscarPorFragmentoApodo(fragmentoApodo));
    }

    @Override
    public PersonajeDto devolverUnoConSusObras(Long id) {
        PersonajeDto personajeARetornar;

        if(repository.findById(id).isPresent()){
            personajeARetornar = Transform.transformarEnPersonajeDto(repository.devolverUnoConSusObras(id).get());
        }else{
            personajeARetornar = new PersonajeDto(-99999L,"Personaje no encontrado",null,null,null,null);
        }

        return personajeARetornar;
    }

    @Override
    public List<PersonajeDto> devolverTodosConSusObras() {
        List<PersonajeDto> personajesARetornar;
        personajesARetornar = Transform.transformarEnPersonajeDtos(repository.buscarTodosConSusObras());

        return personajesARetornar;
    }

    @Override
    public List<PersonajeDto> devolverPorEspecie(String especie) {
        return Transform.transformarEnPersonajeDtos(repository.devolverPorEspecie(especie));
    }

}
