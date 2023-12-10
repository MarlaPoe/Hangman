import java.util.Scanner;

public class Hangman {
    /**
     * In WORDS are all words used in the hangman-game.
     */
    private static final String[] WORDS = { };

    /**
     * MAX_TRIES are the maximum tries a player has in the game.
     * The number 6 for MAX_TRIES stands for 2 legs, 1 body, 2 arms, 1 head
     */
    private static final int MAX_TRIES = 6;

    /**
     * This is the Main Method, where the program starts.
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {

        String wordToGuess = getRandomWord();
        char[] guessedWord = new char[wordToGuess.length()];

        initializeGuessedWordWithLines(guessedWord);

        int attemptsLeft = MAX_TRIES;
        Scanner scanner = new Scanner(System.in);
        while (attemptsLeft > 0 && !isWordGuessed(guessedWord)) {
            System.out.println("Word to guess: " + String.valueOf(guessedWord));
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Enter a letter: ");
            char guessedLetter = scanner.next().charAt(0);

            if (!isValidInput(guessedLetter, guessedWord)) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            if (isLetterInWord(guessedLetter, wordToGuess)) {
                updateGuessedWord(guessedLetter, wordToGuess, guessedWord);
            } else {
                attemptsLeft--;
                System.out.println("The letter is not in word!");
            }
        }
        scanner.close();

        if (isWordGuessed(guessedWord)) {
            System.out.println("You are very smart! You guessed the word: "
                    + wordToGuess + "!");
        } else {
            System.out.println("Oh no! You lost."
                    + "The word was: " + wordToGuess);
        }
    }
    /**
     * Creates a random word from the array.
     * If the array is empty, it returns a message,
     * that the array ist empty.
     * @return A random word from the WORDS-array,
     *         or a message, telling the array is empty.
     */
    private static String getRandomWord() throws Exception {
        if (WORDS.length == 0) {
            System.out.println("The WORD Array is empty."); 
            System.exit(0);
        }
        int randomIndex = (int) (Math.random() * WORDS.length);
        return WORDS[randomIndex];
    }

    /**
     * Initializes a  character-array with Lines ('_').
     * It represents the Word, which the player has already found out.
     * @param guessedWord The character array in form of Lines.
     */
    private static void initializeGuessedWordWithLines(
            final char[] guessedWord) {
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
    }
    /**
     * Checks if the word is already completely guessed.
     * This is the case when no Line is in the character-array anymore.
     * @param guessedWord The character array with the already guessed
     * characters or Lines in the word.
     * @return 'true' if no characters in the array is a Line ('_') anymore.
     * or 'false', if there's still a Line.
     */
    private static boolean isWordGuessed(final char[] guessedWord) {
        for (char c : guessedWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
    /**
     * Checks if the input from the user is a letter
     * and ist not already in the character-array.
     * @param guessedLetter The input character from the player.
     * @param guessedWord   The character-array that is already guessed.
     * @return  'true' if the guessedLetter is a  letter  and
     *          is not already in the guessedWord guessed.
     *          otherwise it is 'false'.
     */
    private static boolean isValidInput(
            final char guessedLetter, final char[] guessedWord) {
        return Character.isLetter(guessedLetter)
        && !isLetterAlreadyGuessed(guessedLetter, guessedWord);
    }

    /**
     * Checks if the guessedLetter is in the wordToGuess.
     * @param guessedLetter The input character from the player.
     * @param wordToGuess   The word that should be guessed.
     * @return  'true' if the guessedLetter is in the wordToGuess,
     *          otherwise it is 'false'.
     */
    private static boolean isLetterInWord(
            final char guessedLetter, final String wordToGuess) {
        return wordToGuess.indexOf(guessedLetter) != -1;
    }
    /**
     * Updates the guessedWord and puts the lettter in the guessedLetter-array.
     * @param guessedLetter The input character from the player,
     *                      that is correct.
     * @param wordToGuess   The word that should be guessed.
     * @param guessedWord   The character-array that is already guessed.
     */
    private static void updateGuessedWord(
            final char guessedLetter, final String wordToGuess,
            final char[] guessedWord) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guessedLetter) {
                guessedWord[i] = guessedLetter;
            }
        }
    }
/**
 * Checks if the guessedLetter is already in the guessedWord-array.
 * @param guessedLetter The input character from the player.
 * @param guessedWord   The character-array that is already guessed.
 * @return  'true' if the guessedLetter is already in guessedWord,
 *          otherwise, it is 'false'.
 */
    private static boolean isLetterAlreadyGuessed(
            final char guessedLetter, final char[] guessedWord) {
        for (char c : guessedWord) {
            if (c == guessedLetter) {
                return true;
            }
        }
        return false;
    }
}
