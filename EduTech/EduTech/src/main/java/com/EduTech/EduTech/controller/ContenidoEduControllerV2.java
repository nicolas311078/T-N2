package com.EduTech.EduTech.controller;
import com.EduTech.EduTech.assemblers.ContenidoEduModelAssembler;
import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.service.ContenidoEduService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/contenido")
@Tag(name="Contenido V2", description = "Operaciones relacionadas para ver el contenido eductivo de los cursos")
public class ContenidoEduControllerV2{
    @Autowired
    private ContenidoEduService contenidoEduService;

    @Autowired
    private ContenidoEduModelAssembler assembler;
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los contenido", description = "Obtiene una lista con todos lo relacionado con contenido")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Contenido obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al obtener el Contenido")
    })
    public CollectionModel<EntityModel<ContenidoEdu>> getAllContenidos() {
        List<EntityModel<ContenidoEdu>> contenidos = contenidoEduService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(contenidos,
                linkTo(methodOn(ContenidoEduControllerV2.class).getAllContenidos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener contenido por codigo", description = "Obtiene una lista con todo el contenido")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Contenido obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al obtener el Contenido")
    })
    public EntityModel<ContenidoEdu> getContenidoEduById(@PathVariable Integer id) {
        ContenidoEdu contenidoEdu = contenidoEduService.findById(id);
        return assembler.toModel(contenidoEdu);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear contenido ", description = "Crea contenido como actividad, evaluacion y progreso del estudiante")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Contenido creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al crear Contenido")
    })
    public ResponseEntity<EntityModel<ContenidoEdu>> createContenidoEdu(@RequestBody ContenidoEdu contenidoEdu) {
        ContenidoEdu newContenidoEdu = contenidoEduService.save(contenidoEdu);
        return ResponseEntity
                .created(linkTo(methodOn(ContenidoEduControllerV2.class).getContenidoEduById(newContenidoEdu.getId())).toUri())
                .body(assembler.toModel(newContenidoEdu));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un contenido ", description = "Actualiza el contenido existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Contenido actualizado exitosamente",
                    content =@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    public ResponseEntity<EntityModel<ContenidoEdu>> updateContenidoEdu(@PathVariable Integer id, @RequestBody ContenidoEdu contenidoEdu) {
        contenidoEdu.setId(id);
        ContenidoEdu newContenidoEdu = contenidoEduService.save(contenidoEdu);
        return ResponseEntity.ok(assembler.toModel(newContenidoEdu));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar Contenido ", description = "Elimina un contenido existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Contenido Eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    public ResponseEntity<?> deleteContenidoEdu(@PathVariable Integer id) {
        contenidoEduService .deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
