package com.f_rafael.API_Personajes_de_ficcion.utils;

import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDePersonajeDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeEnObraDto;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Transform {
    public static PersonajeDto transformarEnPersonajeDto(Personaje informacionPersonaje){
        PersonajeDto personajeARetornar;
        Set<Obra> informacionObras;
        Set<ObraDePersonajeDto> obrasAAsignar = new HashSet<>();
        String especie = null;

        if(informacionPersonaje.getEspecie() != null){
            especie = informacionPersonaje.getEspecie().getNombre();
        }

        if(informacionPersonaje.getObras() != null){
            informacionObras = informacionPersonaje.getObras();

            for(Obra io : informacionObras){
                obrasAAsignar.add(new ObraDePersonajeDto(io.getTitulo(), io.getFechaLanzamiento(), io.getClasificacion()));
            }
        }

        personajeARetornar = new PersonajeDto(informacionPersonaje.getId(),
                informacionPersonaje.getNombreCompleto(),
                informacionPersonaje.getApodo(),
                informacionPersonaje.getUrlImagenes(),
                obrasAAsignar,
                especie);

        return personajeARetornar;
    }

    public static ObraDto tranformarEnObraDto(Obra informacionObra){
        ObraDto obraARetornar;
        Set<PersonajeEnObraDto> personajesAAsignar = new HashSet<>();
        Set<Personaje> informacionPersonajes = null;

        if(informacionObra.getPersonajes() != null){
            informacionPersonajes = informacionObra.getPersonajes();

            for(Personaje ip : informacionPersonajes){
                personajesAAsignar.add(new PersonajeEnObraDto(ip.getNombreCompleto(),
                        ip.getApodo(),
                        ip.getEspecie().getNombre()));
            }
        }

        obraARetornar = new ObraDto(informacionObra.getId(),
                informacionObra.getTitulo(),
                informacionObra.getUrlImagenes(),
                informacionObra.getFechaLanzamiento(),
                informacionObra.getClasificacion(),
                personajesAAsignar);

        return obraARetornar;
    }

    public static List<PersonajeDto> transformarEnPersonajeDtos(List<Personaje> informacionPersonajes){
        List<PersonajeDto> personajesARetornar = new ArrayList<>();

        for(Personaje ip : informacionPersonajes){
            personajesARetornar.add(transformarEnPersonajeDto(ip));
        }

        return personajesARetornar;
    }

    public static List<ObraDto> transformarEnObraDtos(List<Obra> informacionObras){
        List<ObraDto> obrasARetornar = new ArrayList<>();

        for(Obra io : informacionObras){
            obrasARetornar.add(tranformarEnObraDto(io));
        }

        return obrasARetornar;
    }

    public static String quitarGuionesBajos(String cadena){
        String cadenaSinGuiones = cadena.replace('_',' ');
        return cadenaSinGuiones;
    }
}
