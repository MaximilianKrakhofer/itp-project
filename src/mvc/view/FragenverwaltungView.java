package mvc.view;

import mvc.Karten.KarteiKarte;
import mvc.control.FragenverwaltungControl;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class FragenverwaltungView extends JPanel {
    private JButton add;
    private JButton delete;
    private JButton save;
    private JButton load;
    private JTable table;
    private JButton mainMenu;
    private DefaultTableModel cards;
    private JScrollPane cardsPane;
 // möglicher weise FlatLook später hinzufügen
    public FragenverwaltungView( ) {



        this.setLayout(new BorderLayout());

        JPanel operations = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add = new JButton("Hinzufuegen");
        add.setActionCommand("add");
        delete = new JButton("Loeschen");
        delete.setActionCommand("delete");
        save = new JButton("Speichern");
        save.setActionCommand("save");
        load = new JButton("Laden");
        load.setActionCommand("load");
        mainMenu = new JButton("Main Menu");

        mainMenu.setActionCommand("main");
        cards = new DefaultTableModel();
        cards.addColumn("Frage");
        cards.addColumn("Antwort");
        table = new JTable(cards);
        table.setModel(cards);
        table.setRowHeight(35);
        cardsPane = new JScrollPane(table);

        operations.add(add);
        operations.add(delete);
        operations.add(save);
        operations.add(load);
        operations.add(mainMenu);


        this.add(operations, BorderLayout.NORTH);
        this.add(cardsPane, BorderLayout.CENTER);

    }
    public void addButtonListener(FragenverwaltungControl l) {  //  xD Boris kocht
        this.add.addActionListener(l);
        this.delete.addActionListener(l);
        this.save.addActionListener(l);
        this.load.addActionListener(l);
        this.mainMenu.addActionListener(l);

    }
    public KarteiKarte getCard(){


        JTextField front = new JTextField(5);
        JPanel panel = new JPanel(new GridLayout(2,2));
        panel.add(new JLabel("Frage:"));
        panel.add(front);

        JTextField back = new JTextField(5);
        panel.add(new JLabel("Antwort"));
        panel.add(back);
        JOptionPane.showConfirmDialog(null, panel,
                "Frage und Antwort eingeben:", JOptionPane.OK_CANCEL_OPTION);

        return new KarteiKarte(front.getText(), back.getText());
    }

    public void appendCard(KarteiKarte karte) {
        cards.addRow(new Object[]{karte.getFrage(), karte.getAntwort()});
    }
    public int removeCard() {
        int a = table.getSelectedRow();
        cards.removeRow(a);
        return a;

    }
    public KarteiKarte[] getCards() {

        KarteiKarte[] wimma = new KarteiKarte[table.getRowCount()];
        for(int i = 0; i< wimma.length;i++) {


            String front = cards.getValueAt(i, 0).toString();
            String back = cards.getValueAt(i, 1).toString();

            KarteiKarte karte = new KarteiKarte(front,back);
        }
        return wimma;

    }

    public JButton getMainMenu() {
        return mainMenu;
    }
}
