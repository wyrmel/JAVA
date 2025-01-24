// TO DO; Clean up code + make GUI for review window.

package FlashcardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
    private BufferedImage idleSpritesheet;
    private BufferedImage pawAttackSpritesheet;
    private Image[] idleFrames;
    private Image[] pawAttackFrames;
    private int currentFrame = 0;
    private boolean isPawAttack = false;
    private Timer idleTimer;
    private Timer pawAttackTimer;
    private Timer pauseTimer;
    private Timer pawFrameTimer;

    public FlashcardGUI(Map<String, String> flashcards) {
        this.flashcardManager = new FlashcardManager(flashcards);

        // Load the spritesheets
        try {
            idleSpritesheet = ImageIO.read(new File("FlashcardApp/idle/idleSpritesheet.png"));
            pawAttackSpritesheet = ImageIO.read(new File("FlashcardApp/paw/pawAttackSpritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Extract frames from the spritesheets
        idleFrames = extractFrames(idleSpritesheet, 31);
        pawAttackFrames = extractFrames(pawAttackSpritesheet, 31);

        // Initialize the frame
        frame = new JFrame(Constants.FRAME_TITLE);
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Prevent window resizing
        

        // Create a custom panel with animated background
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (isPawAttack) {
                    g.drawImage(pawAttackFrames[currentFrame], 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.drawImage(idleFrames[currentFrame], 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(null); // Disable layout manager for absolute positioning
        panel.setBounds(0, 0, 500, 500);

        // Initialize the counter label
        counterLabel = new JLabel("1/" + flashcardManager.getTotalWordsCount());
        counterLabel.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD, 14));
        counterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        counterLabel.setBounds(65, 335, 90, 20); // Set position and size

        // Initialize the instruction label
        instructionLabel = new JLabel(Constants.INSTRUCTION_TEXT);
        instructionLabel.setFont(new Font(Constants.FONT_BOOKMAN, Font.PLAIN, 12));
        instructionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        instructionLabel.setBounds(120, 80, 300, 20); // Set position and size

        // Initialize the flashcard button
        cardButton = new JButton();
        cardButton.setFont(new Font(Constants.FONT_cardbutton, Constants.STYLE_cardbutton, Constants.SIZE_cardbutton));
        cardButton.setContentAreaFilled(false); // Make the button transparent
        cardButton.setOpaque(false); // Make the button non-opaque
        cardButton.setBorder(null); // Remove the border
        cardButton.setFocusPainted(false);
        cardButton.setBounds(150, 125, Constants.CARD_WIDTH, Constants.CARD_HEIGHT); // Set position and size
        cardButton.addActionListener(e -> handleFlipCard());

        // Set text position and alignment
        cardButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cardButton.setVerticalTextPosition(SwingConstants.CENTER);
        cardButton.setHorizontalAlignment(SwingConstants.CENTER);
        cardButton.setVerticalAlignment(SwingConstants.CENTER);

        // Initialize the next button with an arrow symbol
        nextButton = new JButton(Constants.NEXT_BUTTON_TEXT);
        nextButton.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD, 36));
        nextButton.setBorder(null);
        nextButton.setContentAreaFilled(false);
        nextButton.setFocusPainted(false);
        nextButton.setBounds(300, 240, 100, 50); // Set position and size
        nextButton.addActionListener(e -> handleNextCard());

        // Initialize the correct button
        correctButton = new JButton(Constants.CORRECT_BUTTON_TEXT);
        correctButton.setFont(new Font(Constants.FONT_SEGOE_UI, Font.BOLD, 12));
        correctButton.setForeground(Color.GREEN);
        correctButton.setBorder(null);
        correctButton.setContentAreaFilled(false);
        correctButton.setFocusPainted(false);
        correctButton.setBounds(190, 324, 20, 20); // Set position and size
        correctButton.addActionListener(e -> handleMarkCorrect());

        // Initialize the incorrect button
        incorrectButton = new JButton(Constants.INCORRECT_BUTTON_TEXT);
        incorrectButton.setFont(new Font(Constants.FONT_SEGOE_UI, Font.BOLD, 12));
        incorrectButton.setForeground(Color.RED);
        incorrectButton.setBorder(null);
        incorrectButton.setContentAreaFilled(false);
        incorrectButton.setFocusPainted(false);
        incorrectButton.setBounds(230, 324, 20, 20); // Set position and size
        incorrectButton.addActionListener(e -> handleMarkIncorrect());

        // Initialize the correct count label
        correctCountLabel = new JLabel("0");
        correctCountLabel.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD, 12));
        correctCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        correctCountLabel.setBounds(160, 325, 100, 20); // Set position and size

        // Initialize the incorrect count label
        incorrectCountLabel = new JLabel("0");
        incorrectCountLabel.setFont(new Font(Constants.FONT_ARIAL, Font.BOLD, 12));
        incorrectCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        incorrectCountLabel.setBounds(200, 325, 100, 20); // Set position and size

        // Initialize the Review Results button
        reviewButton = new JButton("Review Results");
        reviewButton.setFont(new Font(Constants.FONT_review_reset, Constants.STYLE_review_reset, Constants.SIZE_review_reset));
        reviewButton.setContentAreaFilled(false); // Make the button transparent
        reviewButton.setOpaque(false); // Make the button non-opaque
        reviewButton.setBorder(null); // Remove the border
        reviewButton.setFocusPainted(false);
        reviewButton.setBounds(265, 427, 100, 20); // Set position and size
        reviewButton.addActionListener(e -> showResults());

        // Initialize the reset button
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font(Constants.FONT_review_reset, Constants.STYLE_review_reset, Constants.SIZE_review_reset));
        resetButton.setContentAreaFilled(false); // Make the button transparent
        resetButton.setOpaque(false); // Make the button non-opaque
        resetButton.setBorder(null); // Remove the border
        resetButton.setFocusPainted(false);
        resetButton.setBounds(128, 419, 50, 20); // Set position and size
        resetButton.addActionListener(e -> resetSession());

        // Add components to the panel
        panel.add(counterLabel);
        panel.add(instructionLabel);
        panel.add(cardButton);
        panel.add(nextButton);
        panel.add(correctButton);
        panel.add(incorrectButton);
        panel.add(correctCountLabel);
        panel.add(incorrectCountLabel);
        panel.add(reviewButton);
        panel.add(resetButton);

        // Add panel to the frame
        frame.add(panel);
        frame.setVisible(true);

        // Set up the idle animation timer
        idleTimer = new Timer(100, e -> {
            if (!isPawAttack) {
                currentFrame = (currentFrame + 1) % idleFrames.length;
                panel.repaint();
                if (currentFrame == 0) {
                    idleTimer.stop();
                    pauseTimer.start();
                }
            }
        });
        idleTimer.start();

        // Set up the pause timer
        pauseTimer = new Timer(10000, e -> {
            currentFrame = 0;
            panel.repaint();
            pauseTimer.stop();
            idleTimer.start();
        });

        // Set up the paw attack timer
        pawAttackTimer = new Timer(60000, e -> {
            isPawAttack = true;
            currentFrame = 0;
            pawFrameTimer = new Timer(100, event -> {
                currentFrame = (currentFrame + 1) % pawAttackFrames.length;
                panel.repaint();
                if (currentFrame == 0) {
                    pawFrameTimer.stop();
                    isPawAttack = false;
                    idleTimer.start();
                }
            });
            pawFrameTimer.start();
        });
        pawAttackTimer.start();

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

    private Image[] extractFrames(BufferedImage spritesheet, int frameCount) {
        int frameWidth = spritesheet.getWidth() / frameCount;
        int frameHeight = spritesheet.getHeight();
        Image[] frames = new Image[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = spritesheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
        return frames;
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

        cardButton.setFont(new Font(Constants.FONT_cardbutton, Constants.STYLE_cardbutton, fontSize));
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
