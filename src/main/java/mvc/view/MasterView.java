package mvc.view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import mvc.model.SettingsModel;
import javax.swing.*;
import java.awt.*;
import java.io.Console;

public class MasterView {
    private JFrame frame;
    public MasterView() {

        frame = new JFrame("LearnITP");
        frame.setMinimumSize(new Dimension(850, 600));
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.pack();

    }
    public static void setupTheme() {
        String theme = SettingsModel.getConfig("theme");
        System.out.println(theme);
        try{
        if (theme == null || theme.isEmpty()) {
            FlatIntelliJLaf.setup();
        } else {
            switch (theme) {
                case"FlatDraculaIJTheme":
                    FlatDraculaIJTheme.setup();
                    break;
                case "FlatDarculaLaf":
                    FlatDarculaLaf.setup();
                    break;
                case "FlatMacLightLaf":
                    FlatMacLightLaf.setup();
                    break;
                case "FlatMacDarkLaf":
                    FlatMacDarkLaf.setup();
                    break;
                case "FlatCarbonIJTheme":
                    FlatCarbonIJTheme.setup();
                    break;
                case "FlatHiberbeeDarkIJTheme":
                    FlatHiberbeeDarkIJTheme.setup();
                    break;
                case "FlatMonokaiProIJTheme":
                    FlatMonokaiProIJTheme.setup();
                    break;
                case "FlatNordIJTheme":
                    FlatNordIJTheme.setup();
                    break;
                case "FlatOneDarkIJTheme":
                    FlatOneDarkIJTheme.setup();
                    break;
                case "FlatGitHubIJTheme":
                    FlatGitHubIJTheme.setup();
                    break;
                case "FlatGitHubDarkIJTheme":
                    FlatGitHubDarkIJTheme.setup();
                    break;
                case "FlatMoonlightIJTheme":
                    FlatMoonlightIJTheme.setup();
                    break;
                case "FlatMaterialPalenightIJTheme":
                    FlatMaterialPalenightIJTheme.setup();
                    break;
                case "FlatMaterialDeepOceanIJTheme":
                    FlatMaterialDeepOceanIJTheme.setup();
                    break;
                case "FlatMaterialOceanicIJTheme":
                    FlatMaterialOceanicIJTheme.setup();
                    break;
                case "FlatNightOwlIJTheme":
                    FlatNightOwlIJTheme.setup();
                    break;
                default:
                    System.out.println("Unbekannt");
                    FlatIntelliJLaf.setup();
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        for (Window window : Window.getWindows()) { // um das Theme im Aktuellen Fenster zu aktualisieren
            SwingUtilities.updateComponentTreeUI(window);
            window.revalidate();
            window.repaint();
        }
    }
    public void repaint() {

    }
    public void updateContent(JPanel newContent) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(newContent);
        System.out.println("UpdateCOntent" + newContent.getName());
        frame.revalidate();
        frame.repaint();
    }

}