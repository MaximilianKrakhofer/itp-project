package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;


public class QuizModel {
    private KarteiKarte[] cards;
    private int currentAnswer;
    private int questions;
    private int questionscorrect;
    private long startTime, endTime;

    public KarteiKarte[] startQuiz(KarteiKarten cards) {
        startTime = System.currentTimeMillis();
        currentAnswer = 0;
        this.cards = cards.shuffle();
        questions = 0;
        questionscorrect = 0;
        return this.cards;
    }

    public int[] getEndResults() {
        endTime = System.currentTimeMillis();
        int[] results = new int[4];
        results[0] = questions;
        results[1] = questionscorrect;
        results[2] = (int) (endTime - startTime) / 1000;

        if(questions != 0) {
            results[3] =  questionscorrect =(int)( (double) questionscorrect /questions *100.0);
        }
        else {
            results[3] = 0;
        }

        return results;
    }

    public boolean check(String answer) {
        System.out.println("checkmet" + cards[currentAnswer].getAntwort() + "answ" + answer + cards[currentAnswer].isAntwort(answer));

        if (cards[currentAnswer].isAntwort(answer)) {

            questions++;
            System.out.println(cards[currentAnswer].getAntwort() + "answ" + answer + cards[currentAnswer].isAntwort(answer));
            this.questionscorrect++;
            this.currentAnswer++;
            return true;
        } else {

            questions++;
            this.currentAnswer++;
            return false;
        }

    }
}
