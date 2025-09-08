package com.f_rafael.API_Personajes_de_ficcion.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PersonajeConFotoDto {
    private String nombreCompleto;
    private List<String> apodos;
    private Set<String> url_imagenes;
}
