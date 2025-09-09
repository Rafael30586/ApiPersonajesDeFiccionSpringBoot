package com.f_rafael.API_Personajes_de_ficcion.models;

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
    private String nombreCompleto;
    @ElementCollection
    @CollectionTable(name = "personaje_apodos")
    private List<String> apodo;
    @ElementCollection
    @CollectionTable(name = "personaje_url_imagenes")
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
