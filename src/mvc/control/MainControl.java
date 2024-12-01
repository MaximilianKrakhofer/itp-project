package mvc.control;

import mvc.model.FragenverwaltungModel;
import mvc.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainControl {

    MainView view;
    FragenverwaltungModel model;
    public MainControl(MainView view, FragenverwaltungModel model ) {
        this.view = view;
        this.model = model;
        view.getFragenverwaltungPanel().addButtonListener(new ButtonListener());
    }
    public static void main(String[] args) {
        MainView view = new MainView();
        FragenverwaltungModel model = new FragenverwaltungModel();
        new MainControl(view, model);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if (action.equals("add")) {
                String[] card = model.getCard();
                view.getFragenverwaltungPanel().appendCard(card);
            }
            else if (action.equals("delete")) {
                view.getFragenverwaltungPanel().removeCards();
            }
            else if (action.equals("save")) {
                model.saveCard(view.getFragenverwaltungPanel().getCardText());
            }
            else if (action.equals("load")) {
            }
        }
    }


}
