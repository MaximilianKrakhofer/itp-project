package mvc.view;

import mvc.control.FragenverwaltungControl;
import mvc.control.HangmanControl;

import javax.swing.*;
import java.awt.*;

public class HangmanView extends JPanel {
    private JButton mainMenu, start, stop, restart, check;
    private JTextField question, answer;
    private JPanel grids, grid, qa, hangman;
    public HangmanView() {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenu = new JButton("Main Menu");
        mainMenu.setActionCommand("mainMenu");
        start = new JButton("Start");
        start.setActionCommand("start");
        operations.add(mainMenu);

        this.add(operations, BorderLayout.NORTH);
        this.add(start, BorderLayout.SOUTH);
    }
    public void startHangman(String question) {
        this.removeAll();
        this.question = new JTextField(question);
        this.question.setEditable(false);
        this.stop = new JButton("End Hangman");
        this.check = new JButton("Check");
        check.setActionCommand("Check");
        this.answer = new JTextField();
        grids = new JPanel(new GridLayout(1, 2));
        grid = new JPanel(new GridLayout(1, 2));
        qa = new JPanel(new GridLayout(2, 1));
        grid.add(check);
        grid.add(stop);
        hangman = new HangmanFigure(0);
        qa.add(this.question);
        qa.add(answer);
        grids.add(hangman);
        grids.add(qa);
        this.add(grids, BorderLayout.CENTER);
        this.add(grid, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
    }
    public void showSolution(String answer) {
        JOptionPane.showMessageDialog(null, this.question.getText()+ "\nAntwort:" + answer);
    }
    public void nextCard(String question, int incorrectGuesses) {
        this.question.setText(question);
        this.answer.setText("");
        this.hangman.removeAll();
        this.hangman = new HangmanFigure(incorrectGuesses);
        this.repaint();
        this.revalidate();
    }
    public void endHangman(int beantwortet, int falsch, double prozent) {
        this.removeAll();
        JLabel fragenBeantwortet, korrektLabel, prozentLabel;
    }
    public JTextField getAnswer() {
        return answer;
    }
    public void addButtonListener(HangmanControl l) {

        this.mainMenu.addActionListener(l);

    }
}
