# Assignment 1 — Hello World

Course: Desenvolvimento de Aplicações Móveis  
Student(s): Zilda Biai A51606  
Date: 08/03/2026  
Repository URL: ___________

---
Tutorial 1 - Hello Kotlin. Hello Android World!
## 1. Introduction
O presente repositório foi desenvolvido no âmbito do primeiro tutorial da disciplina de Desenvolvimento de Aplicações Móveis.  
Nesta parte do trabalho foi implementada uma aplicação Android, **System Info App**, cujo objetivo é apresentar informações do dispositivo do utilizador de forma dinâmica.

As secções do tutorial representadas neste projeto correspondem à parte **5.3** do enunciado.  
O objetivo principal foi explorar APIs do Android (`android.os.Build`) e desenvolver uma interface que apresenta múltiplos campos de informação do sistema.

## 2. System Overview
A aplicação consiste numa interface simples que apresenta informações sobre a build do dispositivo na sua página inicial.  
Funcionalidades implementadas:
- Visualização de **marca, modelo e fabricante**
- Visualização do **nome do dispositivo**
- Informação sobre a **versão do Android e SDK**

## 3. Architecture and Design
O projeto segue a estrutura padrão de aplicações Android:
* **MainActivity**: Classe principal, responsável por recuperar informações do dispositivo usando `android.os.Build` e atualizar a TextView com os dados do dispositivo  
* **Layout XML**: contém um `TextView` multi-line para exibir todas as informações do sistema. Adicionalmentem define o estilo do texto
## 4. Implementation
Foi alerado o método principal do **MainActivity** de modo que, após a inicialização da aplicação, 
é criada uma string dinamicamente acedendo aos valores pretendidos do dispositivo, afetando-os ao 
objeto TextView multiline presente no _layout_.
## 5. Testing and Validation
O programa foi testado no emulador de um Google Pixel 9 Pro, assim como num dispositivo real Samsung, para assegurar a sua compatibilidade com dispositivos reais.
## 6. Usage Instructions
Para executar a aplicação:
1. Abrir o projeto no Android Studio
2. Executar num emulador ou dispositivo Android  

O ecrã principal mostrará automaticamente as informações do sistema

---
# Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections
## 7. Prompting Strategy
Foram utilizados prompts para criar documentação e esclarecer conceitos do Android e Kotlin, 
apenas para apoio na escrita do relatório.
## 8. Autonomous Agent Workflow
A IA foi utilizada unicamente como suporte na elaboração do texto, sem intervir na implementação do código.
## 9. Verification of AI-Generated Artifacts
O relatório gerado foi revisto manualmente, corrigindo inconsistências ou erros na descrição do código.
## 10. Human vs AI Contribution
Todo o código da System Info App foi desenvolvido manualmente. 
A IA foi utilizada apenas para escrever esta documentação.
## 11. Ethical and Responsible Use
A IA foi usada de forma responsável, garantindo a revisão manual do conteúdo antes da sua transcrição.

---
# Development Process
## 12. Version Control and Commit History
O _version control_ do projeto foi realizado com git, realizando commits incrementais por cada funcionalidade adicionada ou ajuste do layout.
## 13. Difficulties and Lessons Learned
As principais dificuldades deste projeto deram-se à introdução do `android.os.Build`, mas foram melhor compreendidas após a consulta do website de documentação do Kotlin.
## 14. Future Improvements
Possíveis melhorias à aplicação:
* Adição de mais propriedades do sistema
* Embelezamento da apresentação da aplicação
* Criação de um modo landscape

---
## 15. AI Usage Disclosure (Mandatory)
Ferramentas de IA utilizadas: ChatGPT.  
Esta ferramenta foi apenas utilizada para apoio na criação da documentação, não tendo sido utilizada na implementação do código da aplicação.