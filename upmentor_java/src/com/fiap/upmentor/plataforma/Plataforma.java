package com.fiap.upmentor.plataforma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fiap.upmentor.cursos.Curso;
import com.fiap.upmentor.usuarios.Gestor;
import com.fiap.upmentor.usuarios.Liderado;

/**
 * Classe principal da plataforma UP Mentor. 
 * Responsável pelo fluxo interativo do sistema e menu principal,
 * permitindo o cadastro de gestores, liderados, cursos e ações entre eles.
 */
public class Plataforma {

    /**
     * Ponto de entrada do sistema. 
     * Exibe o menu principal e controla o loop de execução da plataforma.
     *
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Gestor> gestores = new ArrayList<>();
        List<Liderado> liderados = new ArrayList<>();
        List<Curso> cursos = new ArrayList<>();

        boolean executando = true;

        while (executando) {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1 - Cadastrar Gestor");
            System.out.println("2 - Cadastrar Liderado");
            System.out.println("3 - Cadastrar Curso");
            System.out.println("4 - Atribuir Liderado ao Gestor");
            System.out.println("5 - Atribuir Curso");
            System.out.println("6 - Exibir informações");
            System.out.println("7 - Adicionar curso à fila de um liderado");
            System.out.println("8 - Iniciar próximo curso da fila");
            System.out.println("9 - Exibir fila de cursos pendentes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro(sc);

            switch (opcao) {

                case 1 -> {
                    System.out.print("Nome do gestor: ");
                    String nomeGestor = sc.nextLine();
                    Gestor gestor = new Gestor(nomeGestor);
                    gestores.add(gestor);
                    System.out.print("Gestor cadastrado com sucesso!");
                }

                case 2 -> {
                    System.out.print("Nome do liderado: ");
                    String nomeLiderado = sc.nextLine();

                    System.out.print("Codigo do liderado: ");
                    String codigoLiderado = sc.nextLine();

                    Liderado liderado = new Liderado(nomeLiderado, codigoLiderado);
                    liderados.add(liderado);
                    System.out.println("Liderado cadastrado com sucesso!");
                }

                case 3 -> {
                    System.out.print("Nome do curso: ");
                    String nomeCurso = sc.nextLine();

                    System.out.print("Código do curso: ");
                    String codigo = sc.nextLine();

                    System.out.print("Quantas aulas o curso terá? ");
                    int qtdAulas = lerInteiro(sc);

                    String[] aulas = new String[qtdAulas];
                    for (int i = 0; i < qtdAulas; i++) {
                        System.out.print("Nome da aula " + (i + 1) + ": ");
                        aulas[i] = sc.nextLine();
                    }

                    Curso curso = new Curso(aulas, codigo, nomeCurso);
                    cursos.add(curso);
                    System.out.println("Curso cadastrado com sucesso!");
                }

                case 4 -> {
                    if (gestores.isEmpty() || liderados.isEmpty()) {
                        System.out.println("Cadastre pelo menos um gestor e um liderado antes!");
                        break;
                    }

                    System.out.println("Selecione o gestor:");
                    listarGestores(gestores);
                    int idxGestor = lerInteiro(sc) - 1;
                    if (!indiceValido(idxGestor, gestores.size()))
                        break;

                    Gestor gestor = gestores.get(idxGestor);

                    System.out.println("Selecione o liderado:");
                    listarLiderados(liderados);
                    int idxLiderado = lerInteiro(sc) - 1;
                    if (!indiceValido(idxLiderado, liderados.size()))
                        break;

                    Liderado liderado = liderados.get(idxLiderado);
                    gestor.atribuirLiderado(liderado);

                    System.out.println("Gestor atribuído com sucesso!");
                }

                case 5 -> {
                    if (gestores.isEmpty() || liderados.isEmpty() || cursos.isEmpty()) {
                        System.out.println("Cadastre pelo menos um gestor, um liderado e um curso antes!");
                        break;
                    }

                    System.out.println("Selecione o gestor:");
                    listarGestores(gestores);
                    int idxGestor = lerInteiro(sc) - 1;
                    if (!indiceValido(idxGestor, gestores.size()))
                        break;

                    Gestor gestor = gestores.get(idxGestor);

                    if (gestor.getLiderados().isEmpty()) {
                        System.out.println("Gestor sem liderados atribuidos!");
                        break;
                    }

                    gestor.exibirLiderados();

                    System.out.print("Escolha uma opção: ");
                    int opcaoLiderado = lerInteiro(sc);

                    Liderado tempLiderado = liderados.get(opcaoLiderado - 1);

                    System.out.println("Selecione o curso:");
                    listarCursos(cursos);
                    int idxCurso = lerInteiro(sc) - 1;
                    if (!indiceValido(idxCurso, cursos.size()))
                        break;

                    Curso curso = cursos.get(idxCurso);
                    gestor.atribuirCurso(curso, tempLiderado);

                    System.out.println("Curso atribuído com sucesso!");
                }

                case 7 -> {
                    if (liderados.isEmpty() || cursos.isEmpty()) {
                        System.out.println("Cadastre pelo menos um liderado e um curso antes!");
                        break;
                    }

                    System.out.println("Selecione o liderado:");
                    listarLiderados(liderados);
                    int idxLiderado = lerInteiro(sc) - 1;
                    if (!indiceValido(idxLiderado, liderados.size()))
                        break;

                    Liderado liderado = liderados.get(idxLiderado);

                    System.out.println("Selecione o curso para adicionar na fila:");
                    listarCursos(cursos);
                    int idxCurso = lerInteiro(sc) - 1;
                    if (!indiceValido(idxCurso, cursos.size()))
                        break;

                    Curso curso = cursos.get(idxCurso);
                    liderado.adicionarCursoNaFila(curso);
                }

                case 8 -> {
                    System.out.println("Selecione o liderado para iniciar o próximo curso da fila:");
                    listarLiderados(liderados);
                    int idxLiderado = lerInteiro(sc) - 1;
                    if (!indiceValido(idxLiderado, liderados.size()))
                        break;

                    Liderado liderado = liderados.get(idxLiderado);
                    liderado.iniciarProximoCurso();
                }

                case 9 -> {
                    System.out.println("Selecione o liderado para visualizar a fila de cursos:");
                    listarLiderados(liderados);
                    int idxLiderado = lerInteiro(sc) - 1;
                    if (!indiceValido(idxLiderado, liderados.size()))
                        break;

                    Liderado liderado = liderados.get(idxLiderado);
                    liderado.exibirFilaCursosPendentes();
                }

                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    executando = false;
                }

                default -> System.out.println("Opção inválida. Tente novamente!");
            }
        }

        sc.close();
    }

    /**
     * Lê um valor inteiro do usuário com validação,
     * repetindo até que uma entrada válida seja digitada.
     *
     * @param sc Scanner para leitura
     * @return valor inteiro válido
     */
    private static int lerInteiro(Scanner sc) {
        while (true) {
            try {
                int valor = Integer.parseInt(sc.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite um número inteiro: ");
            }
        }
    }

    /**
     * Valida se um índice está dentro do intervalo da lista.
     *
     * @param indice Índice informado
     * @param tamanho Tamanho da lista
     * @return true se válido, false caso contrário
     */
    private static boolean indiceValido(int indice, int tamanho) {
        if (indice < 0 || indice >= tamanho) {
            System.out.println("Índice inválido!");
            return false;
        }
        return true;
    }

    /**
     * Exibe a lista de gestores cadastrados com numeração visível.
     *
     * @param gestores Lista de gestores
     */
    private static void listarGestores(List<Gestor> gestores) {
        for (int i = 0; i < gestores.size(); i++) {
            System.out.println((i + 1) + " - " + gestores.get(i).getNome());
        }
    }

    /**
     * Exibe a lista de liderados cadastrados com numeração visível.
     *
     * @param liderados Lista de liderados
     */
    private static void listarLiderados(List<Liderado> liderados) {
        for (int i = 0; i < liderados.size(); i++) {
            System.out.println((i + 1) + " - " + liderados.get(i).getNome());
        }
    }

    /**
     * Exibe a lista de cursos cadastrados com numeração visível.
     *
     * @param cursos Lista de cursos
     */
    private static void listarCursos(List<Curso> cursos) {
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + " - " + cursos.get(i).getTitulo());
        }
    }
}
