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

    public String saveLocation() {
        return saveDirectory+File.separator + saveFileName;
    }
    public void setSaveDirectory(String saveDirectory) {
        if(!saveDirectory.endsWith(File.separator)) this.saveDirectory = saveDirectory;
    }
    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public boolean saveCards(KarteiKarte[] karten, String dir) {
        File file = new File(dir);
        File folder = file.getParentFile();
        if(!folder.exists()) {
            folder.mkdirs();
        }
        System.out.println(file.getAbsolutePath());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for(int i = 0; i < karten.length; i++) {
                writer.write(karten[i].getFrage()+";"+ karten[i].getAntwort() + ";" + karten[i].getFragentyp() + System.lineSeparator() );
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean saveCards(KarteiKarte[] karten) {
        return saveCards(karten, saveDirectory + File.separator + saveFileName);
    }
    public KarteiKarten getLoadCards()  {
       return getLoadCards(saveDirectory+File.separator+saveFileName);
    }
    public KarteiKarten getLoadCards(String directory)  {
        try {
            File save = new File(directory);
            if (!save.exists()) {
                System.out.println("File does not exist.");
                return null;
            }
            KarteiKarten karten = new KarteiKarten();
            try (BufferedReader br = new BufferedReader(new FileReader(save))) {
                String line;
                for (int i = 0; (line = br.readLine()) != null; i++) {
                    String[] daten = line.split(";");
                    karten.addKarte(new KarteiKarte(daten[0], daten[1], Integer.parseInt(daten[2])));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return karten;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der KarteiKarten");
            return null;
        }
    }
}
