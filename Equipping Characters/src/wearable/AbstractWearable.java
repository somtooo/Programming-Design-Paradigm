package wearable;

import java.util.Map;

/**
 * Represents some functionality of a Jewelry and HandGear item in the role player game.
 */
public abstract class AbstractWearable implements Wearable {
    protected final String description;
    protected final Boolean wearsOut;

    /**
     *
     * Initializes the head gear with the required parameters.
     * @param description the description of the head gear.
     * @param wearsOut if the item wears out after use or not.
     * @throws IllegalArgumentException if description is empty.
     */
    protected AbstractWearable(String description, Boolean wearsOut) throws IllegalArgumentException {
        this.description = description;
        this.wearsOut = wearsOut;
    }

    public int getAttackPower() {
        return 0;
    }

    public int getDefensePower() {
        return 0;
    }

    @Override
    public String getItemDescription() {
        return description;
    }
}
