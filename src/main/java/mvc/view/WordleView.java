package mvc.view;

import mvc.control.QuizControl;
import mvc.control.WordleControl;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class WordleView extends JPanel {

    private JButton mainMenu, check, restart, stop, start;
    private JPanel question, answersGrid;
    private JTextPane answer, realAnswer;
    private JPanel grid, imagePanel;
    private JLabel imageLabel, questionText;
    private boolean isLoaded;
    private JTextPane[][] answers;
    private int rowCounter;
    public WordleView(boolean isLoaded) {
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
        if (!isLoaded) {
            JOptionPane.showMessageDialog(null, "Keine KarteiKarten vorhanden");
        }
    }
    public void showSolution(String answer) {
        JOptionPane.showMessageDialog(null, this.questionText.getText()+ "\nAntwort:" + answer);
    }
    public void loadImage(String url) throws MalformedURLException {
        try{
            File file = new File(url);
            if(file.exists()) {
                file = new File(file.getAbsolutePath());
            }
            else{
                throw new RuntimeException();
            }
            BufferedImage scaledImg = Thumbnails.of(file.getAbsolutePath()).size(300,400).asBufferedImage();
            //Image scaledImg = img.getScaledInstance(400,300, Image.SCALE_DEFAULT);
            // Library Benötigt Gradle/Maven, also wird das später esetzt
            // BufferedImage resizedImage = Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, 400, 300);
            imageLabel.setIcon(new ImageIcon(scaledImg));
        }
        catch(Exception e) {
            try{
                URL loc = new URL(url);
                BufferedImage img = Thumbnails.of(loc).size(300,400).asBufferedImage();;
                imageLabel.setIcon(new ImageIcon(img));
            }
            catch (Exception e2) {
                throw new MalformedURLException();
            }
        }

    }
    public void startQuiz(String question, int fragentyp) {
        rowCounter = 0;
        this.questionText = new JLabel();

        if(!isLoaded) {
            JOptionPane.showMessageDialog(null, "Keine KarteiKarten vorhanden");
            return;
        }
        this.removeAll();

        this.question = new JPanel();
        double half = this.getHeight()/6.0;
        this.question.setPreferredSize(new Dimension(80, (int) half));
        if(fragentyp == 1) {
            try {
                imagePanel = new JPanel(new BorderLayout());
                imageLabel = new JLabel();
                imagePanel.add(imageLabel, BorderLayout.CENTER);
                loadImage(question);
                this.question.add(imagePanel);
            }
            catch (MalformedURLException e) {
                this.questionText = new JLabel("Fehler beim Laden des Bildes");
            }
        }


        else{
            questionText.setText(question);
            this.question.add(questionText);
        }
        int minFontSize = 10;
        int maxFontSize = 80;
        int textLength = questionText.getText().length();
        int newFontSize = maxFontSize - textLength;
        newFontSize = Math.max(newFontSize, minFontSize);
        questionText.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize ));
        this.add(this.question, BorderLayout.NORTH);

        this.stop = createButton("Stop", "/images/end.png", 70, 70, 20, 20);
        stop.setActionCommand("End Quiz");
        this.check = new JButton("Check");
        this.check = createButton("Check", "/images/check.png", 70, 70, 20, 20);
        check.setActionCommand("Check");
        grid = new JPanel(new GridLayout(1,2));
        grid.add(check);
        JPanel[] answerPanel = new JPanel[questionText.getText().length() + 1];

        answers = new JTextPane[5][questionText.getText().length() + 1];
        answersGrid = new JPanel(new GridLayout(5, questionText.getText().length() +2, 10, 20));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < questionText.getText().length(); j++) {
                this.answers[i][j] = new JTextPane();
                StyledDocument styledDoc = answers[i][j].getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                StyleConstants.setFontSize(center, 30);
                styledDoc.setParagraphAttributes(0, styledDoc.getLength(), center, false);
                for (int k = 0; k < answerPanel.length; k++) {
                    answerPanel[k] = new JPanel(new FlowLayout());
                }
                if(i>=1){
                    answers[i][j].setEditable(false);
                }
                answerPanel[j].add(answers[i][j]);
                this.add(answerPanel[j]);
                answersGrid.add(answers[i][j]);
                if (styledDoc instanceof AbstractDocument) {
                    AbstractDocument doc = (AbstractDocument) styledDoc;
                    doc.setDocumentFilter(oneCharFilter);
                }

                answers[i][j].setAlignmentX(Component.CENTER_ALIGNMENT);
                answers[i][j].setAlignmentY(Component.CENTER_ALIGNMENT);
                answers[i][j].setSize(10, 10);
                answers[i][j].setMaximumSize(new Dimension(10, 10));
                answers[i][j].setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, 20 ));

            }
        }
        this.add(answersGrid, BorderLayout.CENTER);
        grid.add(stop);
        this.add(grid, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();

    }
    public void nextCard(String question, int fragentyp) {

        this.answer.setText("");

        if(fragentyp == 1) {
            try {
                imagePanel = new JPanel(new BorderLayout());
                imageLabel = new JLabel();
                imagePanel.add(imageLabel, BorderLayout.CENTER);
                loadImage(question);
                this.question.add(imagePanel);
            }
            catch (MalformedURLException e) {
                this.questionText = new JLabel("Fehler beim Laden des Bildes");
            }
        }
        else {

            int minFontSize = 10;
            int maxFontSize = 100;
            int textLength = questionText.getText().length();
            int newFontSize = maxFontSize - textLength;
            newFontSize = Math.max(newFontSize, minFontSize);

            StyledDocument doc = answer.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(center, 50);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            questionText.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize));

        }
    }
    public void endQuiz(int beantwortet, int korrekt, int dauer, double prozent) {
        this.removeAll();
        JLabel fragenbeantwortetLabel, korrektLabel, dauerLabel, prozentLabel;
        Font labelFont = new Font("Arial", Font.PLAIN, 30);
        fragenbeantwortetLabel = new JLabel("Fragenbeantwortet: " + beantwortet);
        fragenbeantwortetLabel.setFont(labelFont);
        fragenbeantwortetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        korrektLabel = new JLabel("Korrekt: " + korrekt);
        korrektLabel.setFont(labelFont);
        korrektLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dauerLabel = new JLabel("Dauer: " + dauer);
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
    public char[] getAnswers() {
        char[] answersChar = new char[questionText.getText().length()];
        for (int i = 0; answers !=null && i < questionText.getText().length(); i++) {
            if(answers[rowCounter][i].getText()!= null)
            {
                answersChar[i] = answers[rowCounter][i].getText().charAt(0);
            }
        }
        return answersChar;
    }
    public void addButtonListener(WordleControl l) {

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

    DocumentFilter oneCharFilter = new DocumentFilter() {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (fb.getDocument().getLength() + string.length() <= 1) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int newLength = currentLength - length + (text != null ? text.length() : 0);
            if (newLength <= 1) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    };

    private static JButton createButton(String text, String imagePath,  int width, int height, int imgWidth, int imgHeight) {
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
    public void setColors(int[] characters){
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] != -1)
            {
                answers[rowCounter][characters [i]].setForeground(Color.GREEN);
            }
        }
    }
    public void activateNewFields(){
        for (int i = 0; i < questionText.getText().length()  ; i++) {

        }
        rowCounter ++;

    }

    public int getRowCounter() {
        return rowCounter;
    }
}
