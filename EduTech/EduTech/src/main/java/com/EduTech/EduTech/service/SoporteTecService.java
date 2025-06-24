package com.EduTech.EduTech.service;

import com.EduTech.EduTech.model.SoporteTec;
import com.EduTech.EduTech.repository.SoporteTecRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SoporteTecService {

    @Autowired
    private SoporteTecRepository soporteTecRepository;

    public List<SoporteTec> findAll() {
        return soporteTecRepository.findAll();
    }

    public SoporteTec findById(Integer numeroConsulta) {
        return soporteTecRepository.findById(numeroConsulta).get();
    }

    public SoporteTec save(SoporteTec soporteTec) {
        return soporteTecRepository.save(soporteTec);
    }

    public void delete(Integer numeroConsulta) {
        soporteTecRepository.deleteById(numeroConsulta);
    }
}
