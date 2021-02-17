package wearable;


import java.util.Map;

/**
 * This interface represents a Jewelry and a Hand Gear item in a role playing game.
 */
public interface Wearable extends Comparable<Wearable> {

    /**
     * Gets the item Name
     * @return a String that holds the name of the item.
     */
    String getItemName();

    /**
     * Gets the item description.
     * @return a String that holds the description of the item.
     */
    String getItemDescription();

    /**
     * Temporarily boosts the attack or defence power of an item.
     */
    void boostPower();







}
