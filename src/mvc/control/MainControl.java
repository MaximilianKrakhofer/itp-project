package mvc.control;

import mvc.model.MainModel;
import mvc.view.MasterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainControl {

    MasterView view;
    MainModel model;
    public MainControl(MasterView view, MainModel model) {
        this.view = view;
        this.model = model;
    }
    public static void main(String[] args) {
        MasterView view = new MasterView();
        MainModel model = new MainModel();
        new MainControl(view, model);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
