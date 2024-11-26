package mvc.control;
import mvc.model.FragenverwaltungModel;
import mvc.view.FragenverwaltungView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FragenverwaltungControl {

    FragenverwaltungView view;
    FragenverwaltungModel model;
    public FragenverwaltungControl() {
        FragenverwaltungView view = new FragenverwaltungView("Fragenverwaltung");
        this.view = view;
        this.model = new FragenverwaltungModel();
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
