package wearable.jewelry;

import wearable.AbstractWearable;
import wearable.AttackWearable;
import wearable.DefenceWearable;
import wearable.Wearable;


/** Represents a Jewelry item one of the types of clothing in the role playing game. */
public class Jewelry extends AbstractWearable implements AttackWearable, DefenceWearable {
  private final JewelryName jewelryName;
  private int attackPower;
  private int defensePower;

    /**
     * Initializes the Jewelry with the required parameters.
     * @param jewelryName the name of hte jewelry.
     * @param description the jewelry description.
     * @param wearsOut if the jewelry wears out or not.
     * @param attackPower the jewelry attack power.
     * @param defensePower the jewelry defense power.
     * @throws IllegalArgumentException if the jewelry name is null.
     */
  public Jewelry(
      JewelryName jewelryName,
      String description,
      boolean wearsOut,
      int attackPower,
      int defensePower) throws IllegalArgumentException {
    super(description, wearsOut);
    checkForNull(jewelryName == null, " Null not allowed");
    this.jewelryName = jewelryName;
    this.attackPower = attackPower;
    this.defensePower = defensePower;
  }

  @Override
  public String getItemName() {
    return jewelryName.toString();
  }

  @Override
  public void wearOut() {
    if (wearsOut) {
      if (defensePower > 0) {
        defensePower = defensePower - (int) (defensePower * 0.2);
      } else if (defensePower < 0) {
        defensePower = defensePower + (int) (defensePower * 0.2);
      }

      if (attackPower > 0) {
        attackPower = attackPower - (int) (attackPower * 0.3);
      } else if (attackPower < 0) {
        attackPower = attackPower + (int) (attackPower * 0.3);
      }
    }
  }

  @Override
  public boolean isSameClass(Wearable object) {
    checkForNull(object == null, " Null not allowed");
      if (object instanceof AbstractWearable) {
          AbstractWearable wearable = (AbstractWearable) object;
          return wearable.isJewelry();
      }
      return false;
  }



  /**
   * Checks if the jewelry class calls this function.
   * @return true stating that this is the jewelry class.
   */
  @Override
  public Boolean isJewelry() {
    return true;
  }

  /**
   * Compares this object with the specified object for order. Uses double dispatch to return a
   * negative integer, zero, or a positive integer if this object is less than, equal to, or greater
   * than the specified object.
   *
   * @param object the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   *     or greater than the specified object.
   * @throws IllegalArgumentException if the specified object is null
   * @throws ClassCastException if the specified object's type prevents it from being compared to
   *     this object.
   */
  @Override
  public int compareTo(Wearable object) {
    checkForNull(object == null, " Null not allowed");
    if (object instanceof AbstractWearable) {
      AbstractWearable wearable = (AbstractWearable) object;
      return wearable.compareToJewelry(this);
    }
    throw new ClassCastException("Cant compare of type not Wearable");
  }

  @Override
  public int getAttackPower() {
    return attackPower;
  }

  /**
   * Compares same class object to determine which is greater, lesser or equal.
   * @param object the wearable object to be compared.
   * @return an integer stating equality, greatness or less.
   * @throws IllegalArgumentException if object is null.
   */
  @Override
  public int compareToJewelry(Wearable object) {
    checkForNull(object == null, " Null not allowed");
    AttackWearable attackWearable = (AttackWearable) object;
    if (attackWearable.getAttackPower() > this.getAttackPower()) {
      return 1;
    } else if (attackWearable.getAttackPower() == this.getAttackPower()) {
      return 0;
    }
    return -1;
  }

  @Override
  public int getDefensePower() {
    return defensePower;
  }

  /**
   * Returns a string representation of the object which represents the values of the object
   * properties.
   *
   * @return a string representation of the object.
   */
    @Override
    public String toString() {
        return String.format("Name: %s, Description: %s, AttackPower: %s, DefensePower: %s, Wears Out: %s ", jewelryName,description,attackPower,defensePower,wearsOut);
    }
}
