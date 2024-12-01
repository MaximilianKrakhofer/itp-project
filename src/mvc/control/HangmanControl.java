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
    private String[] cards;
    private int currentCard = 0;

    public HangmanControl(MasterController controller) {
        this.controller = controller;
        this.model = new HangmanModel();
        this.view = new HangmanView();
        view.addButtonListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "start":
                if(!isLoaded()){
                    JOptionPane.showMessageDialog(view, "Cards are not loaded");
                }
                else{
                    String[] shuffled = model.startHangman(cards);
                    view.startHangman(shuffled[currentCard]);
                }
                break;
            case "mainmenu":
                controller.showMainMenu();
                break;
            case "check":
                model.check(view.getAnswer().getText());
        }

    }
    public boolean isLoaded() {
        if(controller.getCards() != null) {
            cards = controller.getCards();
            return true;
        }
        return false;
    }
    public JPanel getView() {
        return view;
    }
}
