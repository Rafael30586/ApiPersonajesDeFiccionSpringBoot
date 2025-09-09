package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.models.ClasificacionObra;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.services.ObraService;
import com.f_rafael.API_Personajes_de_ficcion.services.PersonajeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private ObraService service;
    private PersonajeService personajeService;

    public ObraController(ObraService service, PersonajeService personajeService){
        this.service = service;
        this.personajeService = personajeService;
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

    @PatchMapping("/editar-titulo/{obra-id}")
    public ResponseEntity<Obra> editarTitulo(@PathVariable("obra-id") Long obraId,
                                             @RequestParam("nuevo-titulo") String nuevoTitulo){
        Obra obraAEditar;

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.devolverObraConPersonajes(obraId).get();
            obraAEditar.setTitulo(nuevoTitulo);

            return ResponseEntity.ok(service.guardar(obraAEditar));
        }else{
            return ResponseEntity.ok(new Obra(-9999999L,null,null,null,null));
        }

    }

    @PatchMapping("/editar-fecha/{obra-id}")
    public ResponseEntity<Obra> editarFecha(@PathVariable("obra-id") Long obraId,
                                            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaLanzamiento){
        Obra obraAEditar;

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.devolverObraConPersonajes(obraId).get();
            obraAEditar.setFechaLanzamiento(fechaLanzamiento);

            return ResponseEntity.ok(service.guardar(obraAEditar));
        }else{
            return ResponseEntity.ok(new Obra(-9999999L,null,null,null,null));
        }

    }

    @PatchMapping
    public ResponseEntity<Obra> editarClasificacion(@PathVariable("obra-id") Long obraId,
                                                    @RequestParam("numero-clasificacion") int numeroClasificacion){
        Obra obraAEditar;
        ClasificacionObra[] clasicaciones = ClasificacionObra.values();
        ClasificacionObra clasificacionAEditar;

        if(numeroClasificacion < clasicaciones.length){
            clasificacionAEditar = clasicaciones[numeroClasificacion];
        }else{
            return ResponseEntity.ok(new Obra(-99999L,"Clasificacion no encontrada",null,null,null));
        }

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.devolverObraConPersonajes(obraId).get();
            obraAEditar.setClasificacion(clasificacionAEditar);

            return ResponseEntity.ok(obraAEditar);
        }else{
            return ResponseEntity.ok(new Obra(-999999L,"Obra no encontrada",null,null,null));
        }

    }

    @PatchMapping("/agregar-personaje/{obra-id}/{personaje-id}")
    public ResponseEntity<Obra> agregarPersonaje(@PathVariable("obra-id") Long obraId,
                                                 @PathVariable("personaje-id") Long personajeId){
        Obra obraAEditar;
        Personaje personajeAAgregar;
        Set<Personaje> setPersonajes;

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.devolverObraConPersonajes(obraId).get();
            setPersonajes = obraAEditar.getPersonajes();
        }else{
            return ResponseEntity.ok(new Obra(-9999999L,"Obra no encontrada",null,null,null));
        }

        if(personajeService.encontrarPorId(personajeId).isPresent()){
            personajeAAgregar = personajeService.encontrarPorId(personajeId).get();
        }else{
            return ResponseEntity.ok(new Obra(-99999L,"Personaje no encontrado",null,null,null));
        }

        setPersonajes.add(personajeAAgregar);
        obraAEditar.setPersonajes(setPersonajes);

        return ResponseEntity.ok(obraAEditar);
    }

    @PatchMapping("/remover-personaje/{obra-id}/{personaje-id}")
    public ResponseEntity<Obra> removerPersonaje(@PathVariable("obra-id") Long obraId,
                                                 @PathVariable("personaje-id") Long personajeId){
        Obra obraAEditar;
        Set<Personaje> setPersonajes;
        Personaje personajeARemover = new Personaje();

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.devolverObraConPersonajes(obraId).get();
            setPersonajes = obraAEditar.getPersonajes();

            for(Personaje p : setPersonajes){
                if(p.getId().equals(personajeId)){
                    personajeARemover = p;
                }
            }

            setPersonajes.remove(personajeARemover);
            obraAEditar.setPersonajes(setPersonajes);

            return ResponseEntity.ok(obraAEditar);
        }else{
            return ResponseEntity.ok(new Obra(-9999999L,"Obra no encontrada",null,null,null));
        }


    }

}
