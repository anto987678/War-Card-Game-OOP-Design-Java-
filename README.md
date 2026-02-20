# War-Card-Game-OOP-Design-Java

# War Card Game – Java OOP Implementation

A console-based implementation of the classic **War** card game, developed in Java with a focus on object-oriented programming principles and clean class design.

This project demonstrates encapsulation, abstraction, modularity, and separation of responsibilities within a structured Java application.

---

## Overview

The application simulates a two-player War card game using a standard 52-card deck.

### Game Rules

- A 52-card deck is generated and shuffled.
- The deck is split evenly between two players.
- In each round, both players draw one card.
- The player with the higher-ranked card wins the round and collects both cards.
- The game continues until one player owns all the cards.

The implementation focuses on backend logic and object-oriented modeling rather than graphical interface development.

---

## Object-Oriented Design Principles Applied

- **Encapsulation** – Card properties and behaviors are contained within their respective classes.
- **Abstraction** – Core entities such as `PlayingCard` and `Deck` represent real-world concepts.
- **Modularity** – Each class has a clearly defined responsibility.
- **Separation of Concerns** – Game flow logic is separated from entity definitions.

---

## Project Structure

.
├── Main.java  
├── Deck.java  
├── PlayingCard.java  
└── (other supporting classes, if applicable)

### Core Components

**PlayingCard**
- Represents an individual card.
- Stores suit and rank.
- Provides comparison logic.

**Deck**
- Generates a standard 52-card deck.
- Handles shuffling.
- Manages card distribution.

**Main (Game Controller)**
- Controls the round-based game loop.
- Executes comparison logic.
- Determines the winner.

---

## How to Run

### Requirements
- Java 17+ (or compatible version)

### Compile and Run from Terminal

    javac *.java
    java Main

The game will execute directly in the console.

---

## Example Game Flow

1. Initialize and shuffle the deck.
2. Split cards evenly between two players.
3. For each round:
   - Both players draw a card.
   - Cards are compared.
   - Winner collects both cards.
4. The game ends when one player owns all cards.

---

## Possible Extensions

- Full tie-resolution logic for “war” scenarios
- Player class abstraction
- Game statistics tracking
- Unit testing (JUnit)
- Graphical user interface
- Strategy pattern for rule variations

---

## Purpose

This project was developed as part of an Object-Oriented Programming course to practice:

- Java class modeling
- Clean architecture principles
- Logical system decomposition
- Core OOP design practices
