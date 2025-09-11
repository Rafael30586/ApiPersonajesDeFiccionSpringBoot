package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import com.f_rafael.API_Personajes_de_ficcion.services.EspecieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/especies")
public class EspecieController {

    private EspecieService service;

    public EspecieController(EspecieService service){
        this.service = service;
    }

    @GetMapping("/{id}") // Funciona
    public ResponseEntity<Especie> encontrarPorId(@PathVariable Long id){
        Especie especie = service.encontrarPorId(id).orElse(new Especie(-99999999L, "No está presente"));
        return ResponseEntity.ok(especie);
    }

    @GetMapping // Funciona
    public ResponseEntity<List<Especie>> encontrarTodos(){
        return ResponseEntity.ok(service.encontrarTodos());
    }

    @DeleteMapping("/{id}") // Funciona
    public ResponseEntity<String> borrarPorId(@PathVariable Long id){
        service.borrarPorId(id);
        return ResponseEntity.ok("Especie borrada");
    }

    @PostMapping // Funciona
    public ResponseEntity<Especie> guardar(@RequestBody Especie especie){
        return ResponseEntity.ok(service.guardar(especie));
    }

    @PutMapping // Funciona
    public ResponseEntity<Especie> actualizar(@RequestBody Especie especie){
        return ResponseEntity.ok(service.actualizar(especie));
    }

    @GetMapping("/por-nombre/{nombre}") // Funciona
    public ResponseEntity<Especie> encontrarPorNombre(@PathVariable String nombre){
        String nombreSinGuiones = nombre.replace('-',' ');
        Especie especie = service.encontrarPorNombre(nombreSinGuiones).orElse(new Especie(-99999999L, "Especie no encontrada"));
        return ResponseEntity.ok(especie);
    }

    @GetMapping("/por-fragmento-nombre/{fragmento-nombre}") // Funciona
    public ResponseEntity<List<Especie>>  encontrarPorFragmentoNombre(@PathVariable("fragmento-nombre") String fragmentoNombre){
        return ResponseEntity.ok(service.encontrarPorFragmentoNombre(fragmentoNombre));
    }
}
