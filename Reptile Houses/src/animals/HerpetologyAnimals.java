package animals;

public final class HerpetologyAnimals implements Animals {
    private final Species species;
    private final PhysicalCharacteristics physicalCharacteristics;
    private final PersonalFeatures personalFeatures;

    public HerpetologyAnimals(Species species, PhysicalCharacteristics physicalCharacteristics, PersonalFeatures personalFeatures) {
        this.species = species;
        this.physicalCharacteristics = physicalCharacteristics;
        this.personalFeatures = personalFeatures;
    }



    @Override
    public Species getSpecies() {
        return species;
    }


    @Override
    public PhysicalCharacteristics getPhysicalCharacteristics() {
        return physicalCharacteristics;
    }


    @Override
    public PersonalFeatures getPersonalFeatures() {
        return personalFeatures;
    }

    @Override
    public String toString() {
        throw new IllegalCallerException();
    }
}
