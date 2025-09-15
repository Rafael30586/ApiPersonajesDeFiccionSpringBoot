package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDto;
import com.f_rafael.API_Personajes_de_ficcion.models.ClasificacionObra;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.repositories.ObraRepository;
import com.f_rafael.API_Personajes_de_ficcion.utils.Transform;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ObraService implements IObraService{

    private ObraRepository repository;

    public ObraService(ObraRepository repository){
        this.repository = repository;
    }
    @Override
    public Optional<Obra> encontrarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Obra> encontrarTodos() {
        return repository.findAll();
    }

    @Override
    public List<ObraDto> devolverObrasConPersonajes() {
        List<ObraDto> obrasARetornar;
        obrasARetornar = Transform.transformarEnObraDtos(repository.buscarTodasConSusPersonajes());

        return obrasARetornar;
    }

    @Override
    public ObraDto borrarPorId(Long id) {
        ObraDto obraARetornar;
        if(this.encontrarPorId(id).isPresent()){
            obraARetornar = Transform.tranformarEnObraDto(this.encontrarPorId(id).get());
            repository.deleteById(id);
        }else{
            obraARetornar = new ObraDto(-999999L,"Obra no encontrada",null,null,null,null);
        }
        return obraARetornar;
    }

    @Override
    public ObraDto guardar(Obra obra) {
        return Transform.tranformarEnObraDto(repository.save(obra));
    }

    @Override
    public ObraDto actualizar(Obra obra) {
        return this.guardar(obra);
    }

    @Override
    public List<ObraDto> encontrarPotTitulo(String titulo) {
        return Transform.transformarEnObraDtos(repository.encontrarPorTitulo(titulo));
    }

    @Override
    public List<ObraDto> encontrarPorFragmentoTitulo(String fragmentoTitulo) {
        return Transform.transformarEnObraDtos(repository.encontrarPorFragmentoTitulo(fragmentoTitulo));
    }

    @Override
    public ObraDto devolverUnaConSusPersonajes(Long id) {
        ObraDto obraARetornar;

        if(repository.findById(id).isPresent()){
            obraARetornar = Transform.tranformarEnObraDto(repository.encontrarUnaYSusPersonajes(id).get());
        }else{
            obraARetornar = new ObraDto(-999999L,"Obra no encontrada",null,null,null,null);
        }

        return obraARetornar;
    }

    @Override
    public List<ObraDto> buscarPorPeriodo(int desde, int hasta) {
        List<ObraDto> obrasARetornar = Transform.transformarEnObraDtos(repository.buscarPorPeriodo(desde,hasta));
        return obrasARetornar;
    }

    @Override
    public List<ObraDto> buscarPorClasificacion(ClasificacionObra clasificacion) {
        List<ObraDto> obrasARetornar = Transform.transformarEnObraDtos(repository.buscarPorClasificacion(clasificacion));
        return obrasARetornar;
    }
}
