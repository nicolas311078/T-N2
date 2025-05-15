package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.model.Valoracion;
import com.EduTech.EduTech.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/valoracion")

public class ValoracionController {
    @Autowired
    private ValoracionService valoracionService;

    @GetMapping
    public ResponseEntity<List<Valoracion>>listar(){
        List<Valoracion> valoraciones = valoracionService.findAll();
        if (valoraciones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(valoraciones);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Valoracion> guardar(@RequestBody Valoracion valoracion){
        Valoracion nuevaValoracion= valoracionService.save(valoracion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaValoracion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> buscar(@PathVariable Integer id){
        try{
            Valoracion valoracion = valoracionService.findById(id);
            return ResponseEntity.ok(valoracion);

        }catch (Exception e ){
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Valoracion> actualizar(@PathVariable Integer id,@RequestBody Valoracion valoracion){
        try{
            Valoracion val = valoracionService.findById(id);
            val.setId(id);
            val.setCalificacion(valoracion.getCalificacion());
            val.setComentario(valoracion.getComentario());
            val.setCorreoUsuario(val.getCorreoUsuario());
            valoracionService.save(val);
            return ResponseEntity.ok(valoracion);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            valoracionService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
