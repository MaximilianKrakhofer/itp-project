package mvc.view;

import javax.swing.*;
import java.awt.*;

public class HangmanView extends JPanel {
    private JButton mainMenu;
    public HangmanView() {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenu = new JButton("Main Menu");

        operations.add(mainMenu);
        this.add(operations, BorderLayout.NORTH);
    }

    public JButton getMainMenu() {
        return mainMenu;
    }
}
