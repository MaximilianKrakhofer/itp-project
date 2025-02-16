package mvc.control;

import mvc.Karten.KarteiKarten;
import mvc.view.FragenverwaltungView;
import mvc.view.MasterView;

import javax.swing.*;

public class MasterController {
    private MasterView view;
    private KarteiKarten karten = null;

    public MasterController() {

        view = new MasterView();
        showMainMenu();
    }
    public void showMainMenu() {
        MainMenuController mainMenuController = new MainMenuController(this);
        view.updateContent(mainMenuController.getView());
    }
    public void showFragenVerwaltung() {
        FragenverwaltungControl fragenverwaltungControl = new FragenverwaltungControl(this);
        if(karten != null) {
            fragenverwaltungControl.setKarten(karten);
        }
        view.updateContent(fragenverwaltungControl.getView());
    }
    public void showQuiz() {
        System.out.println("showQUi");
        QuizControl quizControl = new QuizControl(this);
        view.updateContent(quizControl.getView());
    }
    public void showHangman() {
        HangmanControl hangmanControl = new HangmanControl(this);
        view.updateContent(hangmanControl.getView());
    }
    public void setCards(KarteiKarten cards) {
        this.karten = cards;
    }
    public KarteiKarten getCards() {
        return karten;
    }

    public static void main(String[] args) {
        MasterController masterController = new MasterController();

        MainMenuController  a = new MainMenuController(masterController);


    }
}
