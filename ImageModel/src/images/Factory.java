package images;


import controller.Controller;
import controller.IController;
import controller.TotalFeatures;
import controller.ViewController;
import imagemodel.ImageModel;
import imagemodel.ImageModelInterface;
import view.*;

/**
 * Represent methods that create view objects.
 */
public class Factory {
    private static ImageModelInterface model;

    /**
     * Default constructor
     */
    public Factory(){
        model = new ImageModel();
    }


    /**
     * Creates the main view object.
     * @return the main view object.
     */
    public  ViewInterface createView() {
        ViewInterface view = new MainView();
        TotalFeatures mainController = new ViewController(model, view);
        model.attach(mainController);
        mainController.setView();
        return view;
    }

    /**
     * Creates the bash view object.
     * @return the bash view object.
     */
    public SecondaryViewInterface createBashView() {
        SecondaryViewInterface bashView = new SecondaryView();
        IController bashController = new Controller(model, bashView);
        bashController.setView();
        return bashView;
    }

    /**
     * Creates the color picker view object.
     * @return the color picker view object.
     */
    public SecondaryViewInterface createColorPickerView() {
        SecondaryViewInterface colorPicker = new ColorPicker();
        IController colorPickerController = new Controller(model, colorPicker);
        colorPickerController.setView();
        return  colorPicker;
    }

}
