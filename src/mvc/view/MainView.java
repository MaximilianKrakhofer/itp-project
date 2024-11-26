package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView {
    JButton cards ;
    JButton quiz;
    JButton hangman;
    JButton more;
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

        cards = new JButton("Karteikarten");
        cards.setActionCommand("cards");
        quiz = new JButton("Quiz");
        quiz.setActionCommand("quiz");
        hangman = new JButton("Hangman");
        more = new JButton("More?");
        more.setEnabled(false);
        more.setActionCommand("more");

        panel.add(more);
        panel.add(cards, BorderLayout.WEST);
        panel.add(quiz, BorderLayout.CENTER);
        panel.add(hangman, BorderLayout.EAST);

    }
    public void addButtonListener(ActionListener l) {
        more.addActionListener(l);
        cards.addActionListener(l);
        quiz.addActionListener(l);
        hangman.addActionListener(l);
    }
}