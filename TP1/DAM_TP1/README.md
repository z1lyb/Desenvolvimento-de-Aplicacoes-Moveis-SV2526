# Assignment 1 — Hello World


Course: Desenvolvimento de Aplicações Móveis  
Student(s): Zilda Biai A51606  
Date: 15/03/2026  
Repository URL: ___________

---

# Tutorial 1 - Hello Kotlin. Hello Android World!

## 1. Introduction
O repositório atual foi criado em âmbito do primeiro tutorial da disciplina de Desenvolvimento de Aplicações Móveis,
com o objetivo de introduzir alguns dos conceitos fundamentais da linguagem Kotlin.  
As secções do tutorial representadas neste projeto são:
* 2 - _Getting Started with Kotlin_
* 6 - _Exploring Kotlin Again_
Durante a realização do trabaho, foram criadas aprendizagens sobre programação orientada a objetos.

## 2. System Overview
O sistema desenvolvido neste repositório tem duas partes principais:
* Exercícios de introdução ao Kotlin: implementação de três programas pequenos para a exploração das funcionalidades da linguagem Kotlin:
  * Cálculo dos quadrados de números
  * Calculadora na consola virtual
  * Simulação da queda de uma bola
* Biblioteca virtual: criação de um sistema simples de biblioteca com funcionalidades de tratamento dos seus livros:
  * Adição e remoção de livros
  * Requisição e devolução de livros
  * Listagem de livros

## 3. Architecture and Design
O projeto está organizado em packages com origem no package **dam**, sendo criada uma para cada exercício.
A pasta **exer_vl**, correspondente à biblioteca virtual, utiliza programação orientada a objetos, tomando uso de classes abstratas, herança e _companion objects_.

## 4. Implementation
### Exercício 1 - Cálculo de quadrados
Foram testadas três formas de calcular os primeiros 50 quadrados perfeitos:
* Criação de um objeto IntArray e manipulação dos seus índices de modo que esta guarde os números pedidos.
* Utilização de um _range_ para guardar os números, e da função _map_ para os converter ao seu quadrado.
* Utilização do construtor da classe Array para converter os índices da lista ao quadrado do número seguinte, de forma semelhante ao IntArray.
### Exercício 2 - Calculadora
Foi criada uma calculadora que utiliza a consola, permitindo ao utilizador realizar operações aritméticas, booleanas e bitwise e tratando a resposta do utilizador com a função _when_.  
As operações realizadas podem ser:
* Aritméticas: Adição, subtração, multiplicação e divisão
* Booleanas: AND, OR e NOT
* Bitwise: Left shift e right shift, com resultados apresentados em bitwise, decimal e hexadecimal.  
A calculadora contém tratamento de erros como a introdução de valores inválidos para as operações, e a impossibilidade da divisão por zero.
### Exercício 3 - Queda de bola
Foi utilizada a função _generateSequence_ para simular os ressaltos da bola, com uma diminuição de 60% da altura em cada, sendo apresentados os que têm altura superior a um metro.
### Biblioteca virtual
Para a criação da biblioteca, foram implementadas classes correspondentes à sua estrutura e componentes:
* _Book_: classe abstrata que representa um livro e as suas propriedades principais, assim como o período de publicação e medidas de prevenção de números negativos de cópias disponíveis.
  * _DigitalBook_: representação de um livro digital, guardando os dados exclusivos a este tipo de livro.
  * _PhysicalBook_: representação de um livro físico, guardando os dados exclusivos a este tipo de livro.
* _Library_: representa o sistema da biblioteca, gere a requisição e devolução de livros, assim como a sua listagem.  

Foram implementados os _extended requirements_ adicionais, como métodos toString para cada classe que representem cada elemento da biblioteca, 
métodos para obter informação do armazenamento dos livrosm um _companion object_ à classe _Library_ que guarde o número de livros existente em todas as bibliotecas, e uma data class correspondente a um sócio da biblioteca.

## 5. Testing and Validation
Para cada componente, os testes foram realizados através da função _main_. Para a biblioteca, foi criado um ficheiro Kotlin adicional, virtual_library.kt, onde se encontra a função de teste.

## 6. Usage Instructions
Para executar o projeto:
1.  Instalar IntelliJ IDEA ou Android Studio
2.  Criar um projeto Kotlin
3.  Adicionar os ficheiros .kt
4.  Executar os ficheiros com a função main

---

# Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections

## 7. Prompting Strategy
Foram utilizados prompts para gerar partes do relatório a partir do código realizado manualmente.
## 8. Autonomous Agent Workflow
A ferramenta IA apenas foi utilizada como apoio para formatação do relatório. 
## 9. Verification of AI-Generated Artifacts
A documentação gerada foi manualmente verificada e substituída, caso necessário.
## 10. Human vs AI Contribution  
Não foi permitida a utilização de ferramentas de autocomplete ou inteligência artificial para a elaboração do código do projeto presente.
A inteligência artificial utilizada limita-se à realização deste ficheiro README e da sua formatação. 
## 11. Ethical and Responsible Use
A utilização de IA foi feita de forma responsável, garantindo compreensão da documentação e sua revisão manual.

---
# Development Process

## 12. Version Control and Commit History
Foi utilizado o _git_ para _version control_, sendo apenas realizados commits locais à medida que o projeto era realizado.
Os _commits_ foram incrementais, na sua maioria a cada exercício realizado ou ficheiro adicionado.

## 13. Difficulties and Lessons Learned
As principais dificuldades na elaboração do projeto deveram-se à adaptação à sintaxe do Kotlin em relação à de Java, assim como à criação de ficheiros e classes novos.
A adaptação no entanto foi rápida, devido à semelhança entre as linguagens.

## 14. Future Improvements
Em futuras versões deste projeto, pode existir a possibilidade de desenvolver uma biblioteca virtual mais complexa, com gestão dos seus membros e dos seus dados.

---

## 15. AI Usage Disclosure (Mandatory)
Ferramentas de IA utilizadas: ChatGPT.
Esta ferramenta foi apenas utilizada como apoio para o desenvolvimento do relatório, não sendo consultada durante a relaização dos exercícios.