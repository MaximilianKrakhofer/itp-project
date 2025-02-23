package mvc.view;

import mvc.control.MainMenuController;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenuView extends JPanel {
    private JButton fragenVerwaltung, quiz, hangman, settings;
    public MainMenuView() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        settings = createButton("Settings", "/images/settings.png");
        fragenVerwaltung = createButton("Fragenverwaltung", "/images/library-books.png");
        fragenVerwaltung.setActionCommand("Fragenverwaltung");
        quiz = createButton("Quiz", "/images/Quiz.png");
        quiz.setActionCommand("Quiz");
        hangman = createButton("Hangman", "/images/hangman.png");
        hangman.setActionCommand("Hangman");
        settings.setActionCommand("Settings");
        this.add(fragenVerwaltung);
        this.add(quiz);
        this.add(hangman);
        this.add(settings);
    }
    public void addButtonListener(MainMenuController l) {

        this.fragenVerwaltung.addActionListener(l);
        this.quiz.addActionListener(l);
        this.hangman.addActionListener(l);
        this.settings.addActionListener(l);

    }

    private static JButton createButton(String text, String imagePath) {
        try {
            BufferedImage image = Thumbnails.of(SettingsView.class.getResource(imagePath).getPath()).size(80, 80).asBufferedImage();
            ImageIcon icon = new ImageIcon(image);
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
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
