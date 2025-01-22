package FlashcardApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Map;

public class FlashcardGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton cardButton;
    private JButton nextButton;
    private JButton correctButton;
    private JButton incorrectButton;
    private JButton reviewButton;
    private JButton resetButton;
    private JLabel counterLabel;
    private JLabel instructionLabel;
    private JLabel correctCountLabel;
    private JLabel incorrectCountLabel;
    private FlashcardManager flashcardManager;

    public FlashcardGUI(Map<String, String> flashcards) {
        this.flashcardManager = new FlashcardManager(flashcards);

        // Initialize the frame
        frame = new JFrame(Constants.FRAME_TITLE);
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Initialize the counter label
        counterLabel = new JLabel("1/" + flashcardManager.getTotalWordsCount());
        counterLabel.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD, 12));
        counterLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Initialize the instruction label
        instructionLabel = new JLabel(Constants.INSTRUCTION_TEXT);
        instructionLabel.setFont(new Font(Constants.FONT_BOOKMAN, Font.PLAIN, 12));
        instructionLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Initialize the flashcard button
        cardButton = new JButton();
        cardButton.setFont(new Font(Constants.FONT_BOOK_ANTIQUA, Font.PLAIN, 24));
        cardButton.setPreferredSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));
        cardButton.setMaximumSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));
        cardButton.setMinimumSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));
        cardButton.setBorder(new LineBorder(Color.BLACK, 2));
        cardButton.setBackground(Color.WHITE);
        cardButton.setOpaque(true);
        cardButton.setFocusPainted(false);
        cardButton.addActionListener(e -> handleFlipCard());

        // Initialize the next button with an arrow symbol
        nextButton = new JButton(Constants.NEXT_BUTTON_TEXT);
        nextButton.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD, 36));
        nextButton.setBorder(null);
        nextButton.setContentAreaFilled(false);
        nextButton.setFocusPainted(false);
        nextButton.addActionListener(e -> handleNextCard());

        // Initialize the correct button
        correctButton = new JButton(Constants.CORRECT_BUTTON_TEXT);
        correctButton.setFont(new Font(Constants.FONT_SEGOE_UI, Font.BOLD, 36));
        correctButton.setForeground(Color.GREEN);
        correctButton.setBorder(null);
        correctButton.setContentAreaFilled(false);
        correctButton.setFocusPainted(false);
        correctButton.addActionListener(e -> handleMarkCorrect());

        // Initialize the incorrect button
        incorrectButton = new JButton(Constants.INCORRECT_BUTTON_TEXT);
        incorrectButton.setFont(new Font(Constants.FONT_SEGOE_UI, Font.BOLD, 36));
        incorrectButton.setForeground(Color.RED);
        incorrectButton.setBorder(null);
        incorrectButton.setContentAreaFilled(false);
        incorrectButton.setFocusPainted(false);
        incorrectButton.addActionListener(e -> handleMarkIncorrect());

        // Initialize the correct count label
        correctCountLabel = new JLabel("0");
        correctCountLabel.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD, 12));
        correctCountLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize the incorrect count label
        incorrectCountLabel = new JLabel("0");
        incorrectCountLabel.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD, 12));
        incorrectCountLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize the Review Results button
        reviewButton = new JButton("Review Results");
        reviewButton.setFont(new Font(Constants.FONT_BOOK_ANTIQUA, Font.PLAIN, 12));
        reviewButton.setPreferredSize(new Dimension(100, 20));
        reviewButton.setBorder(new LineBorder(Color.BLACK, 2));
        reviewButton.setBackground(Color.WHITE);
        reviewButton.setOpaque(true);
        reviewButton.setFocusPainted(false);
        reviewButton.addActionListener(e -> showResults());

        // Initialize the reset button
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font(Constants.FONT_BOOK_ANTIQUA, Font.PLAIN, 12));
        resetButton.setPreferredSize(new Dimension(100,20));
        resetButton.setBorder(new LineBorder(Color.BLACK, 2));
        resetButton.setBackground(Color.WHITE);
        resetButton.setOpaque(true);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(e -> resetSession());

        // Add components to the panel with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(instructionLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(counterLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(cardButton, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(incorrectButton, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(correctButton, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 20, 10, 0);
        panel.add(incorrectCountLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 10, 20);
        panel.add(correctCountLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(nextButton, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(reviewButton, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(resetButton, gbc);

        // Add panel to the frame
        frame.add(panel);
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Show the first random card if flashcards are loaded
        if (flashcardManager.getTotalWordsCount() > 0) {
            flashcardManager.showRandomCard();
            updateCard();
            updateInstruction();
            updateCounter();
        } else {
            cardButton.setText(Constants.NO_WORDS_LOADED);
        }
    }

    private void handleFlipCard() {
        flashcardManager.flipCard();
        updateCard();
        updateInstruction();
    }

    private void handleNextCard() {
        flashcardManager.showRandomCard();
        updateCard();
        updateCounter();
        updateInstruction();
        checkCompletion();
    }

    private void handleMarkCorrect() {
        flashcardManager.markCorrect();
        updateCorrectCount();
    }

    private void handleMarkIncorrect() {
        flashcardManager.markIncorrect();
        updateIncorrectCount();
    }

    private void updateCard() {
        String text = flashcardManager.isShowingFinnish() ? flashcardManager.getCurrentWord() : flashcardManager.getTranslation();
        int length = text.length();
        int fontSize = 24;

        if (length > 50) {
            fontSize = 16;
        } else if (length > 30) {
            fontSize = 20;
        }

        cardButton.setFont(new Font(Constants.FONT_BOOK_ANTIQUA, Font.PLAIN, fontSize));
        cardButton.setText("<html>" + text.replaceAll("/", "/<br>") + "</html>");
    }

    private void updateCounter() {
        counterLabel.setText(flashcardManager.getShownWordsCount() + "/" + flashcardManager.getTotalWordsCount());
    }

    private void updateInstruction() {
        if (flashcardManager.isShowingFinnish()) {
            instructionLabel.setText("Click the card to reveal the Swedish word");
        } else {
            instructionLabel.setText("Click the card to reveal the Finnish word");
        }
    }

    private void updateCorrectCount() {
        correctCountLabel.setText(String.valueOf(flashcardManager.getCorrectCount()));
    }

    private void updateIncorrectCount() {
        incorrectCountLabel.setText(String.valueOf(flashcardManager.getIncorrectCount()));
    }

    private void showResults() {
        StringBuilder message = new StringBuilder();
        message.append("<html>");
        message.append("<h2 style='font-family: Book Antiqua; font-size: 14px;'>Correct answers:</h2>");
        message.append("<p style='font-family: Bookman Old Style; font-size: 12px;'>");
        for (String word : flashcardManager.getCorrectWords()) {
            message.append(word).append(" - ").append(flashcardManager.getTranslation(word)).append("<br>");
        }
        message.append("</p>");
        message.append("<h2 style='font-family: Book Antiqua; font-size: 14px;'>Incorrect answers:</h2>");
        message.append("<p style='font-family: Bookman Old Style; font-size: 12px;'>");
        for (String word : flashcardManager.getIncorrectWords()) {
            message.append(word).append(" - ").append(flashcardManager.getTranslation(word)).append("<br>");
        }
        message.append("</p>");
        message.append("</html>");

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(message.toString());
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(frame, scrollPane, "Results", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetSession() {
        // Reset the flashcard manager's state
        flashcardManager.reset();

        // Reset counters and labels
        updateCounter();
        updateCorrectCount();
        updateIncorrectCount();

        // Reload the flashcards and show the first one
        flashcardManager.showRandomCard();
        updateCard();
        updateInstruction();
    }

    private void checkCompletion() {
        if (flashcardManager.getShownWordsCount() == flashcardManager.getTotalWordsCount()) {
            showResults();
            int response = JOptionPane.showConfirmDialog(frame, "Do you want to reset and try again?", "Reset", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                resetSession();
            }
        }
    }
}
