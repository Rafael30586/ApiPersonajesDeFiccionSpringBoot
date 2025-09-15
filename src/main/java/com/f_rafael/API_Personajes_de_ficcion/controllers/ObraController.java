package com.f_rafael.API_Personajes_de_ficcion.controllers;

import com.f_rafael.API_Personajes_de_ficcion.dtos.ObraDto;
import com.f_rafael.API_Personajes_de_ficcion.models.ClasificacionObra;
import com.f_rafael.API_Personajes_de_ficcion.models.Obra;
import com.f_rafael.API_Personajes_de_ficcion.models.Personaje;
import com.f_rafael.API_Personajes_de_ficcion.services.ObraService;
import com.f_rafael.API_Personajes_de_ficcion.services.PersonajeService;
import com.f_rafael.API_Personajes_de_ficcion.utils.Transform;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
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
        return new ResponseEntity<ObraDto>(service.guardar(obra), HttpStatus.CREATED);
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

    @GetMapping("/por-periodo") // Funciona
    public ResponseEntity<List<ObraDto>> mostrarPorPeriodo(@RequestParam int desde, @RequestParam int hasta){
        if(desde > hasta)
            return ResponseEntity.ok(List.of(new ObraDto(-999999L,
                    "El primer valor del intervalo debe ser menor que el segundo",null,null,null,null)));

        if(hasta > (LocalDate.now().getYear() + 3) )
            return ResponseEntity.ok(List.of(new ObraDto(-999999L,"Se ha superado el límite de año permitido", null,null,null,null)));

        return ResponseEntity.ok(service.buscarPorPeriodo(desde,hasta));
    }

    @GetMapping("/por-clasificacion/{clasificacion}") // Funciona
    public ResponseEntity<List<ObraDto>> mostrarPorClasificacion(@PathVariable("clasificacion") String clasificacion){
        ClasificacionObra[] valoresDeClasificacion = ClasificacionObra.values();
        String clasificacionEnMayuscula = clasificacion.toUpperCase();

        for(ClasificacionObra co : valoresDeClasificacion){
            if(co.toString().equals(clasificacionEnMayuscula)){
                return ResponseEntity.ok(service.buscarPorClasificacion(co));
            }
        }

        return ResponseEntity.ok(List.of(new ObraDto(-99999L,"Argumento incorrecto",null,null,null,null)));
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

    @PatchMapping("/cambiar-imagenes/{obra-id}") // Funciona
    public ResponseEntity<ObraDto> cambiarImagenes(@PathVariable("obra-id") Long obraId,
                                                   @RequestBody Set<String> imagenes){
        Obra obraAEditar;
        if(service.encontrarPorId(obraId).isPresent()){
            obraAEditar = service.encontrarPorId(obraId).get();
            obraAEditar.setUrlImagenes(imagenes);

            return ResponseEntity.ok(service.actualizar(obraAEditar));
        }else{
            return ResponseEntity.ok(new ObraDto(-9999L, "Obra no encontrada",null,null,null,null));
        }
    }

    @PatchMapping("/editar-fecha/{obra-id}") // Funciona
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


}
