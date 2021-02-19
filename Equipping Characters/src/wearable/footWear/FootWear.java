package wearable.footWear;

import wearable.AbstractWearable;
import wearable.AttackWearable;
import wearable.Wearable;


/**
 * Represents a foot wear item one of the types of clothing in the role playing game.
 */
public  class FootWear extends AbstractWearable implements AttackWearable {
    private  int attackPower;
    private final FootWearName footWearName;



    /**
     * Initializes the head gear with the required parameters.
     * @param attackPower the items defensive power.
     * @param footWearName the name of the footwear.
     * @param description the description of the footwear.
     * @param wearsOut if the item wears out over time or not.
     * @throws IllegalArgumentException if footWearName is null.
     */
    public FootWear(FootWearName footWearName, String description, boolean wearsOut,  int attackPower) throws IllegalArgumentException {
        super(description, wearsOut);
        checkForNull(footWearName == null, " Null not allowed");
        this.attackPower = attackPower;
        this.footWearName = footWearName;
    }



    @Override
    public String getItemName() {
        return footWearName.toString();
    }

    @Override
    public void wearOut() {
    if (wearsOut) {
      if (attackPower > 0) {
        attackPower = attackPower - (int) (attackPower * 0.3);
      } else {
        attackPower = attackPower + (int) (attackPower * 0.3);
      }
        }

    }


    @Override
    public boolean isSameClass(Wearable object) {
        checkForNull(object == null, " Null not allowed");
        if (object instanceof AbstractWearable) {
            AbstractWearable wearable = (AbstractWearable) object;
            return wearable.isFootWear();
        }
        return false;
    }

    /**
     * Compares this object with the specified object for order. Uses double dispatch to return a
     * negative integer, zero, or a positive integer if this object is less
     * than, equal to, or greater than the specified object.
     * @param object the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws IllegalArgumentException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */

    @Override
    public int compareTo(Wearable object) {
        checkForNull(object == null, " Null not allowed");
        if (object instanceof AbstractWearable) {
            AbstractWearable wearable = (AbstractWearable) object;
            return wearable.compareToFootWear(this);
        }
        throw new ClassCastException("Cant compare of type not Wearable");
    }

    /**
     * Checks if the footWear class calls this function.
     * @return true stating that this is the footWear class.
     */
    @Override
    public Boolean isFootWear() {
        return true;
    }

    /**
     * Compares same class object to determine which is greater, lesser or equal.
     * @param object the wearable object to be compared.
     * @return an integer stating equality, greatness or less.
     * @throws IllegalArgumentException if object is null.
     */
    @Override
    public int compareToFootWear(Wearable object) {
        if (object == null) {
            throw new IllegalArgumentException("null not allowed");
        }
        AttackWearable attackWearable = (AttackWearable) object;
        if(attackWearable.getAttackPower() > this.getAttackPower()) {
            return 1;
        } else if (attackWearable.getAttackPower()==this.getAttackPower()){
            return 0;
        }
        return -1;
    }

    /**
     * Overrides the compareToHandGear to enforce FootWear is always bigger.
     *
     * @param object the wearable object to be compared.
     * @return an integer stating footWear is always bigger than handGear.
     * @throws IllegalArgumentException if object is null.
     */
    @Override
    public int compareToHandGear(Wearable object) {
        checkForNull(object == null, " Null not allowed");
        return 1;
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Returns a string representation of the object which represents the values of the object
     * properties.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("Name: %s, Description: %s, AttackPower: %s, Wears Out: %s ", footWearName,description,attackPower,wearsOut);
    }
}
