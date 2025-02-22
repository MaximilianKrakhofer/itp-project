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
    private JTextArea questionText, answer, realAnswer;
    private JPanel grid, imagePanel;
    private JLabel imageLabel;
    private boolean isLoaded;
    public QuizView(boolean isLoaded) {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenu = createButton("Menu", "./src/images/return.png");
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
            this.questionText = new JTextArea();
            questionText.setText(question);
            this.questionText.setEditable(false);
            this.question.add(questionText);
        }
        this.add(this.question, BorderLayout.NORTH);

        this.stop = new JButton("End Quiz");
        this.check = new JButton("Check");
        check.setActionCommand("Check");
        this.answer = new JTextArea();
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
            this.questionText = new JTextArea();
            this.questionText.setText(question);
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
        JPanel moveTheBodyMoveTheFlow = new JPanel(new FlowLayout());
        JPanel river = new JPanel(new FlowLayout());
        fragenbeantwortetLabel = new JLabel("Fragenbeantwortet:" + beantwortet);
        korrektLabel = new JLabel("Korrekt:" + korrekt);
        dauerLabel = new JLabel("Dauer:" + dauer);
        prozentLabel = new JLabel("Prozent:" + prozent);

        moveTheBodyMoveTheFlow.add(fragenbeantwortetLabel);
        moveTheBodyMoveTheFlow.add(korrektLabel);
        river.add(dauerLabel);
        river.add(prozentLabel);

        JPanel buttons = new JPanel(new GridLayout(1,2));
        buttons.add(mainMenu);
        restart = new JButton("restart");
        restart.setActionCommand("restart");
        buttons.add(restart);
        this.add(moveTheBodyMoveTheFlow );
        this.add(river );
        this.add(buttons, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();

    }
    public String getAnswer() {
        return answer.getText();
    }
    public void addButtonListener(QuizControl l) {

        if(mainMenu!=null && mainMenu.getActionListeners().length ==0) {

            this.mainMenu.addActionListener(l);
        }
        if(check!=null  && check.getActionListeners().length ==0) {
            this.check.addActionListener(l);
        }
        if(restart!=null && restart.getActionListeners().length == 0) {

            this.restart.addActionListener(l); // kochen
        }
        if(stop!=null  && stop.getActionListeners().length ==0) {
            this.stop.addActionListener(l);
        }
        if(start!=null &&  start.getActionListeners().length ==0) {
            this.start.addActionListener(l);
        }

    }
    public void setCheck(boolean truth, String answerText){


        JPanel main = new JPanel( new GridLayout(2, 1));
        JPanel grid = new JPanel( new GridLayout(3, 2));
        JEditorPane correct =  new JEditorPane();
        correct.setContentType("text/html");
        if(truth){
            correct.setText("<html><font color=green>CORRECT</font></html>");
        }
        else{
            correct.setText("<html><font color=red>WRONG</font></html>");
        }
        main.add(correct);

        grid.add(new JLabel("Question"));
        grid.add(new JLabel(questionText.getText()));

        grid.add(new JLabel("Your answer"));
        grid.add(new JLabel(answer.getText()));
        grid.add(new JLabel("Solution: "));
        realAnswer = new JTextArea();
        realAnswer.setText(answerText);

        realAnswer.setEditable(false);
        grid.add(realAnswer);
        main.add(grid);
        JOptionPane.showConfirmDialog(null, main,
                "Solution", JOptionPane.OK_CANCEL_OPTION);

    }

    private static JButton createButton(String text, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);

        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        JButton button = new JButton(text, icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        Font font = new Font("Roboto", Font.TRUETYPE_FONT, 10);
        button.setFont(font);
        button.setPreferredSize(new Dimension(60, 50));
        button.setFocusPainted(false);
        return button;
    }

}
