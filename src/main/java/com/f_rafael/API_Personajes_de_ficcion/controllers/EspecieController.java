package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import com.f_rafael.API_Personajes_de_ficcion.services.EspecieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especie")
public class EspecieController {

    private EspecieService service;

    public EspecieController(EspecieService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especie> findById(@PathVariable Long id){
        Especie especie = service.finfById(id).orElse(new Especie(-99999999L, "No está"));
        return ResponseEntity.ok(especie);
    }

    @GetMapping
    public ResponseEntity<List<Especie>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Especie> save(@RequestBody Especie especie){
        return ResponseEntity.ok(service.save(especie));
    }

    @PutMapping
    public ResponseEntity<Especie> update(@RequestBody Especie especie){
        return ResponseEntity.ok(service.update(especie));
    }
}
