package mvc.control;

import mvc.model.MainModel;
import mvc.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainControl {

    MainView view;
    MainModel model;
    public MainControl(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        view.addButtonListener(new ButtonListener());
    }
    public static void main(String[] args) {
        MainView view = new MainView("Main Menu");
        MainModel model = new MainModel();
        new MainControl(view, model);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "quiz":
                    new QuizControl();
                    break;
                case "cards":
                    new FragenverwaltungControl();
                    break;
                case "hangman":
                    new HangmanControl();
                    break;
                default:
                    break;
            }
        }
    }
}
