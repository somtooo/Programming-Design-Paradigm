package wearable;

import java.util.ArrayList;
import java.util.List;

/** Represents some functionality of different types of gears in the role player game. */
public abstract class AbstractWearable implements Wearable {
  protected final String description;
  protected final boolean wearsOut;

  /**
   * Initializes the head gear with the required parameters.
   *
   * @param description the description of the head gear.
   * @param wearsOut if the item wears out after use or not.
   * @throws IllegalArgumentException if description is empty or null.
   */
  protected AbstractWearable(String description, boolean wearsOut) throws IllegalArgumentException {
    checkForNull(description == null, " Null not allowed");
    checkForNull(description.trim().equals(""), "Description cant be empty");
    this.description = description;
    this.wearsOut = wearsOut;
  }

  @Override
  public boolean isWearOut() {
    return wearsOut;
  }

  /**
   * Finds out if an object is from the HeadGear class.
   *
   * @return false for objects of other classes.
   */
  public boolean isHeadGear() {
    return false;
  }

  /**
   * Finds out if an object is from the FootWear class.
   *
   * @return false for objects of other classes.
   */
  public boolean isFootWear() {
    return false;
  }

  /**
   * Finds out if an object is from the HandGear class.
   *
   * @return false for objects of other classes.
   */
  public boolean isHandGear() {
    return false;
  }

  /**
   * Finds out if an object is from the Jewelry class.
   *
   * @return false for objects of other classes.
   */
  public boolean isJewelry() {
    return false;
  }

  /**
   * Compares a wearable object to Head Gear to determine which is greater or lesser.
   *
   * @param object the wearable object to be compared.
   * @return an integer stating HeadGear is greater than all other wearable objects.
   * @throws IllegalArgumentException if object is null.
   */
  public int compareToHeadGear(Wearable object) {
    checkForNull(object == null, "Null not allowed");
    return -1;
  }

  /**
   * Compares a wearable object to Foot Wear to determine which is greater or lesser.
   *
   * @param object the wearable object to be compared.
   * @return an integer stating FootWear is greater than all other wearable objects.
   * @throws IllegalArgumentException if object is null.
   */
  public int compareToFootWear(Wearable object) {
    checkForNull(object == null, "Null not allowed");
    return -1;
  }

  /**
   * Compares a wearable object to Hand Gear to determine which is greater or lesser.
   *
   * @param object the wearable object to be compared.
   * @return an integer stating HandGear is greater than all other wearable objects.
   * @throws IllegalArgumentException if object is null.
   */
  public int compareToHandGear(Wearable object) {
    checkForNull(object == null, "Null not allowed");
    return -1;
  }

  /**
   * Compares a wearable object to Jewelry to determine which is greater or lesser.
   *
   * @param object the wearable object to be compared.
   * @return an integer stating Jewelry is lesser than all other wearable objects.
   * @throws IllegalArgumentException if object is null.
   */
  public int compareToJewelry(Wearable object) {
    checkForNull(object == null, "Null not allowed");
    return 1;
  }

  /**
   * Checks if object passed is a null or empty.
   *
   * @param isTrue if the expression true or false
   * @param message the message to give the user.
   */
  protected void checkForNull(boolean isTrue, String message) {
    if (isTrue) {
      throw new IllegalArgumentException(message);
    }
  }


  @Override
  public String getItemDescription() {
    return description;
  }
}
