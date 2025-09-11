package com.f_rafael.API_Personajes_de_ficcion.dtos;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("nombre_completo")
    private String nombreCompleto;
    private List<String> apodos;
    @JsonProperty("url_imagenes")
    private Set<String> urlImagenes;
    private Set<ObraDePersonajeDto> obras;
    private String especie;
}
