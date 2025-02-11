package mvc.model;

import mvc.Karten.KarteiKarten;

public class HangmanModel {
    private KarteiKarten karten;
    private int currentAnswer;
    private int questions;
    private int questionsfalse;

    public KarteiKarten startHangman(KarteiKarten cards) {
        currentAnswer = 1;
        this.karten = cards;
        questions = 0;
        questionsfalse = 0;
        return shuffle();
    }
    public int[] endHangmanAndGetResults() {
        int[] results = new int[3];
        results[0] = questions;
        results[1] = questionsfalse;
        results[2] = questions+questionsfalse;
        return results;
    }
    public void check(String answer) {
        questions++;
        System.out.println(currentAnswer);
        if (!karten.getCard(currentAnswer).getAntwort().equalsIgnoreCase(answer)) {
            questionsfalse++;
        }
        currentAnswer=currentAnswer+2;

    }
    public KarteiKarten shuffle() { // zu implementieren
        return karten;
    }
}

