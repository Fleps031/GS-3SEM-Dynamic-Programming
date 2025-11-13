# 🧾 Relatório Técnico — Estruturas de Dados e Complexidades  
### Projeto: **UpMentor**
**Disciplina:** Domain Driven Design  
**Autores:** Felipe Gomes Molinari Lopes | RM 559885
**Descrição:**  
Este relatório apresenta a análise das estruturas de dados utilizadas no projeto *UpMentor*, abordando suas **complexidades temporais e espaciais**.  
O sistema foi desenvolvido com base nos princípios de **DDD (Domain Driven Design)** e nas boas práticas de **organização modular e orientada a objetos**.

---

## 🧠 1. Resumo Teórico das Estruturas de Dados

O sistema **UpMentor** emprega diferentes estruturas de dados para gerenciar cursos, aulas, gestores e liderados.  
Cada uma dessas estruturas foi escolhida conforme as necessidades de acesso, ordenação e iteração.  
Abaixo está um resumo teórico das principais estruturas utilizadas:

| Estrutura de Dados | Descrição Teórica | Complexidade Temporal (geral) | Complexidade Espacial | Observações |
|--------------------|------------------|-------------------------------|-----------------------|--------------|
| **Stack (Pilha)** | Estrutura LIFO (*Last In, First Out*), onde o último elemento inserido é o primeiro removido. Usada em contextos de histórico, recursão e controle hierárquico. | Inserção: O(1) <br> Remoção: O(1) <br> Busca: O(n) | O(n) | Ideal para representar cursos e progresso de forma sequencial. |
| **Queue (Fila)** | Estrutura FIFO (*First In, First Out*), onde o primeiro elemento inserido é o primeiro removido. | Inserção: O(1) <br> Remoção: O(1) <br> Busca: O(n) | O(n) | Útil para gerenciar cursos pendentes em ordem de chegada. |
| **ArrayList** | Implementa `List` usando um vetor dinâmico. Permite acesso direto por índice e cresce automaticamente conforme necessário. | Acesso: O(1) <br> Inserção: O(1)* <br> Remoção: O(n) <br> Busca: O(n) | O(n) | Cresce dinamicamente, mas pode causar *reallocations*. |
| **Linked List (Lista Encadeada)** | Estrutura onde cada elemento aponta para o próximo. Facilita inserções e remoções dinâmicas. | Inserção/Remoção: O(1) <br> Busca: O(n) | O(n) | Usada para encadear aulas dentro de um curso. |

---

## 🧩 2. Classes e Estruturas de Dados

### **Classe: `Aula`**
**Pacote:** `com.fiap.upmentor.cursos`  
**Descrição:**  
Representa uma **aula individual** e atua como um **nó de uma lista encadeada**, apontando para a próxima aula do curso.

| Elemento | Função Principal | Estrutura de Dados | Complexidade Temporal | Complexidade Espacial | Observações |
|-----------|------------------|--------------------|-----------------------|-----------------------|--------------|
| `boolean assistida` | Indica se a aula foi concluída. | Primitivo | O(1) | O(1) | Controle simples de estado. |
| `Aula proximaAula` | Encadeamento entre aulas. | Lista Encadeada | O(1) | O(n) | Cada aula aponta para a próxima. |

---

### **Classe: `EntidadeCurso`**
**Pacote:** `com.fiap.upmentor.cursos`  
**Descrição:**  
Classe base para `Aula` e `Curso`, padronizando os atributos `codigo` e `titulo`.

| Elemento | Função Principal | Estrutura de Dados | Complexidade Temporal | Complexidade Espacial | Observações |
|-----------|------------------|--------------------|-----------------------|-----------------------|--------------|
| `String codigo`, `String titulo` | Identificadores de curso/aula. | String | O(1) | O(1) | Base comum para entidades de ensino. |

---

### **Classe: `Gestor`**
**Pacote:** `com.fiap.upmentor.usuarios`  
**Descrição:**  
Responsável por **gerenciar liderados e atribuir cursos**.  
Utiliza uma **lista dinâmica (`ArrayList`)** para armazenar liderados.

| Método | Função Principal | Estrutura de Dados | Complexidade Temporal | Complexidade Espacial | Observações |
|---------|------------------|--------------------|-----------------------|-----------------------|--------------|
| `atribuirLiderado(Liderado l)` | Adiciona liderado à lista. | `ArrayList<Liderado>` | O(1)* (amortizado) | O(n) | Inserção direta na lista. |
| `atribuirCurso(Curso c, Liderado l)` | Atribui curso ao liderado. | `Stack<Curso>` | O(1) | O(1) | Operação simples de empilhamento. |
| `exibirLiderados()` | Exibe todos os liderados. | `ArrayList<Liderado>` | O(n) | O(n) | Itera sobre a lista completa. |

---

### **Classe: `Liderado`**
**Pacote:** `com.fiap.upmentor.usuarios`  
**Descrição:**  
Mantém a pilha de cursos atribuídos e implementa lógica de ordenação e progresso.  
Além disso, **agora possui uma fila de cursos pendentes** (`Queue<Curso>`) para gerenciar cursos futuros em ordem FIFO.

| Método | Função Principal | Estrutura de Dados | Complexidade Temporal | Complexidade Espacial | Observações |
|---------|------------------|--------------------|-----------------------|-----------------------|--------------|
| `buscarCurso(String codigoBuscado)` | Busca curso por código. | `Stack<Curso>` | O(n) | O(1) | Percorre a pilha linearmente; usar `.equals()` para Strings. |
| `quickSort(Stack<Curso> stack)` | Ordena pilha recursivamente. | `Stack<Curso>` | O(n log n) (média) | O(n) (temporário) | Implementa QuickSort manual adaptado a pilhas. |
| `concluirCurso(String codigo)` | Marca curso como completo. | `Stack<Curso>` | O(n) | O(1) | Atualiza curso específico. |
| `assistirAula(String codigo)` | Avança no curso selecionado. | `Stack<Curso>` + `Curso` | O(n + m) | O(1) | Busca curso (O(n)) + percorre aulas (O(m)). |
| `atribuirCurso(Curso novoCurso)` | Adiciona novo curso à pilha. | `Stack<Curso>` | O(1) | O(1) | Operação direta de empilhamento. |
| `exibirCursos()` | Ordena e exibe cursos. | `Stack<Curso>` | O(n log n) | O(n) | Combina ordenação com exibição. |
| `adicionarCursoNaFila(Curso curso)` | Adiciona um curso na fila de pendentes. | `Queue<Curso>` (LinkedList) | O(1) | O(1) | Enfileira curso para iniciar depois. |
| `iniciarProximoCurso()` | Remove o próximo curso da fila e empilha como ativo. | `Queue<Curso>` + `Stack<Curso>` | O(1) | O(1) | `poll()` da fila e `push()` na pilha. |
| `exibirFilaCursosPendentes()` | Lista os cursos presentes na fila de pendentes. | `Queue<Curso>` | O(n) | O(n) | Percorre a fila em ordem FIFO. |

---

### **Classe: `Sistema` (Plataforma)**
**Pacote:** `com.fiap.upmentor.plataforma`  
**Descrição:**  
Classe de controle principal que gerencia o fluxo de menus, ações e a interação entre gestores, liderados e cursos.  
O menu foi estendido para permitir interação com a **fila de cursos pendentes** dentro de cada `Liderado`.

| Método | Função Principal | Estrutura de Dados | Complexidade Temporal | Complexidade Espacial | Observações |
|---------|------------------|--------------------|-----------------------|-----------------------|--------------|
| `main()` | Inicia o sistema e menus principais. | `ArrayList`, `Stack`, `Queue` | Depende da ação | O(n) | Entrada principal do programa. |
| (menu) `Adicionar curso à fila de um liderado` | Permite escolher um liderado e adicionar um curso à fila de pendentes. | `Queue<Curso>` (interno ao `Liderado`) | O(1) | O(1) | Interface para `adicionarCursoNaFila()` do `Liderado`. |
| (menu) `Iniciar próximo curso da fila` | Inicia o próximo curso pendente de um liderado (move da fila para a pilha de cursos ativos). | `Queue<Curso>` + `Stack<Curso>` | O(1) | O(1) | Interface para `iniciarProximoCurso()` do `Liderado`. |
| (menu) `Exibir fila de cursos pendentes` | Exibe a fila atual de cursos pendentes de um liderado. | `Queue<Curso>` | O(n) | O(n) | Interface para `exibirFilaCursosPendentes()` do `Liderado`. |
| `listarGestores()`, `listarLiderados()`, `listarCursos()` | Exibe listas de entidades para seleção. | `ArrayList` | O(n) | O(n) | Operações auxiliares de listagem. |

---

## 🧱 3. Relação Entre Classes

| Componente | Estrutura Central | Papel no Sistema | Relações Principais |
|-------------|-------------------|------------------|---------------------|
| `EntidadeCurso` | Herança | Define atributos comuns | `Aula`, `Curso` |
| `Aula` | Lista Encadeada | Sequência de aulas | `Curso` |
| `Curso` | Lista Encadeada | Conjunto de aulas | `Aula` |
| `Gestor` | ArrayList | Gerencia liderados | `Liderado`, `Curso` |
| `Liderado` | Stack + Queue | Armazena cursos ativos (Stack) e pendentes (Queue) | `Gestor`, `Curso`, `Aula` |
| `Sistema` | Menu / Controller | Orquestra interações e permite manipular fila de cursos | Todos os módulos |

---

