# Assignment 1 — Habit Tracker

Course: Desenvolvimento de Aplicações Móveis  
Student(s): Zilda Biai A51606  
Date: 15/03/2026  
Repository URL: ___________  

---
# Tutorial 1 - Hello Kotlin. Hello Android World!
## 1. Introduction
The purpose of this assignment is to create a production-quality, greenfield native Android mobile application. The problem being addressed is helping users track their habits by keeping a record of the last time a habit was completed. The main objectives include utilizing modern Android technologies such as Kotlin, XML-based Views, Room for local data storage, Hilt for dependency injection, and adhering to Material Design 3 guidelines.

## 2. System Overview
The Habit Tracker app allows users to create new habits and track how many days have passed since they were last completed. The main features include a list view displaying all tracked habits with dynamic "days since" counters, and a bottom sheet allowing the addition of new habits with customizable "last done" dates. 

## 3. Architecture and Design
The project adheres to the MVVM (Model-View-ViewModel) architectural pattern combined with Clean Architecture principles. 
- **Data Layer:** Uses a Room Database to persist data locally, returning reactive Kotlin Flows to ensure the UI is always up to date.
- **Presentation Layer:** Employs Hilt for Dependency Injection to cleanly manage ViewModels, which expose state to Fragments via StateFlow.
- **UI Layer:** Implements Jetpack Navigation within a Single-Activity structure, using XML layouts with Material 3 components (MaterialCardView, FloatingActionButton, TextInputLayout, etc.).

## 4. Implementation
Key implementation details include:
- `Habit`: A Room `@Entity` representing tracked habits and their last completion timestamp in milliseconds.
- `HabitRepositoryImpl`: Abstracts the Room DAO operations. 
- `HabitListViewModel` & `AddHabitViewModel`: Manage business logic and safely run asynchronous Room transactions inside `viewModelScope`.
- `HabitAdapter`: Uses `ListAdapter` and `DiffUtil` for optimized UI updates in the RecyclerView.
- `java.time` API with Core Library Desugaring ensures mathematically accurate date-time duration calculations across all supported Android versions.

## 5. Testing and Validation
The application was manually validated:
- Verified that adding a new habit immediately updates the `HabitListFragment` through the reactive Room Flow.
- Validated the accuracy of the "days since" counter using `java.time` logic spanning across different days and midnight rollovers.
- Ensured UI correctly renders Material 3 themes, including responsive Dark Mode.
- Tested the app in both the Pixel 9 Pro emulator and a real Samsung phone.

## 6. Usage Instructions
1. Open the **Android Studio** IDE.
2. Select **Open** and choose the `AntigravityHabitApp` project directory.
3. Wait for Android Studio to index the project, download the necessary Gradle wrappers, and resolve the dependencies.
4. Setup a Virtual Device (API 26+) or connect a physical phone.
5. Click **Run** ('Play' icon) to build and launch the application.

---
# Autonomous Software Engineering Sections - only for [AC OK, AI OK] sections
## 7. Prompting Strategy
The AI was prompted with highly structured context defining the goals, constraints, technology stack (Kotlin, XML, Material 3, Android SDK), and the requirement to first output a detailed project plan for approval before generating the code. After approval, the AI was prompted to comment on all generated code automatically.

## 8. Autonomous Agent Workflow
The autonomous agent first structured an implementation plan outlining the exact approach, dependencies (Room, Hilt, Navigation Component), and folder structure. After the plan was approved, the agent scaffolded the initial Gradle project, wrote the data layer interfaces and classes, implemented the UI layer with Fragments and ViewModels, and finally generated the missing dynamic launcher icons.

## 9. Verification of AI-Generated Artifacts
The AI-generated artifacts were verified by manually reviewing the proposed project plan (`implementation_plan.md`) and task breakdown (`task.md`). Afterwards, the code was run to ensure successful compilation and functional correctness, diagnosing an AAPT resource error and automatically fixing it by providing adaptive vector icons.

## 10. Human vs AI Contribution
- **Human:** Conceptualized the app requirements, directed the AI's constraints, reviewed the proposed architecture, approved the plan, and pointed out the compilation error regarding missing launcher icons.
- **AI:** Formulated the architectural design, scaffolded the entire Android project configuration, wrote all Kotlin classes, generated XML layouts, produced dependency injection modules, resolved compilation errors autonomously, and documented the workflows.

## 11. Ethical and Responsible Use
The AI generated purely functional, boilerplate-free Android app code that aligns with the requested architectural patterns. Since this is an offline tool, no sensitive user data is collected or exposed, avoiding privacy risks. The system relied on modern, recognized Android practices to ensure the generated software is maintainable and unbiased.

---
# Development Process
## 12. Version Control and Commit History
The project progressed primarily through the initial autonomous agent bootstrapping, proceeding chronologically from Project Setup, Data Layer integration, to UI/Presentation Layer implementation over successive turns. Version Control was used, in the form of local git commits to track the project progress, done after every change.

## 13. Difficulties and Lessons Learned
A key challenge was missing launcher icons during the build process, leading to AAPT compilation failures (`error: resource mipmap/ic_launcher not found`). The lesson learned was that minimal project scaffolds need explicit adaptive icon mappings (`ic_launcher.xml` and `ic_launcher_round.xml`) as well as valid SVG vector backgrounds and foregrounds in order to satisfy the `AndroidManifest.xml` requirements.

## 14. Future Improvements
- Implement DataStore or SharedPreferences to save user settings (like visual themes or sorting preferences).
- Add functionality to edit or delete existing habits.
- Introduce graphs or statistics over time to visualize habit consistency, beyond just tracking the last completion day.

---
## 15. AI Usage Disclosure (Mandatory)
- **Tool Used:** Gemini / Google Deepmind Advanced Agentic Coding Assistant (Antigravity).
- **Usage:** Used extensively to architect the application, scaffold the workspace, generate the entire Kotlin and XML codebase, identify/fix the AAPT build errors, and draft documentation.
- **Confirmation:** I remain responsible for reviewing, testing, and confirming the correctness of all content produced by the AI assistant.