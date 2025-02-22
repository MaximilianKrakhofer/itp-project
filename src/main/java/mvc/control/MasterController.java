package mvc.control;

import mvc.Karten.KarteiKarten;
import mvc.view.MasterView;
import com.formdev.flatlaf.FlatDarculaLaf;


public class MasterController {
    private MasterView view;
    private KarteiKarten karten = null;
    private mvc.control.FragenverwaltungControl fragenverwaltungControl;


    public MasterController() {
        try {
            FlatDarculaLaf.setup();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        view = new MasterView();
        showMainMenu();
    }
    public void showMainMenu() {
        MainMenuController mainMenuController = new MainMenuController(this);
        view.updateContent(mainMenuController.getView());
    }
    public void showFragenVerwaltung() {
        if(fragenverwaltungControl == null){
            fragenverwaltungControl = new FragenverwaltungControl(this);
        }
        if(karten != null && karten.getCards()!= null) {
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
    public void showSettings()
    {
        SettingsControl settingsControl = new SettingsControl(this);
        view.updateContent(settingsControl.getView());

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

    public FragenverwaltungControl getFragenverwaltungControl() {
        return fragenverwaltungControl;
    }
}
