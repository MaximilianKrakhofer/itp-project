package mvc.view;

import javax.swing.*;
import java.awt.*;

public class HangmanFigure extends JPanel {
    private int incorrectGuesses;
    public HangmanFigure(int incorrectGuesses) {
        this.incorrectGuesses = incorrectGuesses;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 20, 20, 30);
        g.drawOval(0,0,20,20); // Koch den Hangman
    }
}
