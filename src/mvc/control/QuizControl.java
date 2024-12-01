package mvc.control;

import mvc.model.QuizModel;
import mvc.view.QuizView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QuizControl implements ActionListener {
    private QuizModel model;
    private QuizView view;
    private MasterController controller;

    public QuizControl(MasterController controller) {
        this.controller = controller;
        this.model = new QuizModel();
        this.view = new QuizView();
        view.addButtonListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.showMainMenu();
    }


    public JPanel getView() {
        return view;
    }
}
