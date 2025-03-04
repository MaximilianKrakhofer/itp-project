package mvc.control;

import mvc.Karten.KarteiKarten;
import mvc.model.FragenSaveLoader;
import mvc.model.SettingsModel;
import mvc.view.MasterView;


public class MasterController {
    private MasterView view;
    private KarteiKarten karten = null;
    private mvc.control.FragenverwaltungControl fragenverwaltungControl;
    private String configPath = null;


    public MasterController() {
        MasterView.setupTheme();
        view = new MasterView();
        showMainMenu();
        try {
            boolean autoload = Boolean.parseBoolean(SettingsModel.getConfig("autoload", "false"));
            boolean autosave = Boolean.parseBoolean(SettingsModel.getConfig("autosave", "false"));
            configPath = SettingsModel.getConfig("saveLocation", null);
            if (autoload) {
                System.out.println("WIMMER");
                FragenSaveLoader loada = new FragenSaveLoader();
                if (configPath != null) {
                    loada.setConfigPath(configPath);
                }
                karten = loada.getLoadCards();
                System.out.println("" + karten == null);
            }
        } catch (Exception e) {

        }
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
        if (fragenverwaltungControl == null) {
            fragenverwaltungControl = new FragenverwaltungControl(this);
            if (karten != null) {
                fragenverwaltungControl.setKarten(karten);
            }
        }
        if (karten != null && karten.getCards() != null) {
            fragenverwaltungControl = new FragenverwaltungControl(this);
            fragenverwaltungControl.setKarten(karten);
        }
        boolean autosave = Boolean.parseBoolean(SettingsModel.getConfig("autosave", "false"));
        boolean autoload = Boolean.parseBoolean(SettingsModel.getConfig("autoload", "false"));
        configPath = SettingsModel.getConfig("saveLocation", null);
        fragenverwaltungControl.setAutoSaveLoadPathConfig(autosave, autoload, configPath);
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

    public void showWordle() {
        WordleControl wordleControl = new WordleControl(this);
        view.updateContent(wordleControl.getView());
    }

    public void showSettings() {
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

        MainMenuController a = new MainMenuController(masterController);


    }

    public FragenverwaltungControl getFragenverwaltungControl() {
        return fragenverwaltungControl;
    }
}
