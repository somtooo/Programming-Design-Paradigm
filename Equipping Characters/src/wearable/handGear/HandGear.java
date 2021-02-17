package wearable.handGear;

import wearable.*;


public class HandGear extends AbstractWearable implements AttackWearable,DefenceWearable {
    private final HandGearName handGearName;
    private final int attackPower;
    private  final int defensePower;

    public HandGear(HandGearName handGearName, String description, Boolean wearsOut, int attackPower, int defensePower) {
        super(description, wearsOut);
        this.handGearName = handGearName;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }


    @Override
    public String getItemName() {
        return handGearName.toString();
    }

    @Override
    public void boostPower() {

    }

    @Override
    public int compareTo(Wearable o) {
        return 0;
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public int getDefensePower() {
        return defensePower;
    }


}
