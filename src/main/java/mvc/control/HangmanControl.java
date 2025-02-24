package mvc.control;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.model.HangmanModel;
import mvc.view.HangmanView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HangmanControl implements ActionListener {
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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case"mainmenu":
                controller.showMainMenu();
                break;
            case "restart":
                model.setCurrentHangman(currentCard);
                this.model = new HangmanModel();
                view.revalidate();
                view.repaint();
                view.addButtonListener(this);
            case "start":
                if(isLoaded()) {

                    model.setCurrentHangman(currentCard);
                    this.shuffled = model.startQuiz(cards);
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
                    model.check(view.getAnswer());
                    System.out.println("endquizcheck");
                    int[] affe = model.endQuiz();

                    view.endQuiz(affe[0], model.getFailedChars(), model.getFailedWords(), model.getHangmanCompletions(), affe[2], (double) affe[0] / affe[1]);
                    view.addButtonListener(this);
                }
                else{
                    JOptionPane.showMessageDialog(view, "Answer not entered");
                }
                break;
            case "Check":

                model.setCurrentHangman(currentCard);

                int [] correctChars = {-1};
                if(view.getAnswer() != null && !view.getAnswer().isBlank()){
                    correctChars = model.compareChars(view.getAnswer().charAt(0),shuffled[currentCard].getAntwort());
                }

                if(model.getAtleastOne()){
                    view.setCheckedChars(correctChars, view.getAnswer().charAt(0));
                }
                else{

                    if(view.getAnswer() != null && !view.getAnswer().isBlank()){

                        view.setHangmanAscii(model.increaseCounter());
                        model.increaseFailedChars();
                        if(model.getCounter() >6){
                            model.check(view.getAnswer());
                            System.out.println("endquizcheck");

                            view.setCheck(true, cards.getCardAnswer(currentCard) );
                            int[] affe = model.endQuiz();



                            double prozent = affe[1] == 0 ? 0.0: (double) (affe[1] / affe[0])*100;
                            view.endQuiz(affe[0], model.getFailedChars(), model.getFailedWords(), model.getHangmanCompletions(), affe[2], prozent);
                            view.addButtonListener(this);
                        }

                    }

                }



                if(model.check(view.getSolutionPreview()))
                {

                    if(currentCard +1 >= cards.getCards().length) {
                        System.out.println("endquizcheck");

                        view.setCheck(true, cards.getCardAnswer(currentCard) );
                        int[] affe = model.endQuiz();

                        double prozent = affe[1] == 0 ? 0.0: (double) (affe[1] / affe[0])*100;
                        view.endQuiz(affe[0], model.getFailedChars(), model.getFailedWords(), model.getHangmanCompletions(), affe[2], prozent);
                        view.addButtonListener(this);

                    }
                    else{
                        currentCard+=1;
                        view.nextCard(shuffled[currentCard].getFrage(),shuffled[currentCard].getFragentyp());
                    }
                }

                model.setAtleastOne(false);


                view.setAnswer("");
                break;
            case "CheckWord":
                model.setCurrentHangman(currentCard);
                String a = "";
                a =  view.checkWord();


                if ((a != null && a.equals(shuffled[currentCard].getAntwort()))) {
                    if(currentCard +1 >= cards.getCards().length) {
                        System.out.println("endquizcheck");

                        view.setCheck(true, cards.getCardAnswer(currentCard) );
                        int[] affe = model.endQuiz();
                        double prozent = affe[1] == 0 ? 0.0: (double) (affe[1] / affe[0])*100;
                        view.endQuiz(affe[0], model.getFailedChars(), model.getFailedWords(), model.getHangmanCompletions(), affe[2], prozent);
                        view.addButtonListener(this);

                    }
                    else{
                        currentCard+=1;
                        view.nextCard(shuffled[currentCard].getFrage(),shuffled[currentCard].getFragentyp());
                    }
                }
                else{

                    if(view.getAnswer() != null && !view.getAnswer().isBlank()) {
                        currentCard++;
                        model.increaseFailedWords();
                        view.setHangmanAscii(model.increaseCounter());
                        if (model.getCounter() > 6) {
                            model.check(view.getAnswer());
                            System.out.println("endquizcheck");

                            view.setCheck(true, cards.getCardAnswer(currentCard));
                            int[] affe = model.endQuiz();

                            double prozent = affe[1] == 0 ? 0.0: (double) (affe[1] / affe[0])*100;
                            view.endQuiz(affe[0], model.getFailedChars(), model.getFailedWords(), model.getHangmanCompletions(), affe[2], prozent);
                            view.addButtonListener(this);

                        }
                    }
                }


                break;
            case "stop":
                if(currentCard > 0) {
                    int[] affe = model.endQuiz();
                    view.endQuiz(affe[0], model.getFailedChars(), model.getFailedWords(), model.getHangmanCompletions(), affe[2], (double) affe[0] / affe[1]);
                }
                else{
                    controller.showMainMenu();
                }
                break;
        }

    }

    public boolean isLoaded() {

        if(controller.getCards().getCard(0) != null) {
            cards = controller.getCards();
            return true;
        }
        return false;
    }
    public JPanel getView() {
        return view;
    }

}
