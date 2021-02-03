package animals;

/** Creates an object that tracks a species name and type. */
public class Species {
  private final String speciesName;
  private final TypeOfSpecies speciesType;

  /**
   * Constructs a species with the specified parameters.
   *
   * @param speciesName identify species.
   * @param speciesType identify if reptile or amphibian.
   */
  public Species(String speciesName, TypeOfSpecies speciesType) {
    this.speciesName = speciesName;
    this.speciesType = speciesType;
  }

  /**
   * Gets the species name.
   *
   * @return String
   */
  public String getSpeciesName() {
    return speciesName;
  }

  /**
   * Gets the type of species.
   *
   * @return TypeOsSpecies enum
   */
  public TypeOfSpecies getSpeciesType() {
    return speciesType;
  }
}
