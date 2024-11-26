package mvc.view;

import javax.swing.*;
import java.awt.*;

public class MainView {
    JButton cards ;
    JButton quiz;
    JButton hangman;
    public MainView(String title) {
        JFrame frame = new JFrame(title);
        frame.setMinimumSize(new Dimension(850, 600));
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        frame.add(panel);
        frame.setVisible(true);
        frame.pack();


        panel.setLayout(new GridLayout(2,2));
        JButton main = new JButton("MainMenu");
        cards = new JButton("Karteikarten");
        quiz = new JButton("Quiz");
        hangman = new JButton("Hangman");

        panel.add(cards, BorderLayout.WEST);
        panel.add(quiz, BorderLayout.CENTER);
        panel.add(hangman, BorderLayout.EAST);

    }
}