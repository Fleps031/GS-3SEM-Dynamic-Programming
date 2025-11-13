package com.fiap.upmentor.usuarios;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import com.fiap.upmentor.cursos.Curso;

public class Liderado extends Usuario {
    Stack<Curso> cursos;
    int cursosCompletos;

    // NOVO: fila de cursos pendentes
    Queue<Curso> filaDeCursosPendentes;

    public Liderado(String nome, String codigo) {
        this.setNome(nome);
        this.setCodigo(codigo);
        this.cursosCompletos = 0;
        this.cursos = new Stack<Curso>();
        this.filaDeCursosPendentes = new LinkedList<Curso>(); // inicializa fila
    }

    public int buscarCurso(String codigoBuscado) {
        for (int i = 0; i < this.cursos.size(); i = i + 1) {
            if (codigoBuscado.equals(this.cursos.get(i).getCodigo())) {
                return i;
            }
        }
        return -1;
    }

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
        while (!maiores.isEmpty()) {
            temp.push(maiores.pop());
        }
        while (!temp.isEmpty()) {
            sorted.push(temp.pop());
        }

        sorted.push(pivot);

        temp.clear();
        while (!menores.isEmpty()) {
            temp.push(menores.pop());
        }
        while (!temp.isEmpty()) {
            sorted.push(temp.pop());
        }

        return sorted;
    }

    // ==========================
    // MÉTODOS RELACIONADOS À FILA
    // ==========================

    public void adicionarCursoNaFila(Curso curso) {
        this.filaDeCursosPendentes.add(curso);
        System.out.println("Curso [" + curso.getTitulo() + "] adicionado à fila de pendentes de " + this.getNome());
    }

    public void iniciarProximoCurso() {
        if (this.filaDeCursosPendentes.isEmpty()) {
            System.out.println("Nenhum curso pendente na fila para " + this.getNome());
            return;
        }

        Curso proximoCurso = this.filaDeCursosPendentes.poll();
        this.cursos.push(proximoCurso);
        System.out.println("Curso iniciado: [" + proximoCurso.getTitulo() + "] para " + this.getNome());
    }

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

    // ==========================
    // RESTANTE DO CÓDIGO ORIGINAL
    // ==========================

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

    public int getCursosCompletos() {
        return cursosCompletos;
    }

    public Stack<Curso> getCursos() {
        return cursos;
    }

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

    public void assistirAula(String codigoCurso) {
        if (this.buscarCurso(codigoCurso) >= 0) {
            this.cursos.get(this.buscarCurso(codigoCurso)).assistirAula();
        } else {
            System.out.println("ERRO: Usuário não está inscrito no curso!");
        }
    }

    public void atribuirCurso(Curso novoCurso) {
        this.cursos.push(novoCurso);
    }

    public void setCursos(Stack<Curso> cursos) {
        this.cursos = cursos;
    }
}
