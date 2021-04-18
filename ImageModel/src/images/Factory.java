package images;

import controller.Controller;
import controller.IController;
import controller.TotalFeatures;
import controller.ViewController;
import imagemodel.ImageModel;
import imagemodel.ImageModelInterface;
import view.ColorPicker;
import view.MainView;
import view.SecondaryView;
import view.SecondaryViewInterface;
import view.ViewInterface;

/** Represent methods that create view objects. */
public class Factory {
  /*
  Static so the same model can be controlled by different controllers.
   */
  private static ImageModelInterface model;

  /** Default constructor. Creates the model. */
  public Factory() {
    model = new ImageModel();
  }

  /**
   * Creates the main view object.
   *
   * @return the main view object.
   */
  public ViewInterface createView() {
    ViewInterface view = new MainView();
    TotalFeatures mainController = new ViewController(model, view);
    model.attach(mainController);
    mainController.setView();
    return view;
  }

  /**
   * Creates the bash view object.
   *
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
   *
   * @return the color picker view object.
   */
  public SecondaryViewInterface createColorPickerView() {
    SecondaryViewInterface colorPicker = new ColorPicker();
    IController colorPickerController = new Controller(model, colorPicker);
    colorPickerController.setView();
    return colorPicker;
  }
}
