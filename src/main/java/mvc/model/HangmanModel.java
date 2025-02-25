package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;


public class HangmanModel {
    private KarteiKarte[] cards;
    private int questionscorrect, counter, questions, currentAnswer, failedChars, failedWords, currentHangman;
    private long startTime,endTime;
    private boolean atleastOne;
    private int[] hangmanCompletions;


    public KarteiKarte[] startQuiz(KarteiKarten cards) {
        startTime = System.currentTimeMillis();
        currentAnswer = 0;
        this.cards = cards.shuffle();
        questions = 0;
        questionscorrect = 0;
        hangmanCompletions = new int [cards.getCards().length];
        return this.cards;
    }

    public int[] endQuiz() {
        endTime = System.currentTimeMillis();
        int[] results = new int[3];
        results[0] = questions;
        results[1] = questionscorrect;
        results[2] = (int) (endTime-startTime)/1000;
        return results;
    }
    public boolean check(String answer) {
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == '_'){
                return false;
            }
        }
        return true;

    }
    public int[] compareChars(char character, String solution){
        int[] correctChars = new int[solution.length()];
        int j= 0;
        atleastOne = false;
        for (int i = 0; i < solution.length(); i++) {
            if(solution.charAt(i) == character){
                correctChars[i] = i;
                atleastOne =true;
            }
            else {
                correctChars[i] = -1;
            }
        }
        return correctChars;
    }
    public boolean getAtleastOne(){
        return this.atleastOne;
    }
    public String increaseCounter(){
        counter ++;
        hangmanCompletions[currentHangman] ++;
        String hangman ="";
        switch (counter){

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
    public void increaseFailedChars(){
        failedChars ++;
    }
    public void increaseFailedWords(){
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

}
