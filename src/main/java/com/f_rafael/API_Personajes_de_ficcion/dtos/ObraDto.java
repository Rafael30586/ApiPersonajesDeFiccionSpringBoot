package com.f_rafael.API_Personajes_de_ficcion.dtos;

import com.f_rafael.API_Personajes_de_ficcion.models.ClasificacionObra;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObraDto {
    private Long id;
    private String titulo;
    @JsonProperty("url_imagenes")
    private Set<String> urlImagenes;
    @JsonProperty("fecha_lanzamiento")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaLanzamiento;
    @JsonProperty("clasificacion_obra")
    private ClasificacionObra clasificacionObra;
    private Set<PersonajeEnObraDto> personajes;
}
