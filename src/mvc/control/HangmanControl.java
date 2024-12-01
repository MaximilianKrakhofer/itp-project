package mvc.control;

import mvc.model.HangmanModel;
import mvc.view.HangmanView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanControl implements ActionListener {
    private HangmanModel model;
    private HangmanView view;
    private MasterController controller;

    public HangmanControl(MasterController controller) {
        this.controller = controller;
        this.model = new HangmanModel();
        this.view = new HangmanView();
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
