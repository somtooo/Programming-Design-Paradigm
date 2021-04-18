package view;

import controller.TotalFeatures;

/** Represents methods to give control over to the controller for menu items. */
public interface MenuInterface {

  /**
   * Gives control over to the controller to handle menu elements listeners.
   *
   * @param controller controller to give control to.
   */
  void setFeatures(TotalFeatures controller);
}
