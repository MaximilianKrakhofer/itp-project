package mvc.view;

import javax.swing.*;
import java.awt.*;
public class FragenverwaltungView extends JFrame {
    JTextArea cards;
    JScrollPane cardsPane;
    JButton add;
    JButton delete;
    JButton save;
    JButton load;

    public FragenverwaltungView(String title) {
        JFrame frame = new JFrame(title);
        frame.setMinimumSize(new Dimension(850, 600));
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        frame.add(panel);
        frame.setVisible(true);
        frame.pack();


        panel.setLayout(new BorderLayout());

        JPanel operations = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add = new JButton("Hinzufuegen");
        delete = new JButton("Loeschen");
        save = new JButton("Speichern");
        load = new JButton("Laden");

        operations.add(add);
        operations.add(delete);
        operations.add(save);
        operations.add(load);

        panel.add(operations, BorderLayout.NORTH);

        cards = new JTextArea("");
        cards.setEditable(false);
        cards.setColumns(8);
        cards.setRows(7);
        cards.setEditable(false);
        cards.setFont(new Font("Monospaced", Font.PLAIN, 30));
        cardsPane = new JScrollPane(cards);
        panel.add(cardsPane, BorderLayout.CENTER);

        JPanel selectButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton mainMenu = new JButton("MainMenu");
        JButton quiz = new JButton("Quiz");
        JButton hangman = new JButton("Hangman");
        selectButtons.add(mainMenu);
        selectButtons.add(quiz);
        selectButtons.add(hangman);

        panel.add(selectButtons, BorderLayout.SOUTH);

    }


}
