package wearable.jewelry;

import wearable.*;

import java.util.HashMap;


public  class Jewelry extends AbstractWearable implements AttackWearable,DefenceWearable {
    private final JewelryName jewelryName;
    private  int attackPower;
    private   int defensePower;



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
    public void wearOut() {
        if (defensePower > 0) {
            defensePower = defensePower - (int) (defensePower * 0.2);
        } else if (attackPower >0) {
            attackPower = attackPower - (int) (attackPower*0.3);
        }
    }

    @Override
    public boolean isSameClass(Wearable object) {
        return false;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * @param object the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object object) {
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
        if(defenceWearable.getAttackPower() > this.getAttackPower()) {
            return 1;
        } else if (defenceWearable.getAttackPower()==this.getAttackPower()){
            return 0;
        }
        return -1;
    }

    @Override
    public int getDefensePower() {
        return defensePower;
    }


}
