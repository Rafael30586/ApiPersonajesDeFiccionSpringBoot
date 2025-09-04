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
    public Optional<Especie> finfById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Especie> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Especie save(Especie especie) {
        return repository.save(especie);
    }

    @Override
    public Especie update(Especie especie) {
        return this.save(especie);
    }
}
