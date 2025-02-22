package mvc.view;

import mvc.control.QuizControl;
import mvc.control.SettingsControl;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.concurrent.Flow;

public class SettingsView extends JPanel {


    private JButton mainMenu, savePath;
    public SettingsView(){
        this.setLayout(new BorderLayout());

        JPanel operations = new JPanel(new BorderLayout());
        mainMenu = createButton("Menu", "./src/images/return.png", 75, 50, 20, 20);
        mainMenu.setActionCommand("mainmenu");

        operations.add(mainMenu, BorderLayout.EAST);

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Bahnschrift", Font.TRUETYPE_FONT, 60 ));
        operations.add(title, BorderLayout.CENTER) ;

        this.add(operations, BorderLayout.NORTH);
        JPanel settingsGrid = new JPanel(new GridLayout(8,1));

        JPanel saving = new JPanel(new GridLayout(1,3));
        saving.add(new JLabel("Kartei Speicher-Pfad"));
        savePath = new JButton("SpeicherPfad");
        savePath.setActionCommand("path");
        savePath.add(new JPanel());
        saving.add(savePath);

        settingsGrid.add(saving);
        this.add(settingsGrid, BorderLayout.CENTER);


    }
    private static JButton createButton(String text, String imagePath,  int width, int height, int imgWidth, int imgHeight) {
        ImageIcon icon = new ImageIcon(imagePath);

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
        if(mainMenu!=null && mainMenu.getActionListeners().length ==0) {

            this.mainMenu.addActionListener(l);
            this.savePath.addActionListener(l);
        }

    }


}
