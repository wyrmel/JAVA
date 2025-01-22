# FlashcardApp

## Description
The `FlashcardApp` is a Java-based graphical user interface (GUI) application designed to help users study Finnish and Swedish words using flashcards.

## Features
- **Flashcards**: Displays Finnish or Swedish words on flashcards.
- **Random Selection**: Randomly selects a word to display.
- **Flip Card**: Click the card to reveal the translation.
- **Next Word**: Click the arrow button to move to the next word.
- **Progress Counter**: Displays the number of words reviewed out of the total.
- **Instruction Text**: Provides instructions on how to use the flashcards.
- **Correct and Incorrect Counters**: Tracks the number of correct and incorrect answers.
- **Review Results**: Allows users to review which words they got correct and incorrect.
- **Reset Option**: Prompts users to reset and try again after reviewing all words.

## How to Use
1. **Start the Application**: Run the `Main` class to start the application.
2. **View Flashcards**: The application will display a word on the flashcard.
3. **Flip the Card**: Click the flashcard to reveal the translation.
4. **Next Word**: Click the right arrow button to move to the next word.
5. **Mark Correct/Incorrect**: Click the green checkmark button for correct answers and the red X button for incorrect answers.
6. **Review Results**: Click the "Review Results" button to see which words you got correct and incorrect.
7. **Reset**: After reviewing all words, the application will prompt you to reset and try again.

## Code Structure
- **Main.java**: The entry point of the application. Loads the words from a file and initializes the `FlashcardGUI`.
- **FlashcardGUI.java**: The main class that creates the GUI and handles the flashcard functionality.
- **FlashcardManager.java**: Manages the flashcards, including loading, shuffling, flipping, and tracking correct/incorrect answers.
- **Constants.java**: Defines constants for strings and other repeated values used in the application.

## How to Add Words
1. **Create a Text File**: Create a text file named `words.txt` in the project directory or alternatively use the existing one.
2. **Add Words**: Add Finnish and Swedish word pairs in the format `Finnish, Swedish`, one pair per line. For example:
    työskennellä, arbeta (-ade -at)
    kehittynyt, avancerad

## Dependencies
- Java Development Kit (JDK) 8 or higher

## Running the Application
1. **Compile the Code**: Compile the Java files using the following command:
```sh
    javac FlashcardApp/Main.java FlashcardApp/FlashcardGUI.java FlashcardApp/FlashcardManager.java FlashcardApp/Constants.java
```
2. **Run the Application**: Run the application using the following command:
```sh
    java FlashcardApp.Main
```
