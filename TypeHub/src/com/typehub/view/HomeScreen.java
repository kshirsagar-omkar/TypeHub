package com.typehub.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import com.typehub.utilities.filehandler.FileHelper;

/**
 * HomeScreen is the main frame for the TypeHub application. This GUI-based
 * application allows users to perform typing speed tests by displaying sample
 * text and tracking user input. Correctly typed characters are highlighted in
 * green, while incorrect characters are highlighted in red.
 * <p>
 * It includes:
 * <ul>
 * <li>A JTextArea to display and type the sample text</li>
 * <li>Highlighters to mark correct and incorrect characters</li>
 * <li>Keyboard listener for real-time character matching and cursor
 * control</li>
 * </ul>
 * 
 * @author Omkar Kshirsagar
 * @version 1.0
 * @since 1.0
 */
public class HomeScreen extends JFrame {

	/**
	 * JTextArea for displaying and typing the sample text.
	 */
	JTextArea typingArea;

	/**
	 * JScrollPane to enable scrolling for the typing area if the text exceeds its
	 * display area.
	 */
	JScrollPane scrollPane;

	/**
	 * Tracks the current typing position within the sample text.
	 */
	private Integer currentPosition = 0;

	/*
	 * Tracks the count of incorrect typed characters
	 */
	private Integer countOfIncorrectTypedChars = 0;

	/**
	 * The sample text that users are expected to type, retrieved from FileHelper.
	 */
	private final String sampleText;

	/**
	 * Highlighter for marking incorrect characters in red.
	 */
	Highlighter incorrectHighlighter;

	/**
	 * Highlighter for marking correct characters in green.
	 */
	Highlighter correctHighlighter;

	/**
	 * Constructor for HomeScreen. Sets up the layout, text area, scroll pane, and
	 * key listener to track user input. Initializes highlighters for marking
	 * correct and incorrect characters.
	 */
	public HomeScreen() {

		// Retrieve sample text to be displayed in typing area
		sampleText = FileHelper.getSampleParagraph();

		// Set layout to null for custom positioning
		this.setLayout(null);

		// Initialize JTextArea for the typing area
		typingArea = new JTextArea();
		typingArea.setText(sampleText); // Set the text to be typed
		typingArea.setBackground(new Color(245, 137, 212)); // Background color for typing area
		typingArea.setFont(new Font("Monospaced", Font.PLAIN, 18)); // Font settings
		typingArea.setWrapStyleWord(true); // Enable word wrapping
		typingArea.setLineWrap(true); // Enable line wrapping
		typingArea.setEditable(false); // Disable editing to prevent user from changing text

		// Initialize highlighter for incorrect characters
		incorrectHighlighter = typingArea.getHighlighter();
		Highlighter.HighlightPainter incorrectPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);

		// Initialize highlighter for correct characters
		correctHighlighter = typingArea.getHighlighter();
		Highlighter.HighlightPainter correctPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);

		// Add a KeyListener to handle user typing
		typingArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Character typedChar = e.getKeyChar();

				// Check if the typed character matches the current character in sample text
				if (currentPosition < sampleText.length()) {
					if (typedChar.equals(sampleText.charAt(currentPosition))) {
						try {
							correctHighlighter.addHighlight(currentPosition, currentPosition + 1, correctPainter);
						} catch (BadLocationException ex) {
							ex.printStackTrace();
						}
						// Move to the next character if correct
						currentPosition++;
					} else {
						// Highlight incorrect character if it doesn't match

						// Increase the counter, which counts the characters which are typed incorrect
						countOfIncorrectTypedChars++;
						try {
							incorrectHighlighter.addHighlight(currentPosition, currentPosition + 1, incorrectPainter);
						} catch (BadLocationException ex) {
							ex.printStackTrace();
						}
						currentPosition++; // Move to the next character
					}
					// Update the caret position
					typingArea.setCaretPosition(currentPosition);
				}

				// Check if user typed all characters
				// If he\she has typed all characters display him\her his\her score.
				if (currentPosition == sampleText.length()) {
					// message box which displays score
					JOptionPane.showMessageDialog(null,
							"Your Score " + "\nAll Characters : " + currentPosition + "\nCorrectly typed characters : "
									+ (currentPosition - countOfIncorrectTypedChars) + "\nIncorrect typed characters : "
									+ countOfIncorrectTypedChars);

					// It's important to increase currentPosition
					// If it's not increased this condition will execute on every click of user
					// after typing all characters
					currentPosition++;
				}

				// Prevent JTextArea from processing the typed character further
				e.consume();
			}
		});

		// Set typing area size and position
		typingArea.setSize(1200, 400);
		typingArea.setLocation(145, 100);
		typingArea.setOpaque(true); // Ensure background color is visible

		// Wrap the JTextArea in a JScrollPane for scrolling functionality
		scrollPane = new JScrollPane(typingArea);
		scrollPane.setBounds(145, 100, 1200, 400); // Set bounds of scroll pane
		this.add(scrollPane);

		// Frame settings
		this.setTitle("TypeHub");
		this.setSize(1500, 700);
		this.setLocation(215, 180);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(74, 189, 255));
	}
}
