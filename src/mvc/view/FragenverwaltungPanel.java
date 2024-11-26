package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FragenverwaltungPanel extends JPanel {
    JTextArea cards;
    JScrollPane cardsPane;
    JButton add;
    JButton delete;
    JButton save;
    JButton load;
    JButton mainMenu;
    JButton quiz;
    JButton hangman;


    public FragenverwaltungPanel( ) {



        this.setLayout(new BorderLayout());

        JPanel operations = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add = new JButton("Hinzufuegen");
        add.setActionCommand("add");
        delete = new JButton("Loeschen");
        delete.setActionCommand("delete");
        save = new JButton("Speichern");
        save.setActionCommand("save");
        load = new JButton("Laden");

        operations.add(add);
        operations.add(delete);
        operations.add(save);
        operations.add(load);

        this.add(operations, BorderLayout.NORTH);

        cards = new JTextArea("");
        cards.setEditable(false);
        cards.setColumns(8);
        cards.setRows(7);
        cards.setEditable(false);
        cards.setFont(new Font("Monospaced", Font.PLAIN, 30));
        cardsPane = new JScrollPane(cards);
        this.add(cardsPane, BorderLayout.CENTER);
    }
    public void addButtonListener(ActionListener l) {
        this.add.addActionListener(l);
        this.delete.addActionListener(l);
        this.save.addActionListener(l);
        this.load.addActionListener(l);

        this.mainMenu.addActionListener(l);
        this.quiz.addActionListener(l);
        this.hangman.addActionListener(l);
    }

}
