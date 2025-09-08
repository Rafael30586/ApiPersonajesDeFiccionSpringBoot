package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;

import java.util.List;
import java.util.Optional;

public interface IEspecieService {
    public Optional<Especie> encontrarPorId(Long id);
    public List<Especie> encontrarTodos();
    public void borrarPorId(Long id);
    public Especie guardar(Especie especie);
    public Especie actualizar(Especie especie);
    public Optional<Especie> encontrarPorNombre(String nombre);
    public List<Especie> encontrarPorFragmentoNombre(String fragmentoNombre);
}
