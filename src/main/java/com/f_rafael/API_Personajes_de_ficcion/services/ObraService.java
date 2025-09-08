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
    public Optional<Obra> encontrarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Obra> encontrarTodos() {
        return repository.findAll();
    }

    @Override
    public void borrarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Obra guardar(Obra obra) {
        return repository.save(obra);
    }

    @Override
    public Obra actualizar(Obra obra) {
        return this.guardar(obra);
    }

    @Override
    public List<Obra> encontrarPotTitulo(String titulo) {
        return repository.encontrarPorTitulo(titulo);
    }

    @Override
    public List<Obra> encontrarPorFragmentoTitulo(String fragmentoTitulo) {
        return repository.encontrarPorFragmentoTitulo(fragmentoTitulo);
    }

    @Override
    public Optional<Obra> devolverObraConPersonajes(Long id) {
        return repository.encontrarObraYSusPersonajes(id);
    }
}
