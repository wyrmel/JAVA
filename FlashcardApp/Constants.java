// This class handles constants for strings and other repeated values.

package FlashcardApp;

import java.awt.Font;

public class Constants {
    public static final String FRAME_TITLE = "Flashcard App";
    public static final String NO_WORDS_LOADED = "No words loaded";
    
    // Next button
    public static final String NEXT_BUTTON_TEXT = "\u2192"; // Unicode for right arrow
    public static final String FONT_ARIAL = "Arial";

    // Instructions
    public static final String INSTRUCTION_TEXT = "Click the card to reveal the word";
    public static final String FONT_BOOKMAN = "Bookman Old Style";

    // Reset and Review Results
    public static final String FONT_review_reset = "Segoe Script";
    public static final int STYLE_review_reset = Font.BOLD;
    public static final int SIZE_review_reset = 12;

    // Correct and Incorrect buttons
    public static final String FONT_SEGOE_UI = "Segoe UI Symbol";
    public static final String CORRECT_BUTTON_TEXT = "\u2713"; // Unicode for checkmark
    public static final String INCORRECT_BUTTON_TEXT = "\u2717"; // Unicode for X mark
    // Flashcard
    public static final String FONT_cardbutton = "Courier New";
    public static final int STYLE_cardbutton = Font.BOLD;
    public static final int SIZE_cardbutton = 20;
    public static final int CARD_WIDTH = 200;
    public static final int CARD_HEIGHT = 100;

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 500;
}
