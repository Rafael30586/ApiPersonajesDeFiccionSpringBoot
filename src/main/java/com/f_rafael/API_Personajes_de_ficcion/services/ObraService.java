package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeEnObraDto;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.repositories.ObraRepository;
import com.f_rafael.API_Personajes_de_ficcion.utils.Transform;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ObraService implements IObraService{

    private ObraRepository repository;

    public ObraService(ObraRepository repository){
        this.repository = repository;
    }
    @Override
    public Optional<Obra> encontrarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Obra> encontrarTodos() {
        return repository.findAll();
    }

    @Override
    public List<ObraDto> devolverObrasConPersonajes() {
        List<ObraDto> obrasARetornar;
        //List<Obra> informacionObras = repository.buscarTodasConSusPersonajes();
       // Set<Personaje> informacionPersonajes;
        //Set<PersonajeEnObraDto> personajesParaAsignar = new HashSet<>();
/*
        for(Obra o : informacionObras){
            informacionPersonajes = o.getPersonajes();

            for(Personaje p : informacionPersonajes){
                personajesParaAsignar.add(new PersonajeEnObraDto(p.getNombreCompleto(),p.getApodo(),p.getEspecie().getNombre()));
            }

            obrasARetornar.add(new ObraDto(o.getId(),o.getTitulo(),o.getFechaLanzamiento(),o.getClasificacion(), personajesParaAsignar));

        }*/
        obrasARetornar = Transform.transformarEnObraDtos(repository.buscarTodasConSusPersonajes());

        return obrasARetornar;
    }

    @Override
    public void borrarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Obra guardar(Obra obra) {
        return repository.save(obra);
    }

    @Override
    public Obra actualizar(Obra obra) {
        return this.guardar(obra);
    }

    @Override
    public List<Obra> encontrarPotTitulo(String titulo) {
        return repository.encontrarPorTitulo(titulo);
    }

    @Override
    public List<Obra> encontrarPorFragmentoTitulo(String fragmentoTitulo) {
        return repository.encontrarPorFragmentoTitulo(fragmentoTitulo);
    }

    @Override
    public ObraDto devolverUnaConSusPersonajes(Long id) {
        ObraDto obraARetornar;
        /*Set<Personaje> informacionPersonajes;
        Obra informacionObra;
        Set<PersonajeEnObraDto> personajesParaAsignar = new HashSet<>();*/

        if(repository.findById(id).isPresent()){
            obraARetornar = Transform.tranformarEnObraDto(repository.encontrarUnaYSusPersonajes(id).get());
            /*
            informacionObra = repository.encontrarUnaYSusPersonajes(id).get();
            informacionPersonajes = informacionObra.getPersonajes();

            for(Personaje p : informacionPersonajes){
                personajesParaAsignar.add(new PersonajeEnObraDto(p.getNombreCompleto(),p.getApodo(),p.getEspecie().getNombre()));
            }

            obraARetornar = new ObraDto(informacionObra.getId(),
                    informacionObra.getTitulo(),
                    informacionObra.getFechaLanzamiento(),
                    informacionObra.getClasificacion(),
                    personajesParaAsignar);*/
        }else{
            obraARetornar = new ObraDto(-999999L,"Obra no encontrada",null,null,null);
        }

        return obraARetornar;
    }
}
