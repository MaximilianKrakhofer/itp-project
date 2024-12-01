package mvc.control;

import mvc.model.FragenverwaltungModel;
import mvc.view.FragenverwaltungView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FragenverwaltungControl {
    private FragenverwaltungView view;
    private FragenverwaltungModel model;
    private MasterController masterController;

    public FragenverwaltungControl(MasterController masterController) {
        this.masterController = masterController;
        this.model = new FragenverwaltungModel();
        this.view = new FragenverwaltungView();

    }
    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            masterController.showMainMenu();
            String action = e.getActionCommand();
            switch (action) {
                case "add":
                    String [] card = model.getCard();
                    view.appendCard(card);
                    break;
                case "delete":
                    view.removeCards();
                    break;
                case "save":
                    model.saveCard(view.getCardText());
                    break;
                case "load":

            }

        }
    public JPanel getView() {
        return view;
    }
}
