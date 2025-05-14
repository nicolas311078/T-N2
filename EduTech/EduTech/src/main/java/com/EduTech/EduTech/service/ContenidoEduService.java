package com.EduTech.EduTech.service;

import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.repository.ContenidoEduRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContenidoEduService {
    @Autowired
    private ContenidoEduRepository contenidoEduRepository;

    public List<ContenidoEdu> findAll(){return contenidoEduRepository.findAll();}

    public ContenidoEdu findById(long id){return contenidoEduRepository.findById(id).get();}

    public ContenidoEdu save(ContenidoEdu contenidoEdu) {
        return contenidoEduRepository.save(contenidoEdu);
    }

    public void delete(long id) {
        contenidoEduRepository.deleteById(id);
    }
}
