package mvc.control;

import mvc.model.SettingsModel;
import mvc.view.SettingsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsControl implements ActionListener {

    private SettingsModel model;
    private SettingsView view;
    private MasterController controller;

    public SettingsControl(MasterController controller) {
        this.controller = controller;
        this.model = new SettingsModel();
        this.view = new SettingsView();
        view.addButtonListener(this);
    }

    public SettingsView getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case"mainmenu":
                controller.showMainMenu();
                break;
            case "path":
                try {
                    String location = view.getSaveLocation(controller.getFragenverwaltungControl().getModel().getSaveDirectory());
                   // KarteiKarten cards = model.getLoadCards(location);
                   // masterController.setCards(cards);
                   // view.resetCards();
                   // karten = new KarteiKarten();
                   // for(int i = 0; i<cards.getCards().length;i++) {
                   //     KarteiKarte karte = new KarteiKarte(cards.getCardQuestion(i),cards.getCardAnswer(i), cards.getCardType(i));
                   //     view.appendCard(karte);
                   //     karten.addKarte(karte);
                   // }
                   // model.saveCards(karten.getCards());
                }
                catch(Exception exc) {
                    JOptionPane.showMessageDialog(null,"Cards not Found");
                    exc.printStackTrace();
                    exc.getMessage();
                }
                break;
        }
    }
}
