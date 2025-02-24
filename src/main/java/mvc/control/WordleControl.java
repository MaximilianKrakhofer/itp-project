package mvc.control;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.model.QuizModel;
import mvc.model.WordleModel;
import mvc.view.QuizView;
import mvc.view.WordleView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class WordleControl implements ActionListener {
    private WordleModel model;
    private WordleView view;
    private MasterController controller;
    private KarteiKarten cards;
    private KarteiKarte[] shuffled;
    private int currentCard = 0;
    public WordleControl(MasterController controller) {
        System.out.println("quiz control");
        this.controller = controller;
        this.model = new WordleModel();
        this.view = new WordleView(isLoaded());
        view.addButtonListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case"mainmenu":
                controller.showMainMenu();
                break;
            case "restart":
                this.model = new WordleModel();
                view.revalidate();
                view.repaint();
                view.addButtonListener(this);
            case "start":
                if(isLoaded()) {
                    KarteiKarte[] shuffled = model.startQuiz(cards);
                    this.shuffled = shuffled;
                    view.startQuiz(shuffled[currentCard].getFrage(),shuffled[currentCard].getFragentyp());
                    view.addButtonListener(this);
                    view.repaint();
                    view.revalidate();
                }
                else{
                    JOptionPane.showMessageDialog(view, "Cards are not loaded");
                    controller.showMainMenu();
                }
                break;
            case "End Quiz":
                if(view.getAnswer()!= null) {
                    currentCard = 0;
                    model.check(view.getAnswer());
                    System.out.println("endquizcheck");
                    int[] affe = model.endQuiz();
                    double prozent = affe[1] == 0 ? 0.0: (double) (affe[1] / affe[0])*100;

                    view.endQuiz(affe[0],affe[1],affe[2],prozent);
                    view.addButtonListener(this);
                }
                else{
                    JOptionPane.showMessageDialog(view, "Answer not entered");
                }
                break;
            case "Check":
                System.out.println("check"+ currentCard);
                view.setCheck(model.check(view.getAnswer()), shuffled[currentCard].getAntwort(), shuffled[currentCard].getFragentyp()); // checkt ob korrekt
                if(currentCard +1 >= cards.getCards().length) {
                    System.out.println("endquizcheck");
                    currentCard = 0;
                    int[] affe = model.endQuiz();
                    double prozent = affe[1] == 0 ? 0.0: (double) (affe[1] / affe[0])*100;
                    view.endQuiz(affe[0],affe[1],affe[2],prozent);
                    view.addButtonListener(this);
                }
                else{
                    currentCard+=1;
                    view.nextCard(shuffled[currentCard].getFrage(),shuffled[currentCard].getFragentyp());
                }


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
