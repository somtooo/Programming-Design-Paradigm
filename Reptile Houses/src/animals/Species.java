package animals;

/** Creates an object that tracks a species name and type. */
public class Species {
  private final String speciesName;
  private final TypeOfSpecies speciesType;
  private final String definingCharacteristics;

  /**
   * Constructs a species with the specified parameters.
   *
   * @param speciesName identify species.
   * @param speciesType identify if reptile or amphibian.
   * @param definingCharacteristics special feature of this species.
   */
  public Species(String speciesName, TypeOfSpecies speciesType, String definingCharacteristics)
      throws IllegalArgumentException {
    this.definingCharacteristics = definingCharacteristics;
    if (speciesName.trim().isEmpty())
      throw new IllegalArgumentException("Species Name can't be empty");
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

  /**
   * Gives important details about the class.
   *
   * @return String
   */
  @Override
  public String toString() {
    return String.format(
        "Species: %s, Type: %s, Defining Characteristics: %s",
        speciesName, speciesType, definingCharacteristics);
  }

  public String getDefiningCharacteristics() {
    return definingCharacteristics;
  }
}
