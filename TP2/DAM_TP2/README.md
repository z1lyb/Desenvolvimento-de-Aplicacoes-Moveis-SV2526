# Assignment 2 - Weather App

Course: Desenvolvimento de Aplicações Móveis  
Student(s): Zilda Biai (51606)  
Date: 12/04/2026  
Repository URL: [DAM_TP2](https://github.com/z1lyb/Desenvolvimento-de-Aplicacoes-Moveis-SV2526/tree/main/TP2/DAM_TP2)

---

# Tutorial 2 - Kotlin Exercises

## 1. Introduction
O presente repositório foi criado no âmbito da disciplina de Desenvolvimento de Aplicações Móveis, durante a realização 
do segundo tutorial da disciplina. Este relatório consistirá na descrição do trabalho referente à _**Section 1: Kotlin Exercises**_.  
Durante a sua realização, são consolidados mais conceitos da linguagem Kotlin, como _extension functions_, _generics_ e composição de funções.
## 2. System Overview
O sistema desenvolvido é composto pela resolução dos quatro exercícios:
1. Event Log Processing  
  Desenvolvimento dos conceitos de _sealed classes_, _extension functions_ e _higher-order functions_, com
o desenvolvimento de uma classe que processa eventos por meio de funções.
2. Memory Cache  
    Criação de uma cache de memória, consolidando os conceitos de _generics_ e funções com parâmetros de tipo.
3. Configurable Data Pipeline  
    Criação de uma _pipeline_ de processamento de dados configurável, utilizando _lambdas_ e composição de funções para 
usar uma sequência de passos configuráveis para alterar uma sequência de strings.
4. 2D Vector Library  
    Implementação de uma classe representativa de um vetor 2D e as operações a si associadas, de modo a usar o conceito de 
_operator overloading_.  

Todas as classes criadas são testadas com dados fornecidos com esse objetivo.
## 3. Architecture and Design
O projeto está organizado por _packages_, situadas dentro do _package_ raiz dam e separadas por exercício. A arquitetura segue a seguinte estrutura:
* dam
  * exer_1
    * Event
  * exer_2
    * Cache
  * exer_3
    * Pipeline
  * exer_4
    * Vec2

## 4. Implementation
### Exercise 1 - Event Log Processing
Foi criada a _sealed class_ **Event**, que controla a herança das suas subclasses, **Login**, **Purchase** e **Logout**.
Cada uma das classes filhas guarda o _username_ do autor do evento, e _timestamp_ da sua utilização, com parâmetros adicionais
caso necessário. 
A classe conta com duas _extension functions_:
* **filterByUser** - recebe um _username_ e retorna uma lista dos eventos a ele afetados.
* **totalSpent** - calcula o montante total gasto pelo utilizador entre todas as suas operações do tipo Purchase.

Adicionalmente, a _higher-order function_ **processEvents** recebe uma lista de eventos e um _lambda_ a ser aplicado a todos eles. 
Após aplicar cada um, imprime uma descrição da operação realizada.

### Exercise 2 - Memory Cache
Neste exercício, é criada a classe genérica Cache, que mapeia chaves a valores de qualquer tipo num _mutableMap_ interno.  
É composta pelas funções:
* **put** - cria ou altera uma entrada.
* **get** - retorna o valor associado a uma chave, ou null se esta não existir.
* **evict** - remove uma entrada.
* **size** - retorna o número de entradas.
* **getOrPut** - se houver uma determinada chave no mapa, retorna-a. caso contrário, computa-a e retorna o valor.
* **transform** - aplica uma transformação ao valor de uma chave.
* **snapshot** - torna o mapa inalterável, retornando-o.
* **filterValues** - filtra os valores por uma condição

### Exercise 3 - Configurable Data Pipeline
Implementação de um _pipeline_ que guarda um conjunto de transformações e as aplica a uma lista de Strings.  
É composta por:
* **addStage** - adiciona um passo e o seu nome ao _pipeline_.
* **execute**
* **describe**
* **then**
* **compose**


### Exercise 4 - 2D Vector Library

<!-- Implementation details: main modules, components, algorithms, and relevant code
excerpts. -->
## 5. Testing and Validation
<!-- Testing strategy, test cases, scenarios, edge cases, and known limitations. -->
## 6. Usage Instructions
<!-- How to run the project: requirements, setup, configuration, and execution steps. -->

---

# Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections

## 7. Prompting Strategy
<!-- Describe the prompts used with AI tools, their purpose, and how they evolved.
Include representative examples. -->
## 8. Autonomous Agent Workflow
<!-- Explain how AI tools or agents contributed to development: planning, coding,
debugging, testing, documentation, etc. -->
## 9. Verification of AI-Generated Artifacts
<!-- Describe how you verified correctness of AI-generated code/designs (testing,
manual review, static analysis, etc.). -->
## 10. Human vs AI Contribution
<!-- Clearly state which parts were primarily human-developed and which were
AI-assisted. -->
## 11. Ethical and Responsible Use
<!-- Reflect on risks, limitations, biases, or inappropriate outputs from AI tools
and how they were handled. -->

---

# Development Process

## 12. Version Control and Commit History
<!-- Describe how version control was used. The commit history must reflect
continuous work (not only final commits). -->
## 13. Difficulties and Lessons Learned
<!-- Main challenges, mistakes, insights, and skills acquired during the assignment.
-->
## 14. Future Improvements
<!-- Possible extensions, optimizations, or features that could be added in future work. -->

---

## 15. AI Usage Disclosure (Mandatory)
<!-- List all AI tools used (e.g., ChatGPT, Copilot, etc.), how they were used, and
confirmation that you remain responsible for all content. -->
