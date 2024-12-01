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
                myWriter.write( card[0] + "\n" + card[1] + "\n\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public String[] getLoadCards() throws IOException {
        String cards [];
        String userHome = System.getProperty("user.home");
        File save = new File(userHome + File.separator + "LearnITP-save "+ File.separator + "LearnITP-save.txt");
        if (!save.exists()) {
            return null;
        }
        try (FileInputStream input = new FileInputStream(save)) {
            StringBuilder content = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line;

            while ((line = br.readLine()) != null) {

                content.append(line + "/n");

            }
            System.out.println(content.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
