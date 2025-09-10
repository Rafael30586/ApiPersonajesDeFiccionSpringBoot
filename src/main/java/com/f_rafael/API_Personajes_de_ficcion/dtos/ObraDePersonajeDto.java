package com.f_rafael.API_Personajes_de_ficcion.dtos;

import com.f_rafael.API_Personajes_de_ficcion.models.ClasificacionObra;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObraDePersonajeDto {
    private String titulo;
    private LocalDate fecha_lanzamiento;
    private ClasificacionObra clasificacion;
}
