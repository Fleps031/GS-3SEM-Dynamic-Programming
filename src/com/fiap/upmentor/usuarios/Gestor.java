package com.fiap.upmentor.usuarios;

import java.util.Stack;

import com.fiap.upmentor.cursos.Curso;

public class Gestor extends Usuario {
	Stack<Liderado> liderados;
	
	public Gestor(String nome) {
		this.setNome(nome);
		this.liderados = new Stack<Liderado>();
	}
	
	
	public boolean buscarLiderado(String codigoLiderado) {
		for(Liderado liderado : liderados) {
			if(codigoLiderado == liderado.getCodigo()) {
				return true;
			};
			
		}
		return false;
	}
	
	public boolean atribuirCurso(Curso curso, Liderado liderado) {
		if(buscarLiderado(liderado.getCodigo())) {
			if(liderado.buscarCurso(curso.getCodigo()) >= 0) {
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
	
	public boolean atribuirLiderado(Liderado liderado) {
		if(buscarLiderado(liderado.getCodigo())) {
			System.out.println("ERRO: Liderado já atribuido a esse gestor!");
			System.out.println("Codigo liderado -> "  + liderado.getNome());
			System.out.println("Liderados atuais -> " + this.liderados.get(0).getNome());
			return false;
		}
		
		this.liderados.push(liderado);
		
		return true;

	}
	
	public void exibirLiderados() {
		System.out.println("Liderados Do Gestor " + this.getNome() + "\n");
		for(int i = 0; i < liderados.size(); i = i + 1) {
			System.out.println("[" + (i + 1) + "] - " + liderados.get(i).getNome() + "\n");
		}
	}
	
	public Stack<Liderado> getLiderados() {
		return this.liderados;
	}
		
}
