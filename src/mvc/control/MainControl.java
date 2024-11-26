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

    }
    public static void main(String[] args) {
        MainView view = new MainView("Main Menu");
        MainModel model = new MainModel();
        new MainControl(view, model);
        new FragenverwaltungControl();
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
