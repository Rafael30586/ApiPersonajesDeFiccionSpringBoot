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
import com.f_rafael.API_Personajes_de_ficcion.utils.Transform;
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

    @GetMapping("/{id}") // Funciona
    public ResponseEntity<PersonajeDto> encontrarPorId(@PathVariable Long id){
        PersonajeDto personaje = service.devolverUnoConSusObras(id);
        return ResponseEntity.ok(personaje);
    }

    @GetMapping // Funciona
    public ResponseEntity<List<PersonajeDto>> encontrarTodos(){
        return ResponseEntity.ok(service.devolverTodosConSusObras());
    }

    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable Long id){
        service.borrarPorId(id);
    }

    @PostMapping // Guarda el personaje pero retorna un json con valores null que no deberían estar ahí
    public ResponseEntity<PersonajeDto> guardar(@RequestBody Personaje personaje){
        return ResponseEntity.ok(service.guardar(personaje));
    }
    @PutMapping // Igual al postmapping
    public ResponseEntity<PersonajeDto> actualizar(@RequestBody Personaje personaje){
        return ResponseEntity.ok(service.actualizar(personaje));
    }

    @GetMapping("/personaje-con-fotos/{id}")
    public ResponseEntity<PersonajeConFotoDto> mostrarPerosnajeConFotos(@PathVariable Long id){
        return ResponseEntity.ok(service.devolverPersonajeConFotos(id));
    }

    @GetMapping("/por-nombre-completo/{nombre-completo}") // Funciona
    public ResponseEntity<List<PersonajeDto>> buscarPorNombreCompleto(@PathVariable("nombre-completo") String nombreCompleto){
        String nombreSinGuiones = Transform.quitarGuionesBajos(nombreCompleto);
        return ResponseEntity.ok(service.buscarPorNombreCompleto(nombreSinGuiones));
    }

    @GetMapping("/por-apodo/{apodo}") // Funciona
    public ResponseEntity<List<PersonajeDto>> buscarPorApodo(@PathVariable String apodo){
        String apodoSinGuiones = Transform.quitarGuionesBajos(apodo);
        return ResponseEntity.ok(service.buscarPorApodo(apodoSinGuiones));
    }

    @GetMapping("/por-titulo-obra/{titulo-obra}") // Funciona
    public ResponseEntity<List<PersonajeDto>> buscarPorTituloObra(@PathVariable("titulo-obra") String tituloObra){
        String tituloObraSinGuiones = Transform.quitarGuionesBajos(tituloObra);
        return ResponseEntity.ok(service.buscarPorTituloDeObra(tituloObraSinGuiones));
    }

    @GetMapping("/por-fragmento-nombre/{fragmento-nombre}") // Funciona
    public ResponseEntity<List<PersonajeDto>> buscarPorFragmentoNombre(@PathVariable("fragmento-nombre") String fragmentoNombre){
        return ResponseEntity.ok(service.buscarPorFragmentoNombre(fragmentoNombre));
    }

    @GetMapping("/por-fragmento-apodo/{fragmento-apodo}") // No funciona
    public ResponseEntity<List<PersonajeDto>> buscarPorFragmentoApodo(@PathVariable("fragmento-apodo") String fragmentoApodo){
        return ResponseEntity.ok(service.buscarPorFragmentoApodo(fragmentoApodo));
    }

    @PatchMapping("/editar-nombre/{personaje-id}") // Funciona
    public ResponseEntity<PersonajeDto> editarNombre(@PathVariable("personaje-id") Long personajeId,
                                                     @RequestParam("nombre-completo") String nombreCompleto){

        Personaje personajeAEditar;

        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.encontrarPorId(personajeId).get();
            personajeAEditar.setNombreCompleto(nombreCompleto);
            //service.guardar(personajeAEditar);

            return ResponseEntity.ok(service.guardar(personajeAEditar));
        }else{
            return ResponseEntity.ok(new PersonajeDto(-99999L,"Personaje no encontrado",null,null,null,null));
        }

    }

    @PatchMapping("/editar-apodos/{personaje-id}") // Funciona
    public ResponseEntity<PersonajeDto> editarApodos(@PathVariable("personaje-id") Long personajeId,
                                               @RequestBody List<String> listaApodos){
        Personaje personajeAEditar;


        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.encontrarPorId(personajeId).get();
            personajeAEditar.setApodo(listaApodos);
            // service.guardar(personajeAEditar);

            return ResponseEntity.ok(service.guardar(personajeAEditar)); // Corregir esta parte
        }else{
            return ResponseEntity.ok(new PersonajeDto(-999999L,"Personaje no encontrado",null,null,null,null));
        }

    }

    @PatchMapping("/cambiar-imagenes/{personaje-id}") // Funciona
    public ResponseEntity<PersonajeDto> cambiarImagenes(@PathVariable("personaje-id") Long personajeId,
                                                  @RequestBody Set<String> urlImagenes){
        Personaje personajeAEditar;

        if(service.encontrarPorId(personajeId).isPresent()) {
            personajeAEditar = service.encontrarPorId(personajeId).get();
            personajeAEditar.setUrlImagenes(urlImagenes);
            service.guardar(personajeAEditar);

            return ResponseEntity.ok(service.guardar(personajeAEditar));
        }else{
            return ResponseEntity.ok(new PersonajeDto(-99999L,"Personaje no encontrado",null,null,null,null));
        }

    }

    @PatchMapping("/agregar-obra/{personaje-id}/{obra-id}") // Funciona
    public ResponseEntity<PersonajeDto> agregarObra(@PathVariable("personaje-id") Long personajeId,
                                                 @PathVariable("obra-id") Long obraId){
        Obra obraAAgregar;
        Personaje personajeAEditar;
        Set<Obra> obrasDelPersonaje;

        if(obraService.encontrarPorId(obraId).isPresent()) obraAAgregar = obraService.encontrarPorId(obraId).get();
        else return ResponseEntity.ok(new PersonajeDto(-99999L,"Obra no encontrada",null,null,null,null));

        if(service.encontrarPorId(personajeId).isPresent()) personajeAEditar = service.encontrarPorId(personajeId).get();
        else return ResponseEntity.ok(new PersonajeDto(-99999L,"Personaje no encontrado",null,null,null,null));

        obrasDelPersonaje = personajeAEditar.getObras();
        obrasDelPersonaje.add(obraAAgregar);
        personajeAEditar.setObras(obrasDelPersonaje);

        //service.guardar(personajeAEditar);

        return ResponseEntity.ok(service.guardar(personajeAEditar));
    }

    @PatchMapping("/remover-obra/{personaje-id}/{obra-id}") // Funciona
    public ResponseEntity<PersonajeDto> removerObra(@PathVariable("personaje-id") Long personajeId,
                                              @PathVariable("obra-id") Long obraId){
        Obra obraARemover = new Obra();
        Personaje personajeAEditar;
        Set<Obra> obrasDelPersonaje;
        boolean obraPresente = false;

        if(service.encontrarPorId(personajeId).isPresent()) {
            personajeAEditar = service.encontrarPorId(personajeId).get();
            obrasDelPersonaje = personajeAEditar.getObras();
        }
        else return ResponseEntity.ok(new PersonajeDto(-99999L,"Personaje no encontrado",null,null,null,null));

        for(Obra o : obrasDelPersonaje){
            if(o.getId() == (obraId)) {
                obraARemover = o;
                obraPresente = true;
            }
        }

        if(obraPresente){
            obrasDelPersonaje.remove(obraARemover);
        }else{
            return ResponseEntity.ok(new PersonajeDto(-99999L,"Obra no encontrada",null,null,null,null));
        }

        personajeAEditar.setObras(obrasDelPersonaje);

        return ResponseEntity.ok(service.actualizar(personajeAEditar));
    }

    @PatchMapping("editar-especie/{personaje-id}/{especie-id}") // Funciona
    public ResponseEntity<PersonajeDto> editarEspecie(@PathVariable("personaje-id") Long personajeId,
                                                @PathVariable("especie-id") Long especieId){
        Personaje personajeAEditar;
        Especie especideAAgregar;

        if(especieService.encontrarPorId(especieId).isPresent()){
            especideAAgregar = especieService.encontrarPorId(especieId).get();
        }else{
            return ResponseEntity.ok(new PersonajeDto(-9999999L,"Especie no encontrada",null,null,null,null));
        }

        if(service.encontrarPorId(personajeId).isPresent()){
            personajeAEditar = service.encontrarPorId(personajeId).get();
        }else{
            return ResponseEntity.ok(new PersonajeDto(-99999L,"Personaje no encontrado",null,null,null,null));
        }

        personajeAEditar.setEspecie(especideAAgregar);
        //service.guardar(personajeAEditar);

        return ResponseEntity.ok(service.guardar(personajeAEditar));
    }


}
