package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDto;
import com.f_rafael.API_Personajes_de_ficcion.models.ClasificacionObra;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;

import java.util.List;
import java.util.Optional;

public interface IObraService {
    public Optional<Obra> encontrarPorId(Long id);
    public List<Obra> encontrarTodos();
    public List<ObraDto> devolverObrasConPersonajes();
    public ObraDto borrarPorId(Long id);
    public ObraDto guardar(Obra obra);
    public ObraDto actualizar(Obra obra);
    public List<ObraDto> encontrarPotTitulo(String titulo);
    public List<ObraDto> encontrarPorFragmentoTitulo(String fragmentoTitulo);
    public ObraDto devolverUnaConSusPersonajes(Long id);
    public List<ObraDto> buscarPorPeriodo(int desde, int hasta);
    public List<ObraDto> buscarPorClasificacion(ClasificacionObra clasificacion);
}
