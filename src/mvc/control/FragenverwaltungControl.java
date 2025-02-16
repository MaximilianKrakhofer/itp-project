package mvc.control;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
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
    private KarteiKarten karten;

    public FragenverwaltungControl(MasterController masterController)  {
        System.out.println("FragenverwaltungControl");
        this.masterController = masterController;
        this.model = new FragenverwaltungModel();
        this.view = new FragenverwaltungView();
        view.addButtonListener(this);
        karten = new KarteiKarten();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "main":
                masterController.setCards(karten);
                masterController.showMainMenu();
                break;
            case "add":
                KarteiKarte card = view.getCard();
                view.appendCard(card);
                karten.addKarte(card);
                break;
            case "delete":
                try {
                    int row = view.removeCard();
                    if(row != -1) {
                        karten.removeKarte(row);
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Keine KarteiKarte zum Löschen ausgewählt");
                }
                break;
            case "save":
                if (model.saveCards(karten.getCards())) {
                    JOptionPane.showMessageDialog(view, "Cards saved at:" + model.SaveLocation());
                }
                else{
                    JOptionPane.showMessageDialog(view, "Cards failed to saved");
                }
                break;
            case "load":
                try {
                    KarteiKarten cards = model.getLoadCards();
                    masterController.setCards(cards);
                    view.resetCards();
                    karten = new KarteiKarten();
                    for(int i = 0; i<cards.getCards().length;i++) {
                        KarteiKarte karte = new KarteiKarte(cards.getCardQuestion(i),cards.getCardAnswer(i));
                        view.appendCard(karte);
                        karten.addKarte(karte);
                    }
                    model.saveCards(karten.getCards());
                }
                catch(Exception exc) {
                    JOptionPane.showMessageDialog(null,"Cards not Found");
                    exc.printStackTrace();
                    exc.getMessage();
                }
        }

    }
    public void setKarten(KarteiKarten karten ) {
        this.karten = karten;
        for(int i = 0; i< karten.getCards().length;i++) {
            KarteiKarte karte = new KarteiKarte(karten.getCardQuestion(i),karten.getCardAnswer(i));
            view.appendCard(karte);
        }
    }
    public JPanel getView() {
        return view;
    }
}
