package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDto;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;

import java.util.List;
import java.util.Optional;

public interface IObraService {
    public Optional<Obra> encontrarPorId(Long id);
    public List<Obra> encontrarTodos();
    public List<ObraDto> devolverObrasConPersonajes();
    public void borrarPorId(Long id);
    public Obra guardar(Obra obra);
    public Obra actualizar(Obra obra);
    public List<Obra> encontrarPotTitulo(String titulo);
    public List<Obra> encontrarPorFragmentoTitulo(String fragmentoTitulo);
    public ObraDto devolverUnaConSusPersonajes(Long id);
}
