package mvc.view;

import javax.swing.*;
import java.awt.*;

public class MasterView {
    private JFrame frame;
    public MasterView() {

        frame = new JFrame("LearnITP");
        frame.setMinimumSize(new Dimension(850, 600));
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.pack();

    }
    public void updateContent(JPanel newContent) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(newContent);
        frame.revalidate();
        frame.repaint();
    }

}