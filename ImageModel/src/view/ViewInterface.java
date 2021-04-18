package view;

import controller.TotalFeatures;
import java.awt.Color;
import java.awt.image.BufferedImage;

/** Represents methods that handle the main views elements. */
public interface ViewInterface {

  /**
   * Sets the main views Image Panel element with an Image.
   *
   * @param image the image to set the Image panel with.
   */
  void setImagePanel(BufferedImage image);

  /**
   * Gets the filename of the image to load.
   *
   * @return the image file name.
   */
  String getImage();

  /** Starts the main view and shows it to the screen. */
  void start();

  /**
   * Gives the controller of the main views elements action listeners.
   *
   * @param controller the controller to give control to.
   */
  void setFeatures(TotalFeatures controller);

  /**
   * Changes the focus of the slider to apply the Blur effect this is controlled by the controller.
   *
   * @param controller the controller that sets the focus.
   * @param min the min value determined by the controller.
   * @param max the maxvalue determined by hte controller.
   */
  void setSliderListenerToBlur(TotalFeatures controller, int min, int max);

  /**
   * Changes the focus of the slider to apply the Sharpen effect this is controlled by the
   * controller.
   *
   * @param controller the controller that sets the focus.
   * @param min the min value determined by the controller.
   * @param max the maxvalue determined by hte controller.
   */
  void setSliderListenerToSharpen(TotalFeatures controller, int min, int max);

  /**
   * Changes the focus of the slider to apply the Pixelate effect this is controlled by the
   * controller.
   *
   * @param controller the controller that sets the focus.
   * @param min the min value determined by the controller.
   * @param max the maxvalue determined by hte controller.
   */
  void setSliderListenerToPixelate(TotalFeatures controller, int min, int max);

  /**
   * Changes the focus of the slider to apply the Mosaic effect this is controlled by the
   * controller.
   *
   * @param controller the controller that sets the focus.
   * @param min the min value determined by the controller.
   * @param max the maxvalue determined by hte controller.
   */
  void setSliderListenerToMosaic(TotalFeatures controller, int min, int max);

  /**
   * Changes the focus of the slider to apply the reduce effect this is controlled by the
   * controller.
   *
   * @param controller the controller that sets the focus.
   * @param min the min value determined by the controller.
   * @param max the maxvalue determined by hte controller.
   */
  void setSliderListenerToReduce(TotalFeatures controller, int min, int max);

  /**
   * Gets the current value of the slider.
   *
   * @return the current value of the slider as an int.
   */
  int getSliderValue();

  /** Sets the slider visibility to false. */
  void hideSlider();

  void setScrollable(TotalFeatures controller);

  /**
   * Adds information on the JPanel in the main view.
   *
   * @param info the information to add.
   * @param colorOfInfo the color to set the JPanel too.
   */
  void addToInfo(String info, Color colorOfInfo);

  /** Makes visible the BatchView view in the main view. */
  void initiateBatchView();

  /**
   * Shows an informational message to the client and requests for input.
   *
   * @param message the message.
   * @param command the request message for the input.
   * @return the input from the user.
   */
  String getInputDialog(String message, String command);

  /**
   * Throws an error to the user.
   *
   * @param error the error message.
   * @param errorType the type of error message.
   */
  void throwError(String error, String errorType);

  /**
   * Gets the name of a file the user wants to save.
   *
   * @return the name of the file.
   */
  String getImageSaveName();

  /**
   * Throws a successes message to the user.
   *
   * @param success the message.
   * @param successType the type of success.
   */
  void throwSuccess(String success, String successType);

  /** Gives control to the controller to handle the scrollable view element listeners.
   *
   * @param viewController the controller to give control too.
   * */
  void showDmcDialog(TotalFeatures viewController);

  /**
   * Clears the JPanel info so new info can be set.
   */
  void clearInfo();

  /**
   * Clears the input string of the color picker view.
   */
  void clearInputString();

  /**
   * Adds an element to the color picker view's JList.
   *
   * @param dmcValue the element to add.
   */
  void setAddToList(String dmcValue);

  /**
   * Gets all the elements in the color picker views JList.
   * @return an array that contains the elements.
   */
  int[] getListElementColor();

  /**
   * Sets the color pickers view JList elements color.
   *
   * @param color the color to set the JList element with.
   */
  void setListElementColor(Color color);

  /**
   * Sets the color pickers JList color.
   *
   * @param red the red color.
   * @param green the green color value.
   * @param blue the blue color value.
   */
  void setListColor(int red, int green, int blue);
}
