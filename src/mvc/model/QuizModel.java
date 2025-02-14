package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;



public class QuizModel {
    private KarteiKarte[] cards;
    private int currentAnswer;
    private int questions;
    private int questionscorrect;
    public KarteiKarte[] startQuiz(KarteiKarten cards) {
        startTime();
        currentAnswer = 1;
        this.cards = cards.shuffle();
        questions = 0;
        questionscorrect = 0;
        return this.cards;
    }

    public int[] endQuiz() {
        int[] results = new int[3];
        results[0] = questions;
        results[1] = questionscorrect;
        results[2] = endTime();
        return results;
    }
    public void check(String answer) {
        questions++;
        System.out.println(currentAnswer);
        if(cards[currentAnswer].isAntwort(answer)) {
            questionscorrect++;
            currentAnswer=currentAnswer+1;
        }
        else{
            currentAnswer=currentAnswer+1;
        }

    }
    public void startTime() {

    }
    public int endTime() {
        return 0;
    }
}
