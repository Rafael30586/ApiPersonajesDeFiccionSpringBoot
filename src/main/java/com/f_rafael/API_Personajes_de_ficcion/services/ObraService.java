package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.repositories.ObraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraService implements IObraService{

    private ObraRepository repository;

    public ObraService(ObraRepository repository){
        this.repository = repository;
    }
    @Override
    public Optional<Obra> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Obra> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Obra save(Obra obra) {
        return repository.save(obra);
    }

    @Override
    public Obra update(Obra obra) {
        return this.save(obra);
    }
}
