package mvc.view;

import mvc.control.FragenverwaltungControl;
import mvc.control.HangmanControl;

import javax.swing.*;
import java.awt.*;

public class HangmanView extends JPanel {
    private JButton mainMenu;
    public HangmanView() {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenu = new JButton("Main Menu");
        mainMenu.setActionCommand("mainMenu");
        operations.add(mainMenu);

        this.add(operations, BorderLayout.NORTH);
    }
    public void addButtonListener(HangmanControl l) {

        this.mainMenu.addActionListener(l);

    }
        public JButton getMainMenu() {
        return mainMenu;
    }
}
