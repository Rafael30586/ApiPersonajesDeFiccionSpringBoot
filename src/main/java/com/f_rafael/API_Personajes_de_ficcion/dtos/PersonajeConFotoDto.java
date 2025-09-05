package com.f_rafael.API_Personajes_de_ficcion.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PersonajeConFotoDto {
    private List<String> nombre;
    private List<String> apodo;
    private String[] url_imagen;
}
