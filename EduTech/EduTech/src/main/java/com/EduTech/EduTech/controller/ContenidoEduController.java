package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.service.ContenidoEduService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
@RestController
@RequestMapping("/api/v1/contenido")
@Tag(name="Contenido", description = "Operaciones relacionadas para ver el contenido eductivo de los cursos")
public class ContenidoEduController {

    @Autowired
    private ContenidoEduService contenidoEduService;

    @GetMapping
    @Operation(summary = "Obtener contenido de los cursos ", description = "Obtiene una lista con todos lo relacionado con el curso")
    public ResponseEntity<List<ContenidoEdu>> lister() {
        List<ContenidoEdu> contenidoEdus = contenidoEduService.findAll();
        if (contenidoEdus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contenidoEdus);
    }

    @PostMapping
    @Operation(summary = "Crear contenido ", description = "Crea contenido como actividad, evaluacion y progreso del estudiante")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Contenido creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al crear Contenido")
    })
    public ResponseEntity<ContenidoEdu> guardar(@RequestBody ContenidoEdu contenidoEdu){
        ContenidoEdu ContenidoNuevo = contenidoEduService.save(contenidoEdu);
        return ResponseEntity.status(HttpStatus.CREATED).body(ContenidoNuevo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener contenido ", description = "Obtiene una lista con todo el contenido")
    public ResponseEntity<ContenidoEdu> buscar(@PathVariable Integer id) {
        try {
            ContenidoEdu contenidoEdu = contenidoEduService.findById(id);
            return ResponseEntity.ok(contenidoEdu);
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar contenido ", description = "Actualiza el contenido existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Contenido actualizado exitosamente",
                    content =@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
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
    @Operation(summary = "Eliminar Contenido ", description = "Elimina un contenido existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Contenido Eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            contenidoEduService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
}
