package mvc.model;

import java.awt.*;

public class HangmanModel {
    private String[] cards;
    private int currentAnswer;
    private int questions;
    private int questionsfalse;

    public String[] startHangman(String[] cards) {
        currentAnswer = 1;
        this.cards = cards;
        questions = 0;
        questionsfalse = 0;
        return shuffle();
    }
    public int[] endHangman() {
        int[] results = new int[3];
        results[0] = questions;
        results[1] = questionsfalse;
        results[2] = questions+questionsfalse;
        return results;
    }
    public void check(String answer) {
        questions++;
        System.out.println(currentAnswer);
        if (!cards[currentAnswer].equalsIgnoreCase(answer)) {
            questionsfalse++;
        }
        currentAnswer=currentAnswer+2;

    }
    public String[] shuffle() { // zu implementieren
        return cards;
    }
}

