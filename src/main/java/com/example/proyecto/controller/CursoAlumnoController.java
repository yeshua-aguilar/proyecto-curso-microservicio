package com.example.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.proyecto.dto.AlumnosCursoDTO;
import com.example.proyecto.dto.ResponseDto;
import com.example.proyecto.service.CursoAlumnoService;


@RestController
@RequestMapping("/curso-alumnos")
public class CursoAlumnoController {
	private final CursoAlumnoService cursoAlumnoService;

    @Autowired
    public CursoAlumnoController(CursoAlumnoService cursoAlumnoService) {
        this.cursoAlumnoService = cursoAlumnoService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> crearAlumnoCurso(@RequestBody AlumnosCursoDTO alumnosCursoDTO) {
        ResponseDto responseDto = cursoAlumnoService.crearAlumnoCurso(alumnosCursoDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> obtenerAlumnosCursoPorId(@PathVariable int id) {
        ResponseDto responseDto = cursoAlumnoService.obtenerAlumnosCursoPorId(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> obtenerTodosLosAlumnosCurso() {
        ResponseDto responseDto = cursoAlumnoService.obtenerTodosLosAlumnosCurso();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> eliminarAlumnosCurso(@PathVariable int id) {
        ResponseDto responseDto = cursoAlumnoService.eliminarAlumnosCurso(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
