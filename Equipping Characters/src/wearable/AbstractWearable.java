package wearable;

/** Represents some functionality of a Jewelry and HandGear item in the role player game. */
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
      if (description == null) {
          throw new IllegalArgumentException(" Null not allowed");
      }
      if (description.trim().equals("")) {
          throw new IllegalArgumentException("Description cant be empty");
      }
    this.description = description;
    this.wearsOut = wearsOut;
  }

  @Override
  public boolean isWearOut() {
    return wearsOut;
  }

    /**
     * Finds out if an object is from the HeadGear class.
     * @return false for objects of other classes.
     */
  public Boolean isHeadGear() {
    return false;
  }

    /**
     * Finds out if an object is from the FootWear class.
     * @return false for objects of other classes.
     */
  public Boolean isFootWear() {
    return false;
  }

    /**
     * Finds out if an object is from the HandGear class.
     * @return false for objects of other classes.
     */
  public Boolean isHandGear() {
    return false;
  }

    /**
     * Finds out if an object is from the Jewelry class.
     * @return false for objects of other classes.
     */
  public Boolean isJewelry() {
    return false;
  }

    /**
     * Compares a wearable object to Head Gear to determine which is greater or lesser.
     * @param object the wearable object to be compared.
     * @return an integer stating HeadGear is greater than all other wearable objects.
     */
  public int compareToHeadGear(Wearable object) {
    return -1;
  }

    /**
     * Compares a wearable object to Foot Wear to determine which is greater or lesser.
     * @param object the wearable object to be compared.
     * @return an integer stating FootWear is greater than all other wearable objects.
     */
  public int compareToFootWear(Wearable object) {
    return -1;
  }

    /**
     * Compares a wearable object to Hand Gear to determine which is greater or lesser.
     * @param object the wearable object to be compared.
     * @return an integer stating HandGear is greater than all other wearable objects.
     */
  public int compareToHandGear(Wearable object) {
    return -1;
  }

    /**
     * Compares a wearable object to Jewelry to determine which is greater or lesser.
     * @param object the wearable object to be compared.
     * @return an integer stating Jewelry is lesser than all other wearable objects.
     */
  public int compareToJewelry(Wearable object) {
    return 1;
  }


  @Override
  public String getItemDescription() {
    return description;
  }
}
