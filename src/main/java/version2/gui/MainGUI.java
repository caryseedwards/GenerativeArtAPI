package version2.gui;

import version2.gui.controller.ArtworkGUIController;
import version2.gui.model.ParametersModel;
import version2.gui.view.ArtworkGUIView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Entry point to the program for Version 3.
 * @author carysedwards
 */
public class MainGUI {
    public static void main(String[] args) {
        ParametersModel model = new ParametersModel();
        ArtworkGUIView view = new ArtworkGUIView();
        ArtworkGUIController artworkGUIController = new ArtworkGUIController(view, model);
        view.setupViewWindow();
        view.getFrame().setVisible(true);
        view.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                view.getFrame().dispose();
            }
        });
    }
}
