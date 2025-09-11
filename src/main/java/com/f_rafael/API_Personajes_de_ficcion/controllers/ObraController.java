package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDto;
import com.f_rafael.API_Personajes_de_ficcion.models.ClasificacionObra;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.services.ObraService;
import com.f_rafael.API_Personajes_de_ficcion.services.PersonajeService;
import com.f_rafael.API_Personajes_de_ficcion.utils.Transform;
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

    @GetMapping("/{id}") // Funciona
    public ResponseEntity<ObraDto> findById(@PathVariable Long id){
        ObraDto obra = service.devolverUnaConSusPersonajes(id);
        return ResponseEntity.ok(obra);
    }

    @GetMapping //Funciona
    public ResponseEntity<List<ObraDto>> encontrarTodas(){
        return ResponseEntity.ok(service.devolverObrasConPersonajes());
    }

    @DeleteMapping("/{id}") // Funciona
    public ObraDto deleteById(@PathVariable Long id){
        return service.borrarPorId(id);
    }

    @PostMapping // Funciona
    public ResponseEntity<ObraDto> save(@RequestBody Obra obra){
        return ResponseEntity.ok(service.guardar(obra));
    }

    @PutMapping // Funciona
    public ResponseEntity<ObraDto> update(@RequestBody Obra obra){
        return ResponseEntity.ok(service.guardar(obra));
    }

    @GetMapping("/por-titulo/{titulo}") // Funciona
    public ResponseEntity<List<ObraDto>> buscarPorTitulo(@PathVariable String titulo){
        String tituloSinGuiones = titulo.replace('-',' ');
        return ResponseEntity.ok(service.encontrarPotTitulo(tituloSinGuiones));
    }

    @GetMapping("/por-fragmento-titulo/{fragmento-titulo}") // Funciona
    public ResponseEntity<List<ObraDto>> buscarPorFragmentoTitulo(@PathVariable("fragmento-titulo") String fragmentoTitulo){
        return ResponseEntity.ok(service.encontrarPorFragmentoTitulo(fragmentoTitulo));
    }

    @PatchMapping("/editar-titulo/{obra-id}") // Funciona
    public ResponseEntity<ObraDto> editarTitulo(@PathVariable("obra-id") Long obraId,
                                             @RequestParam("nuevo-titulo") String nuevoTitulo){
        Obra obraAEditar;
        String nuevoTituloSinGuiones = Transform.quitarGuionesBajos(nuevoTitulo);

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.encontrarPorId(obraId).get();
            obraAEditar.setTitulo(nuevoTituloSinGuiones);

            return ResponseEntity.ok(service.actualizar(obraAEditar));
        }else{
            return ResponseEntity.ok(new ObraDto(-9999999L,"Personaje no encontrado",null,null,null,null));
        }

    }

    @PatchMapping("/editar-fecha/{obra-id}")
    public ResponseEntity<ObraDto> editarFecha(@PathVariable("obra-id") Long obraId,
                                            @RequestParam("fecha-lanzamiento") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaLanzamiento){
        Obra obraAEditar;

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.encontrarPorId(obraId).get();
            obraAEditar.setFechaLanzamiento(fechaLanzamiento);

            return ResponseEntity.ok(service.actualizar(obraAEditar));
        }else{
            return ResponseEntity.ok(new ObraDto(-9999999L,"PErsonaje no encontrado",null,null,null,null));
        }

    }

    @PatchMapping("/editar-clasificacion/{obra-id}") // Funciona
    public ResponseEntity<Obra> editarClasificacion(@PathVariable("obra-id") Long obraId,
                                                    @RequestParam("numero-clasificacion") int numeroClasificacion){
        Obra obraAEditar;
        ClasificacionObra[] clasicaciones = ClasificacionObra.values();
        ClasificacionObra clasificacionAEditar;

        if(numeroClasificacion < clasicaciones.length){
            clasificacionAEditar = clasicaciones[numeroClasificacion];
        }else{
            return ResponseEntity.ok(new Obra(-99999L,"Clasificacion no encontrada",null,null,null,null));
        }

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.encontrarPorId(obraId).get();
            obraAEditar.setClasificacion(clasificacionAEditar);

            return ResponseEntity.ok(obraAEditar);
        }else{
            return ResponseEntity.ok(new Obra(-999999L,"Obra no encontrada",null,null,null,null));
        }

    }

    @PatchMapping("/agregar-personaje/{obra-id}/{personaje-id}")
    public ResponseEntity<ObraDto> agregarPersonaje(@PathVariable("obra-id") Long obraId,
                                                 @PathVariable("personaje-id") Long personajeId){
        Obra obraAEditar;
        Personaje personajeAAgregar;
        Set<Personaje> setPersonajes;

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.encontrarPorId(obraId).get();
            setPersonajes = obraAEditar.getPersonajes();
        }else{
            return ResponseEntity.ok(new ObraDto(-9999999L,"Obra no encontrada",null,null,null,null));
        }

        if(personajeService.encontrarPorId(personajeId).isPresent()){
            personajeAAgregar = personajeService.encontrarPorId(personajeId).get();
        }else{
            return ResponseEntity.ok(new ObraDto(-99999L,"Personaje no encontrado",null,null,null,null));
        }

        setPersonajes.add(personajeAAgregar);
        obraAEditar.setPersonajes(setPersonajes);


        return ResponseEntity.ok(service.actualizar(obraAEditar));
    }

    @PatchMapping("/remover-personaje/{obra-id}/{personaje-id}")
    public ResponseEntity<ObraDto> removerPersonaje(@PathVariable("obra-id") Long obraId,
                                                 @PathVariable("personaje-id") Long personajeId){
        Obra obraAEditar;
        Set<Personaje> setPersonajes;
        Personaje personajeARemover = new Personaje();
        boolean personajePresente = false;

        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.encontrarPorId(obraId).get();
            setPersonajes = obraAEditar.getPersonajes();

            for(Personaje p : setPersonajes){
                if(p.getId().equals(personajeId)){
                    personajePresente = true;
                    personajeARemover = p;
                }
            }

            if(!personajePresente) return ResponseEntity.ok(new ObraDto(-9999L,"Personaje no encontrado",null,null,null,null));

            setPersonajes.remove(personajeARemover);
            obraAEditar.setPersonajes(setPersonajes);

            return ResponseEntity.ok(service.actualizar(obraAEditar));
        }else{
            return ResponseEntity.ok(new ObraDto(-9999999L,"Obra no encontrada",null,null,null,null));
        }


    }

}
