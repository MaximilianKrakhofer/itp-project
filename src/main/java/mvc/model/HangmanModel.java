package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;

import java.util.Arrays;


public class HangmanModel {
    private KarteiKarte[] cards;
    private int questionscorrect, counter, questions, currentAnswer, failedChars, failedWords, currentHangman;
    private long startTime, endTime;
    private boolean atleastOne;
    private int[] hangmanCompletions;


    public KarteiKarte[] startQuiz(KarteiKarten cards) {
        startTime = System.currentTimeMillis();
        currentAnswer = 0;
        this.cards = cards.shuffle();
        questions = 0;
        questionscorrect = 0;
        hangmanCompletions = new int[cards.getCards().length];
        return this.cards;
    }

    public int[] endQuiz() {
        endTime = System.currentTimeMillis();
        int[] results = new int[5];
        results[0] = currentHangman ;
        results[1] = failedChars;
        results[2] = failedWords;
        results[3] = (int) (endTime - startTime) / 1000;
        double []toAvg = new double[hangmanCompletions.length ];
        for (int i = 0; i < hangmanCompletions.length; i++) {
                toAvg[i] = 100.0 - (hangmanCompletions[i] * (100.0/7.0));
        }
        int percent =0;
        for (int i = 0; i < hangmanCompletions.length; i++) {
            if(toAvg[i] != 0) {
                percent += toAvg[i] /hangmanCompletions.length;

            }


        }

        results[4] = percent;
        return results;
    }

    public boolean check(String answer) {
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == '_') {
                return false;
            }
        }
        return true;

    }

    public int[] compareChars(char character, String solution) {
        solution = solution.toLowerCase();
        int[] correctChars = new int[solution.length()];
        int j = 0;
        atleastOne = false;
        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == character) {
                correctChars[i] = i;
                atleastOne = true;
            } else {
                correctChars[i] = -1;
            }
        }
        return correctChars;
    }

    public boolean getAtleastOne() {
        return this.atleastOne;
    }

    public String increaseCounter() {
        counter++;
        hangmanCompletions[currentHangman]++;
        String hangman = "";
        switch (counter) {

            case 1:
                hangman = "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========''', ''";
                break;
            case 2:
                hangman = "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========''', '''";
                break;
            case 3:
                hangman = "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========''', '''";
                break;
            case 4:
                hangman = "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========''', '''";
                break;
            case 5:
                hangman = "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========''', '''";
                break;
            case 6:
                hangman = "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========''', '''";
                break;
            case 7:
                hangman = "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "=========''']";
        }
        return hangman;
    }

    public int getCounter() {
        return counter;
    }

    public void setCurrentHangman(int currentHangman) {
        this.currentHangman = currentHangman;
    }

    public void setAtleastOne(boolean atleastOne) {
        this.atleastOne = atleastOne;
    }

    public void increaseFailedChars() {
        failedChars++;
    }

    public void increaseFailedWords() {
        failedWords++;
    }

    public int getFailedChars() {
        return failedChars;
    }

    public int getFailedWords() {
        return failedWords;
    }

    public int[] getHangmanCompletions() {
        return hangmanCompletions;
    }

    public int getCurrentHangman() {
        return currentHangman;
    }
}
