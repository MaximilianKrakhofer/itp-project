package mvc.view;

import javax.swing.*;
import java.awt.*;

public class MainView {
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

        FragenverwaltungPanel fragenverwaltungPanel = new FragenverwaltungPanel();
        HangmanPanel hangmanPanel = new HangmanPanel();
        QuizPanel quizPanel = new QuizPanel();
        main.add(fragenverwaltungPanel, kartei);
        main.add(quizPanel, quiz);
        main.add(hangmanPanel, hangman);



    }

}