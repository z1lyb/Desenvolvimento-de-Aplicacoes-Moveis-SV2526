# Tutorial 3 - Annotation Processor

Course: Desenvolvimento de Aplicações Móveis  
Student(s): Zilda Biai (51606)
Date: 03/05/2026  
Repository URL: [GreetingProcessorProject](https://github.com/z1lyb/Desenvolvimento-de-Aplicacoes-Moveis-SV2526/tree/main/TP3/GreetingProcessorProject)

---

## 1. Introduction
Este projeto foi desenvolvido no âmbito da unidade curricular de Desenvolvimento de Aplicações Móveis (DAM), 
correspondendo à primeira secção: **Annotation Processor**. Envolve o desenvolvimento de um processador em Kotlin, 
focando na automação de geração de código em tempo de compilação.  
Os objetivos específicos do trabalho incluem a criação de anotações, a implementação de lógica de processamento com o 
*KotlinPoet* e a integração de vários módulos num projeto Gradle.

## 2. System Overview
O sistema implementado oferece duas funcionalidades principais:
* **Greeting Processor**: Gera wrappers que injetam mensagens antes da execução de métodos específicos.
* **Regex Processor**: Gera a automação da extração de substrings de texto, usando a _annotation_ `@Extract`.

## 3. Architecture and Design
A arquitetura é separada em módulos, permitindo a separação de responsabilidades:
* `:annotations`: Define as anotações.
* `:processor`: Contém os processadores de greetings e Regex. Utiliza a biblioteca _KotlinPoet_ para gerar novos ficheiros Kotlin.
* `:app`: Módulo de execução para teste das classes criadas, contendo **MyClass** para o GreetingProcessor e **DataProcesor** para o Regex.

## 4. Implementation
A implementação destaca dois componentes:
* **GreetingProcessor**: Para cada classe que contenha a anotação, é criado um wrapper que imprime o seu _greeting_, seguido da execução original da função.
* **ExtractProcessor**: Gera implementações para classes abstratas, usando a lógica de Regex para encontrar o input e filtrá-lo das strings.
* **Infraestrutura comum**: Ambos os processadores usam a gestão de ficheiros como base comum.
  * AutoService: registo dos processadores no compilador.
  * Kapt Configuration: escrita dos ficheiros nos diretórios.

## 5. Testing and Validation
A validação foi feita no ficheiro `Main.kt`, onde se testaram e verificaram resultados desejados para ambas 
as implementações.

## 6. Usage Instructions
1. Abrir o projeto no IntelliJ.
2. Certificar-se da definição "Enable annotation processing".
3. Esperar pela build do Gradle.
4. Correr a classe MyClass e verificar o output.

---

# Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections

## 7. Prompting Strategy
Não foi utilizada inteligência artificial na criação do projeto, unicamente na sua documentação.
A _prompting strategy_ focou-se em perguntas clarificativas sobre o que incluir no relatório.

## 8. Autonomous Agent Workflow
A IA analisou os ficheiros, caso necessário, para completar as partes necessárias.

## 9. Verification of AI-Generated Artifacts
Todo o conteúdo gerado foi manualmente relido para compreensão pessoal e verificação da sua veracidade.

## 10. Human vs AI Contribution
O trabalho foi inteiramente manualmente realizado. O relatório foi manualmente escrito com assistência de IA.

## 11. Ethical and Responsible Use
A IA foi utilizada para garantir que a terminologia fosse aplicada 
corretamente no contexto de desenvolvimento Android/Kotlin.

---

# Development Process

## 12. Version Control and Commit History
Utilizou-se o Git para o controlo de versões, com commits incrementais que refletem cada etapa do desenvolvimento.

## 13. Difficulties and Lessons Learned
O maior desafio foi garantir que o `ExtractProcessor` gerasse métodos com o tipo de retorno correto (`String?`), 
respeitando a assinatura da classe abstrata original. 
Aprendeu-se a utilizar o `ClassName` do KotlinPoet para referenciar tipos nulos.

## 14. Future Improvements
* Suporte para múltiplos grupos de captura no `@Extract`.

---

## 15. AI Usage Disclosure (Mandatory)
Relatório atualizado com auxílio de IA (Google Gemini) para incluir a componente de Regex Annotation Processing. 
A autora validou todas as descrições técnicas face ao código implementado.