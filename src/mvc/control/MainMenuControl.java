package mvc.control;
import mvc.model.MainMenuModel;
import mvc.view.MainMenuView;
import mvc.control.MainMenuControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuControl {

    MainMenuView view;
    MainMenuModel model;
    public MainMenuControl(MainMenuView view, MainMenuModel model) {
        this.view = view;
        this.model = model;

    }
    public static void main(String[] args) {
        MainMenuView view = new MainMenuView("Main Menu");
        MainMenuModel model = new MainMenuModel();
        new MainMenuControl(view, model);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
