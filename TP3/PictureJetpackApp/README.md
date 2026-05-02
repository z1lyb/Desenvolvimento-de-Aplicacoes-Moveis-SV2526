# Tutorial 3 — Assisted code generation – MIP-3

Course: Desenvolvimento de Aplicações Móveis  
Student: Zilda Biai (51606)  
Date: 03/05/2026  
Repository URL: [PictureJetpackApp](https://github.com/z1lyb/Desenvolvimento-de-Aplicacoes-Moveis-SV2526/tree/main/TP3/PictureJetpackApp)

---

## 1. Introduction
Este projeto foi desenvolvido no âmbito da Unidade Curricular de Desenvolvimento de Aplicações Móveis (DAM), correspondendo ao desafio **Mission Impossible Possible - 3**. O objetivo principal consistiu na refatorização de uma aplicação Android existente (criada no Tutorial 2) para uma arquitetura multi-módulo, separando a lógica de acesso a dados para um módulo partilhado (`:core`) usado em duas interfaces: uma baseada em **XML Views** (`:app-xml`) e outra baseada em **Jetpack Compose** (`:app-compose`).

## 2. System Overview
A aplicação, **CatMap**, permite aos utilizadores explorar uma galeria de raças de gatos utilizando a "The Cat API". As suas principais funcionalidades incluem:
- Visualização de uma grelha de imagens de gatos.
- Pesquisa por raça.
- Consulta de detalhes específicos (origem, temperamento, esperança de vida) com ligação externa à Wikipedia.
- Gestão de favoritos com política FIFO (limite de 5 imagens).
- Suporte para modos Claro e Escuro (Dark/Light Mode).

## 3. Architecture and Design
O projeto adota uma arquitetura modular, promovendo a reutilização de código e a separação de responsabilidades (*Separation of Concerns*).

### Estrutura de Módulos:
- **`:core`**: Módulo de biblioteca Kotlin/Android que contém os modelos de dados (`ImageItem`, `Breed`), o cliente de API (Retrofit) e a camada de repositório (`CatRepository`). Este módulo é independente da interface gráfica.
- **`:app-xml`**: Implementação da interface de utilizador "legacy" utilizando Activities e XML Layouts tradicionais.
- **`:app-compose`**: Implementação moderna utilizando Jetpack Compose, seguindo padrões e estados reativos.

O padrão de desenho utilizado é o **MVVM (Model-View-ViewModel)** em ambos os módulos de aplicação, garantindo que a lógica de estado é mantida de forma consistente.

## 4. Implementation
A implementação focou-se na paridade de funcionalidades entre as duas UIs, com o módulo Compose a introduzir melhorias exclusivas.

### Destaques da Implementação:
- **Injeção de Dependências/Repositórios**: O `CatRepository` centraliza o acesso à API e a persistência em memória dos favoritos, sendo injetado nos ViewModels de ambos os módulos.
- **Jetpack Compose (Exclusive Features)**:
  - **Shared Element Transitions**: Animações fluidas entre a grelha principal e o ecrã de detalhes, onde a imagem selecionada expande/encolhe suavemente.
  - **Pull-to-Refresh**: Substituição do botão de atualização por um gesto de "puxar para atualizar".
  - **Animações de Interface**: Indicador de escrita a piscar durante a escrita na barra de pesquisa e barra de progresso linear subtil durante o carregamento.
  - **Navegação Declarativa**: Utilização do `NavHost` com passagem de argumentos JSON codificados.

## 5. Testing and Validation
A validação foi realizada através de testes manuais num dispositivo Android real, focando-se em:
- **Navegação**: Verificação da transição correta entre ecrãs em ambos os módulos.
- **Paridade**: Garantir que as cores, textos e lógica de favoritos são idênticos entre XML e Compose.
- **Funcionalidades exclusivas**: Teste do gesto de pull-to-refresh e da suavidade das animações de elementos partilhados.
- **Robustez**: Tratamento de erros de rede e estados de carregamento (*loading states*).

## 6. Usage Instructions
1. Abrir o projeto no **Android Studio**.
2. Sincronizar o projeto com o **Gradle**.
3. Selecionar a configuração de execução pretendida:
   - `app-xml`: Para testar a versão com XML Views.
   - `app-compose`: Para testar a versão com Jetpack Compose.
4. Executar num emulador ou dispositivo físico com API 24+.

---

# Autonomous Software Engineering Sections

## 7. Prompting Strategy
A estratégia de *prompting* foi baseada no planeamento prévio. Foram utilizados os documentos Markdown da pasta `/docs` como guia para as interações com o agente AntiGravity. As prompts evoluíram de requisitos arquiteturais (refatoração para módulos) para detalhes de UI finos (cores específicas, animações personalizadas), sempre mantendo um registo histórico em `docs/prompts_log.md`.

## 8. Autonomous Agent Workflow
O agente AntiGravity foi fundamental em:
- **Refatoração**: Migração automática de classes para o módulo `:core` e ajuste de pacotes.
- **Implementação Compose**: Geração de ecrãs composables baseados nos layouts XML existentes.
- **Debugging**: Resolução de erros de compilação após atualizações de versões de bibliotecas (BOM) para suportar animações avançadas.

## 9. Verification of AI-Generated Artifacts
O código gerado foi verificado através de:
- **Revisão Manual**: Análise do código Kotlin gerado para garantir conformidade com as diretrizes do projeto.
- **Compilação Iterativa**: Testes de build frequentes para detetar erros de injeção de dependências ou parâmetros ausentes.

## 10. Human vs AI Contribution
- **Humano**: Definição da arquitetura, supervisão do plano de implementação, validação visual e testes funcionais.
- **IA**: Execução da refatorização modular, implementação dos ecrãs Compose, criação de animações complexas e documentação técnica.

## 11. Ethical and Responsible Use
O uso da IA foi transparente e supervisionado. Todas as sugestões de código foram validadas pelo utilizador para garantir que a lógica de negócio (especialmente a gestão de favoritos e acesso a dados) permanecia precisa e ética.

---

# Development Process

## 12. Version Control and Commit History
Utilizou-se o Git para o controlo de versões, com commits incrementais que refletem cada etapa do desenvolvimento (Modularização -> XML Refactor -> Compose UI -> Advanced Features).

## 13. Difficulties and Lessons Learned
O maior desafio foi a implementação das **Shared Element Transitions** no Compose, que exigiu a atualização das dependências para versões experimentais (Compose 1.7.0+) e a adaptação das APIs de navegação. Aprendeu-se a importância de uma base de código modular para permitir a coexistência de diferentes paradigmas de UI.

## 14. Future Improvements
- Implementação de persistência local definitiva para favoritos e cache de imagens.
- Implementação de suporte para layouts adaptativos (Tablets).

---

## 15. AI Usage Disclosure (Mandatory)
Este projeto utilizou a ferramenta de IA **Google AntiGravity** para a geração e refatoração de código, sob a supervisão e validação do autor, que assume total responsabilidade pelo conteúdo final.
