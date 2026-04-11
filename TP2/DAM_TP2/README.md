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
    Criação de um _pipeline_ de processamento de dados configurável, utilizando _lambdas_ e composição de funções para 
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
Implementação e teste de um _pipeline_ que guarda um conjunto de transformações e as aplica a uma lista de Strings.  
É composta por:
* **addStage** - adiciona um passo e o seu nome ao _pipeline_.
* **execute** - executa todos os passos do _pipeline_ e retorna o seu resultado.
* **describe** - descreve o _pipeline_, indicando todos os seus passos.
* **then** - função criada para a composição de duas funções. Executa ambas em sequência.
* **compose** - recebe dois passos do _pipeline_ e junta-os, recorrendo à função anterior e voltando a colocar a transformação resultante no local devido da lista de passos.

São definidas também as _higher-order functions_ seguintes:
* **buildPipeline** - constrói um _pipeline_ ao lhe aplicar as funções inseridas como parâmetro.
* **fork** - executa duas pipelines paralelamente com o mesmo input, retornando um Pair com os seus resultados.

### Exercise 4 - 2D Vector Library
Criação e teste de uma classe representativa de um vetor 2D, para a implementação do _overload_ de operadores.
Os operadores **plus**, **minus**, **times**, **unaryMinus** e **compareTo** foram _overloaded_ para realizar, respetivamente, 
adição, subtração, multiplicação escalar, inversão e comparação.
As seguintes funções específicas a vetores foram criadas:
* **magnitude** - calcula e retorna a magnitude do vetor.
* **dot** - calcula o produto interno de dois vetores.
* **normalized** - normaliza um vetor. Em caso de ser um vetor zero, lança um **IllegalStateException** para evitar a divisão por zero.
Criou-se também um operador **times**, adicionado como _extension function_ a valores do tipo Double, para permitir a multiplicação escalar _left-hand_. 
Foi implementado suporte a _destructuring declarations_ ao definir a classe como data class, sendo automaticamente definido o suporte à separação da classe nos componentes **x** e **y**.

## 5. Testing and Validation
Cada componente implementado foi testado através da função **main**, utilizando os dados de teste fornecidos no enunciado
do tutorial. Os testes foram todos verificados com os resultados esperados.

## 6. Usage Instructions
Para executar o projeto:
1.  Instalar IntelliJ IDEA
2.  Criar um projeto Kotlin
3.  Adicionar os ficheiros .kt presentes
4.  Executar cada ficheiro com a função main

---

## Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections
Não foi utilizada inteligência artificial na implementação deste repositório. 
Toda a programação foi desenvolvida autonomamente, com consulta à documentação oficial de Kotlin, assim como os _websites_
_GeeksforGeeks_ e _StackOverflow_.
O relatório presente também foi inteiramente desenvolvido por mim.

---

# Development Process

## 12. Version Control and Commit History
O trabalho foi mantido em repositório Git local, assim como remoto no GitHub. Os commits realizados 
foram incrementais, refletindo a evolução do projeto e não apenas a sua finalização.

## 13. Difficulties and Lessons Learned
A principal dificuldade na implementação dos exercícios pedidos deu-se na composição de funções, sendo ultrapassada com 
a revisão da documentação da linguagem.

---

## 15. AI Usage Disclosure (Mandatory)
Não foi utilizada inteligência artificial na implementação deste repositório. 
Sou responsável por todo o conteúdo incluído nos ficheiros. 