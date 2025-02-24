package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;

import javax.swing.*;
import java.io.*;

public class FragenSaveLoader {
    private String configPath= null;
    private String saveDirectory ="."+File.separator + "LearnITP-saves";
    private String saveFileName = "LearnITP-save.txt" ;
    private boolean autoLoad;
    private boolean autoSave;

    public void setAutoLoad(boolean bool) {
        this.autoLoad = bool;
    }

    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }

    public String saveLocation() {
        return configPath == null ? saveDirectory+File.separator + saveFileName:configPath;
    }
    public void setSaveDirectory(String saveDirectory) {
        if(!saveDirectory.endsWith(File.separator)) this.saveDirectory = saveDirectory;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
    public String getConfigPath() {
        return configPath;
    }

    public String getSaveDirectory() {
        return saveDirectory;
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
        return saveCards(karten, configPath== null?saveDirectory + File.separator + saveFileName:configPath);
    }
    public KarteiKarten getLoadCards()  {
       return getLoadCards(configPath==null?saveDirectory+File.separator+saveFileName:configPath);
    }
    public KarteiKarten getLoadCards(String directory)  {
        try {
            System.out.println(directory +"was ist loos");
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
                    karten.addKarte(new KarteiKarte(daten[0], daten[1], Integer.parseInt(daten[2].trim())));
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
