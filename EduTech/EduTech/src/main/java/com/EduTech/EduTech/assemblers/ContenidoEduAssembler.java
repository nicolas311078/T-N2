package com.EduTech.EduTech.assemblers;
import com.EduTech.EduTech.controller.ContenidoEduControllerV2;
import com.EduTech.EduTech.model.ContenidoEdu;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
public class ContenidoEduAssembler implements RepresentationModelAssembler<ContenidoEdu, EntityModel<ContenidoEdu>> {
    @Override
    public EntityModel<ContenidoEdu> toModel(ContenidoEdu contenidoEdu) {
        return EntityModel.of(contenidoEdu,
                linkTo(methodOn(ContenidoEduControllerV2.class).getContenidoEduById(contenidoEdu.getId())).withSelfRel(),
                linkTo(methodOn(ContenidoEduControllerV2.class).getAllContenidos()).withRel("contenidos"));
    }
}
