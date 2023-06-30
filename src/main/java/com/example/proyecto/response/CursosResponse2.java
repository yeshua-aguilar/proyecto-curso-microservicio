package com.example.proyecto.response;

import java.util.List;

import com.example.proyecto.dto.CursoDTO;

public class CursosResponse2 {
	private String resultado;
    private List<CursoDTO> curso;

    public CursosResponse2(String resultado, List<CursoDTO> curso) {
        this.resultado = resultado;
        this.curso = curso;
    }

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public List<CursoDTO> getCurso() {
		return curso;
	}

	public void setCurso(List<CursoDTO> curso) {
		this.curso = curso;
	}

    
}
