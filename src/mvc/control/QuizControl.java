package mvc.control;

import mvc.model.QuizModel;
import mvc.view.QuizView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QuizControl implements ActionListener {
    private QuizModel model;
    private QuizView view;
    private MasterController controller;
    private String[] cards;
    private int currentCard = 0;

    public QuizControl(MasterController controller) {
        System.out.println("quiz control");
        this.controller = controller;
        this.model = new QuizModel();
        this.view = new QuizView();
        view.addButtonListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case"mainmenu":
            controller.showMainMenu();
            break;
            case "start":
                if(isLoaded()) {
                    String[] shuffled = model.startQuiz(cards);
                    view.startQuiz(shuffled[currentCard]);
                }
                else{
                    JOptionPane.showMessageDialog(view, "Cards are not loaded");
                }
            case "Check":

                model.check(view.getAnswer().getText());
                if(currentCard +2 > cards.length) {
                    System.out.println("endquizcheck");
                    int[] affe = model.endQuiz();
                    view.endQuiz(affe[0],affe[1],affe[2],(double)affe[0]/affe[1]);
                }
                else{
                    view.nextCard(cards[currentCard]);
                    currentCard+=2;
                }
                break;
            case "Restart":
                new QuizControl(controller);
                break;
            case "stop":
                if(currentCard > 0) {
                    int[] affe = model.endQuiz();
                    view.endQuiz(affe[0],affe[1],affe[2],(double)affe[0]/affe[1]);
                }
                else{
                    controller.showMainMenu();
                }
                break;
        }

    }

    public boolean isLoaded() {
        if(controller.getCards() != null) {
            cards = controller.getCards();
            return true;
        }
        return false;
    }
    public JPanel getView() {
        return view;
    }
}
