package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.model.SoporteTec;
import com.EduTech.EduTech.service.SoporteTecService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
@Tag( name= "Soporte", description = "Operaciones Relacionadas con los soportes")
public class SoporteTecController {

    @Autowired
    private SoporteTecService soporteTecService;

    @GetMapping
    @Operation(summary = "Obtener todos los Soportes tecnicos", description = "Obtiene una lista con todos lo relacionado con el Soporte tecnico")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al obtener soporte tecnico")
    })
    public ResponseEntity<List<SoporteTec>> lister() {
        List<SoporteTec> soporteTecs = soporteTecService.findAll();
        if (soporteTecs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(soporteTecs);
    }

    @PostMapping
    @Operation(summary = "Crear soporte tecnico", description = "Crea un nuevo soporte tecnico")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "error al crear Soporte tecnico ")
    })

    public ResponseEntity<SoporteTec> guardar(@RequestBody SoporteTec soporteTec){
        SoporteTec incidenciaNueva = soporteTecService.save(soporteTec);
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaNueva);
    }

    @GetMapping("/{numeroConsulta}")
    @Operation(summary = "Obtener soporte tecnico por codigo ", description = "Obtiene soporte tecnico via codigo")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al obtener soporte tecnico")
    })
    public ResponseEntity<SoporteTec> buscar(@PathVariable Integer numeroConsulta) {
        try {
            SoporteTec soporteTec = soporteTecService.findById(numeroConsulta);
            return ResponseEntity.ok(soporteTec);
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{numeroConsulta}")
    @Operation(summary = "Actualizar un soporte tecnico", description = "Actualiza un soporte tecnico existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico actualizado exitosamente",
                    content =@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Soporte tecnico no encontrado")
    })
    public ResponseEntity<SoporteTec> actualizar(@PathVariable Integer numeroConsulta, @RequestBody SoporteTec soporteTec) {
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
    @Operation(summary = "Eliminar un soporte tecnico", description = "Elimina un soporte tecnico existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico Eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Soporte tecnico no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Integer numeroConsulta) {
        try {
            soporteTecService.deleteById(numeroConsulta);
            return ResponseEntity.noContent().build();
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
}
