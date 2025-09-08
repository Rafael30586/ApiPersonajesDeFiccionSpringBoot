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
    public ResponseEntity<Especie> encontrarPorId(@PathVariable Long id){
        Especie especie = service.encontrarPorId(id).orElse(new Especie(-99999999L, "No está presente"));
        return ResponseEntity.ok(especie);
    }

    @GetMapping
    public ResponseEntity<List<Especie>> encontrarTodos(){
        return ResponseEntity.ok(service.encontrarTodos());
    }

    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable Long id){
        service.borrarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Especie> guardar(@RequestBody Especie especie){
        return ResponseEntity.ok(service.guardar(especie));
    }

    @PutMapping
    public ResponseEntity<Especie> actualizar(@RequestBody Especie especie){
        return ResponseEntity.ok(service.actualizar(especie));
    }

    @GetMapping("/por-nombre")
    public ResponseEntity<Especie> encontrarPorNombre(@PathVariable String nombre){
        String nombreSinGuiones = nombre.replace('-',' ');
        Especie especie = service.encontrarPorNombre(nombreSinGuiones).orElse(new Especie(-99999999L, null));
        return ResponseEntity.ok(especie);
    }
}
