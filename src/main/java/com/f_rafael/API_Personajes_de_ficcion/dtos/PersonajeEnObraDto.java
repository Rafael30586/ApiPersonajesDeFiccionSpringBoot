package com.f_rafael.API_Personajes_de_ficcion.dtos;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class PersonajeEnObraDto {
    private String nombre_completo;
    private List<String> apodos;
    private String especie;
}
