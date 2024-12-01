package mvc.view;

import mvc.control.HangmanControl;
import mvc.control.QuizControl;

import javax.swing.*;
import java.awt.*;

public class QuizView extends JPanel {

    private JButton mainMenu, check, restart, stop, start;
    private JTextField question, answer;
    private JPanel grid;
    public QuizView() {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenu = new JButton("Main Menu");
        mainMenu.setActionCommand("mainmenu");
        start = new JButton("Start");
        start.setActionCommand("start");
        operations.add(mainMenu);
        System.out.println("Quizview");
        this.add(operations, BorderLayout.NORTH);
        this.add(start, BorderLayout.SOUTH);
    }
    public void showSolution(String answer) {
        JOptionPane.showMessageDialog(null, this.question.getText()+ "\nAntwort:" + answer);
    }
    public void startQuiz(String question) {
        this.removeAll();
        this.question = new JTextField(question);
        this.question.setEditable(false);
        this.stop = new JButton("End Quiz");
        this.check = new JButton("Check");
        check.setActionCommand("Check");
        this.answer = new JTextField();
        grid = new JPanel(new GridLayout(1,2));
        grid.add(check);

        grid.add(stop);
        this.add(this.question, BorderLayout.NORTH);
        this.add(answer, BorderLayout.CENTER);
        this.add(grid, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
    }
    public void nextCard(String question) {
        this.question.setText(question);
        this.answer.setText("");
        this.repaint();
        this.revalidate();
    }
    public void endQuiz(int beantwortet, int korrekt, int dauer, double prozent) {
        this.removeAll();
        JLabel fragenbeantwortetLabel, korrektLabel, dauerLabel, prozentLabel;
        JPanel griddy = new JPanel(new GridLayout(4,1));
        fragenbeantwortetLabel = new JLabel("Fragenbeantwortet:" + beantwortet);
        korrektLabel = new JLabel("Korrekt:" + korrekt);
        dauerLabel = new JLabel("Dauer:" + dauer);
        prozentLabel = new JLabel("Prozent:" + prozent);
        griddy.add(fragenbeantwortetLabel);
        griddy.add(korrektLabel);
        griddy.add(dauerLabel);
        griddy.add(prozentLabel);

        JPanel buttons = new JPanel(new GridLayout(1,2));
        buttons.add(mainMenu);
        restart = new JButton("Restart");
        buttons.add(restart);
        this.add(griddy, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
    }
    public JTextField getAnswer() {
        return answer;
    }
    public void addButtonListener(QuizControl l) {

        if(mainMenu!=null) {

            this.mainMenu.addActionListener(l);
        }
        if(check!=null) {
            this.check.addActionListener(l);
        }
        if(restart!=null) {

            this.restart.addActionListener(l); // kochen
        }
        if(stop!=null) {
            this.stop.addActionListener(l);
        }
        if(start!=null) {
            this.start.addActionListener(l);
        }

    }
}
