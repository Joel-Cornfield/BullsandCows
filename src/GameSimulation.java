package bullscows;

import java.util.Scanner;

class GameSimulation {
    private int codeLength;
    private String secretCode;
    private int possibleSymbols;

    private static Scanner scanner = new Scanner(System.in);

    public GameSimulation() {
        getCodeDetails();
        this.secretCode = CodeTools.generateSecretCode(codeLength, possibleSymbols);
    }

    private void getCodeDetails() {

        String code = "";
        do {
            System.out.println("Input the length of the secret code:");
            try {
                code = scanner.next();
                codeLength = Integer.parseInt(code);

                if (codeLength <= 0) {
                    System.out.println("Error: The length must be a positive integer.");
                } else if (codeLength > 36) {
                    System.out.println("Error: Invalid input. Code length cannot be greater than 36.");
                }
            } catch (NumberFormatException e) {
                System.out.printf("Error: \"%s\" isn't a valid number.%n", code);
            }
        } while (codeLength <= 0 || codeLength > 36);

        do {
            System.out.println("Input the number of possible symbols in the code:");
            try {
                possibleSymbols = scanner.nextInt();
                if (possibleSymbols <= 0) {
                    System.out.println("Error: The amount must be a positive integer.");
                } else if (possibleSymbols > 36) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                } else if (possibleSymbols < codeLength) {
                    System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.%n", codeLength, possibleSymbols);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Error: \"%s\" isn't a valid number.%n", possibleSymbols);
                scanner.next(); // Consume the invalid input to avoid an infinite loop
            }
        } while (possibleSymbols <= 0 || possibleSymbols > 36 || possibleSymbols < codeLength);

    }

    public void startGame() {
        System.out.println("The secret is prepared: " + "*".repeat(codeLength) + " " + CodeTools.getSymbolRange());
        System.out.println("Okay, let's start a game!");

        String guess;
        boolean gameWon = false;
        int turn = 1;

        do {
            System.out.printf("Turn %d:%n", turn);
            do {
                guess = scanner.next();
                if (guess.length() != codeLength) {
                    System.out.println("Invalid guess. Please enter a guess of the correct length.");
                }
            } while (guess.length() != codeLength);

            gameWon = playTurns(guess);
            turn++;
        } while (!gameWon);
    }

    private boolean playTurns(String guess) {
        boolean gameWon = CodeTools.checkInput(guess, secretCode, codeLength);
        return gameWon;
    }
}

