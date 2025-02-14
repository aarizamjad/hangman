import java.util.Scanner;
import java.util.Random;

public class Hangman {
    // List of words for the game
    private static final String[] WORDS = {"java", "computer", "programming", "developer", "algorithm"};
    private static final int MAX_ATTEMPTS = 6; // Maximum wrong guesses allowed

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // Select a random word from the list
        String wordToGuess = WORDS[random.nextInt(WORDS.length)];
        char[] guessedWord = new char[wordToGuess.length()];
        boolean[] guessedLetters = new boolean[26]; // To track guessed letters

        // Initialize guessed word with underscores
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        int attemptsLeft = MAX_ATTEMPTS;
        boolean wordGuessed = false;

        System.out.println("Welcome to Hangman!");

        // Main game loop
        while (attemptsLeft > 0 && !wordGuessed) {
            System.out.println("\nWord to guess: " + String.valueOf(guessedWord));
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Guess a letter: ");

            char guess = scanner.next().toLowerCase().charAt(0);

            // Check if input is a valid letter
            if (guess < 'a' || guess > 'z') {
                System.out.println("Invalid input. Please enter a letter.");
                continue;
            }

            // Check if the letter was already guessed
            if (guessedLetters[guess - 'a']) {
                System.out.println("You already guessed that letter!");
                continue;
            }

            guessedLetters[guess - 'a'] = true; // Mark letter as guessed
            boolean correctGuess = false;

            // Check if guessed letter is in the word
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    correctGuess = true;
                }
            }

            // If guess was incorrect, decrease attempts
            if (!correctGuess) {
                attemptsLeft--;
                System.out.println("Wrong guess!");
            }

            // Check if the whole word is guessed
            wordGuessed = String.valueOf(guessedWord).equals(wordToGuess);
        }

        // Game over message
        if (wordGuessed) {
            System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("\nGame Over! The correct word was: " + wordToGuess);
        }

        scanner.close(); }}