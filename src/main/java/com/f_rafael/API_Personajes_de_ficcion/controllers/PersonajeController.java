package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDePersonajeDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeConFotoDto;
import com.f_rafael.API_Personajes_de_ficcion.dtos.PersonajeDto;
import com.f_rafael.API_Personajes_de_ficcion.models.Especie;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.services.EspecieService;
import com.f_rafael.API_Personajes_de_ficcion.services.ObraService;
import com.f_rafael.API_Personajes_de_ficcion.services.PersonajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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
    public ResponseEntity<PersonajeDto> encontrarPorId(@PathVariable Long id){
        PersonajeDto personaje = service.devolverUnoConSusObras(id);
        return ResponseEntity.ok(personaje);
    }

    @GetMapping
    public ResponseEntity<List<PersonajeDto>> encontrarTodos(){
        return ResponseEntity.ok(service.devolverTodosConSusObras());
    }

    @DeleteMapping("/{id}")
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
    public ResponseEntity<Personaje> editarNombre(@PathVariable("personaje-id") Long personajeId,
                                                     @RequestParam("nombre-completo") String nombreCompleto){

        Personaje personajeAEditar;

        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.encontrarPorId(personajeId).get();
            personajeAEditar.setNombreCompleto(nombreCompleto);
            //service.guardar(personajeAEditar);

            return ResponseEntity.ok(service.guardar(personajeAEditar));
        }else{
            return ResponseEntity.ok(new Personaje(-99999L,"Personaje no encontrado",null,null,null,null));
        }

    }

    @PatchMapping("/editar-apodos/{personaje-id}")
    public ResponseEntity<Personaje> editarApodos(@PathVariable("personaje-id") Long personajeId,
                                               @RequestBody List<String> listaApodos){
        Personaje personajeAEditar;


        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.encontrarPorId(personajeId).get();
            personajeAEditar.setApodo(listaApodos);
            // service.guardar(personajeAEditar);

            return ResponseEntity.ok(service.guardar(personajeAEditar)); // Corregir esta parte
        }else{
            return ResponseEntity.ok(new Personaje(-999999L,"Personaje no encontrado",null,null,null,null));
        }

    }

    @PatchMapping("/cambiar-imagenes/{personaje-id}")
    public ResponseEntity<Personaje> cambiarImagenes(@PathVariable Long personajeId,
                                                  @RequestBody Set<String> urlImagenes){
        Personaje personajeAEditar;

        if(service.encontrarPorId(personajeId).isPresent()) {
            personajeAEditar = service.encontrarPorId(personajeId).get();
            personajeAEditar.setUrlImagenes(urlImagenes);
            service.guardar(personajeAEditar);

            return ResponseEntity.ok(service.guardar(personajeAEditar));
        }else{
            return ResponseEntity.ok(new Personaje(-99999L,"PErsonaje no encontrado",null,null,null,null));
        }

    }

    @PatchMapping("/agregar-obra/{personaje-id}/{obra-id}")
    public ResponseEntity<Personaje> agregarObra(@PathVariable("personaje-id") Long personajeId,
                                                 @PathVariable("obra-id") Long obraId){
        Obra obraAAgregar;
        Personaje personajeAEditar;
        Set<Obra> obrasDelPersonaje;

        if(obraService.encontrarPorId(obraId).isPresent()) obraAAgregar = obraService.encontrarPorId(obraId).get();
        else return ResponseEntity.ok(new Personaje(-99999L,"Obra no encontrada",null,null,null,null));

        if(service.encontrarPorId(personajeId).isPresent()) personajeAEditar = service.encontrarPorId(personajeId).get();
        else return ResponseEntity.ok(new Personaje(-99999L,"Personaje no encontrado",null,null,null,null));

        obrasDelPersonaje = personajeAEditar.getObras();
        obrasDelPersonaje.add(obraAAgregar);
        personajeAEditar.setObras(obrasDelPersonaje);

        //service.guardar(personajeAEditar);

        return ResponseEntity.ok(service.guardar(personajeAEditar));
    }

    @PatchMapping("/remover-obra/{personaje-id}/{obra-id}")
    public ResponseEntity<Personaje> removerObra(@PathVariable("personaje-id") Long personajeId,
                                              @PathVariable("obra-id") Long obraId){
        Obra obraARemover = new Obra();
        Personaje personajeAEditar;
        Set<Obra> obrasDelPersonaje;
        boolean obraPresente = false;

        if(service.encontrarPorId(personajeId).isPresent()) {
            personajeAEditar = service.encontrarPorId(personajeId).get();
            obrasDelPersonaje = personajeAEditar.getObras();
        }
        else return ResponseEntity.ok(new Personaje(-99999L,"Personaje no encontrado",null,null,null,null));

        for(Obra o : obrasDelPersonaje){
            if(o.equals(obraId)) {
                obraARemover = o;
                obraPresente = true;
            }
        }

        if(obraPresente){
            obrasDelPersonaje.remove(obraARemover);
        }else{
            return ResponseEntity.ok(new Personaje(-99999L,"Obra no encontrada",null,null,null,null));
        }

        personajeAEditar.setObras(obrasDelPersonaje);

        return ResponseEntity.ok(service.actualizar(personajeAEditar));
    }

    @PatchMapping("editar-especie/{personaje-id}/{especie-id}")
    public ResponseEntity<Personaje> editarEspecie(@PathVariable("personaje-id") Long personajeId,
                                                @PathVariable("especie-id") Long especieId){
        Personaje personajeAEditar;
        Especie especideAAgregar;

        if(especieService.encontrarPorId(especieId).isPresent()){
            especideAAgregar = especieService.encontrarPorId(especieId).get();
        }else{
            return ResponseEntity.ok(new Personaje(-9999999L,"Especie no encontrada",null,null,null,null));
        }

        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.encontrarPorId(personajeId).get();
        }else{
            return ResponseEntity.ok(new Personaje(-99999L,"Personaje no encontrado",null,null,null,null));
        }

        personajeAEditar.setEspecie(especideAAgregar);
        //service.guardar(personajeAEditar);

        return ResponseEntity.ok(service.guardar(personajeAEditar));
    }


}
