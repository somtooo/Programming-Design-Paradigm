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
    if (jewelryName == null) {
      throw new IllegalArgumentException("null not allowed");
    }
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
    if (defensePower > 0) {
      defensePower = defensePower - (int) (defensePower * 0.2);
    } else if (attackPower > 0) {
      attackPower = attackPower - (int) (attackPower * 0.3);
    }
  }

  @Override
  public boolean isSameClass(Wearable object) {
      if (object instanceof AbstractWearable) {
          AbstractWearable wearable = (AbstractWearable) object;
          return wearable.isJewelry();
      }
      return false;
  }



  @Override
  public Boolean isJewelry() {
    return true;
  }

  @Override
  public int compareTo(Wearable object) {
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

  @Override
  public int compareToJewelry(Wearable object) {
    AttackWearable defenceWearable = (AttackWearable) object;
    if (defenceWearable.getAttackPower() > this.getAttackPower()) {
      return 1;
    } else if (defenceWearable.getAttackPower() == this.getAttackPower()) {
      return 0;
    }
    return -1;
  }

  @Override
  public int getDefensePower() {
    return defensePower;
  }
}
