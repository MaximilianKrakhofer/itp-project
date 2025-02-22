package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;

public class HangmanModel {
    private KarteiKarten karten;
    private int currentAnswer;
    private int questions;
    private int questionsfalse;

    public KarteiKarte[] startHangman(KarteiKarten cards) {
        currentAnswer = 0;
        questions = 0;
        return this.karten.shuffle();
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
        if(karten.getCard(currentAnswer).isAntwort(answer)) {
            currentAnswer=currentAnswer+1;
        }
        else{
            currentAnswer=currentAnswer+1;
        }

    }
}

