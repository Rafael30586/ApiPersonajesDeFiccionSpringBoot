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
        Obra obra = service.devolverObraConPersonajes(id).orElse(new Obra(-99999L,null,null,null,null));
        return ResponseEntity.ok(obra);
    }

    @GetMapping
    public ResponseEntity<List<Obra>> findAll(){
        return ResponseEntity.ok(service.encontrarTodos());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.borrarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Obra> save(@RequestBody Obra obra){
        return ResponseEntity.ok(service.guardar(obra));
    }

    @PutMapping
    public ResponseEntity<Obra> update(@RequestBody Obra obra){
        return ResponseEntity.ok(service.guardar(obra));
    }

    @GetMapping("/por-titulo/{titulo}")
    public ResponseEntity<List<Obra>> buscarPorTitulo(@PathVariable String titulo){
        String tituloSinGuiones = titulo.replace('-',' ');
        return ResponseEntity.ok(service.encontrarPotTitulo(tituloSinGuiones));
    }

    @GetMapping("por-fragmento-titulo/{fragmento-titulo}")
    public ResponseEntity<List<Obra>> buscarPorFragmentoTitulo(@PathVariable("fragmento-titulo") String fragmentoTitulo){
        return ResponseEntity.ok(service.encontrarPorFragmentoTitulo(fragmentoTitulo));
    }
}
