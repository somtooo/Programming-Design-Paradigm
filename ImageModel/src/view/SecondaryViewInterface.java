package view;

import controller.IController;
import java.awt.Color;

/** Represents methods used to operate the second view in the GUI application. */
public interface SecondaryViewInterface {

  /** Starts the secondary view, by making the Frame visible. */
  void start();



  /**
   * Gives the controller control over its element listeners.
   *
   * @param controller the controller to give control too.
   */
  void setFeatures(IController controller);

  /** Clears the input string for the view JTextField element. */
  void clearInputString();

  /** Clears the list screen for the views JList element. */
  void clearListScreen();

  /**
   * Adds an element to the views Jlist.
   *
   * @param command the element to add.
   */
  void setAddToList(String command);

  /**
   * Throws a success message when an action has been accomplished.
   *
   * @param success the message.
   * @param successType the message type.
   */
  void throwSuccess(String success, String successType);

  /**
   * Updates the views JList element.
   *
   * @param command the element to use to update.
   * @param index the index of the JlIST to update.
   */
  void updateList(String command, int index);

  /**
   * Gets the input value from the views JTextfield.
   *
   * @return the input value.
   */
  String getInputValue();

  /**
   * Sets the string value of the views JTextField.
   *
   * @param text the string to use to set.
   */
  void setInputString(String text);

  /**
   * Gets the selected index of the Jlist .
   *
   * @return the selected index.
   */
  int getIndex();


}
