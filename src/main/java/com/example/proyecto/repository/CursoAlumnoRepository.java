package com.example.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.entity.CursoAlumno;


@Repository
public interface CursoAlumnoRepository extends JpaRepository<CursoAlumno, Integer> {
	List<CursoAlumno> findByIdAlumno(int idCurso);
}

