package mvc.model;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FragenverwaltungModel {

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
                myWriter.write( card[0] + "\n" + card[1] + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public String[] getLoadCards()  {
        String userHome = System.getProperty("user.home");
        File save = new File(userHome + File.separator + "LearnITP-save "+ File.separator + "LearnITP-save.txt");
        if (!save.exists()) {
            return null;
        }
        String cards[] = new String[1];
        try (FileInputStream input = new FileInputStream(save)) {
            System.out.println("File does not exist.2");
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line;
            String[] cardsBefore = new String[0];

            for (int i = 0; (line = br.readLine()) != null; i++){
                System.out.println("File does not exist.3");
                cards  = new String[i+1];
                int j;
                for ( j= 0; j < cardsBefore.length && i>0; j++) {
                    System.out.println("File does not exist.4");
                    cards[j] = cardsBefore[j];
                }
                if (i!=0){
                    cards[j+1] = line;
                    cardsBefore = new String[cards.length];
                    cardsBefore = cards;
                }


            }
            return cards;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
