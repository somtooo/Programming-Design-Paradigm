package controller;

import imagemodel.ImageModelInterface;
import java.io.IOException;
import java.util.List;

/**
 * The controller interface for the ImageModel. Represents methods that have been designed to give
 * control to the controller.
 */
public interface IController {
  void setView();

  /**
   * Method that grants the controller control.
   *
   * @param model the model to use.
   * @throws IOException if appending to out goes wrong.
   * @throws IllegalArgumentException if model is null.
   */
  void start(ImageModelInterface model) throws IOException, IllegalArgumentException;

  /**
   * Processes input gotten from view and tells the view what to do next.
   * @param text the input gotten from the view.
   */
  void processInput(String text);

  void runCommand(Object[] toArray);

  void updateScript(String text);


  void processMouseEvent();

  /**
   * Loads the dmc palette to the view.
   */
  void loadDmc();

  /**
   * Makes the background of the view change color based on what color is selected.
   * @param selectedValue
   */
  void handleColorClick(String selectedValue);

  /**
   * Handles replacing one dmc color with another in an image.
   */
  void replaceColor(String color);

    void removeColorFromImage(String selectedValue);

    void handleMultipleSelection(List<String> selectedValuesList);
}
