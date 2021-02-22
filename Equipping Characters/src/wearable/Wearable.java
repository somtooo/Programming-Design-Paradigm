package wearable;

/** This interface represents different types of Gears in a role playing game. */
public interface Wearable extends Comparable<Wearable> {

  /**
   * Gets the item Name.
   *
   * @return a String that holds the name of the item.
   */
  String getItemName();

  /**
   * Gets the item description.
   *
   * @return a String that holds the description of the item.
   */
  String getItemDescription();

  /** Temporarily boosts the attack or defence power of an item. */
  void wearOut();

  /**
   * Finds out if an Item wears out after use.
   *
   * @return returns a boolean true or false if the item is wears out or not.
   */
  boolean isWearOut();

  /**
   * Checks if the object passed in is from the same class as the function caller.
   *
   * @param object the object to compare
   * @return a boolean stating if its the same or not
   * @throws IllegalArgumentException if object is null.
   */
  boolean isSameClass(Wearable object) throws IllegalArgumentException;
}
