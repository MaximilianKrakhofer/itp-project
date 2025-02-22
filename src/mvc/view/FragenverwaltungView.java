package mvc.view;

import mvc.Karten.KarteiKarte;
import mvc.control.FragenverwaltungControl;

// import org.imgscalr.Scalr;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.io.File;

public class FragenverwaltungView extends JPanel {
    private JButton add;
    private JButton delete;
    private JButton save;
    private JButton load;
    private JTable table;
    private JButton mainMenu;
    private DefaultTableModel cards;
    private JScrollPane cardsPane;
 // möglicher weise FlatLook später hinzufügen
    public FragenverwaltungView( ) {



        this.setLayout(new BorderLayout());

        JPanel operations = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add = new JButton("Hinzufuegen");
        add.setActionCommand("add");
        delete = new JButton("Loeschen");
        delete.setActionCommand("delete");
        save = new JButton("Speichern");
        save.setActionCommand("save");
        load = new JButton("Laden");
        load.setActionCommand("load");
        mainMenu = createButton("Menu", "./src/images/return.png");

        mainMenu.setActionCommand("main");
        cards = new DefaultTableModel();
        cards.addColumn("Frage");
        cards.addColumn("Antwort");
        cards.addColumn("Fragentyp");
        table = new JTable(cards);
        table.setModel(cards);
        table.setRowHeight(35);
        cardsPane = new JScrollPane(table);

        operations.add(add);
        operations.add(delete);
        operations.add(save);
        operations.add(load);
        operations.add(mainMenu);


        this.add(operations, BorderLayout.NORTH);
        this.add(cardsPane, BorderLayout.CENTER);

    }
    public void addButtonListener(FragenverwaltungControl l) {  //  xD Boris kocht
        this.add.addActionListener(l);
        this.delete.addActionListener(l);
        this.save.addActionListener(l);
        this.load.addActionListener(l);
        this.mainMenu.addActionListener(l);

    }
    public KarteiKarte getCard(){


        JTextField front = new JTextField(5);
        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(new JLabel("Frage:"));
        panel.add(front);

        JTextField back = new JTextField(5);
        panel.add(new JLabel("Antwort"));
        panel.add(back);

        JComboBox box = new JComboBox(new String[]{"Text","URL"});
        panel.add(new JLabel("Fragentyp"));
        panel.add(box);
        JOptionPane.showConfirmDialog(null, panel,
                "Frage und Antwort eingeben:", JOptionPane.OK_CANCEL_OPTION);


        return new KarteiKarte(front.getText(), back.getText(), box.getSelectedIndex());
    }
    public String getLoadLocation(String location) {
        File defaultLocation = new File(location);
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(defaultLocation);
        chooser.setCurrentDirectory(defaultLocation.getParentFile());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt", "clv");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else{
            JOptionPane.showMessageDialog(null, "Datei nicht gefunden");
            return null;
        }
    }
    public void resetCards() {
        cards.setRowCount(0);
    }
    public DefaultTableModel getTableModel () {
        return cards;
    }
    public void appendCard(KarteiKarte karte) {
        String fragentyp;
        switch(karte.getFragentyp()) {
            case 1:
                fragentyp="Bild";
                break;
            default:
                fragentyp="Text";
        }
        cards.addRow(new Object[]{karte.getFrage(), karte.getAntwort(), fragentyp});
    }
    public int removeCard() {
        int a = table.getSelectedRow();
        if(a == -1) return -1;
        cards.removeRow(a);

        return a;

    }
    public KarteiKarte[] getCards() {

        KarteiKarte[] wimma = new KarteiKarte[table.getRowCount()];
        for(int i = 0; i< wimma.length;i++) {


            String front = cards.getValueAt(i, 0).toString();
            String back = cards.getValueAt(i, 1).toString();
            int fragentyp = cards.getValueAt(i,2).toString().equals("Bild") ? 1:0;

            KarteiKarte karte = new KarteiKarte(front,back,fragentyp);
            wimma[i] = karte;
        }
        return wimma;

    }

    public JButton getMainMenu() {
        return mainMenu;
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
