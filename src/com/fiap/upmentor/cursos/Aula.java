package com.fiap.upmentor.cursos;

public class Aula extends EntidadeCurso{
	boolean assistida;
	Aula proximaAula;
	
	public Aula(String codigo, String titulo, boolean assistida) {
		this.codigo = codigo;
		this.assistida = assistida;
		this.titulo = titulo;
		this.proximaAula = null;
	}
}




