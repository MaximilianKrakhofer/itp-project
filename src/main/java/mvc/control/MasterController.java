package mvc.control;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import mvc.Karten.KarteiKarten;
import mvc.model.SettingsModel;
import mvc.view.MasterView;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.themes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.intellijthemes.*;


public class MasterController {
    private MasterView view;
    private KarteiKarten karten = null;
    private mvc.control.FragenverwaltungControl fragenverwaltungControl;
    private String loadLocation = null;


    public MasterController() {
        MasterView.setupTheme();
        view = new MasterView();
        showMainMenu();
    }
    public static void setupTheme() {
        MasterView.setupTheme();
    }
    public void repaint() {
        view.repaint();
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
