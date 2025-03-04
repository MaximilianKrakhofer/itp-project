package mvc.control;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.model.FragenverwaltungsModel;
import mvc.view.FragenverwaltungView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FragenverwaltungControl implements ActionListener {
    private FragenverwaltungView view;
    private FragenverwaltungsModel model;
    private MasterController masterController;
    private KarteiKarten karten;
    private boolean autosave;
    private boolean autoload;

    public void setAutoSaveLoadPathConfig(boolean autosave, boolean autoload, String pathConfig) {
        this.autosave = (autosave);
        model.setConfigPath(pathConfig);
    }

    public FragenverwaltungControl(MasterController masterController) {
        System.out.println("FragenverwaltungControl");
        this.masterController = masterController;
        this.model = new FragenverwaltungsModel();
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
                if (karten != null && karten.getCards() != null && karten.getCards().length != 0) {

                    masterController.setCards(karten);
                }
                masterController.showMainMenu();
                break;
            case "add":
                KarteiKarte card = view.getCard();
                if (card.getFrage() == null || card.getFrage().isEmpty() || card.getAntwort() == null || card.getAntwort().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Leere Fragen oder Antworten sind nicht erlaubt");
                    break;
                }
                view.appendCard(card);
                karten.addKarte(card);
                if (autosave) model.saveCards(karten.getCards());
                break;
            case "delete":
                try {
                    int row = view.removeCard();
                    if (row != -1) {
                        karten.removeKarte(row);
                    }
                    if (autosave) {
                        model.saveCards(karten.getCards());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Keine KarteiKarte zum Löschen ausgewählt");
                }
                break;
            case "save":
                if (model.saveCards(karten.getCards())) {
                    JOptionPane.showMessageDialog(view, "Cards saved at:" + model.saveLocation());
                } else {
                    JOptionPane.showMessageDialog(view, "Cards failed to saved");
                }
                break;
            case "load":
                try {
                    System.out.println("Load");
                    String location = view.getLoadLocation(model.saveLocation());
                    if (location == null || location.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Cards not Found");
                        return;
                    }
                    KarteiKarten cards = model.getLoadCards(location);
                    masterController.setCards(cards);
                    view.resetCards();
                    karten = new KarteiKarten();
                    for (int i = 0; cards != null && i < cards.getCards().length; i++) {
                        KarteiKarte karte = new KarteiKarte(cards.getCardQuestion(i), cards.getCardAnswer(i), cards.getCardType(i));
                        view.appendCard(karte);
                        karten.addKarte(karte);
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Cards not Found");
                    exc.printStackTrace();
                    exc.getMessage();
                }
        }

    }

    public void addTableListener(DefaultTableModel modele) {
        modele.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int a = e.getFirstRow();
                    int b = e.getLastRow();
                    for (int i = a; i <= b; i++) {
                        KarteiKarte cards = view.getCardAt(i);
                        karten.getCards()[a] = cards;
                    }
                    masterController.setCards(karten);
                    if (autosave) {
                        model.saveCards(karten.getCards());
                    }
                }
            }
        });
    }

    public void setKarten(KarteiKarten karten) {
        this.karten = karten;
        view.resetCards();
        for (int i = 0; i < karten.getCards().length; i++) {
            KarteiKarte karte = new KarteiKarte(karten.getCardQuestion(i), karten.getCardAnswer(i), karten.getCardType(i));
            view.appendCard(karte);
        }
    }

    public JPanel getView() {
        return view;
    }

    public FragenverwaltungsModel getModel() {
        return model;
    }
}
