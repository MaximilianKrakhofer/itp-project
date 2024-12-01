package mvc.control;

import mvc.model.QuizModel;
import mvc.view.QuizView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QuizControl {
    private QuizModel model;
    private QuizView view;
    private MasterController controller;

    public QuizControl(MasterController controller) {
        this.controller = controller;
        this.model = new QuizModel();
        this.view = new QuizView();
        view.getMainMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showMainMenu();
            }
        });
    }

    public JPanel getView() {
        return view;
    }
}
