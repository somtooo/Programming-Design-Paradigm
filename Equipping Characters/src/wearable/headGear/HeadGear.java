package wearable.headGear;

import wearable.AbstractWearable;
import wearable.DefenceWearable;
import wearable.Wearable;

/** Represents a head gear item one of the types of clothing in the role playing game. */
public class HeadGear extends AbstractWearable implements DefenceWearable {
  private final HeadGearName headGearName;
  private int defensePower;

  /**
   * Initializes the head gear with the required parameters.
   *
   * @param defensePower the items defensive power.
   * @param headGearName the name of head gear.
   * @param description the description of the head gear.
   * @param wearsOut if the item wears out after use or not.
   * @throws IllegalArgumentException if name is null.
   */
  public HeadGear(
      HeadGearName headGearName, String description, boolean wearsOut, int defensePower) throws IllegalArgumentException {
    super(description, wearsOut);
    if (headGearName == null) {
      throw new IllegalArgumentException("null not allowed");
    }
    this.defensePower = defensePower;
    this.headGearName = headGearName;
  }

  @Override
  public Boolean isHeadGear() {
    return true;
  }

  @Override
  public String getItemName() {
    return headGearName.toString();
  }

  @Override
  public void wearOut() {
    if (defensePower > 0) {
      defensePower = defensePower - (int) (defensePower * 0.2);
    } else {
      defensePower = defensePower + (int) (defensePower * 0.2);
    }
  }

  @Override
  public boolean isSameClass(Wearable object) {
    if (object instanceof AbstractWearable) {
      AbstractWearable wearable = (AbstractWearable) object;
      return wearable.isHeadGear();
    }
    return false;
  }

  //    /**
  //     * Compares this object with the specified object for order.  Returns a
  //     * negative integer, zero, or a positive integer as this object is less
  //     * than, equal to, or greater than the specified object.
  //     * @param object the object to be compared.
  //     * @return a negative integer, zero, or a positive integer as this object
  //     * is less than, equal to, or greater than the specified object.
  //     * @throws NullPointerException if the specified object is null
  //     * @throws ClassCastException   if the specified object's type prevents it
  //     *                              from being compared to this object.
  //     */
  //    @Override
  //    public int compareTo(DefenseWearable object) {
  //        return 0;
  //    }

  @Override
  public int compareTo(Wearable object) {
    if (object instanceof AbstractWearable) {
      AbstractWearable wearable = (AbstractWearable) object;
      return wearable.compareToHeadGear(this);
    }
    throw new ClassCastException("Cant compare of type not Wearable");
  }

  @Override
  public int compareToHeadGear(Wearable object) {
    DefenceWearable defenceWearable = (DefenceWearable) object;
    if (defenceWearable.getDefensePower() > this.getDefensePower()) {
      return 1;
    } else if (defenceWearable.getDefensePower() == this.getDefensePower()) {
      return 0;
    }
    return -1;
  }

  @Override
  public int compareToFootWear(Wearable object) {
    return 1;
  }

  @Override
  public int compareToHandGear(Wearable object) {
    return 1;
  }

  @Override
  public int getDefensePower() {
    return defensePower;
  }

  /**
   * Returns a string representation of the object.
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return String.format("Name: %s, Description: %s, DefensePower: %s, Wears Out: %s ", headGearName,description,defensePower,wearsOut);
  }
}
