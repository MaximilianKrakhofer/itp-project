package mvc.view;

import mvc.control.HangmanControl;
import mvc.control.QuizControl;

import javax.swing.*;
import java.awt.*;

public class QuizView extends JPanel {

    private JButton mainMenu;
    public QuizView() {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenu = new JButton("Main Menu");

        operations.add(mainMenu);
        this.add(operations, BorderLayout.NORTH);
    }
    public void addButtonListener(QuizControl l) {

        this.mainMenu.addActionListener(l);

    }

    public JButton getMainMenu() {
        return mainMenu;
    }
}
