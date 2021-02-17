package wearable.footWear;

import wearable.AbstractWearable;
import wearable.AttackWearable;
import wearable.Wearable;



/**
 * Represents a foot wear item one of the types of clothing in the role playing game.
 */
public  class FootWear extends AbstractWearable implements AttackWearable {
    private final int attackPower;
    private final FootWearName footWearName;


    /**
     * Initializes the head gear with the required parameters.
     * @param attackPower the items defensive power.
     * @param footWearName the name of the footwear.
     * @param description the description of the footwear.
     * @param wearsOut if the item wears out over time or not.
     */
    public FootWear(FootWearName footWearName, String description, Boolean wearsOut,  int attackPower) {
        super(description, wearsOut);
        this.attackPower = attackPower;
        this.footWearName = footWearName;
    }



    @Override
    public String getItemName() {
        return footWearName.toString();
    }

    @Override
    public void boostPower() {

    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * @param object the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */

    @Override
    public int compareTo(Wearable object) {
        return 0;
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }
}
