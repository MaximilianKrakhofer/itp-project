package mvc.view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FragenverwaltungView extends JPanel {
    private JButton add;
    private JButton delete;
    private JButton save;
    private JButton load;
    private JTable table;
    private JButton mainMenu;
    DefaultTableModel cards;
    JScrollPane cardsPane;

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
        mainMenu = new JButton("Main Menu");
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


    }
    public void addButtonListener(ActionListener l) {
        this.add.addActionListener(l);
        this.delete.addActionListener(l);
        this.save.addActionListener(l);
        this.load.addActionListener(l);

    }


    public void appendCard(String[] cardText) {
        for (int i = 0; i < cardText.length; i=i+2) {
            cards.addRow(new Object[]{cardText[i], cardText[i+1]});
        }


    }
    public void removeCards () {
        cards.removeRow(table.getSelectedRow());
    }

    public String[] getCardText() {
        String front = cards.getValueAt(table.getSelectedRow(), 0).toString();
        String back = cards.getValueAt(table.getSelectedRow(),  1 ).toString();
        System.out.println(front + " " + back);
        return new String[]{front, back};
    }

    public JButton getMainMenu() {
        return mainMenu;
    }
}
