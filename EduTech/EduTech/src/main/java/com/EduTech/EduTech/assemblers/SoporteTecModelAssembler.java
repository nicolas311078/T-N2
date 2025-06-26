package com.EduTech.EduTech.assemblers;
import com.EduTech.EduTech.controller.SoporteTecControllerV2;
import com.EduTech.EduTech.model.SoporteTec;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SoporteTecModelAssembler implements RepresentationModelAssembler<SoporteTec, EntityModel<SoporteTec>> {
    @Override
    public EntityModel<SoporteTec> toModel(SoporteTec soporteTec) {
        return EntityModel.of(soporteTec,
                linkTo(methodOn(SoporteTecControllerV2.class).getSoporteTecById(soporteTec.getNumeroConsulta())).withSelfRel(),
                linkTo(methodOn(SoporteTecControllerV2.class).getAllSoportes()).withRel("soportes"));
    }
}
