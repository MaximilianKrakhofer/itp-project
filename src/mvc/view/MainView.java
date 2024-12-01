package mvc.view;

import javax.swing.*;
import java.awt.*;

public class MainView {
    HangmanPanel hangmanPanel;
    QuizPanel quizPanel;
    FragenverwaltungPanel fragenverwaltungPanel;
    final static String kartei = "Fragenverwaltug";
    final static String quiz = "Quiz";
    final static String hangman= "Hangman";
    public MainView() {

        JFrame frame = new JFrame("LearnITP");
        frame.setMinimumSize(new Dimension(850, 600));
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane main = new JTabbedPane();
        frame.add(main);
        frame.setVisible(true);
        frame.pack();

        fragenverwaltungPanel = new FragenverwaltungPanel();
        hangmanPanel = new HangmanPanel();
        quizPanel = new QuizPanel();
        main.add(fragenverwaltungPanel, kartei);
        main.add(quizPanel, quiz);
        main.add(hangmanPanel, hangman);



    }

    public HangmanPanel getHangmanPanel() {
        return hangmanPanel;
    }
    public QuizPanel getQuizPanel() {
        return quizPanel;
    }
    public FragenverwaltungPanel getFragenverwaltungPanel() {
        return fragenverwaltungPanel;
    }
}