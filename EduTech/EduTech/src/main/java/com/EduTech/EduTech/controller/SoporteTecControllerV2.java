package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.assemblers.SoporteTecModelAssembler;
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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/soportes")
@Tag( name= "Soporte V2", description = "Operaciones Relacionadas con los soportes")
public class SoporteTecControllerV2 {

    @Autowired
    private SoporteTecService soporteTecService;

    @Autowired
    private SoporteTecModelAssembler assembler;
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los Soportes tecnicos", description = "Obtiene una lista con todos lo relacionado con el Soporte tecnico")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al obtener soporte tecnico")
    })
    public CollectionModel<EntityModel<SoporteTec>> getAllSoportes() {
        List<EntityModel<SoporteTec>> soportes = soporteTecService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(soportes,
                linkTo(methodOn(SoporteTecControllerV2.class).getAllSoportes()).withSelfRel());
    }

    @GetMapping(value = "/{numeroConsulta}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener soporte tecnico por codigo ", description = "Obtiene soporte tecnico via codigo")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al obtener soporte tecnico")
    })
    public EntityModel<SoporteTec> getSoporteTecById(@PathVariable Integer numeroConsulta) {
        SoporteTec soporteTec = soporteTecService.findById(numeroConsulta);
        return assembler.toModel(soporteTec);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear soporte tecnico", description = "Crea un nuevo soporte tecnico")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "error al crear Soporte tecnico ")
    })
    public ResponseEntity<EntityModel<SoporteTec>> createSoporteTec(@RequestBody SoporteTec soporteTec) {
        SoporteTec newSoporteTec = soporteTecService.save(soporteTec);
        return ResponseEntity
                .created(linkTo(methodOn(SoporteTecControllerV2.class).getSoporteTecById(newSoporteTec.getNumeroConsulta())).toUri())
                .body(assembler.toModel(newSoporteTec));
    }

    @PutMapping(value = "/{numeroConsulta}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un soporte tecnico", description = "Actualiza un soporte tecnico existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico actualizado exitosamente",
                    content =@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Soporte tecnico no encontrado")
    })
    public ResponseEntity<EntityModel<SoporteTec>> updateSoporteTec(@PathVariable Integer numeroConsulta, @RequestBody SoporteTec soporteTec) {
        soporteTec.setNumeroConsulta(numeroConsulta);
        SoporteTec newSoporteTec = soporteTecService.save(soporteTec);
        return ResponseEntity.ok(assembler.toModel(newSoporteTec));
    }

    @DeleteMapping(value = "/{numeroConsulta}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un soporte tecnico", description = "Elimina un soporte tecnico existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Soporte tecnico Eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Soporte tecnico no encontrado")
    })
    public ResponseEntity<?> deleteSoporteTec(@PathVariable Integer numeroConsulta) {
        soporteTecService.deleteById(numeroConsulta);
        return ResponseEntity.noContent().build();
    }
}
