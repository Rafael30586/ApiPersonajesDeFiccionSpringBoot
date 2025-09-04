package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.services.ObraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private ObraService service;

    public ObraController(ObraService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> findById(@PathVariable Long id){
        Obra obra = service.findById(id).orElse(new Obra(-99999L,null,null,null,null));
        return ResponseEntity.ok(obra);
    }

    @GetMapping
    public ResponseEntity<List<Obra>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Obra> save(@RequestBody Obra obra){
        return ResponseEntity.ok(service.save(obra));
    }

    @PutMapping
    public ResponseEntity<Obra> update(@RequestBody Obra obra){
        return ResponseEntity.ok(service.save(obra));
    }
}
