package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;


public class HangmanModel {
    private KarteiKarte[] cards;
    private int currentAnswer;
    private int questions;
    private int questionscorrect;
    private long startTime,endTime;

    public KarteiKarte[] startQuiz(KarteiKarten cards) {
        startTime = System.currentTimeMillis();
        currentAnswer = 0;
        this.cards = cards.shuffle();
        questions = 0;
        questionscorrect = 0;
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
        questions++;
        System.out.println(currentAnswer);
        if( cards[currentAnswer].isAntwort(answer)) {
            this.questionscorrect++;
            this.currentAnswer++;
            return true;
        }
        else{
            this.currentAnswer++;
            return false;
        }

    }
    public int[] compareChars(char character, String solution){
        int[] correctChars = new int[solution.length()];
        int j= 0;
        for (int i = 0; i < solution.length(); i++) {
            if(solution.charAt(i) == character){
                correctChars[j] = i;
                j ++;
            }
        }
        return correctChars;
    }


}
