package mvc.view;

import mvc.control.HangmanControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.text.*;
public class HangmanView extends JPanel {

    private JButton mainMenu, check, restart, stop, start, checkWord;
    private JPanel question;
    private JTextPane  answer, hangmanAscii;
    private JPanel grid, imagePanel;
    private JLabel imageLabel, questionText, solutionPreview;
    private boolean isLoaded;
    private int answerlength;
    private StringBuilder underscore;
    public HangmanView(boolean isLoaded) {
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
                throw new MalformedURLException();
            }
        }

    }
    public void startQuiz(String question, int answerlength,int fragentyp) {

        this.answer = new JTextPane();
        this.questionText = new JLabel();
        if(!isLoaded) {
            JOptionPane.showMessageDialog(null, "Keine KarteiKarten vorhanden");
            return;
        }
        this.removeAll();

        this.question = new JPanel(new GridLayout(2, 1, 0, -80));
        double half = this.getHeight()/3;
        this.question.setPreferredSize(new Dimension(80, (int) half));
        this.answerlength = answerlength;
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
            this.question.add(questionText, BorderLayout.CENTER);

             underscore = new StringBuilder();
            for (int i = 0; i < answerlength -1; i++) {
                underscore.append("_ ");
            }
            underscore.append("_");
            solutionPreview = new JLabel(underscore.toString());
            this.question.add(solutionPreview, BorderLayout.CENTER);

        }

        int minFontSize = 10;
        int maxFontSize = 60;
        int textLength = answerlength;
        int newFontSize = maxFontSize - textLength;
        newFontSize = Math.max(newFontSize, minFontSize);
        questionText.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize ));

        solutionPreview.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize ));

        questionText.setHorizontalAlignment(SwingConstants.CENTER);
        solutionPreview.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(this.question, BorderLayout.NORTH);

        this.stop = createButton("Stop", "/images/end.png", 80, 70, 20, 20);
        stop.setActionCommand("End Quiz");
        this.check = new JButton("Check");
        this.check = createButton("Check", "/images/check.png", 80, 70, 20, 20);
        check.setActionCommand("Check");
        grid = new JPanel(new GridLayout(1,3));
        grid.add(check);
        StyledDocument styledDoc = answer.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center, 50);
        styledDoc.setParagraphAttributes(0, styledDoc.getLength(), center, false);

        answer.setSize(10, 10);
        answer.setMaximumSize(new Dimension(10, 10));

        if (styledDoc instanceof AbstractDocument) {
            AbstractDocument doc = (AbstractDocument) styledDoc;
            doc.setDocumentFilter(oneCharFilter);
        }

        JPanel hangmanePane = new JPanel(new BorderLayout(0, 30));
        hangmanAscii = new JTextPane();
        StyledDocument doc2 = hangmanAscii.getStyledDocument();
        SimpleAttributeSet centerAttr = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttr, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(centerAttr, 20);
        doc2.setParagraphAttributes(0, doc2.getLength(), centerAttr, false);
        hangmanAscii.setEditable(false);
        hangmanAscii.setText(" +---+\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========''', ''' ");

        hangmanePane.add(hangmanAscii);
        JPanel answerPanel = new JPanel(new FlowLayout());
        answerPanel.add(answer);
        this.checkWord = createButton("Wort Eingabe", "/images/fullword.png", 70, 80, 40, 40);
        checkWord.setActionCommand("CheckWord");
        grid.add(checkWord);
        grid.add(stop);


        JPanel southGrid = new JPanel(new GridLayout(2,1));
        southGrid.add(answerPanel);
        southGrid.add(grid);
        this.add(southGrid, BorderLayout.SOUTH);
        this.add(hangmanePane, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }
    public void nextCard(String question, int answerlength, int fragentyp) {

        underscore = new StringBuilder();
        this.answer.setText("");
        this.answerlength = answerlength;
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

        }
        this.questionText.setText(question);

        underscore = new StringBuilder();
        for (int i = 0; i < answerlength -1; i++) {
            underscore.append("_ ");
        }
        underscore.append("_");

        solutionPreview.setText(underscore.toString());
        int minFontSize = 10;
        int maxFontSize = 60;
        int textLength = answerlength;
        int newFontSize = maxFontSize - textLength;
        newFontSize = Math.max(newFontSize, minFontSize);

        StyledDocument doc = answer.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center, 50);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        questionText.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize ));

    }
    public String checkWord(){

        JPanel main = new JPanel( new GridLayout(1, 2, 20,0));
        JLabel q = new JLabel("Your Answer?");
        JTextField a = new JTextField();
        main.add(q);
        main.add(a);

        JOptionPane.showConfirmDialog(null, main,
                "Solution", JOptionPane.OK_CANCEL_OPTION);
        return a.getText();
    }

    public void endQuiz(int beantwortet, int failedChars, int failedWords, int[] hangmanCompletions, int dauer, double prozent) {
        this.removeAll();

        JLabel fragenbeantwortetLabel, korrektLabel, dauerLabel, prozentLabel;

        fragenbeantwortetLabel = new JLabel("Fragenbeantwortet:" + beantwortet);
        StringBuilder hangmanCompletionsString = new StringBuilder();

        for (int i = 0; i < hangmanCompletions.length; i++) {
            hangmanCompletionsString.append(hangmanCompletions[i] +"/7; " );
        }

        Font labelFont = new Font("Arial", Font.PLAIN, 30);

        fragenbeantwortetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fragenbeantwortetLabel.setFont(labelFont);
        JLabel hangmanCompletionsLabel = new JLabel(hangmanCompletionsString.toString());
        hangmanCompletionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hangmanCompletionsLabel.setFont(labelFont);
        JLabel failedWordsLabel = new JLabel("Failed Words: " + failedWords);
        failedWordsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        failedWordsLabel.setFont(labelFont);
        JLabel charWrongLabel = new JLabel("Buchstaben Falsch erraten:" + failedChars);
        charWrongLabel.setFont(labelFont);
        charWrongLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dauerLabel = new JLabel("Dauer:" + dauer);
        dauerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dauerLabel.setFont(labelFont);
        prozentLabel = new JLabel("Prozent:" + prozent);
        prozentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        prozentLabel.setFont(labelFont);

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.PAGE_AXIS));
        box.add(fragenbeantwortetLabel);
        box.add(hangmanCompletionsLabel);
        box.add(failedWordsLabel);
        box.add(charWrongLabel);
        box.add(dauerLabel);
        box.add(prozentLabel);

        JPanel buttons = new JPanel(new GridLayout(1,2));
        buttons.add(mainMenu);
        restart = new JButton("restart");
        restart.setActionCommand("restart");
        buttons.add(restart);
        this.add(box );
        this.add(buttons, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();

    }
    public String getAnswer() {
        return answer.getText();
    }

    public void setAnswer(String answerString) {
        this.answer.setText(answerString);
    }

    public void addButtonListener(HangmanControl l) {

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
        if(checkWord != null && checkWord.getActionListeners().length == 0){
            this.checkWord.addActionListener(l);

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
        grid.add(new JLabel(solutionPreview.getText()));
        grid.add(new JLabel("Solution: "));
        grid.add(new JLabel(answerText));
        main.add(grid);
        JOptionPane.showConfirmDialog(null, main,
                "Solution", JOptionPane.OK_CANCEL_OPTION);

    }

    private static JButton createButton(String text, String imagePath,  int width, int height, int imgWidth, int imgHeight) {
        ImageIcon icon = new ImageIcon(HangmanView.class.getResource(imagePath));

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
    public void setCheckedChars(int[] correctChars, char correct){

        for (int i = 0; i < correctChars.length; i++) {
            if(correctChars[i] != -1){
                if(underscore.charAt(correctChars[i] *2) == '_'){
                    underscore.setCharAt(correctChars[i] *2 , correct);
                }
            }

        }

        solutionPreview.setText(underscore.toString());
        int minFontSize = 10;
        int maxFontSize = 60;
        int textLength = answerlength;
        int newFontSize = maxFontSize - textLength;
        newFontSize = Math.max(newFontSize, minFontSize);

        solutionPreview.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, newFontSize ));



    }

    public void setHangmanAscii(String hangmanAscii) {

        this.hangmanAscii.setText(hangmanAscii);
    }

    public String getSolutionPreview() {
        return solutionPreview.getText();
    }
    public void addAttemptChars(){

    }
}
