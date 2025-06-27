package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.assemblers.CursoModelAssembler;
import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.service.CursoService;
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
@RequestMapping("/api/v2/cursos")
@Tag( name= "Cursos v2", description = "Operaciones Relacionadas con los cursos")
public class CursoControllerV2 {

        @Autowired
        private CursoService cursoService;

        @Autowired
        private CursoModelAssembler assembler;

        @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
        @Operation(summary = "Obtener todos los cursos ", description = "Obtiene una lista con todos los cursos")
        @ApiResponses(value= {
                @ApiResponse(responseCode = "200", description = "Cursos obtenido exitosamente"),
                @ApiResponse(responseCode = "404", description = "cursos encontrados")
        })
        public CollectionModel<EntityModel<Curso>> getAllCursos() {
            List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());

            return CollectionModel.of(cursos, linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withSelfRel());
        }

        @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
        @Operation(summary = "Obtener un curso por codigo ", description = "Obtiene curso via codigo")
        @ApiResponses(value= {
                @ApiResponse(responseCode = "200", description = "Curso obtenido exitosamente"),
                @ApiResponse(responseCode = "404", description = "curso encontrado")
        })
        public EntityModel<Curso> getCursoById(@PathVariable Integer id) {
            Curso curso = cursoService.findById(id);
            return assembler.toModel(curso);
        }

        @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
        @Operation(summary = "Crear curso ", description = "Crea un nuevo curso")
        @ApiResponses(value= {
                @ApiResponse(responseCode = "200", description = "Curso creado exitosamente"),
                @ApiResponse(responseCode = "404", description = "curso no encontrado")
        })
        public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody Curso curso) {
            Curso newCurso = cursoService.save(curso);
            return ResponseEntity
                    .created(linkTo(methodOn(CursoControllerV2.class).getCursoById(newCurso.getId())).toUri())
                    .body(assembler.toModel(newCurso));
        }

        @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
        @Operation(summary = "Actualizar un curso", description = "Actualiza un curso existente")
        @ApiResponses(value= {
                @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente",
                        content =@Content(mediaType = "aplication/json",
                                schema = @Schema(implementation = Curso.class))),
                @ApiResponse(responseCode = "404", description = "Curso no encontrado")
        })
        public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable Integer id, @RequestBody Curso curso) {
            curso.setId(id);
            Curso updatedCurso = cursoService.save(curso);
            return ResponseEntity
                    .ok(assembler.toModel(updatedCurso));
        }

        @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
        @Operation(summary = "Eliminar un Curso ", description = "Elimina un curso existente")
        @ApiResponses(value= {
                @ApiResponse(responseCode = "200", description = "Curso Eliminado exitosamente"),
                @ApiResponse(responseCode = "404", description = "Curso no encontrado")
        })
        public ResponseEntity<?> deleteCurso(@PathVariable Integer id) {
            cursoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }