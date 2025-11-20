package com.fiap.upmentor.usuarios;

import java.util.Stack;
import com.fiap.upmentor.cursos.Curso;

/**
 * Representa um gestor responsável por administrar liderados
 * e atribuir cursos a eles.
 */
public class Gestor extends Usuario {

	Stack<Liderado> liderados;

	/**
	 * Constrói um gestor atribuindo um nome
	 * e inicializando a lista de liderados.
	 *
	 * @param nome Nome do gestor
	 */
	public Gestor(String nome) {
		this.setNome(nome);
		this.liderados = new Stack<Liderado>();
	}
	
	/**
	 * Busca um liderado pelo código dentro da lista de liderados do gestor.
	 *
	 * @param codigoLiderado Código do liderado a buscar
	 * @return true se encontrado, false caso contrário
	 */
	public boolean buscarLiderado(String codigoLiderado) {
		for (Liderado liderado : liderados) {
			if (codigoLiderado == liderado.getCodigo()) {
				return true;
			};
		}
		return false;
	}
	
	/**
	 * Atribui um curso a um liderado caso ele pertença ao gestor
	 * e ainda não esteja inscrito no curso.
	 *
	 * @param curso Curso a ser atribuído
	 * @param liderado Liderado que receberá o curso
	 * @return true se atribuído com sucesso, false caso contrário
	 */
	public boolean atribuirCurso(Curso curso, Liderado liderado) {
		if (buscarLiderado(liderado.getCodigo())) {
			if (liderado.buscarCurso(curso.getCodigo()) >= 0) {
				System.out.println("ERRO: Liderado já está inscrito no curso!");
				return false;
			};
			
			Curso cursoNovo = new Curso(curso);
			liderado.atribuirCurso(cursoNovo);
			return true;
		}
		
		System.out.println("ERRO: Liderado não está atribuido a esse gestor!");
		return false;
	}
	
	/**
	 * Atribui um novo liderado ao gestor,
	 * garantindo que ele ainda não esteja na lista.
	 *
	 * @param liderado Liderado a ser adicionado
	 * @return true se adicionado, false caso já exista
	 */
	public boolean atribuirLiderado(Liderado liderado) {
		if (buscarLiderado(liderado.getCodigo())) {
			System.out.println("ERRO: Liderado já atribuido a esse gestor!");
			System.out.println("Codigo liderado -> "  + liderado.getNome());
			System.out.println("Liderados atuais -> " + this.liderados.get(0).getNome());
			return false;
		}
		
		this.liderados.push(liderado);
		return true;
	}
	
	/**
	 * Exibe todos os liderados atribuídos ao gestor.
	 */
	public void exibirLiderados() {
		System.out.println("Liderados Do Gestor " + this.getNome() + "\n");
		for (int i = 0; i < liderados.size(); i = i + 1) {
			System.out.println("[" + (i + 1) + "] - " + liderados.get(i).getNome() + "\n");
		}
	}
	
	/**
	 * Retorna a pilha de liderados atribuídos ao gestor.
	 *
	 * @return Stack contendo os liderados
	 */
	public Stack<Liderado> getLiderados() {
		return this.liderados;
	}
}
