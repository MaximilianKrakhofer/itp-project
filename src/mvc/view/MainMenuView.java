package mvc.view;

import mvc.control.MainMenuController;
import mvc.control.QuizControl;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {
    private JButton fragenVerwaltung, quiz, hangman;
    public MainMenuView() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        fragenVerwaltung = createButton("Fragenverwaltung", "./src/images/library-books.png");
        fragenVerwaltung.setActionCommand("Fragenverwaltung");
        quiz = createButton("Quiz", "./src/images/Quiz.png");
        quiz.setActionCommand("Quiz");
        hangman = createButton("Hangman", "./src/images/hangman.png");
        hangman.setActionCommand("Hangman");
        this.add(fragenVerwaltung);
        this.add(quiz);
        this.add(hangman);
    }
    public void addButtonListener(MainMenuController l) {

        this.fragenVerwaltung.addActionListener(l);
        this.quiz.addActionListener(l);
        this.hangman.addActionListener(l);

    }

    private static JButton createButton(String text, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);

        Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);


        JButton button = new JButton(text, icon);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(10);
        Font font = new Font("Arial", Font.TRUETYPE_FONT, 30);
        button.setFont(font);

        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        button.setPreferredSize(new Dimension(400, 140));
        button.setFocusPainted(false);

        return button;
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
