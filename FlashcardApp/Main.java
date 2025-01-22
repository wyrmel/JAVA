package FlashcardApp;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            // Load flashcards from the words.txt file
            Map<String, String> flashcards = loadWordsFromFile("FlashcardApp\\words.txt");

            if (flashcards.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No flashcards were loaded. Please check the file format and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create and display the FlashcardApp GUI
            SwingUtilities.invokeLater(() -> new FlashcardGUI(flashcards));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred in the application", e);
        }
    }

    // Method to load words from a file into a map
    private static Map<String, String> loadWordsFromFile(String filename) {
        Map<String, String> flashcards = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;
    
                // Validate format: must contain exactly one comma
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    logger.log(Level.WARNING, "Invalid line format: " + line);
                    continue;
                }
    
                // Add valid entries to the map
                flashcards.put(parts[0].trim(), parts[1].trim());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load words from file: " + filename, e);
        }
    
        if (flashcards.isEmpty()) {
            logger.log(Level.WARNING, "No valid flashcards found in the file: " + filename);
        }
    
        return flashcards;
    }    
}
