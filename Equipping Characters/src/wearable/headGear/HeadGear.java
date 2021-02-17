package wearable.headGear;

import wearable.AbstractWearable;
import wearable.DefenceWearable;
import wearable.Wearable;



/**
 * Represents a head gear item one of the types of clothing in the role playing game.
 */
public  class HeadGear extends AbstractWearable implements DefenceWearable {
    private final int defensePower;
    private final HeadGearName headGearName;


    /**
     * Initializes the head gear with the required parameters.
     * @param defensePower the items defensive power.
     * @param headGearName the name of head gear.
     * @param description the description of the head gear.
     * @param wearsOut if the item wears out after use or not.
     */
    public HeadGear( HeadGearName headGearName, String description, Boolean wearsOut,  int defensePower) {
        super(description, wearsOut);
        this.defensePower = defensePower;
        this.headGearName = headGearName;
    }




    @Override
    public String getItemName() {
        return headGearName.toString();
    }

    @Override
    public void boostPower() {

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
    public int compareTo(Wearable o) {
        return 0;
    }

    @Override
    public int getDefensePower() {
        return defensePower;
    }
}
