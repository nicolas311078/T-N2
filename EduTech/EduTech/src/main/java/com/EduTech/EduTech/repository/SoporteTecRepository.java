package com.EduTech.EduTech.repository;

import com.EduTech.EduTech.model.SoporteTec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoporteTecRepository extends JpaRepository<SoporteTec, Long> {
}
