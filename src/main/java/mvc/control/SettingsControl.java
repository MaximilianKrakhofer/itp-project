package mvc.control;

import mvc.model.SettingsModel;
import mvc.view.SettingsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SettingsControl implements ActionListener {

    private SettingsModel model;
    private SettingsView view;
    private MasterController controller;

    public SettingsControl(MasterController controller) {
        this.controller = controller;
        this.model = new SettingsModel();
        this.view = new SettingsView(SettingsModel.getConfig("theme"), Boolean.parseBoolean(SettingsModel.getConfig("autosave")), Boolean.parseBoolean(SettingsModel.getConfig("autoload")), SettingsModel.getConfig("saveLocation"));
        view.addButtonListener(this);
    }

    public SettingsView getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "mainmenu":
                controller.showMainMenu();
                break;
            case "path":
                try {
                    String loc = SettingsModel.getConfig("saveLocation");
                    String location = view.getSaveLocation(loc != null && !loc.isEmpty() ? loc : ("." + File.separator + "LearnITP-saves" + File.separator + "LearnITP-save.txt"));
                    SettingsModel.saveSetting("saveLocation", location);
                    if (location == null || location.isEmpty()) {
                        return;
                    }
                    view.setPath(location);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Cards not Found");
                    exc.printStackTrace();
                    exc.getMessage();
                }
                break;
            case "autosave":
                SettingsModel.saveSetting("autosave", String.valueOf(view.autoSaveIsChecked()));
                break;
            case "autoload":
                SettingsModel.saveSetting("autoload", String.valueOf(view.autoLoadIsChecked()));
                break;
            case "theme":
                SettingsModel.saveSetting("theme", view.getTheme());
                MasterController.setupTheme();
                view.revalidate();
                controller.repaint();
                break;
        }
    }
}
