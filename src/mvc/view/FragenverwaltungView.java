package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FragenverwaltungView extends JPanel {
    JButton add;
    JButton delete;
    JButton save;
    JButton load;
    JButton mainMenu;
    JButton quiz;
    JButton hangman;


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

        this.mainMenu.addActionListener(l);
        this.quiz.addActionListener(l);
        this.hangman.addActionListener(l);
    }

    public JButton getMainMenu() {
        return mainMenu;
    }
}
