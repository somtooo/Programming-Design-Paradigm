package animals;

public class PersonalFeatures {
    private final Boolean isPoisonous;
    private final Danger danger;


    public PersonalFeatures(Boolean isPoisonous, Danger danger) {
        this.isPoisonous = isPoisonous;
        this.danger = danger;

    }

    public Danger getDangerState() {
        return danger;
    }


    public Boolean getPoisonous() {
        return isPoisonous;
    }
}
