package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;
import mvc.control.MasterController;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FragenverwaltungModel {
    private String saveDirectory ="."+File.separator + "LearnITP-saves";
    private String saveFileName = "LearnITP-save.txt" ;

    public String SaveLocation() {
        return saveDirectory+File.separator + saveFileName;
    }
    public void setSaveDirectory(String saveDirectory) {
        if(!saveDirectory.endsWith(File.separator))
        this.saveDirectory = saveDirectory;
    }
    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public boolean saveCards(KarteiKarte[] karten) {
        String outputFolder = saveDirectory;
        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(outputFolder + File.separator + saveFileName);
        System.out.println(file.getAbsolutePath());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for(int i = 0; i < karten.length; i++) {
                writer.write(karten[i].getFrage()+","+ karten[i].getAntwort() + System.lineSeparator());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public KarteiKarten getLoadCards()  {
        String outputFolder = saveDirectory;
        File save = new File(outputFolder + File.separator + saveFileName);
        System.out.println(save.getAbsolutePath());
        if (!save.exists()) {
            System.out.println("File does not exist.");
            return null;
        }
        KarteiKarten karten = new KarteiKarten();
        try (BufferedReader br = new BufferedReader(new FileReader(save))) {
            String line;
            for (int i = 0; (line = br.readLine()) != null; i++){
                String[] daten = line.split(",");
                karten.addKarte(new KarteiKarte(daten[0],daten[1]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return karten;
    }
}
