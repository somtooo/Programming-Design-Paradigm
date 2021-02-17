package character;

import wearable.Wearable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character {
    private final String nameOfCharacter;
    private final Map<CharacterSlots, List<Wearable>> gearDescription;
    private final int hitPoints;
    private final int attackPower;
    private final int defensePower;

    public Character(String nameOfCharacter) {
        this.nameOfCharacter = nameOfCharacter;
        gearDescription = new HashMap<>();
        hitPoints = 0;
        attackPower = 0;
        defensePower = 0;
    }

    public void equipCharacter(List<Wearable> chest) {

    }
}
