package com.example.proyecto.repository;

import com.example.proyecto.entity.Curso;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	
}



