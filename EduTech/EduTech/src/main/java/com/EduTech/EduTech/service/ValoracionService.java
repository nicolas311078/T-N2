package com.EduTech.EduTech.service;

import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.model.Valoracion;
import com.EduTech.EduTech.repository.ValoracionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Transactional
public class ValoracionService {
    @Autowired
    private ValoracionRepository valoracionRepository;
    public List<Valoracion> findAll(){return valoracionRepository.findAll();}

    public Valoracion findById(long id){return valoracionRepository.findById(id).get();}

    public Valoracion save(Valoracion valoracion){return  valoracionRepository.save(valoracion);}

    public void delete(Long id){valoracionRepository.deleteById(id);}
}
