# Tutorial 3 — Cool Jetpack Weather App

Course: Desenvolvimento de Aplicações Móveis  
Student(s): Zilda Biai (51606)  
Date: 03/05/2026  
Repository URL: [Cool Jetpack Weather App](https://github.com/z1lyb/Desenvolvimento-de-Aplicacoes-Moveis-SV2526/tree/main/TP3/CoolJetpackWeatherApp)

---

## 1. Introduction

O repositório presente tem como objetivo um desenvolvimento da **Cool Weather App** criada no tutorial anterior, adaptando a sua interface para Jetpack Compose. Implementa também uma arquitetura **MVVM** e a utilização de APIs externas para atualização dos dados de temperatura e localização.

## 2. System Overview

A aplicação desenvolvida permite consultar dados meteorológicos de qualquer localização, usando a sua latitude e longitude. As suas funcionalidades principais são:

* **Consulta de dados**: São exibidas temperatura, pressão marítima, direção e velocidade do vento e hora do local desejado.
* **Estado da UI**: A interface atualiza automaticamente com a mudança de estado.
* **Seletor de localização**: Mapa para seleção de localização, utilizando a API de acesso ao Google Maps.

## 3. Architecture and Design

A aplicação adota a arquitetura **MVVM** - _Model-View-ViewModel_, garantindo que as suas várias camadas estão separadas de acordo com as suas responsabilidades:

* **Model**: Classes do package `data`, que guardam dados relativos às possíveis condições meteorológicas.
* View: Classes do package `ui`, que determinam a apresentação da aplicação em Jetpack Compose.
* ViewModel: Classes do package `viewmodel`, que lidam com o contacto com as APIs e alteração do estado da interface conforme a informação presente.

## 4. Implementation

### Gestão de estado e UI

A interface da aplicação é construída de forma modular, o `WeatherScreen` observando o estado do ViewModel e distribuindo os seus dados pelas componentes:

* `CoordinatesCard`: gere os inputs de texto das coordendas, assim como o botão para o acesso ao Location Picker.
* `WeatherCard`: Organiza os dados meteorológicos em linhas, representadas por `WeatherRow`.
* `WeatherIcon`: Mapeia os códigos de temperatura para recursos visuais correspondentes, dependendo se é de dia ou noite no local.

### Comunicação com a API

Foi usado o **Ktor Client** para realização de pedidos HTTP assíncronos a partir do ViewModel, de modo a não parar a thread principal da aplicação.

### Location Picker

A `LocationPickerActivity` utiliza o **Google Maps Compose library**. Quando um utilizador seleciona um ponto no mapa, a atividade devolve o resultado, que por sua vez aciona uma atualização do ViewModel.

## 5. Testing and Validation

Os testes da aplicação foram realizados ao corrê-la num dispositivo Android.

* **Validação de dados**: Verificação da recolha de dados pela API para os objetos.
* **Teste da UI**: Alteração de coordenadas, manual e com a `LocationPickerActivity`, e visualização da atualização da interface.
* **Temas e idiomas**: Teste dos temas claro e escuro criados, assim como idiomas de inglês e português.

## 6. Usage Instructions

1. Abrir projeto no Android Studio.
2. Esperar pela configuração Gradle.
3. Lançar o módulo `app`, que inicia a aplicação.

---

## Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections

## 7. Prompting Strategy

O prompting foi realizado a partir do fornecimento dos segmentos de código relevantes para a geração de partes da documentação.

## 8. Autonomous Agent Workflow

A IA auxiliou na criação da documentação, não tendo sido envolvida no desenvolvimento do projeto.

## 9. Verification of AI-Generated Artifacts

Todo o conteúdo gerado foi manualmente relido para compreensão pessoal e verificação da sua veracidade.

## 10. Human vs AI Contribution

O trabalho foi inteiramente manualmente realizado. O relatório foi manualmente escrito com alguma assistência de IA.

## 11. Ethical and Responsible Use

A IA foi utilizada para garantir que a terminologia fosse aplicada corretamente, assim como para realizar adequadas descrições do funcionamento da aplicação.

---

## Development Process

## 12. Version Control and Commit History

A forma de _version control_ utilizada para este projeto foi um repositório no GitHub, que foi atuallizado de forma incremental, de forma a descrever adequadamente a sua evolução.

## 13. Difficulties and Lessons Learned

* **Desafios**: Lidar com a passagem de dados entre a API e aplicação, assim como o preenchimento de _inputs_ pelo utilizador.
* **Aprendizagens**: Uso do Jetpack Compose e atualização de estados da UI.

## 14. Future Improvements

* Possível implementação de animações de transição entre ecrãs.

---

## 15. AI Usage Disclosure (Mandatory)

Relatório realizado com o auxílio de IA (Google Gemini) A autora validou todas as descrições técnicas relativas ao código implementado.
