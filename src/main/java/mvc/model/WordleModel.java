package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;


public class WordleModel {
    private KarteiKarte[] cards;
    private int currentAnswer;
    private int questions;
    private int questionscorrect;
    private long startTime, endTime;

    public KarteiKarte[] startQuiz(KarteiKarten cards) {
        startTime = System.currentTimeMillis();
        currentAnswer = 0;
        cards.removePictures();
        this.cards = cards.shuffle();
        questions = 0;
        questionscorrect = 0;
        return this.cards;
    }

    public int[] endQuiz() {
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
        if (cards[currentAnswer].getAntwort().equalsIgnoreCase(answer)) {

            currentAnswer++;
            return true;
        } else {
            return false;
        }


    }
    public void increaseCurrentAnswer() {
        currentAnswer++;
    }
    public int[] compareChars(String attempt, String solution) {
        int[] correctChars = new int[solution.length()];
        boolean[] used = new boolean[solution.length()];

        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == attempt.charAt(i)) {
                correctChars[i] = 1;
                used[i] = true;
            } else {
                correctChars[i] = -1;
            }
        }
        for (int i = 0; i < attempt.length(); i++) {
            if (correctChars[i] == 1) {
                continue;
            }

            for (int j = 0; j < solution.length(); j++) {
                if (!used[j] && attempt.charAt(i) == solution.charAt(j)) {
                    correctChars[i] = 2;
                    used[j] = true;
                    break;
                }
            }
        }

        return correctChars;
    }

    public void increaseQuestionsCorrect() {
        this.questionscorrect++;
    }

    public void increaseQustions() {
        this.questions++;
    }

}
