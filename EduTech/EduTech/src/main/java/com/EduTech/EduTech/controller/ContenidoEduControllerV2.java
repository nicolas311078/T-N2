package com.EduTech.EduTech.controller;
import com.EduTech.EduTech.assemblers.ContenidoEduAssembler;
import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.service.ContenidoEduService;
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

public class ContenidoEduControllerV2 {
    @Autowired
    private ContenidoEduService contenidoEduService;

    @Autowired
    private ContenidoEduAssembler assembler;
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<ContenidoEdu>> getAllContenidos() {
        List<EntityModel<ContenidoEdu>> contenidos = contenidoEduService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(contenidos,
                linkTo(methodOn(ContenidoEduControllerV2.class).getAllContenidos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<ContenidoEdu> getContenidoEduById(@PathVariable Integer id) {
        ContenidoEdu contenidoEdu = contenidoEduService.findById(id);
        return assembler.toModel(contenidoEdu);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ContenidoEdu>> createContenidoEdu(@RequestBody ContenidoEdu contenidoEdu) {
        ContenidoEdu newContenidoEdu = contenidoEduService.save(contenidoEdu);
        return ResponseEntity
                .created(linkTo(methodOn(ContenidoEduControllerV2.class).getContenidoEduById(newContenidoEdu.getId())).toUri())
                .body(assembler.toModel(newContenidoEdu));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ContenidoEdu>> updateContenidoEdu(@PathVariable Integer id, @RequestBody ContenidoEdu contenidoEdu) {
        contenidoEdu.setId(id);
        ContenidoEdu newContenidoEdu = contenidoEduService.save(contenidoEdu);
        return ResponseEntity.ok(assembler.toModel(newContenidoEdu));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteContenidoEdu(@PathVariable Integer id) {
        contenidoEduService .deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
