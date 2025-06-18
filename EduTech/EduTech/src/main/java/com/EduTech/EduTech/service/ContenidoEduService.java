package com.EduTech.EduTech.service;

import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.repository.ContenidoEduRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class ContenidoEduService {
    @Autowired
    private ContenidoEduRepository contenidoEduRepository;

    public List<ContenidoEdu> findAll(){return contenidoEduRepository.findAll();}

    public ContenidoEdu findById(Integer id){return contenidoEduRepository.findById(id).get();}

    public ContenidoEdu save(ContenidoEdu contenidoEdu) {
        return contenidoEduRepository.save(contenidoEdu);
    }

    public void deleteById(Integer id) {
        contenidoEduRepository.deleteById(id);
    }
}
