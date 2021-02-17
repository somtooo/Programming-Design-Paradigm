package wearable.jewelry;

import wearable.*;


public  class Jewelry extends AbstractWearable implements AttackWearable,DefenceWearable {
    private final JewelryName jewelryName;
    private final int attackPower;
    private  final int defensePower;



    public Jewelry(JewelryName jewelryName, String description, Boolean wearsOut, int attackPower, int defensePower) {
        super(description, wearsOut);
        this.jewelryName = jewelryName;
        this.attackPower = attackPower;
        this.defensePower = defensePower;

    }


    @Override
    public  String getItemName() {
        return jewelryName.toString();
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
