package mvc.control;

import mvc.view.MainMenuView;

import javax.swing.*;
import java.awt.event.*;

public class MainMenuController {
    private MainMenuView view;
    private MasterController masterController;

    public MainMenuController(MasterController masterController) {
        this.masterController = masterController;
        this.view=new MainMenuView();
        view.getFragenverwaltung().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                masterController.showFragenVerwaltung();
            }
        });
        view.getQuiz().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                masterController.showQuiz();
            }
        });
        view.getHangman().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                masterController.showHangman();
            }
        });


    }
    public JPanel getView() {
        return view;
    }
}
