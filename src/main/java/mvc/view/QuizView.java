package mvc.view;

import mvc.control.QuizControl;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class QuizView extends JPanel {

    private JButton mainMenu, check, restart, stop, start;
    private JPanel question;
    private JTextPane answer, realAnswer;
    private JPanel grid;
    private JPanel imagePanel;
    private JLabel  questionText;
    private boolean isLoaded;

    public QuizView(boolean isLoaded) {
        this.setLayout(new BorderLayout());
        JPanel operations = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenu = createButton("Menu", "/images/return.png", 75, 50, 20, 20);
        mainMenu.setActionCommand("mainmenu");
        start = createButton("Start", "/images/start.png", 90, 90, 140, 140);
        start.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, 45));
        this.isLoaded = isLoaded;
        start.setActionCommand("start");
        operations.add(mainMenu);
        System.out.println("Quizview");
        this.add(operations, BorderLayout.NORTH);
        this.add(start, BorderLayout.CENTER);
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image if it's loaded
                if (loadedImage != null) {
                    g.drawImage(loadedImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        if (!isLoaded) {
            JOptionPane.showMessageDialog(null, "Keine KarteiKarten vorhanden");
        }
    }

    public void showSolution(String answer) {
        JOptionPane.showMessageDialog(null, this.questionText.getText() + "\nAntwort:" + answer);
    }
    private BufferedImage loadedImage;
    public void loadImage(String url) throws MalformedURLException {
        try {
            File file = new File(url);
            if (file.exists()) {
                loadedImage = Thumbnails.of(file.getAbsolutePath()).size(300, 400).asBufferedImage();
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            try {
                URL loc = new URL(url);
                loadedImage = Thumbnails.of(loc).size(300, 400).asBufferedImage();
            } catch (Exception e2) {
                throw new MalformedURLException();
            }
        }
        if (loadedImage != null) {
            imagePanel.setPreferredSize(new Dimension(loadedImage.getWidth(), loadedImage.getHeight()));
        }
        imagePanel.repaint();
        this.revalidate();
    }





    public void startQuiz(String question, int fragentyp) {

        this.questionText = new JLabel();
        this.answer = new JTextPane();

        questionText.setBorder(new EmptyBorder(0,3,0,0));
        if (!isLoaded) {
            JOptionPane.showMessageDialog(null, "Keine KarteiKarten vorhanden");
            return;
        }
        this.removeAll();

        this.question = new JPanel();
        double half = this.getHeight() / 2.0;
        questionText.setPreferredSize(new Dimension(this.getWidth(),(int)half));
        this.question.setPreferredSize(new Dimension(80, (int) half));
        if (fragentyp == 1) {
            try {
                imagePanel = new JPanel(new BorderLayout());
                loadImage(question);
                JLabel picture = new JLabel(new ImageIcon(loadedImage));
                imagePanel.add(picture);
                this.question.add(imagePanel);
                imagePanel.revalidate();
                imagePanel.repaint();
            } catch (MalformedURLException e) {
                this.questionText = new JLabel("Fehler beim Laden des Bildes");
            }
        } else {
            questionText.setText("<html>  " +"  "+ question + "</html>");
            questionText.setPreferredSize(new Dimension(this.getWidth(),(int)half));
            this.question.add(questionText);
            int minFontSize = 30;
            int maxFontSize = 70;
            int textLength = questionText.getText().length();
            int newFontSize = maxFontSize -(int) (textLength*0.8);
            newFontSize = Math.max(newFontSize, minFontSize);
            questionText.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize));
        }

        answer.setAlignmentX(Component.CENTER_ALIGNMENT);
        answer.setAlignmentY(Component.CENTER_ALIGNMENT);

        answer.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, 50));
        this.add(this.question, BorderLayout.NORTH);

        this.stop = createButton("Stop", "/images/end.png", 70, 70, 20, 20);
        stop.setActionCommand("End Quiz");
        this.check = new JButton("Check");
        this.check = createButton("Check", "/images/check.png", 70, 70, 20, 20);
        check.setActionCommand("Check");
        grid = new JPanel(new GridLayout(1, 2));
        grid.add(check);



        StyledDocument doc = answer.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center, 50);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        grid.add(stop);
        this.add(answer, BorderLayout.CENTER);
        this.add(grid, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
    }

    public void nextCard(String question, int fragentyp) {
        this.question.removeAll();

        this.answer.setText("");

        if (fragentyp == 1) {
            try {
                loadImage(question);  // Ensure `question` holds the correct URL or path.
                imagePanel.revalidate();  // Update the layout.
                this.question.add(imagePanel);  // Add the imagePanel to your `question` panel.
            } catch (MalformedURLException e) {
                this.questionText.setText("<html> Fehler beim Laden des Bildes <html>");
                this.question.add(questionText);
            }
        } else {
            imagePanel.removeAll();
            questionText.setText("<html>  " +"  "+ question + "<html>");
            this.question.add(questionText);
            int minFontSize = 30;
            int maxFontSize = 70;
            int textLength = questionText.getText().length();
            int newFontSize = maxFontSize -(int) (textLength*0.8);
            newFontSize = Math.max(newFontSize, minFontSize);
            questionText.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize));
        }
        questionText.setBorder(new EmptyBorder(0,3,0,0));
        int minFontSize = 40;
        int maxFontSize = 70;
        int textLength = questionText.getText().length();
        int newFontSize = maxFontSize - textLength;
        newFontSize = Math.max(newFontSize, minFontSize);


        answer.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, 50));

        StyledDocument doc = answer.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center, 50);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);


        questionText.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize));

        questionText.setHorizontalAlignment(SwingConstants.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void endQuiz(int beantwortet, int korrekt, int dauer, int prozent) {
        this.removeAll();
        JLabel fragenbeantwortetLabel, korrektLabel, dauerLabel, prozentLabel;
        Font labelFont = new Font("Arial", Font.PLAIN, 30);
        fragenbeantwortetLabel = new JLabel("Fragenbeantwortet: " + beantwortet);
        fragenbeantwortetLabel.setFont(labelFont);
        fragenbeantwortetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        korrektLabel = new JLabel("Korrekt: " + korrekt);
        korrektLabel.setFont(labelFont);
        korrektLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dauerLabel = new JLabel("Dauer: " + dauer + " Sekunden");
        dauerLabel.setFont(labelFont);
        dauerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        prozentLabel = new JLabel("Prozent: " + prozent);
        prozentLabel.setFont(labelFont);
        prozentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(Box.createVerticalGlue());
        box.add(fragenbeantwortetLabel);
        box.add(korrektLabel);
        box.add(dauerLabel);
        box.add(prozentLabel);


        box.add(Box.createVerticalGlue());
        JPanel buttons = new JPanel(new GridLayout(1, 2));
        buttons.add(mainMenu);
        restart = new JButton("restart");
        restart.setActionCommand("restart");
        buttons.add(restart);
        this.add(box, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
    }

    public String getAnswer() {
        return answer.getText();
    }

    public void addButtonListener(QuizControl l) {

        if (mainMenu != null && mainMenu.getActionListeners().length == 0) {

            this.mainMenu.addActionListener(l);
        }
        if (check != null && check.getActionListeners().length == 0) {
            this.check.addActionListener(l);
        }
        if (restart != null && restart.getActionListeners().length == 0) {

            this.restart.addActionListener(l); // kochen
        }
        if (stop != null && stop.getActionListeners().length == 0) {
            this.stop.addActionListener(l);
        }
        if (start != null && start.getActionListeners().length == 0) {
            this.start.addActionListener(l);
        }

        if (answer != null && answer.getKeyListeners().length== 0) {
            answer.addKeyListener(l);

        }

    }
    public void setCheck(boolean truth, String answerText, int fragentyp) {
        JPanel main = new JPanel(new GridLayout(2, 1));
        JPanel gridy = new JPanel();
        gridy.setLayout(new BoxLayout(gridy, BoxLayout.Y_AXIS));

        JPanel boxquest = new JPanel();
        boxquest.setLayout(new BoxLayout(boxquest, BoxLayout.X_AXIS));
        boxquest.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel boxansw = new JPanel();
        boxansw.setLayout(new BoxLayout(boxansw, BoxLayout.X_AXIS));
        boxansw.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel boxsolut = new JPanel();
        boxsolut.setLayout(new BoxLayout(boxsolut, BoxLayout.X_AXIS));
        boxsolut.setAlignmentX(Component.LEFT_ALIGNMENT);

        JEditorPane correct = new JEditorPane();
        correct.setContentType("text/html");
        if (truth) {
            correct.setText("<html><font color=green>CORRECT</font></html>");
        } else {
            correct.setText("<html><font color=red>WRONG</font></html>");
        }
        main.add(correct);

        boxquest.add(new JLabel("Question: "));
        if (fragentyp == 0) {
            boxquest.add(new JLabel(questionText.getText()));
        } else {

        }

        boxansw.add(new JLabel("Your answer: "));
        boxansw.add(new JLabel(answer.getText()));

        boxsolut.add(new JLabel("Solution: "));
        realAnswer = new JTextPane();
        realAnswer.setText(answerText);
        realAnswer.setEditable(false);
        boxsolut.add(realAnswer);

        gridy.add(boxquest);
        gridy.add(boxansw);
        gridy.add(boxsolut);
        main.add(gridy);

        JOptionPane.showConfirmDialog(null, main, "Solution", JOptionPane.OK_CANCEL_OPTION);
    }
    private static JButton createButton(String text, String imagePath, int width, int height, int imgWidth, int imgHeight) {
        ImageIcon icon = new ImageIcon(QuizView.class.getResource(imagePath));

        Image scaledImage = icon.getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        JButton button = new JButton(text, icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        Font font = new Font("Roboto", Font.TRUETYPE_FONT, 10);
        button.setFont(font);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);
        return button;
    }

    public void setTextFieldActive(){
        answer.requestFocus();
    }

}
