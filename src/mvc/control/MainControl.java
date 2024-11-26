package mvc.control;

import com.sun.tools.javac.Main;
import mvc.model.MainModel;
import mvc.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainControl {

    MainView view;
    MainModel model;
    public MainControl(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }
    public static void main(String[] args) {
        MainView view = new MainView();
        MainModel model = new MainModel();
        new MainControl(view, model);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
