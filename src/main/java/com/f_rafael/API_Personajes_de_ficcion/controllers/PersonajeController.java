package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeConFotoDto;
import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.services.EspecieService;
import com.f_rafael.API_Personajes_de_ficcion.services.ObraService;
import com.f_rafael.API_Personajes_de_ficcion.services.PersonajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private PersonajeService service;
    private ObraService obraService;
    private EspecieService especieService;

    public PersonajeController(PersonajeService service,ObraService obraService, EspecieService especieService){
        this.service = service;
        this.obraService = obraService;
        this.especieService = especieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> encontrarPorId(@PathVariable Long id){
        Personaje personaje = service.devolverPersonajeConSusObras(id).orElse(new Personaje(-99999L,null,null,null,null,null));
        return ResponseEntity.ok(personaje);
    }

    @GetMapping
    public ResponseEntity<List<Personaje>> encontrarTodos(){
        return ResponseEntity.ok(service.encontrarTodos());
    }

    @DeleteMapping("{id}")
    public void borrarPorId(@PathVariable Long id){
        service.borrarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Personaje> guardar(@RequestBody Personaje personaje){
        return ResponseEntity.ok(service.guardar(personaje));
    }
    @PutMapping
    public ResponseEntity<Personaje> actualizar(@RequestBody Personaje personaje){
        return ResponseEntity.ok(service.actualizar(personaje));
    }

    @GetMapping("/personaje-con-fotos/{id}")
    public ResponseEntity<PersonajeConFotoDto> mostrarPerosnajeConFotos(@PathVariable Long id){
        return ResponseEntity.ok(service.devolverPersonajeConFotos(id));
    }

    @GetMapping("/perosnajes-por-nombre-completo/{nombre-completo}")
    public ResponseEntity<List<Personaje>> buscarPorNombreCompleto(@PathVariable("nombre-completo") String nombreCompleto){
        String nombreSinGuiones = nombreCompleto.replace('-',' ');
        return ResponseEntity.ok(service.buscarPorNombreCompleto(nombreSinGuiones));
    }

    @GetMapping("/personajes-por-apodo/{apodo}")
    public ResponseEntity<List<Personaje>> buscarPorApodo(@PathVariable String apodo){
        String apodoSinGuiones = apodo.replace('-',' ');
        return ResponseEntity.ok(service.buscarPorApodo(apodoSinGuiones));
    }

    @GetMapping("/personajes-por-titulo-obra/{titulo-obra}")
    public ResponseEntity<List<Personaje>> buscarPorTituloObra(@PathVariable("titulo-obra") String tituloObra){
        return ResponseEntity.ok(service.buscarPorTituloDeObra(tituloObra));
    }

    @GetMapping("/personajes-por-fragmento-nombre/{fragmento-nombre}")
    public ResponseEntity<List<Personaje>> buscarPorFragmentoNombre(@PathVariable("fragmento-nombre") String fragmentoNombre){
        return ResponseEntity.ok(service.buscarPorFragmentoNombre(fragmentoNombre));
    }

    @GetMapping("/por-fragmento-apodo/{fragmento-apodo}")
    public ResponseEntity<List<Personaje>> buscarPorFragmentoApodo(@PathVariable("fragmento-apodo") String fragmentoApodo){
        return ResponseEntity.ok(service.buscarPorFragmentoApodo(fragmentoApodo));
    }

    @PatchMapping("/editar-nombre/{personaje-id}")
    public ResponseEntity<String> editarNombre(@PathVariable("personaje-id") Long personajeId,
                                               @RequestParam("nombre-completo") String nombreCompleto){

        Personaje personajeAEditar;

        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.devolverPersonajeConSusObras(personajeId).get();
            personajeAEditar.setNombreCompleto(nombreCompleto);
            service.guardar(personajeAEditar);

            return ResponseEntity.ok("Personaje editado");
        }else{
            return ResponseEntity.ok("Personaje no encontrado");
        }

    }

    @PatchMapping("/editar-apodos/{personaje-id}")
    public ResponseEntity<String> editarApodos(@PathVariable("personaje-id") Long personajeId,
                                               @RequestBody List<String> listaApodos){
        Personaje personajeAEditar;

        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.devolverPersonajeConSusObras(personajeId).get();
            personajeAEditar.setApodo(listaApodos);
            service.guardar(personajeAEditar);

            return ResponseEntity.ok("Personaje editado");
        }else{
            return ResponseEntity.ok("Personaje no encontrado");
        }

    }

    @PatchMapping("/cambiar-imagenes/{personaje-id}")
    public ResponseEntity<String> cambiarImagenes(@PathVariable Long personajeId,
                                                  @RequestBody Set<String> urlImagenes){
        Personaje personajeAEditar;

        if(service.encontrarPorId(personajeId).isPresent()) {
            personajeAEditar = service.devolverPersonajeConSusObras(personajeId).get();
            personajeAEditar.setUrlImagenes(urlImagenes);
            service.guardar(personajeAEditar);

            return ResponseEntity.ok("Personaje editado");
        }else{
            return ResponseEntity.ok("Personaje no encontrado");
        }

    }

    @PatchMapping("/agregar-obra/{personaje-id}/{obra-id}")
    public ResponseEntity<String> agregarObra(@PathVariable("personaje-id") Long personajeId,
                                                 @PathVariable("obra-id") Long obraId){
        Obra obraAAgregar;
        Personaje personajeAEditar;
        Set<Obra> obrasDelPersonaje;

        if(obraService.encontrarPorId(obraId).isPresent()) obraAAgregar = obraService.encontrarPorId(obraId).get();
        else return ResponseEntity.ok("Obra no encontrada");

        if(service.encontrarPorId(personajeId).isPresent()) personajeAEditar = service.devolverPersonajeConSusObras(personajeId).get();
        else return ResponseEntity.ok("Personaje no encontrado");

        obrasDelPersonaje = personajeAEditar.getObras();
        obrasDelPersonaje.add(obraAAgregar);
        personajeAEditar.setObras(obrasDelPersonaje);

        service.guardar(personajeAEditar);

        return ResponseEntity.ok(personajeAEditar.getNombreCompleto()+" editado");
    }

    @PatchMapping("/remover-obra/{personaje-id}/{obra-id}")
    public ResponseEntity<String> removerObra(@PathVariable("personaje-id") Long personajeId,
                                              @PathVariable("obra-id") Long obraId){
        Obra obraARemover = new Obra();
        Personaje personajeAEditar;
        Set<Obra> obrasDelPersonaje;
        boolean obraPresente = false;

        if(service.encontrarPorId(personajeId).isPresent()) {
            personajeAEditar = service.devolverPersonajeConSusObras(personajeId).get();
            obrasDelPersonaje = personajeAEditar.getObras();
        }
        else return ResponseEntity.ok("Personaje no encontrado");

        for(Obra o : obrasDelPersonaje){
            if(o.equals(obraId)) {
                obraARemover = o;
                obraPresente = true;
            }
        }

        if(obraPresente){
            obrasDelPersonaje.remove(obraARemover);
        }else{
            return ResponseEntity.ok("Obra no encontrada");
        }

        personajeAEditar.setObras(obrasDelPersonaje);

        return ResponseEntity.ok("Personaje editado");
    }

    @PatchMapping("editar-especie/{personaje-id}/{especie-id}")
    public ResponseEntity<String> editarEspecie(@PathVariable("personaje-id") Long personajeId,
                                                @PathVariable("especie-id") Long especieId){
        Personaje personajeAEditar;
        Especie especideAAgregar;

        if(especieService.encontrarPorId(especieId).isPresent()){
            especideAAgregar = especieService.encontrarPorId(especieId).get();
        }else{
            return ResponseEntity.ok("Especie no encontrada");
        }

        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.devolverPersonajeConSusObras(personajeId).get();
        }else{
            return ResponseEntity.ok("Personaje no encontrado");
        }

        personajeAEditar.setEspecie(especideAAgregar);
        service.guardar(personajeAEditar);

        return ResponseEntity.ok("Personaje editado");
    }


}
