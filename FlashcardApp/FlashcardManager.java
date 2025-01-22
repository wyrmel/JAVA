// This class handles the logic related to flashcards.

package FlashcardApp;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class FlashcardManager {
    private Map<String, String> flashcards;
    private Set<String> shownWords;
    private Set<String> correctWords;
    private Set<String> incorrectWords;
    private String currentWord;
    private boolean showingFinnish;

    public FlashcardManager(Map<String, String> flashcards) {
        this.flashcards = flashcards;
        this.shownWords = new HashSet<>();
        this.correctWords = new HashSet<>();
        this.incorrectWords = new HashSet<>();
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public String getTranslation() {
        return flashcards.get(currentWord);
    }

    public boolean isShowingFinnish() {
        return showingFinnish;
    }

    public void showRandomCard() {
        if (shownWords.size() == flashcards.size()) {
            shownWords.clear();
        }

        Random random = new Random();
        Object[] keys = flashcards.keySet().toArray();
        do {
            currentWord = (String) keys[random.nextInt(keys.length)];
        } while (shownWords.contains(currentWord));

        shownWords.add(currentWord);
        showingFinnish = random.nextBoolean();
    }

    public void flipCard() {
        showingFinnish = !showingFinnish;
    }

    public int getShownWordsCount() {
        return shownWords.size();
    }

    public int getTotalWordsCount() {
        return flashcards.size();
    }

    public void markCorrect() {
        correctWords.add(currentWord);
    }

    public void markIncorrect() {
        incorrectWords.add(currentWord);
    }

    public int getCorrectCount() {
        return correctWords.size();
    }

    public int getIncorrectCount() {
        return incorrectWords.size();
    }

    public Set<String> getCorrectWords() {
        return correctWords;
    }
    
    public Set<String> getIncorrectWords() {
        return incorrectWords;
    }
    
    public String getTranslation(String word) {
        return flashcards.get(word);
    }

    public void reset() {
        shownWords.clear();
        correctWords.clear();
        incorrectWords.clear();
    }
}
