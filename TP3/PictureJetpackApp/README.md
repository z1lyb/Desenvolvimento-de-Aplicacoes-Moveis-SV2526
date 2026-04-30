# Assignment 2 - Weather App

Course: Desenvolvimento de Aplicações Móveis  
Student(s): Zilda Biai (51606)  
Date: 12/04/2026  
Repository URL: [PictureApp](https://github.com/z1lyb/Desenvolvimento-de-Aplicacoes-Moveis-SV2526/tree/main/TP2/PictureApp)

---

# Tutorial 2 - Assisted code generation (MIP-2)

## 1. Introduction
O presente repositório foi criado no âmbito da disciplina de Desenvolvimento de Aplicações Móveis, durante a realização 
do segundo tutorial da disciplina. Este relatório consistirá na descrição do trabalho referente à _**Section 3: Assisted code generation - MIP-2**_.  
O seu objetivo é a planificação e implementação de uma aplicação que aceda a uma galeria de imagens através de uma API, com o auxílio da ferramenta Google Antigravity.

## 2. System Overview
A aplicação criada, CatMap, é uma aplicação que permite explorar uma galeria de imagens de gatos através da "The Cat API". Os utilizadores podem ver e pesquisar por raças de animais, consultar informações sobre cada uma e adicionar até cinco fotos aos seus favoritos, assim como dar _refresh_ à página e observar o progresso de carregamento das imagens.
A aplicação suporta o modo escuro e claro, adaptando-se ao sistema do dispositivo.

## 3. Architecture and Design
A aplicação segue o modelo MVVM, tendo uma separação clara entre a interface do utilizador (View), a lógica de interação com os dados (ViewModel) e os dados importados da API (Model).

As pastas são estruturadas da seguinte forma:
* **data**: contém os dados importados da API
* **model**: contém os modelos de dados, as classes **ImageItem** e **BreedInformation**
* **network**: contém a lógica de interação com a API
* **repository**: contém o repositório de dados, uma camada de abstração entre o ViewModel e a fonte de dados
* **ui**: contém a interface do utilizador
* **util**: classes que ajudam com a formatação da interface do utilizador


## 4. Implementation
O projeto foi implementado como uma aplicação Android utilizando o Kotlin e XML Views.  
Componentes principais:
* **MainActivity**: página principal que exibe a lista de imagens de gatos.
* **DetailActivity**: página que exibe os detalhes de uma raça de gato, acessível ao clicar numa imagem da lista.
* **FavoritesActivity**: página que exibe a lista de imagens de gatos favoritas, acessível ao clicar no ícone de coração no canto superior direito da MainActivity. Os favoritos são guardados usando uma lógica FIFO (First-In, First-Out), onde ao se adicionar um sexto item, o primeiro é retirado.


## 5. Testing and Validation
A aplicação foi testada num dispositivo Android real, em cenários diferentes para testar cada uma das funcionalidades:
* Carregamento de imagens
* Pesquisa de animais por raça
* Adição de imagens aos favoritos
* Mudança entre modo escuro e claro
* Erros de acesso à API

## 6. Usage Instructions
Para executar o projeto, é necessário ter o Android Studio instalado e um dispositivo Android ou emulador.  
1. Abrir o projeto no Android Studio
2. Sincronizar o projeto com o Gradle
3. Correr a aplicação com o dispositivo ou emulador conectado

---

# Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections

## 7. Prompting Strategy
O projeto foi desenvolvido a usar uma _prompting strategy_ centrada na documentação. O plano de implementação e documentos de descrição dos componentes e funcionalidades da aplicação foram criados previamente ao seu uso, e colocados na pasta `/docs`.  
Todas as prompts utilizadas, assim como os seus objetivos e resultados foram guardados no documento `/docs/prompts_log.md`.

## 8. Autonomous Agent Workflow
O desenvolvimento foi realizado pelo agente IA autónomo, seguindo as fases seguintes de trabalho:
1. **Planeamento**: analisar os requisitos e criar um plano de implementação
2. **Fundação**: criar a estrutura e dependências do projeto
3. **Programação iterativa**: implementar as funcionalidades do projeto
4. **Documentação**: documentar as alterações realizadas para refletirem o estado atual da aplicação

## 9. Verification of AI-Generated Artifacts
Foi verificada a correção do código gerado através de testes manuais e análise estática.

## 10. Human vs AI Contribution
O agente IA autónomo foi responsável por configurar e implementar a aplicação, enquanto o utilizador foi responsável por fornecer os requisitos, prompts e validar o desenvolvimento com testes e revisão de código.

## 11. Ethical and Responsible Use
O uso de IA foi feito de forma responsável, com o utilizador a garantir que o código gerado era ético e não continha fontes ou informações inapropriadas. Pode haver o risco de terem sido gerados algoritmos menos eficientes que outros disponíveis, mas que cumprem os requisitos do trabalho.

---

# Development Process

## 12. Version Control and Commit History
O controlo de versão foi realizado através do GitHub, com o seu histórico de commits a refletir o trabalho contínuo realizado durante o desenvolvimento do projeto.

## 13. Difficulties and Lessons Learned
As dificuldades principais enfrentadas na criação da aplicação foram a compreensão da arquitetura MVVM e de como aplicar esta arquitetura no projeto, a partir do modelo de implementação e do código gerado.  
Considero ter ganho uma boa compreensão do modelo acima, assim como de como utilizar agentes de IA para auxiliar no desenvolvimento de software.

## 14. Future Improvements
* Apoio ao uso offline da aplicação, ao manter ficheiros em cache
* Implementação de um "scroll infinito", que continuaria a carregar imagens à medida que o utilizador navega pela lista

---

## 15. AI Usage Disclosure (Mandatory)
O projeto foi desenvolvido tirando partido do agente de código Google Antigravity, que auxiliou na implementação das funcionalidades da aplicação.  
O agente foi utilizado de forma iterativa, com o utilizador a fornecer prompts e a validar o desenvolvimento com testes e revisão de código. Deste modo, mantenho a responsabilidade por todo o conteúdo gerado e a sua apresentação.
