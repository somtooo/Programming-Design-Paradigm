package character;

import wearable.Wearable;

/**
 * Represents functionality for a character in an rpg game.
 */
public interface CharacterInterFace {

    /**
     * Gets the characters total hit points
     * @return an int which is the hit point of the character
     */
    int getHitPoints();

    /**
     * Gets the characters total attack power.
     * @return an int the attack power of the character.
     */
    int getAttackPower();

    /**
     * Gets the characters total defense power.
     * @return an int the defense power of the character.
     */
    int getDefensePower();

    /**
     * Gets the characters name.
     * @return a string which is the name of the character.
     */
    String getName();

    /**
     * Adds an item to the head of a character.
     * @param wearable the item that's going to be added.
     * @throws IllegalArgumentException if the item is null.
     */
    void addToHeadSlot(Wearable wearable) throws IllegalArgumentException;

    /**
     * Adds an item to the feet of a character.
     * @param wearable the item that's going to be added.
     * @throws IllegalArgumentException if the item is null.
     */
    void addToFeetSlot(Wearable wearable) throws IllegalArgumentException;

    /**
     * Adds an item to the hand of a character.
     * @param wearable the item that's going to be added.
     * @throws IllegalArgumentException if the item is null.
     */
    void addToHandSlot(Wearable wearable) throws IllegalArgumentException;


    /**
     * Calculates the total attack and defense power of a character,
     * this should be called after every equip.
     */
    void calculateTotalAttackAndDefense();

    /**
     * Adds an item to the hand of a character.
     * @param wearable the item that's going to be added.
     * @throws IllegalArgumentException if the item is null.
     */
    void addToNeckSlot(Wearable wearable) throws IllegalArgumentException;

    /**
     * Wears out items that isWear is true if the item is not
     * an item that wears out then it does nothing.
     */
    void wearOutWearableItems();
}
