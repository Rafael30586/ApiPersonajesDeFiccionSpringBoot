package com.f_rafael.API_Personajes_de_ficcion.dtos;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonajeDto {
    private Long id;
    private String nombre_completo;
    private List<String> apodos;
    private Set<String> url_imagenes;
    private Set<ObraDePersonajeDto> obras;
    private String especie; // Cambiar a String
}
