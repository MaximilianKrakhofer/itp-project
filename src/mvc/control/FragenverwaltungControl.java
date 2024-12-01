package mvc.control;

import mvc.model.FragenverwaltungModel;
import mvc.view.FragenverwaltungView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FragenverwaltungControl implements ActionListener
{
    private FragenverwaltungView view;
    private FragenverwaltungModel model;
    private MasterController masterController;

    public FragenverwaltungControl(MasterController masterController)  {
        this.masterController = masterController;
        this.model = new FragenverwaltungModel();
        this.view = new FragenverwaltungView();
        view.addButtonListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "main":
                masterController.showMainMenu();
                break;
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
                try {
                    String[] cards = model.getLoadCards();
                    masterController.setCards(cards);
                    view.appendCard(cards);
                }
                catch(Exception exc) {
                    JOptionPane.showMessageDialog(null,"Cards not Found");
                }
        }

    }
    public JPanel getView() {
        return view;
    }
}
