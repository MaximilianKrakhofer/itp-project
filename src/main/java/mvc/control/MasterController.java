package mvc.control;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import mvc.Karten.KarteiKarten;
import mvc.view.FragenverwaltungView;
import mvc.view.MasterView;
import com.formdev.flatlaf.FlatLaf;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

public class MasterController {
    private MasterView view;
    private KarteiKarten karten = null;
    private FragenverwaltungControl fragenverwaltungControl;

    public MasterController() {
        try {
            FlatIntelliJLaf.setup();
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
        fragenverwaltungControl = new FragenverwaltungControl(this);
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
