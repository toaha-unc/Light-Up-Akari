# 🧩 a09-akari — Akari Puzzle Game in JavaFX

## 📌 Introduction

This project is a complete graphical implementation of the **Akari** logic puzzle game, built using JavaFX and the **Model-View-Controller (MVC)** design pattern. Users can play a variety of built-in Akari puzzles and interact with an intuitive and responsive UI.

If you're unfamiliar with Akari, it's a single-player logic puzzle where the goal is to place lamps in corridors to light the entire board while satisfying all clue constraints. [Learn more on Wikipedia](https://en.wikipedia.org/wiki/Light_Up_(puzzle)).

---
## Video

https://github.com/user-attachments/assets/b0ab8b1d-8ae6-4b29-84ed-4ca42597803d

## 🎮 Features

- ✅ Fully playable Akari puzzles with GUI
- ✅ Support for multiple built-in puzzles
- ✅ Reset, next, previous, and random puzzle navigation
- ✅ Puzzle completion detection with GUI success message
- ✅ Illegal lamps and satisfied clues are visually distinguished
- ✅ Support for puzzles of varying sizes
- ✅ Uses JavaFX for interactive and clean UI

---

## 🧠 MVC Architecture Overview

### 🧩 Model (`model`)
- **PuzzleImpl**: Represents a puzzle board with clue, wall, and corridor cells.
- **ModelImpl**: Stores the state of the current puzzle, manages lamp placements, puzzle validation, and observer notifications.

### 🎮 Controller (`controller`)
- **ControllerImpl**: Bridges the view and model. Handles user actions (like clicking cells or buttons) and modifies the model accordingly.

### 🖼️ View (`view`)
- **FXComponent**-based classes (`PuzzleView`, `ControlView`, `MessageView`) for modular UI sections.
- **AppLauncher**: JavaFX `Application` that launches the game and wires up MVC components.

---

## 🧪 Sample Puzzles

The application comes with **5 built-in sample puzzles** of different sizes to demonstrate the game's dynamic board rendering and logic validation features. You can find the puzzle data encoded in `SamplePuzzles.java`.

---

## 🚀 How to Run the App

This project uses **Maven** to manage dependencies (including JavaFX).

### ✅ In IntelliJ:

1. Open the project in IntelliJ.
2. Open the **Maven** panel (right sidebar).
3. Navigate to **Plugins > javafx**.
4. Double-click **`javafx:run`** to launch the app.

---

## 🖱️ User Instructions

- Click **corridor cells** to place or remove lamps.
- Clue and wall cells **cannot** hold lamps.
- Light from a lamp travels in straight lines and stops at walls or board edges.
- Click:
  - **Next** → go to the next puzzle
  - **Previous** → go back one puzzle
  - **Random** → jump to a random puzzle
  - **Reset** → remove all placed lamps
- The app automatically displays a **"Puzzle Solved!"** message in the UI when:
  - All corridors are lit
  - All clue cells are satisfied
  - No illegal lamps are present

---

## 🏗️ Code Structure

```plaintext
src/
├── controller/
│   └── ControllerImpl.java
├── model/
│   ├── PuzzleImpl.java
│   ├── ModelImpl.java
│   └── ...
├── view/
│   ├── AppLauncher.java
│   ├── PuzzleView.java
│   ├── ControlView.java
│   ├── MessageView.java
│   └── ...
└── Main.java
```

---

## 💡 Notes

- Built as part of COMP 301 at UNC Chapel Hill.
- Designed with flexibility in mind—more puzzles can easily be added via `SamplePuzzles.java`.

---
