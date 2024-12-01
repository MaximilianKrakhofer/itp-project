package mvc.control;

import mvc.model.FragenverwaltungModel;
import mvc.view.FragenverwaltungView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FragenverwaltungControl {
    private FragenverwaltungView view;
    private FragenverwaltungModel model;
    private MasterController masterController;

    public FragenverwaltungControl(MasterController masterController) {
        this.masterController = masterController;
        this.model = new FragenverwaltungModel();
        this.view = new FragenverwaltungView();
        view.getMainMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                masterController.showMainMenu();
            }
        });

    }
    public JPanel getView() {
        return view;
    }
}
