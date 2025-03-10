package mvc.control;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.model.HangmanModel;
import mvc.view.HangmanView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HangmanControl implements ActionListener, KeyListener {
    private HangmanModel model;
    private HangmanView view;
    private MasterController controller;
    private KarteiKarten cards;
    private KarteiKarte[] shuffled;
    private int currentCard = 0;

    public HangmanControl(MasterController controller) {
        System.out.println("quiz control");
        this.controller = controller;
        this.model = new HangmanModel();
        this.view = new HangmanView(isLoaded());
        view.addButtonListener(this);
        view.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "mainmenu":
                controller.showMainMenu();
                break;
            case "restart":
                model.setCurrentHangman(currentCard);
                this.model = new HangmanModel();
                view.revalidate();
                view.repaint();
                view.addButtonListener(this);
            case "start":
                if (isLoaded()) {
                    currentCard = 0;

                    model.setCurrentHangman(currentCard);
                    this.shuffled = model.startQuiz(cards);
                    view.startQuiz(shuffled[currentCard].getFrage(), shuffled[currentCard].getAntwort().length(), shuffled[currentCard].getFragentyp());
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
                    System.out.println("endquizcheck");
                    endQuiz();
                    view.addButtonListener(this);
                } else {
                    JOptionPane.showMessageDialog(view, "Answer not entered");
                }
                break;
            case "Check":
                check();

                break;
            case "CheckWord":
                String a = "";
                a = view.checkWord();


                if ((a != null && a.equalsIgnoreCase(shuffled[currentCard].getAntwort()))) {
                    if (currentCard + 1 >= cards.getCards().length) {
                        System.out.println("endquizcheck");

                        view.setCheck(true, shuffled[currentCard].getAntwort());
                        endQuiz();

                    } else {

                        view.setCheck(true, shuffled[currentCard].getAntwort());
                        currentCard += 1;

                        model.setCurrentHangman(currentCard);
                        view.nextCard(shuffled[currentCard].getFrage(), shuffled[currentCard].getAntwort().length(), shuffled[currentCard].getFragentyp());
                    }
                } else {

                    if (view.getAnswer() != null && !view.getAnswer().isBlank()) {
                        currentCard++;
                        view.setHangmanAscii(model.increaseCounter());
                        if (model.getCounter() > 6) {
                            model.check(view.getAnswer());
                            System.out.println("endquizcheck");

                            view.setCheck(false, shuffled[currentCard].getAntwort());
                            endQuiz();

                        } else {
                            model.increaseFailedWords();
                            if (model.getFailedWords() ==0){
                                model.setCurrentHangman(currentCard);
                            }
                        }
                    }
                }


                break;
            case "stop":
                if (currentCard > 0) {
                    endQuiz();
                } else {
                    controller.showMainMenu();
                }
                break;
        }
        if(!e.getActionCommand().equals("mainmenu")) {
            view.setTextFieldActive();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            check();
            view.setAnswer("");


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
    public void endQuiz(){

        int[] results = model.endQuiz();

        view.endQuiz(results[0],results[1],results[2],  results[3], results[4]);
        view.addButtonListener(this);

    }
    public void check(){
        int[] correctChars = {-1};
        if (view.getAnswer() != null && !view.getAnswer().isBlank()) {
            correctChars = model.compareChars(view.getAnswer().toLowerCase().charAt(0), shuffled[currentCard].getAntwort());
        }

        if (model.getAtleastOne()) {
            view.setCheckedChars(correctChars, view.getAnswer().charAt(0));
        } else {

            if (view.getAnswer() != null && !view.getAnswer().isBlank()) {

                view.setHangmanAscii(model.increaseCounter());
                if (model.getCounter() > 6) {
                    model.check(view.getAnswer());
                    System.out.println("endquizcheck");

                    view.setCheck(false, shuffled[currentCard].getAntwort());
                    endQuiz();
                } else {
                    if (model.getFailedChars() ==0 ){
                        model.setCurrentHangman(currentCard +1);

                    }

                    model.increaseFailedChars();
                }

            }

        }


        if (model.check(view.getSolutionPreview())) {

            if (currentCard + 1 >= cards.getCards().length) {
                System.out.println("endquizcheck");

                view.setCheck(true, shuffled[currentCard].getAntwort());
                endQuiz();

            } else {

                view.setCheck(true, shuffled[currentCard].getAntwort());
                currentCard += 1;

                model.setCurrentHangman(currentCard);
                view.nextCard(shuffled[currentCard].getFrage(), shuffled[currentCard].getAntwort().length(), shuffled[currentCard].getFragentyp());
            }
        }

        model.setAtleastOne(false);


        view.setAnswer("");
    }

}
