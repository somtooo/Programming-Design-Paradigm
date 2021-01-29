package animals;

public class Species {
    private final String speciesName;
    private final TypeOfSpecies speciesType;

    public Species(String speciesName, TypeOfSpecies speciesType) {
        this.speciesName = speciesName;
        this.speciesType = speciesType;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public TypeOfSpecies getSpeciesType() {
        return speciesType;
    }
}
