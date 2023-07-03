package com.example.proyecto.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto.Client.AlumnoFeignClient;
import com.example.proyecto.dto.AlumnosCursoDTO;
import com.example.proyecto.dto.ResponseDto;
import com.example.proyecto.entity.*;
import com.example.proyecto.repository.CursoAlumnoRepository;
import com.example.proyecto.repository.CursoRepository;
import com.example.proyecto.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CursoAlumnoServiceImpl implements CursoAlumnoService {
    
    private final CursoAlumnoRepository cursoAlumnoRepository;
    
    @Autowired
    public CursoAlumnoServiceImpl(CursoAlumnoRepository cursoAlumnoRepository) {
        this.cursoAlumnoRepository = cursoAlumnoRepository;
    }
    
    @Autowired
    private AlumnoFeignClient alumnoFeignClient;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Override
    public ResponseDto crearAlumnoCurso(AlumnosCursoDTO alumnosCursoDTO) {
        CursoAlumno cursoAlumno = new CursoAlumno();
        cursoAlumno.setIdCurso(alumnosCursoDTO.getIdCurso());
        cursoAlumno.setIdAlumno(alumnosCursoDTO.getIdAlumno());
        
        cursoAlumnoRepository.save(cursoAlumno);
        
        return new ResponseDto("200","Alumno curso creado exitosamente", cursoAlumno);
    }
    
    @Override
    public ResponseDto obtenerAlumnosCursoPorId(int id) {
        Optional<CursoAlumno> optionalCursoAlumno = cursoAlumnoRepository.findById(id);
        List<Curso> cursos = cursoRepository.findAll();
        if (optionalCursoAlumno.isPresent()) {
            CursoAlumno cursoAlumno = optionalCursoAlumno.get();
            AlumnosCursoDTO alumnosCursoDTO = new AlumnosCursoDTO();
            alumnosCursoDTO.setId(cursoAlumno.getId());
            alumnosCursoDTO.setIdCurso(cursoAlumno.getIdCurso());
            alumnosCursoDTO.setIdAlumno(cursoAlumno.getIdAlumno());
            
            try {
                ResponseDto responseDto = alumnoFeignClient.readAlumnos(cursoAlumno.getIdAlumno());
                if (responseDto != null && responseDto.getData() != null) {
                	ObjectMapper mapper = new ObjectMapper();
                	AlumnosDto alumnoDto = mapper.convertValue(responseDto.getData(), AlumnosDto.class);
                    alumnosCursoDTO.setNombreAlumno(alumnoDto.getNombres());
                }
            } catch (Exception e) {
                // Manejar la excepción aquí
                // Puedes imprimir el mensaje de error o realizar alguna acción específica
                e.printStackTrace();
            }
            
            Curso curso = cursos.stream().filter(p -> p.getId() == cursoAlumno.getIdCurso()).findFirst()
					.orElse(null);

			if (curso != null) {
				alumnosCursoDTO.setNombreCurso(curso.getNombre());
			}
            
            
            return new ResponseDto("200","Alumnos curso por id obtenidos exitosamente", alumnosCursoDTO);
        } else {
            return new ResponseDto("404","No se encontró ningún alumno curso con el ID proporcionado", null);
        }
    }
    
    @Override
    public ResponseDto obtenerTodosLosAlumnosCurso() {
        List<CursoAlumno> alumnosCurso = cursoAlumnoRepository.findAll();
        List<Curso> cursos = cursoRepository.findAll();
        List<AlumnosCursoDTO> alumnosCursoDTOList = new ArrayList<>();
        for (CursoAlumno cursoAlumno : alumnosCurso) {
            AlumnosCursoDTO alumnosCursoDTO = new AlumnosCursoDTO();
            alumnosCursoDTO.setId(cursoAlumno.getId());
            alumnosCursoDTO.setIdCurso(cursoAlumno.getIdCurso());
            alumnosCursoDTO.setIdAlumno(cursoAlumno.getIdAlumno());
            
         // Llamar al método readAlumnos() del FeignClient para obtener el nombre del alumno
            try {
                ResponseDto responseDto = alumnoFeignClient.readAlumnos(cursoAlumno.getIdAlumno());
                if (responseDto != null && responseDto.getData() != null) {
                	ObjectMapper mapper = new ObjectMapper();
                	AlumnosDto alumnoDto = mapper.convertValue(responseDto.getData(), AlumnosDto.class);
                    alumnosCursoDTO.setNombreAlumno(alumnoDto.getNombres());
                }
            } catch (Exception e) {
                // Manejar la excepción aquí
                // Puedes imprimir el mensaje de error o realizar alguna acción específica
                e.printStackTrace();
            }
            
            Curso curso = cursos.stream().filter(p -> p.getId() == cursoAlumno.getIdCurso()).findFirst()
					.orElse(null);

			if (curso != null) {
				alumnosCursoDTO.setNombreCurso(curso.getNombre());
			}
            
            alumnosCursoDTOList.add(alumnosCursoDTO);
        }
        
        return new ResponseDto("200","Todos los alumnos curso obtenidos exitosamente", alumnosCursoDTOList);
    }
    
    @Override
    public ResponseDto eliminarAlumnosCurso(int id) {
        Optional<CursoAlumno> optionalCursoAlumno = cursoAlumnoRepository.findById(id);
        
        if (optionalCursoAlumno.isPresent()) {
            CursoAlumno cursoAlumno = optionalCursoAlumno.get();
            cursoAlumnoRepository.delete(cursoAlumno);
            return new ResponseDto("200","Alumnos curso eliminados exitosamente", cursoAlumno);
        } else {
            return new ResponseDto("404","No se encontró ningún alumno en el curso con el ID proporcionado", null);
        }
    }
}