package com.f_rafael.API_Personajes_de_ficcion.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "obras")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(name = "fecha_lanzamiento")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaLanzamiento;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClasificacionObra clasificacion;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "obras")
    private Set<Personaje> personajes;
}
