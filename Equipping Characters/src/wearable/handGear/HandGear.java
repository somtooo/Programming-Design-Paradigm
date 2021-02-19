package wearable.handGear;

import wearable.*;


public class HandGear extends AbstractWearable implements AttackWearable,DefenceWearable {
    private final HandGearName handGearName;
    private  int attackPower;
    private  int defensePower;

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


    @Override
    public boolean equals(Object object) {
        if (object instanceof AbstractWearable) {
            AbstractWearable wearable = (AbstractWearable) object;
            return wearable.isHandGear();
        }
        return false;
    }

    @Override
    public int compareTo(Wearable object) {
        if (object instanceof AbstractWearable) {
            AbstractWearable wearable = (AbstractWearable) object;
            return wearable.compareToHandGear(this);
        }
        throw new ClassCastException("Cant compare of type not Wearable");
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public Boolean isHandGear() {
        return true;
    }

    @Override
    public int compareToHandGear(Wearable object) {
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
