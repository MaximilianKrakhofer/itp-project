package mvc.control;

import mvc.view.FragenverwaltungView;
import mvc.view.MasterView;

public class MasterController {
    private MasterView view;
    private String[] cards;

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
    public void setCards(String[] cards) {
        this.cards = cards;
    }
    public String[] getCards() {
        return cards;
    }

    public static void main(String[] args) {
        MasterController masterController = new MasterController();

        MainMenuController  a = new MainMenuController(masterController);


    }
}
