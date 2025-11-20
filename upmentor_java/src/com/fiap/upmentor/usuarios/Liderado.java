package com.fiap.upmentor.usuarios;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import com.fiap.upmentor.cursos.Curso;

public class Liderado extends Usuario {

    Stack<Curso> cursos;
    int cursosCompletos;
    Queue<Curso> filaDeCursosPendentes;

    /**
     * Constrói um novo Liderado com nome e código identificador.
     *
     * @param nome   Nome do liderado.
     * @param codigo Código único do usuário.
     */
    public Liderado(String nome, String codigo) {
        this.setNome(nome);
        this.setCodigo(codigo);
        this.cursosCompletos = 0;
        this.cursos = new Stack<Curso>();
        this.filaDeCursosPendentes = new LinkedList<Curso>();
    }

    /**
     * Busca um curso pelo código dentro da lista de cursos do liderado.
     *
     * @param codigoBuscado Código do curso desejado.
     * @return Índice do curso encontrado ou -1 se não existir.
     */
    public int buscarCurso(String codigoBuscado) {
        for (int i = 0; i < this.cursos.size(); i = i + 1) {
            if (codigoBuscado.equals(this.cursos.get(i).getCodigo())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Ordena uma pilha de cursos utilizando o algoritmo QuickSort,
     * comparando pelo percentual de conclusão do curso.
     *
     * @param stack Pilha de cursos a ser ordenada.
     * @return Nova pilha ordenada em ordem crescente de progresso.
     */
    public Stack<Curso> quickSort(Stack<Curso> stack) {
        if (stack.size() <= 1) {
            return stack;
        }

        Curso pivot = stack.pop();

        Stack<Curso> menores = new Stack<>();
        Stack<Curso> maiores = new Stack<>();

        while (!stack.isEmpty()) {
            Curso current = stack.pop();
            if (current.getPercentualCurso() < pivot.getPercentualCurso()) {
                menores.push(current);
            } else {
                maiores.push(current);
            }
        }

        menores = quickSort(menores);
        maiores = quickSort(maiores);

        Stack<Curso> sorted = new Stack<>();
        Stack<Curso> temp = new Stack<>();

        while (!maiores.isEmpty()) temp.push(maiores.pop());
        while (!temp.isEmpty()) sorted.push(temp.pop());

        sorted.push(pivot);

        temp.clear();
        while (!menores.isEmpty()) temp.push(menores.pop());
        while (!temp.isEmpty()) sorted.push(temp.pop());

        return sorted;
    }

    /**
     * Adiciona um curso à fila de cursos pendentes do liderado.
     *
     * @param curso Curso a ser adicionado à fila.
     */
    public void adicionarCursoNaFila(Curso curso) {
        this.filaDeCursosPendentes.add(curso);
        System.out.println("Curso [" + curso.getTitulo() + "] adicionado à fila de pendentes de " + this.getNome());
    }

    /**
     * Inicia o próximo curso presente na fila de cursos pendentes.
     * Move o curso da fila para a pilha de cursos ativos.
     */
    public void iniciarProximoCurso() {
        if (this.filaDeCursosPendentes.isEmpty()) {
            System.out.println("Nenhum curso pendente na fila para " + this.getNome());
            return;
        }

        Curso proximoCurso = this.filaDeCursosPendentes.poll();
        this.cursos.push(proximoCurso);
        System.out.println("Curso iniciado: [" + proximoCurso.getTitulo() + "] para " + this.getNome());
    }

    /**
     * Exibe todos os cursos presentes na fila de pendentes.
     */
    public void exibirFilaCursosPendentes() {
        if (this.filaDeCursosPendentes.isEmpty()) {
            System.out.println("Fila de cursos pendentes vazia para " + this.getNome());
            return;
        }

        System.out.println("\nFila de cursos pendentes - " + this.getNome() + ":");
        int pos = 1;
        for (Curso curso : this.filaDeCursosPendentes) {
            System.out.println(pos + ". " + curso.getTitulo());
            pos++;
        }
    }

    /**
     * Conclui um curso do liderado com base no código informado.
     * Marca o curso como completo caso ainda não esteja.
     *
     * @param codigoCurso Código do curso a ser concluído.
     */
    public void concluirCurso(String codigoCurso) {
        int codigoEncontrado = this.buscarCurso(codigoCurso);

        if (codigoEncontrado >= 0) {
            if (this.cursos.get(codigoEncontrado).getCursoCompleto()) {
                System.out.println("ERRO: Curso já foi concluido!");
            } else {
                this.cursos.get(codigoEncontrado).setCursoCompleto(true);
                this.cursosCompletos = this.cursosCompletos + 1;
            }
        }
    }

    /**
     * Exibe todos os cursos do liderado em ordem crescente de progresso.
     * A lista é ordenada automaticamente antes da exibição.
     */
    public void exibirCursos() {
        System.out.println("");
        System.out.println("CURSOS ATRIBUIDOS - [" + this.getNome() + "]\n");

        this.cursos = quickSort(cursos);

        for (Curso curso : this.cursos) {
            System.out.println(cursos.indexOf(curso) + 1 + ".");
            curso.exibirCurso();
            System.out.println();
        }
    }

    /**
     * Executa a ação de assistir uma aula em um curso específico,
     * caso o liderado esteja inscrito nele.
     *
     * @param codigoCurso Código do curso a ser assistido.
     */
    public void assistirAula(String codigoCurso) {
        if (this.buscarCurso(codigoCurso) >= 0) {
            this.cursos.get(this.buscarCurso(codigoCurso)).assistirAula();
        } else {
            System.out.println("ERRO: Usuário não está inscrito no curso!");
        }
    }

    /**
     * Adiciona um curso diretamente à pilha de cursos ativos do liderado.
     *
     * @param novoCurso Curso a ser adicionado.
     */
    public void atribuirCurso(Curso novoCurso) {
        this.cursos.push(novoCurso);
    }
}
