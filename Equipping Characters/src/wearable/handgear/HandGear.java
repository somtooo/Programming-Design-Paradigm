package wearable.handgear;

import wearable.AbstractWearable;
import wearable.AttackWearable;
import wearable.DefenceWearable;
import wearable.Wearable;

/** Represents a HandGear item one of the types of clothing in the role playing game. */
public class HandGear extends AbstractWearable implements AttackWearable, DefenceWearable {
  private final HandGearName handGearName;
  private int attackPower;
  private int defensePower;

  /**
   * Initializes the HandGear with the required parameters.
   *
   * @param handGearName the name of the hand gear.
   * @param description the description of the hand gear.
   * @param wearsOut if the hand gear wears out or not.
   * @param attackPower the attack power of the hand gear
   * @param defensePower the defense power of the hand gear.
   * @throws IllegalArgumentException if the hand gear name is null.
   */
  public HandGear(
      HandGearName handGearName,
      String description,
      Boolean wearsOut,
      int attackPower,
      int defensePower)
      throws IllegalArgumentException {
    super(description, wearsOut);
    checkForNull(handGearName == null, " Null not allowed");
    this.handGearName = handGearName;
    this.attackPower = attackPower;
    this.defensePower = defensePower;
  }

  @Override
  public String getItemName() {
    return handGearName.toString();
  }

  @Override
  public void wearOut() {
    if (wearsOut) {
      if (defensePower > 0) {
        defensePower = defensePower - (int) (defensePower * 0.1);
      } else if (defensePower < 0) {
        defensePower = defensePower + (int) (defensePower * 0.1);
      }

      if (attackPower > 0) {
        attackPower = attackPower - (int) (attackPower * 0.4);
      } else if (attackPower < 0) {
        attackPower = attackPower + (int) (attackPower * 0.4);
      }
    }
  }

  @Override
  public boolean isSameClass(Wearable object) {
    checkForNull(object == null, " Null not allowed");
    if (object instanceof AbstractWearable) {
      AbstractWearable wearable = (AbstractWearable) object;
      return wearable.isHandGear();
    }
    return false;
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
      return wearable.compareToHandGear(this);
    }
    throw new ClassCastException("Cant compare of type not Wearable");
  }

  @Override
  public int getAttackPower() {
    return attackPower;
  }

  /**
   * Checks if the handGear class calls this function.
   *
   * @return true stating that this is the handGear class.
   */
  @Override
  public boolean isHandGear() {
    return true;
  }

  /**
   * Compares same class object to determine which is greater, lesser or equal.
   *
   * @param object the wearable object to be compared.
   * @return an integer stating equality, greatness or less.
   * @throws IllegalArgumentException if object is null.
   */
  @Override
  public int compareToHandGear(Wearable object) {
    if (object == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
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
    return String.format(
        "Name: %s, Description: %s, AttackPower: %s, DefensePower: %s, Wears Out: %s ",
        handGearName, description, attackPower, defensePower, wearsOut);
  }
}
