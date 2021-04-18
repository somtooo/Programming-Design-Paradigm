package view;

import controller.TotalFeatures;
import java.awt.Color;

/**
 * A window that helps the user to perform color operations on a crossed stitched image.
 */
public interface ColorPickerInterface {

  /**
   * Gives control to the controller to handle its elements listeners.
   *
   * @param controller the controller to give control too.
   */
  void resetFeatures(TotalFeatures controller);

  void start();

  /**
   * Sets the color of the Jlist.
   *
   * @param red the red color value.
   * @param green the green color value.
   * @param blue the blue color value.
   */
  void setListColor(int red, int green, int blue);

  /**
   * Gets the color of the views JList.
   *
   * @return the color values.
   */
  int[] getListElementColor();

  /**
   * Sets the Jlist Element colors.
   *
   * @param color the color to use to set.
   */
  void setListElementColor(Color color);

  /**
   * Adds an element to the views Jlist.
   *
   * @param command the element to add.
   */
  void setAddToList(String command);

  /** Clears the input string for the view JTextField element. */
  void clearInputString();
}
