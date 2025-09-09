package com.f_rafael.API_Personajes_de_ficcion.models;

public enum ClasificacionObra {
    VIDEO_JUEGO("video juego"),
    PELICULA("pelicula"),
    SERIE("serie"),
    COMIC("comic"),
    CUENTO("cuento"),
    NOVELA("novela");

    private String nombre;

    private ClasificacionObra(String nombre){
        this.nombre = nombre;
    }

    private String getNombre(){
        return this.nombre;
    }
}
