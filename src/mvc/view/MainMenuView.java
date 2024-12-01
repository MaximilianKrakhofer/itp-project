package mvc.view;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {
    private JButton fragenVerwaltung, quiz, hangman;
    public MainMenuView() {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fragenVerwaltung = new JButton("FragenVerwaltung");
        quiz = new JButton("Quiz");
        hangman = new JButton("Hangman");
        operations.add(fragenVerwaltung);
        operations.add(quiz);
        operations.add(hangman);
        this.add(operations, BorderLayout.CENTER);
    }

    public JButton getFragenverwaltung() {
        return fragenVerwaltung;
    }
    public JButton getQuiz() {
        return quiz;
    }
    public JButton getHangman() {
        return hangman;
    }
}
