package com.f_rafael.API_Personajes_de_ficcion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personajes")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_completo")
    @JsonProperty("nombre_completo")
    private String nombreCompleto;
    @ElementCollection
    @CollectionTable(name = "personaje_apodos")
    @JsonProperty("apodos")
    private List<String> apodo;
    @ElementCollection
    @CollectionTable(name = "personaje_url_imagenes")
    @JsonProperty("url_imagenes")
    private Set<String> urlImagenes;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "personaje_obra",
            joinColumns = @JoinColumn(name = "personaje_id"),
            inverseJoinColumns = @JoinColumn(name = "obra_id")
    )
    private Set<Obra> obras;
    @ManyToOne
    private Especie especie;
}
