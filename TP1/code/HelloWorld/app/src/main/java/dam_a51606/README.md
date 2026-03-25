# Assignment 1 — Hello World

Course: Desenvolvimento de Aplicações Móveis  
Student(s): Zilda Biai A51606  
Date: 08/03/2026  
Repository URL: ___________

---
# Tutorial 1 - Hello Kotlin. Hello Android World!
## 1. Introduction
O presente repositório foi desenvolvido no âmbito do primeiro tutorial
da disciplina de Desenvolvimento de Aplicações Móveis. Nesta parte do
trabalho foram explorados os primeiros conceitos de desenvolvimento
Android utilizando Android Studio e Kotlin.

As secções do tutorial representadas neste projeto correspondem às
partes 4.1, 4.2 e 7 do enunciado. O objetivo principal foi compreender a
estrutura básica de uma aplicação Android, a criação de interfaces
simples e a interação com elementos da interface gráfica.

Durante o desenvolvimento foram explorados conceitos como:
- Estrutura básica de um projeto Android
- Utilização de Activities
- Manipulação de Views 
- Tratamento de eventos de interface

## 2. System Overview
A aplicação final consiste numa interface simples Android com uma imagem, calendário e um
botão. Implementa:
* Apresentação da imagem numa ImageView 
* Apresentação de uma CalendarView
* Botão para alterar a imagem 
* Tratamento da interação do utilizador

## 3. Architecture and Design
O projeto segue a estrutura padrão de aplicações Android, contendo subpastas da package principal **dam.A51606.helloworld**.  

Componentes principais:
* MainActivity - Classe principal da aplicação - Responsável por inicializar a interface e tratar eventos
* Layout XML - Define a interface gráfica - Contém ImageView e Button
* Drawable Resources - Pasta onde se encontram as imagens da aplicação

## 4. Implementation
### 4.1 -- Create a "Hello Mobile World" Appplication

Foi criado um projeto Android com uma Activity principal, que apresentava a string "Hello World!" na sua página.
Nesta parte da implementação foi tratada a extração de strings como _resources_ para o ficheiro **strings.xml**.

### 4.2 -- Improve your application - Hello World V2

A TextView previamente criada foi alterada, e foi criada uma nova. Adicionalmente, adicionou-se uma imagem e uma CalendarView ao layout.
Esta fase também visou a implementação de uma variante em modo landscape da da aplicação, e a alteração das vistas e constraints de modo a funcionarem também nesse modo.
Foi também criado um ícone da aplicação, com a imagem utilizada na ImageView.

### 7 -- Return and Enhance your Android experience

Como o modo _landacape_ já teria sido previamente implementado, nesta fase apenas foi adaptado às novas adições.  
Foi adicionado um botão que altera a imagem apresentada no ImageView. As imagens encontram-se na pasta **drawable** e são trocadas quando o
utilizador pressiona o botão, circulando entre três imagens disponíveis.
Esta alteração é realizada a partir de um _listener_ colocado no botão, durante o runtime do **MainActivity.kt** .

## 5. Testing and Validation
A aplicação foi testada num emulador Android, tanto como num dispositivo real Android.

Testes realizados: 
- Inicialização da aplicação 
- Apresentação da imagem 
- Funcionamento do botão para alterar a imagem
- Funcionamento do modo _landscape_

## 6. Usage Instructions
1.  Abrir o projeto no Android Studio
2.  Executar num emulador ou dispositivo Android
3.  Pressionar o botão para alterar a imagem

---

# Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections
## 7. Prompting Strategy
Foram utilizados prompts para auxiliar na criação e formatação da
documentação.
## 8. Autonomous Agent Workflow
Ferramentas de IA foram utilizadas apenas como apoio para geração deste
relatório.
## 9. Verification of AI-Generated Artifacts
O conteúdo gerado foi manualmente revisto antes da sua inserção no relatório.
## 10. Human vs AI Contribution
Todo o código foi desenvolvido manualmente, com consulta realizada em sites web para melhor entendimento do funcionamento do Android Studio. 
A IA foi usada apenas para documentação.
## 11. Ethical and Responsible Use
A utilização de IA foi feita de forma responsável e todo o conteúdo foi verificado.

---
# Development Process
## 12. Version Control and Commit History
Foi utilizado git para controlo de versões, utilizando commits locais incrementais, à medida que os exercícios eram realizados ou novos ficheiros eram criados.
## 13. Difficulties and Lessons Learned
As principais dificuldades que tive na elaboração do projeto Android foram de adaptação à ingterface do IDE, assim como da relação entre o XML e o Kotlin. 
Apesar delas, o uso introdutório do Android Studio permitiu a aprendizagem de com ultrapassar a sua maioria.
## 14. Future Improvements
Possíveis melhorias à aplicação criada:
* Adição de imagens
* Randomização da imagem apresentada na ImageView
* Interação do utilizador com o calendário

---
## 15. AI Usage Disclosure (Mandatory)
Ferramenta utilizada: ChatGPT. 
Utilizada apenas para apoio na criação da documentação.