package mvc.control;

import mvc.view.MainMenuView;

import javax.swing.*;
import java.awt.event.*;

public class MainMenuController implements ActionListener {
    private MainMenuView view;
    private MasterController masterController;

    public MainMenuController(MasterController masterController) {
        this.masterController = masterController;
        this.view = new MainMenuView();
        view.addButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        switch (command) {
            case "Fragenverwaltung":
                masterController.showFragenVerwaltung();
                break;
            case "Quiz":

                masterController.showQuiz();
                break;
            case "Hangman":
                masterController.showHangman();
                break;
            case "Wordle":
                masterController.showWordle();
                break;
            case "Settings":
                masterController.showSettings();
                break;
        }
    }

    public JPanel getView() {
        return view;
    }
}
