package mvc.control;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.model.QuizModel;
import mvc.view.QuizView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class QuizControl implements ActionListener, KeyListener {
    private QuizModel model;
    private QuizView view;
    private MasterController controller;
    private KarteiKarten cards;
    private KarteiKarte[] shuffled;
    private int currentCard = 0;

    public QuizControl(MasterController controller) {
        System.out.println("quiz control");
        this.controller = controller;
        this.model = new QuizModel();
        this.view = new QuizView(isLoaded());
        view.addButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "mainmenu":
                controller.showMainMenu();
                break;
            case "restart":
                this.model = new QuizModel();
                view.revalidate();
                view.repaint();
                view.addButtonListener(this);
            case "start":
                if (isLoaded()) {
                    KarteiKarte[] shuffled = model.startQuiz(cards);
                    this.shuffled = shuffled;
                    view.startQuiz(shuffled[currentCard].getFrage(), shuffled[currentCard].getFragentyp());
                    view.addButtonListener(this);
                    view.repaint();
                    view.revalidate();
                } else {
                    JOptionPane.showMessageDialog(view, "Cards are not loaded");
                    controller.showMainMenu();
                }
                break;
            case "End Quiz":
                if (view.getAnswer() != null) {
                    currentCard = 0;
                    System.out.println("endquizcheck");
                    endQuiz();

                } else {
                    JOptionPane.showMessageDialog(view, "Answer not entered");
                }
                break;
            case "Check":
                check();
                break;
            case "stop":
                if (currentCard > 0) {
                    endQuiz();
                } else {
                    controller.showMainMenu();
                }
                break;
        }

        view.setTextFieldActive();

    }

    public boolean isLoaded() {
        if (controller.getCards() != null &&controller.getCards().getCard(0) != null) {
            cards = controller.getCards();
            return true;
        }
        return false;
    }
    public void endQuiz() {
        int[] results = model.getEndResults();

        view.endQuiz(results[0], results[1], results[2], results[3]);

    }

    public JPanel getView() {
        return view;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            check();


        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // You can leave this empty if you don't need to handle the event
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // You can leave this empty if you don't need to handle the event
    }
    public void check(){
        System.out.println("check" + currentCard);
        view.setCheck(model.check(view.getAnswer()), shuffled[currentCard].getAntwort(), shuffled[currentCard].getFragentyp()); // checkt ob korrekt
        if (currentCard + 1 >= cards.getCards().length) {
            System.out.println("endquizcheck");
            currentCard = 0;

            this.endQuiz();
        } else {
            currentCard += 1;
            view.nextCard(shuffled[currentCard].getFrage(), shuffled[currentCard].getFragentyp());
        }

    }

}
