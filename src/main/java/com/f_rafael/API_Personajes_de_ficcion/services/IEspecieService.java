package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;

import java.util.List;
import java.util.Optional;

public interface IEspecieService {
    public Optional<Especie> finfById(Long id);
    public List<Especie> findAll();
    public void deleteById(Long id);
    public Especie save(Especie especie);
    public Especie update(Especie especie);
}
