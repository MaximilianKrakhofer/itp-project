package mvc.model;

import mvc.control.MasterController;

public class QuizModel {
    private String[] cards;
    private int currentAnswer;
    private int questions;
    private int questionscorrect;
    public String[] startQuiz(String[] cards) {
        startTime();
        currentAnswer = 1;
        this.cards = cards;
        questions = 0;
        questionscorrect = 0;
        return shuffle();
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
        if(cards[currentAnswer].equalsIgnoreCase(answer)) {
            questionscorrect++;
            currentAnswer=currentAnswer+2;
        }
        else{
            currentAnswer=currentAnswer+2;
        }

    }
    public void startTime() {

    }
    public int endTime() {
        return 0;
    }
}
