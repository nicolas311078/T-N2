package com.EduTech.EduTech.service;

import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll(){return cursoRepository.findAll();}

    public Curso findById(Integer id){return cursoRepository.findById(id).get();}

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void delete(Integer id) {
        cursoRepository.deleteById(id);
    }
}
