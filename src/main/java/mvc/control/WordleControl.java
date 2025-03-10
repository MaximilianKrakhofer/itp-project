package mvc.control;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.model.WordleModel;
import mvc.view.WordleView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WordleControl implements ActionListener, KeyListener {
    private WordleModel model;
    private WordleView view;
    private MasterController controller;
    private KarteiKarten cards;
    private KarteiKarte[] shuffled;
    private int currentCard = 0;

    public WordleControl(MasterController controller) {
        this.controller = controller;
        this.model = new WordleModel();
        this.view = new WordleView(isLoaded());
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
                this.model = new WordleModel();
                view.revalidate();
                view.repaint();
                view.addButtonListener(this);
            case "start":
                if (isLoaded()) {
                    KarteiKarte[] shuffled = model.startQuiz(cards);
                    this.shuffled = shuffled;
                    view.startQuiz(shuffled[currentCard].getFrage(), shuffled[currentCard].getAntwort().length(), shuffled[currentCard].getFragentyp());
                    view.addButtonListener(this);
                    view.repaint();
                    view.revalidate();
                    view.setFocus();
                } else {
                    JOptionPane.showMessageDialog(view, "Cards are not loaded");
                    controller.showMainMenu();
                }
                break;
            case "End Quiz":
                if (view.getAnswersText() != null) {
                    currentCard = 0;
                    model.check(new String(view.getAnswersText()));
                    System.out.println("endquizcheck");
                    int[] stats = model.endQuiz();

                    double percentage = stats[0] == 0 ? 0.0 : ((double) stats[1] / stats[0]) * 100;
                    view.endQuiz(stats[0], stats[1], stats[2], percentage);
                    view.addButtonListener(this);
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

    }
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                e.consume();
                check();
                break;
            case KeyEvent.VK_TAB:
                e.consume();
                if (e.isShiftDown()) {
                    e.consume();
                    view.setFocusPrevious();
                } else {
                    e.consume();
                    view.setFocusNext();
                }
                break;
            case KeyEvent.VK_BACK_SPACE:
                break;

            default:
                if(!e.isShiftDown() && e.getKeyCode()!= KeyEvent.VK_TAB){
                    view.setFocusNext();
                }
                break;

            // SHIFT,
        }





    }



    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean isLoaded() {

        if (controller.getCards().getCard(0) != null) {
            cards = controller.getCards();
            return true;
        }
        return false;
    }

    public JPanel getView() {
        return view;
    }

    public void endQuiz() {
        int [] results = model.endQuiz();
        view.endQuiz(results[0], results[1], results[2], results[3]);
        view.addButtonListener(this);
    }
    public void check(){
        if (model.check(new String(view.getAnswersText()))) {
            model.increaseQuestionsCorrect();

            model.increaseQustions();
            if (currentCard + 1 >= cards.getCards().length) {
                System.out.println("endquizcheck");
                currentCard = 0;
                endQuiz();
            } else {
                currentCard += 1;
                view.nextCard(shuffled[currentCard].getFrage(), shuffled[currentCard].getAntwort().length(), shuffled[currentCard].getFragentyp());
            }
        } else {
            if (view.getRowCounter() >= 4) {
                System.out.println("endquizcheck");
                currentCard = 0;
                endQuiz();
                currentCard += 1;
                view.nextCard(shuffled[currentCard].getFrage(), shuffled[currentCard].getAntwort().length(), shuffled[currentCard].getFragentyp());
            } else {
                if (!view.getAnswersText().isBlank()) {
                    view.setColors(model.compareChars(new String(view.getAnswersText()), shuffled[currentCard].getAntwort()));
                    view.activateNewFields();
                    view.setFocus();
                }
            }
        }
    }
}
