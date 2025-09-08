package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import com.f_rafael.API_Personajes_de_ficcion.repositories.EspecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieService implements IEspecieService{

    private EspecieRepository repository;

    public EspecieService(EspecieRepository repository){
        this.repository = repository;
    }

    @Override
    public Optional<Especie> encontrarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Especie> encontrarTodos() {
        return repository.findAll();
    }

    @Override
    public void borrarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Especie guardar(Especie especie) {
        return repository.save(especie);
    }

    @Override
    public Especie actualizar(Especie especie) {
        return this.guardar(especie);
    }

    @Override
    public Optional<Especie> encontrarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public List<Especie> encontrarPorFragmentoNombre(String fragmentoNombre) {
        return repository.encontrarPorFragmentoNombre(fragmentoNombre);
    }
}
