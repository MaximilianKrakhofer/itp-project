package mvc.view;

import mvc.control.SettingsControl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import net.coobird.thumbnailator.*;


public class SettingsView extends JPanel {

    private JLabel path;
    private JButton mainMenu, savePath;
    private JCheckBox autosave, autoload;
    private JComboBox<String> themeBox;
    private String[] themes = {"FlatIntelliJLaf", "FlatDarculaLaf", "FlatMacLightLaf", "FlatMacDarkLaf", "FlatCarbonIJTheme",
            "FlatHiberbeeDarkIJTheme", "FlatDraculaIJTheme", "FlatMonokaiProIJTheme", "FlatNordIJTheme", "FlatOneDarkIJTheme",
            "FlatGitHubIJTheme", "FlatGitHubDarkIJTheme", "FlatMoonlightIJTheme", "FlatMaterialPalenightIJTheme",
            "FlatMaterialDeepOceanIJTheme", "FlatMaterialOceanicIJTheme", "FlatNightOwlIJTheme"};

    public SettingsView(String selectedTheme, boolean autoSave, boolean autoLoad, String filedir) {
        this.setLayout(new BorderLayout());

        JPanel operations = new JPanel(new BorderLayout());
        mainMenu = createButton("Menu", "/images/return.png", 75, 50, 20, 20);
        mainMenu.setActionCommand("mainmenu");

        operations.add(mainMenu, BorderLayout.EAST);

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, 60));
        operations.add(title, BorderLayout.CENTER);

        this.add(operations, BorderLayout.NORTH);
        JPanel settingsGrid = new JPanel(new GridLayout(8, 1));

        JPanel saving = new JPanel();
        saving.setLayout(new BoxLayout(saving, BoxLayout.X_AXIS));
        saving.add(new JLabel("Kartei Speicher-Pfad:"));
        savePath = new JButton("Default-Pfad auswählen");
        System.out.println(filedir);
        path = new JLabel(filedir == null ? "" : filedir);
        saving.add(Box.createRigidArea(new Dimension(10, 0)));
        saving.add(savePath);
        saving.add(Box.createRigidArea(new Dimension(10, 0)));
        saving.add(path);


        savePath.setActionCommand("path");

        settingsGrid.add(saving);

        JPanel autosave = new JPanel();
        autosave.setLayout(new BoxLayout(autosave, BoxLayout.X_AXIS));
        autosave.add(new JLabel("Auto Save"));
        this.autosave = new JCheckBox("");
        this.autosave.setActionCommand("autosave");
        this.autosave.setSelected(autoSave);
        autosave.add(this.autosave);
        settingsGrid.add(autosave);
        JPanel autoload = new JPanel();
        autoload.setLayout(new BoxLayout(autoload, BoxLayout.X_AXIS));
        autoload.add(new JLabel("Auto Load"));
        this.autoload = new JCheckBox("");
        this.autoload.setActionCommand("autoload");
        this.autoload.setSelected(autoLoad);
        autoload.add(this.autoload);
        settingsGrid.add(autoload);

        JPanel theme = new JPanel();
        theme.setLayout(new BoxLayout(theme, BoxLayout.X_AXIS));
        theme.add(new JLabel("Theme:"));
        this.themeBox = new JComboBox<>(themes);
        theme.add(this.themeBox);
        this.themeBox.setActionCommand("theme");
        settingsGrid.add(theme);
        if (!(selectedTheme == null || selectedTheme.isEmpty())) {
            this.themeBox.setSelectedItem(selectedTheme);
        }
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(settingsGrid, BorderLayout.CENTER);


    }

    public String getTheme() {
        System.out.println((String) this.themeBox.getSelectedItem());
        return (String) this.themeBox.getSelectedItem();
    }

    public boolean autoSaveIsChecked() {
        return this.autosave.isSelected();
    }

    public boolean autoLoadIsChecked() {
        return this.autoload.isSelected();
    }

    public void setPath(String path) {
        this.path.setText(path);
        this.repaint();
        this.revalidate();
    }

    private static JButton createButton(String text, String imagePath, int width, int height, int imgWidth, int imgHeight) {
        ImageIcon icon = new ImageIcon(SettingsView.class.getResource(imagePath));
        Image scaledImage = icon.getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        JButton button = new JButton(text, icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        Font font = new Font("Roboto", Font.TRUETYPE_FONT, 10);
        button.setFont(font);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);
        return button;
    }

    public String getSaveLocation(String location) {
        File defaultLocation = new File(location);
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(defaultLocation);
        chooser.setCurrentDirectory(defaultLocation.getParentFile());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Folder", "dir");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "Datei nicht gefunden");
            return null;
        }
    }

    public void addButtonListener(SettingsControl l) {
        if (mainMenu != null && mainMenu.getActionListeners().length == 0) {
            this.autosave.addActionListener(l);
            this.autoload.addActionListener(l);
            this.themeBox.addActionListener(l);
            this.mainMenu.addActionListener(l);
            this.savePath.addActionListener(l);
        }

    }


}
