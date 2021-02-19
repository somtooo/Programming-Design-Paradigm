package character;

import wearable.Wearable;

public interface CharacterInterFace {
    int getHitPoints();

    int getAttackPower();

    int getDefensePower();

    String getName();

    void addToHeadSlot(Wearable wearable);

    void addToFeetSlot(Wearable wearable);

    void addToHandSlot(Wearable wearable);

    void calculateTotalAttackAndDefense();

    void addToNeckSlot(Wearable wearable);

    void wearOutWearableItems();
}
