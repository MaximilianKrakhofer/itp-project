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
                this.model = new HangmanModel();
                view.revalidate();
                view.repaint();
                view.addButtonListener(this);
            case "start":
                if(isLoaded()) {
                    KarteiKarte[] shuffled = model.startQuiz(cards);
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
                    view.endQuiz(affe[0], affe[1], affe[2], (double) affe[0] / affe[1]);
                    view.addButtonListener(this);
                }
                else{
                    JOptionPane.showMessageDialog(view, "Answer not entered");
                }
                break;
            case "Check":
                int [] correctChars = model.compareChars(view.getAnswer().charAt(0),cards.getCardAnswer(currentCard));

                if(model.getAtleastOne()){
                    view.setCheckedChars(correctChars, view.getAnswer().charAt(0));
                }
                else{
                    view.setHangmanAscii(model.increaseCounter());
                }

                    if(model.check(view.getSolutionPreview()))
                    {
                        System.out.println("endquizcheck");
                        int[] affe = model.endQuiz();
                        view.endQuiz(affe[0],affe[1],affe[2],(double)affe[0]/affe[1]);
                        view.addButtonListener(this);
                    }

                model.setAtleastOne(false);

                break;
            case "CheckWord":
                String a = "";
                a =  view.checkWord();
                if(a!=null && a.equals(cards.getCardAnswer(currentCard +1))) {
                    System.out.println("endquizcheck");
                    int[] affe = model.endQuiz();
                    view.endQuiz(affe[0],affe[1],affe[2],(double)affe[0]/affe[1]);
                    view.addButtonListener(this);
                }

                else{

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
