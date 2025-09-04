package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.models.Obra;

import java.util.List;
import java.util.Optional;

public interface IObraService {
    public Optional<Obra> findById(Long id);
    public List<Obra> findAll();
    public void deleteById(Long id);
    public Obra save(Obra obra);
    public Obra update(Obra obra);
}
