package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.control.MasterController;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FragenverwaltungModel {
    private KarteiKarten karten = null;
    /*
    1. Model soll nicht View machen
    2. Model soll
     */

    public String[] getCard(){


        JTextField front = new JTextField(5);
        JPanel panel = new JPanel(new GridLayout(2,2));
        panel.add(new JLabel("Frage:"));
        panel.add(front);

        JTextField back = new JTextField(5);
        panel.add(new JLabel("Antwort"));
        panel.add(back);
        JOptionPane.showConfirmDialog(null, panel,
                "Frage und Antwort eingeben:", JOptionPane.OK_CANCEL_OPTION);
        return new String[]{front.getText(), back.getText()};
    }

    public void saveCard(String[] card) {
        String userHome = System.getProperty("user.home");
        String outputFolder = userHome + File.separator + "LearnITP-save";
        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            File myObj = new File(outputFolder + File.separator + "LearnITP-save.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            try (FileWriter myWriter = new FileWriter(myObj)) {
                for(int i = 0; i<karten.getCards().length; i++) {
                    myWriter.write(karten.getCardQuestion(i) + ":" + karten.getCardAnswer(i) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public KarteiKarten getLoadCards()  {
        String userHome = System.getProperty("user.home");
        String outputFolder = userHome + File.separator + "LearnITP-save";
        File folder = new File(outputFolder);
        File save = new File(outputFolder + File.separator + "LearnITP-save.txt");
        if (!save.exists()) {
            System.out.println("File does not exist.");
            return null;
        }
        KarteiKarten karten = new KarteiKarten();
        try (BufferedReader br = new BufferedReader(new FileReader(save))) {
            String line;
            KarteiKarte karte = new KarteiKarte();
            for (int i = 0; (line = br.readLine()) != null; i++){
                String[] daten = line.split(":");
                karte.setFrage(daten[0]);
                karte.setAntwort(daten[1]);
                karten.addKarte(karte);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
