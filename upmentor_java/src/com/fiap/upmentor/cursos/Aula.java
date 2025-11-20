package com.fiap.upmentor.cursos;

/**
 * Representa uma aula individual dentro de um curso.
 * Utiliza uma estrutura de lista encadeada, onde cada aula aponta para a próxima.
 */
public class Aula extends EntidadeCurso {

	boolean assistida;
	Aula proximaAula;
	
	/**
	 * Constrói uma aula com código, título e estado inicial (assistida ou não).
	 * A referência para a próxima aula começa como nula.
	 *
	 * @param codigo Código identificador da aula
	 * @param titulo Nome/título da aula
	 * @param assistida Indica se a aula já foi assistida pelo aluno
	 */
	public Aula(String codigo, String titulo, boolean assistida) {
		this.codigo = codigo;
		this.assistida = assistida;
		this.titulo = titulo;
		this.proximaAula = null;
	}
}
