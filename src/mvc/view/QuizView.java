package mvc.view;

import mvc.control.QuizControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class QuizView extends JPanel {

    private JButton mainMenu, check, restart, stop, start;
    private JPanel question;
    private JTextField questionText, answer;
    private JPanel grid, imagePanel;
    private JLabel imageLabel;
    private boolean isLoaded;
    public QuizView(boolean isLoaded) {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenu = new JButton("Main Menu");
        mainMenu.setActionCommand("mainmenu");
        start = new JButton("Start");
        this.isLoaded = isLoaded;
        start.setActionCommand("start");
        operations.add(mainMenu);
        System.out.println("Quizview");
        this.add(operations, BorderLayout.NORTH);
        this.add(start, BorderLayout.SOUTH);
        if (!isLoaded) {
            JOptionPane.showMessageDialog(null, "Keine KarteiKarten vorhanden");
        }
    }
    public void showSolution(String answer) {
        JOptionPane.showMessageDialog(null, this.questionText.getText()+ "\nAntwort:" + answer);
    }
    public void loadImage(String url) {
        try{
            File file = new File(url);
            if(file.exists()) {
                file = new File(file.getAbsolutePath());
            }
            else{
                throw new RuntimeException();
            }
            BufferedImage img = ImageIO.read(file);
            Image scaledImg = img.getScaledInstance(400,300, Image.SCALE_DEFAULT);
            // Library Benötigt Gradle/Maven, also wird das später esetzt
            // BufferedImage resizedImage = Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, 400, 300);
            imageLabel.setIcon(new ImageIcon(scaledImg));
        }
        catch(Exception e) {
            try{
                URL loc = new URL(url);
                BufferedImage img = ImageIO.read(loc);
                Image scaledImg = img.getScaledInstance(400,300, Image.SCALE_REPLICATE);
                imageLabel.setIcon(new ImageIcon(scaledImg));
            }
            catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Message could not load");
            }
        }

    }
    public void startQuiz(String question, int fragentyp) {
        if(!isLoaded) {
            JOptionPane.showMessageDialog(null, "Keine KarteiKarten vorhanden");
            return;
        }
        this.removeAll();
        this.question = new JPanel();
        if(fragentyp == 1) {
            imagePanel = new JPanel(new BorderLayout());
            imageLabel = new JLabel();
            imagePanel.add(imageLabel, BorderLayout.CENTER);
            loadImage(question);
            this.question.add(imagePanel);
        }
        else{
            this.questionText = new JTextField(question);
            this.questionText.setEditable(false);
            this.question.add(questionText);
        }
        this.add(this.question, BorderLayout.NORTH);

        this.stop = new JButton("End Quiz");
        this.check = new JButton("Check");
        check.setActionCommand("Check");
        this.answer = new JTextField();
        grid = new JPanel(new GridLayout(1,2));
        grid.add(check);

        grid.add(stop);
        this.add(answer, BorderLayout.CENTER);
        this.add(grid, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
    }
    public void nextCard(String question, int fragentyp) {
        this.question.removeAll();
        if(fragentyp == 1) {
            imagePanel = new JPanel(new BorderLayout());
            imageLabel = new JLabel();
            imagePanel.add(imageLabel, BorderLayout.CENTER);
            loadImage(question);
            this.question.add(imagePanel);
        }
        else{
            this.questionText = new JTextField(question);
            this.questionText.setEditable(false);
            this.question.add(questionText);
        }
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
