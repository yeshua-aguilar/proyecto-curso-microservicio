package com.example.proyecto.service;



import com.example.proyecto.dto.AlumnosCursoDTO;
import com.example.proyecto.dto.ResponseDto;


public interface CursoAlumnoService {
	
	ResponseDto crearAlumnoCurso(AlumnosCursoDTO alumnosCursoDTO);
	ResponseDto obtenerAlumnosCursoPorId(int id);
	ResponseDto obtenerTodosLosAlumnosCurso();
 	ResponseDto eliminarAlumnosCurso(int id);

}