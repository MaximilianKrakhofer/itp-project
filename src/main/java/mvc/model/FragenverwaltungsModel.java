package mvc.model;

import mvc.Karten.KarteiKarte;
import mvc.Karten.KarteiKarten;

public class FragenverwaltungsModel {
    private FragenSaveLoader fragenSaveLoada;

    public FragenverwaltungsModel() {
        this.fragenSaveLoada = new FragenSaveLoader();
    }

    public void setAutoLoad(boolean bool) {
        fragenSaveLoada.setAutoLoad(bool);
    }

    public void setAutoSave(boolean autoSave) {
        fragenSaveLoada.setAutoSave(autoSave);
    }

    public String saveLocation() {
        return fragenSaveLoada.saveLocation();
    }

    public void setSaveDirectory(String saveDirectory) {
        fragenSaveLoada.setSaveDirectory(saveDirectory);
    }

    public String getSaveDirectory() {
        return fragenSaveLoada.getSaveDirectory();
    }

    public void setSaveFileName(String saveFileName) {
        fragenSaveLoada.setSaveFileName(saveFileName);
    }

    public void setConfigPath(String configPath) {
        fragenSaveLoada.setConfigPath(configPath);
    }

    public String getConfigPath() {
        return fragenSaveLoada.getConfigPath();
    }

    public boolean saveCards(KarteiKarte[] karten, String dir) {
        return fragenSaveLoada.saveCards(karten, dir);
    }

    public boolean saveCards(KarteiKarte[] karten) {
        return fragenSaveLoada.saveCards(karten);
    }

    public KarteiKarten getLoadCards() {
        return fragenSaveLoada.getLoadCards();
    }

    public KarteiKarten getLoadCards(String directory) {
        return fragenSaveLoada.getLoadCards(directory);
    }
}
