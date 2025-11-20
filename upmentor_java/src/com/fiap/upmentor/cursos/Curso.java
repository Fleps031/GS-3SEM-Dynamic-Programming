package com.fiap.upmentor.cursos;

/**
 * Representa um curso composto por uma lista encadeada de aulas.
 * Controla progresso, exibição e conclusão do curso.
 */
public class Curso extends EntidadeCurso{
	
	private int numeroAulas;
	private int aulasAssistidas;
	private double percentualCurso;
	
	private boolean cursoCompleto;
	
	private Aula head;

	/**
	 * Constrói um novo curso gerando automaticamente as aulas
	 * com base na lista de títulos fornecida.
	 *
	 * @param tituloAulas Lista de títulos das aulas
	 * @param codigo Código identificador do curso
	 * @param titulo Nome do curso
	 */
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

	/**
	 * Construtor de cópia.
	 * Clona estrutura e metadados, mas reinicia o progresso.
	 *
	 * @param copia Curso original a ser copiado
	 */
	public Curso(Curso copia) {
		this.codigo = copia.codigo;
		this.titulo = copia.titulo;
		this.head = copia.head;
		this.aulasAssistidas = 0;
		this.percentualCurso = 0.00;
		this.numeroAulas = copia.numeroAulas;
		this.cursoCompleto = copia.cursoCompleto;	
	}

	/**
	 * Adiciona uma nova aula ao final da lista encadeada.
	 *
	 * @param titulo Título da aula
	 * @param id Identificador único da aula
	 */
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

	/**
	 * Formata o texto de exibição do status da aula.
	 *
	 * @param assistida Se a aula já foi assistida
	 * @return "Concluída" ou "Não vista"
	 */
	private String formatarAulaAssistida(boolean assistida) {
		if(assistida) {
			return "Concluída";
		}
		return "Não vista";
	}

	/**
	 * Exibe todas as aulas do curso com seus respectivos status.
	 */
	public void exibirAulas() {
		Aula atual = head;
		
		System.out.println("AULAS DO CURSO " + "[" +this.titulo + "]\n");
		while(atual != null) {
			System.out.println(atual.titulo + " | " + formatarAulaAssistida(atual.assistida));
			atual = atual.proximaAula;
		}
		System.out.println("");
	}

	/**
	 * Conta e retorna o número de aulas existentes
	 * percorrendo a lista encadeada.
	 *
	 * @return Total de aulas
	 */
	public int getNumeroAulas() {
		Aula atual = head;
		int contagem = 0;
		
		while(atual != null) {
			atual = atual.proximaAula;
			contagem = contagem + 1;
		}
		
		return contagem;
	}

	/**
	 * Marca a próxima aula ainda não assistida como concluída.
	 * Atualiza percentual e conclui o curso caso seja a última aula.
	 */
	public void assistirAula() {
		if(!this.cursoCompleto) {
			Aula atual = head;
			
			while(atual.assistida) {
				atual = atual.proximaAula;
			}
			
			atual.assistida = true;
			this.aulasAssistidas = this.aulasAssistidas + 1;
			this.setPercentualCurso();
			
			// Caso seja a última aula da lista
			if(atual.proximaAula == null) {
				this.cursoCompleto = true;
			}
		}else {
			System.out.println("ERRO: Curso concluido!");
		}
	}

	/**
	 * Exibe um resumo do curso contendo:
	 * título, aulas assistidas e percentual concluído.
	 */
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

	/**
	 * Marca o curso como concluído,
	 * define todas as aulas como assistidas
	 * e atualiza o percentual para 100%.
	 *
	 * @param cursoCompleto Se o curso foi concluído
	 */
	public void setCursoCompleto(boolean cursoCompleto) {
		this.cursoCompleto = cursoCompleto;
		if(cursoCompleto) {}
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

	/**
	 * Atualiza o percentual de conclusão do curso
	 * com base nas aulas assistidas.
	 */
	public void setPercentualCurso() {		
		this.percentualCurso =  (double) this.getAulasAssistidas()/this.getNumeroAulas();
	}
}
