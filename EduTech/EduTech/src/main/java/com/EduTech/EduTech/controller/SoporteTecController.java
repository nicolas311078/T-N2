package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.model.SoporteTec;
import com.EduTech.EduTech.service.SoporteTecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
public class SoporteTecController {

    @Autowired
    private SoporteTecService soporteTecService;

    @GetMapping
    public ResponseEntity<List<SoporteTec>> lister() {
        List<SoporteTec> soporteTecs = soporteTecService.findAll();
        if (soporteTecs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(soporteTecs);
    }

    @PostMapping
    public ResponseEntity<SoporteTec> guardar(@RequestBody SoporteTec soporteTec){
        SoporteTec incidenciaNueva = soporteTecService.save(soporteTec);
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaNueva);
    }

    @GetMapping("/{numeroConsulta}")
    public ResponseEntity<SoporteTec> buscar(@PathVariable Long numeroConsulta) {
        try {
            SoporteTec soporteTec = soporteTecService.findById(numeroConsulta);
            return ResponseEntity.ok(soporteTec);
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{numeroConsulta}")
    public ResponseEntity<SoporteTec> actualizar(@PathVariable Long numeroConsulta, @RequestBody SoporteTec soporteTec) {
        try {
            SoporteTec pac = soporteTecService.findById(numeroConsulta);
            pac.setNumeroConsulta(numeroConsulta);
            pac.setTipoIncidencia(soporteTec.getTipoIncidencia());
            pac.setDescripcion(soporteTec.getDescripcion());
            pac.setCorreoSolicitante(soporteTec.getCorreoSolicitante());

            soporteTecService.save(pac);
            return ResponseEntity.ok(pac);
        } catch (Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{numeroConsulta}")
    public ResponseEntity<?> eliminar(@PathVariable Long numeroConsulta) {
        try {
            soporteTecService.delete(numeroConsulta);
            return ResponseEntity.noContent().build();
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
}
