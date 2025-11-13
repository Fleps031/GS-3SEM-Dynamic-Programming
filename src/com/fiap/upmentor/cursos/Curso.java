package com.fiap.upmentor.cursos;

public class Curso extends EntidadeCurso{
	
	private int numeroAulas;
	private int aulasAssistidas;
	private double percentualCurso;
	
	private boolean cursoCompleto;
	
	
	private Aula head;
	
	
	public Curso(String[] tituloAulas, String codigo, String titulo) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.aulasAssistidas = 0;
		this.percentualCurso = 0.00;
		
		for(int i = 0; i < tituloAulas.length; i = i + 1) {
			this.adicionarAula(tituloAulas[i], Integer.toString(i));
		}
 		
		this.setNumeroAulas(this.getNumeroAulas());
		
	}
	
	public Curso(Curso copia) {
		this.codigo = copia.codigo;
		this.titulo = copia.titulo;
		this.head = copia.head;
		this.aulasAssistidas = 0;
		this.percentualCurso = 0.00;
		this.numeroAulas = copia.numeroAulas;
		this.cursoCompleto = copia.cursoCompleto;
		
	}
	
	public void adicionarAula(String titulo, String id) {
		Aula novaAula = new Aula(id, titulo, false);
		
		if(head == null) {
			head = novaAula;
		} else {
			Aula atual = head;
			
			while(atual.proximaAula != null) {
				atual = atual.proximaAula;
			}
			
			atual.proximaAula = novaAula;
		}
		
	}
	
	private String formatarAulaAssistida(boolean assistida) {
		if(assistida) {
			return "Concluída";
		}
		return "Não vista";
	}
	
	public void exibirAulas() {
		Aula atual = head;
		
		System.out.println("AULAS DO CURSO " + "[" +this.titulo + "]\n");
		while(atual != null) {
			System.out.println(atual.titulo + " | " + formatarAulaAssistida(atual.assistida));
			atual = atual.proximaAula;
		}
		System.out.println("");
	}
	
	public int getNumeroAulas() {
		Aula atual = head;
		int contagem = 0;
		
		while(atual != null) {
			atual = atual.proximaAula;
			contagem = contagem + 1;
		}
		
		return contagem;
	}
	
	public void assistirAula() {
		if(!this.cursoCompleto) {
			Aula atual = head;
			
			while(atual.assistida) {
				atual = atual.proximaAula;
			}
			
			atual.assistida = true;
			this.aulasAssistidas = this.aulasAssistidas + 1;
			this.setPercentualCurso();
			
			/*Caso a aula assistida seja a última do curso (Verificando pelo próximo item da lista encadeada ser vazio), 
			completar o curso.*/
			if(atual.proximaAula == null) {
				this.cursoCompleto = true;
			}
		}else {
			System.out.println("ERRO: Curso concluido!");
		}
		

		
	}
	
	public void exibirCurso() {
		System.out.println(this.getTitulo() + 
				" |  [" + Integer.toString(this.aulasAssistidas) 
				+ "/" + Integer.toString(this.numeroAulas) + "]" 
				+ " | " + Double.toString(this.percentualCurso * 100) + "%"
		);
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean getCursoCompleto() {
		return cursoCompleto;
	}

	public void setCursoCompleto(boolean cursoCompleto) {
		this.cursoCompleto = cursoCompleto;
		if(cursoCompleto) {
			
		}
		this.aulasAssistidas = this.numeroAulas;
		this.percentualCurso = 1;
	}

	public void setNumeroAulas(int numeroAulas) {
		this.numeroAulas = numeroAulas;
	}
	
	public double getPercentualCurso() {
		return this.percentualCurso;
	}
	
	public int getAulasAssistidas() {
		return this.aulasAssistidas;
	}

	public void setPercentualCurso() {
		System.out.println("Set percentual!" + this.getAulasAssistidas() + "/" + this.getNumeroAulas());
		
		this.percentualCurso =  (double) this.getAulasAssistidas()/this.getNumeroAulas();
		System.out.println(this.percentualCurso);
	}
}
