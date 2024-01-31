package bullscows;

import java.util.HashSet;
import java.util.Set;

class CodeTools {
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz";

    private static String symbolString;
    public static String generateSecretCode(int length, int possibleSymbols) {

        symbolString = SYMBOLS.substring(0, possibleSymbols);

        StringBuilder codeBuilder = new StringBuilder();
        Set<Character> usedDigits = new HashSet<>();

        do {
            codeBuilder.setLength(0);
            usedDigits.clear();

            for (int i = 0; i < length; i++) {
                char symbol;
                do {
                    symbol = symbolString.charAt((int) (Math.random() * symbolString.length()));
                } while (i == 0 && symbol == '0' || !usedDigits.add(symbol));

                codeBuilder.append(symbol);
            }
        } while (codeBuilder.length() < length);

        return codeBuilder.toString();
    }

    public static boolean checkInput(String guess, String secretCode, int length) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (guess.charAt(i) == secretCode.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }

        if (bulls == length) {
            System.out.printf("Grade: %d bulls%n", bulls);
            System.out.println("Congratulations! You guessed the secret code.");
            return true;
        } else {
            if (bulls > 0 || cows > 0) {
                System.out.printf("Grade: %d bull(s) and %d cow(s)%n", bulls, cows);
            } else {
                System.out.printf("Grade: None%n");
            }
            return false;
        }
    }

    public static String getSymbolRange() {
        char lastSymbol = symbolString.charAt(symbolString.length() - 1);

        String numbers = "";
        String characters = "";

        for (char symbol : symbolString.toCharArray()) {
            if (Character.isDigit(symbol)) {
                numbers += symbol;
            } else {
                characters += symbol;
            }
        }

        String range = "";
        if (!numbers.isEmpty()) {
            range += "(" + getRange(numbers) + ")";
        }

        if (!characters.isEmpty()) {
            range += (range.isEmpty() ? "" : " ") + "(" + getRange(characters) + ")";
        }

        return range;
    }

    private static String getRange(String symbols) {
        char start = symbols.charAt(0);
        char end = symbols.charAt(symbols.length() - 1);

        return start == end ? String.valueOf(start) : start + "-" + end;
    }





}

