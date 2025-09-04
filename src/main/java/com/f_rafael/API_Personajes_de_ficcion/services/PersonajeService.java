package com.f_rafael.API_Personajes_de_ficcion.services;

import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.repositories.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService implements IPersonajeService{

    private PersonajeRepository repository;

    public PersonajeService(PersonajeRepository repository){
        this.repository = repository;
    }

    @Override
    public Optional<Personaje> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Personaje> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Personaje save(Personaje personaje) {
        return repository.save(personaje);
    }

    @Override
    public Personaje update(Personaje personaje) {
        return this.save(personaje);
    }
}
