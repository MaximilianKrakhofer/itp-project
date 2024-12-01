package mvc.view;

import mvc.control.MainMenuController;
import mvc.control.QuizControl;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {
    private JButton fragenVerwaltung, quiz, hangman;
    public MainMenuView() {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fragenVerwaltung = new JButton("FragenVerwaltung");
        fragenVerwaltung.setActionCommand("Fragenverwaltung");
        quiz = new JButton("Quiz");
        quiz.setActionCommand("Quiz");
        hangman = new JButton("Hangman");
        hangman.setActionCommand("Hangman");
        operations.add(fragenVerwaltung);
        operations.add(quiz);
        operations.add(hangman);
        this.add(operations, BorderLayout.CENTER);
    }
    public void addButtonListener(MainMenuController l) {

        this.fragenVerwaltung.addActionListener(l);
        this.quiz.addActionListener(l);
        this.hangman.addActionListener(l);

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
