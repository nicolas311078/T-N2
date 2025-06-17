package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.service.ContenidoEduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/contenido")
public class ContenidoEduController {

    @Autowired
    private ContenidoEduService contenidoEduService;

    @GetMapping
    public ResponseEntity<List<ContenidoEdu>> lister() {
        List<ContenidoEdu> contenidoEdus = contenidoEduService.findAll();
        if (contenidoEdus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contenidoEdus);
    }

    @PostMapping
    public ResponseEntity<ContenidoEdu> guardar(@RequestBody ContenidoEdu contenidoEdu){
        ContenidoEdu ContenidoNuevo = contenidoEduService.save(contenidoEdu);
        return ResponseEntity.status(HttpStatus.CREATED).body(ContenidoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContenidoEdu> buscar(@PathVariable Integer id) {
        try {
            ContenidoEdu contenidoEdu = contenidoEduService.findById(id);
            return ResponseEntity.ok(contenidoEdu);
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContenidoEdu> actualizar(@PathVariable Integer id, @RequestBody ContenidoEdu contenidoEdu) {
        try {
            ContenidoEdu pac = contenidoEduService.findById(id);
            pac.setId(id);
            pac.setEvaluacion(contenidoEdu.getEvaluacion());
            pac.setActividad(contenidoEdu.getActividad());
            pac.setProgresoEstudiante(contenidoEdu.getProgresoEstudiante());

            contenidoEduService.save(pac);
            return ResponseEntity.ok(pac);
        } catch (Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            contenidoEduService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
}
