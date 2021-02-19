package factoryWearable;

import wearable.AttackWearable;
import wearable.DefenceWearable;
import wearable.Wearable;
import wearable.footWear.FootWear;
import wearable.footWear.FootWearName;
import wearable.headGear.HeadGear;
import wearable.headGear.HeadGearName;

public class WearableFactory {
    public AttackWearable createAttackWearable() {
        return new FootWear(FootWearName.BOOTS, "of spear", false, 200);
    }

    public DefenceWearable createDefenseWearable() {
        return new HeadGear(HeadGearName.HATS,"of love", false,100);
    }

}
