package mvc.control;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.model.FragenverwaltungModel;
import mvc.view.FragenverwaltungView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
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
        addTableListener(view.getTableModel());
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
                    JOptionPane.showMessageDialog(view, "Cards saved at:" + model.saveLocation());
                }
                else{
                    JOptionPane.showMessageDialog(view, "Cards failed to saved");
                }
                break;
            case "load":
                try {
                    String location = view.getLoadLocation(model.saveLocation());
                    KarteiKarten cards = model.getLoadCards(location);
                    masterController.setCards(cards);
                    view.resetCards();
                    karten = new KarteiKarten();
                    for(int i = 0; i<cards.getCards().length;i++) {
                        KarteiKarte karte = new KarteiKarte(cards.getCardQuestion(i),cards.getCardAnswer(i), cards.getCardType(i));
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
    public void addTableListener(DefaultTableModel model)  {
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE) {
                    int a = e.getFirstRow();
                    int b = e.getLastRow();
                    for(int i = a; i<=b; i++) {
                        KarteiKarte cards = view.getCardAt(i);
                        karten.getCards()[a]=cards;
                    }
                    masterController.setCards(karten);
                }
            }
        });
    }
    public void setKarten(KarteiKarten karten ) {
        this.karten = karten;
        for(int i = 0; i< karten.getCards().length;i++) {
            KarteiKarte karte = new KarteiKarte(karten.getCardQuestion(i),karten.getCardAnswer(i), karten.getCardType(i));
            view.appendCard(karte);
        }
    }
    public JPanel getView() {
        return view;
    }
}
