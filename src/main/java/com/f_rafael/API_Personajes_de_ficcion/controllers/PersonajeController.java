package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.services.PersonajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private PersonajeService service;

    public PersonajeController(PersonajeService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> findById(@PathVariable Long id){
        Personaje personaje = service.findById(id).orElse(new Personaje(-99999L,null,null,null,null,null));
        return ResponseEntity.ok(personaje);
    }

    @GetMapping
    public ResponseEntity<List<Personaje>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Personaje> save(@RequestBody Personaje personaje){
        return ResponseEntity.ok(service.save(personaje));
    }
    @PutMapping
    public ResponseEntity<Personaje> update(@RequestBody Personaje personaje){
        return ResponseEntity.ok(service.update(personaje));
    }
}
