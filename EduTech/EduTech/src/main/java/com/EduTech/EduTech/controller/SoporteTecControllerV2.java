package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.assemblers.SoporteTecModelAssembler;
import com.EduTech.EduTech.model.SoporteTec;
import com.EduTech.EduTech.service.SoporteTecService;
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
public class SoporteTecControllerV2 {

    @Autowired
    private SoporteTecService soporteTecService;

    @Autowired
    private SoporteTecModelAssembler assembler;
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<SoporteTec>> getAllSoportes() {
        List<EntityModel<SoporteTec>> soportes = soporteTecService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(soportes,
                linkTo(methodOn(SoporteTecControllerV2.class).getAllSoportes()).withSelfRel());
    }

    @GetMapping(value = "/{numeroConsulta}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<SoporteTec> getSoporteTecById(@PathVariable Integer numeroConsulta) {
        SoporteTec soporteTec = soporteTecService.findById(numeroConsulta);
        return assembler.toModel(soporteTec);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<SoporteTec>> createSoporteTec(@RequestBody SoporteTec soporteTec) {
        SoporteTec newSoporteTec = soporteTecService.save(soporteTec);
        return ResponseEntity
                .created(linkTo(methodOn(SoporteTecControllerV2.class).getSoporteTecById(newSoporteTec.getNumeroConsulta())).toUri())
                .body(assembler.toModel(newSoporteTec));
    }

    @PutMapping(value = "/{numeroConsulta}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<SoporteTec>> updateSoporteTec(@PathVariable Integer numeroConsulta, @RequestBody SoporteTec soporteTec) {
        soporteTec.setNumeroConsulta(numeroConsulta);
        SoporteTec newSoporteTec = soporteTecService.save(soporteTec);
        return ResponseEntity.ok(assembler.toModel(newSoporteTec));
    }

    @DeleteMapping(value = "/{numeroConsulta}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteSoporteTec(@PathVariable Integer numeroConsulta) {
        soporteTecService.deleteById(numeroConsulta);
        return ResponseEntity.noContent().build();
    }
}
