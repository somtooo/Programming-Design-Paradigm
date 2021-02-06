package animals;

/** Creates an object that tracks a species name, type and definingCharacteristics. */
public class Species implements SpeciesInterface {
  private final String speciesName;
  private final TypeOfSpecies speciesType;
  private final String definingCharacteristics;

  /**
   * Constructs a species with the specified parameters.
   *
   * @param speciesName identify species.
   * @param speciesType identify if reptile or amphibian.
   * @param definingCharacteristics special feature of this species.
   * @throws IllegalArgumentException if the speciesName is an empty string and if
   *        any other values are null.
   */
  public Species(String speciesName, TypeOfSpecies speciesType, String definingCharacteristics)
      throws IllegalArgumentException {
    if (speciesName == null || speciesType == null || definingCharacteristics == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
    if (speciesName.trim().isEmpty()) {
      throw new IllegalArgumentException("Species Name can't be empty");
    }
    this.speciesName = speciesName;
    this.speciesType = speciesType;
    this.definingCharacteristics = definingCharacteristics;
  }

  @Override
  public String getSpeciesName() {
    return speciesName;
  }

  @Override
  public TypeOfSpecies getSpeciesType() {
    return speciesType;
  }

  /**
   * Gets important details about the class.
   *
   * @return a String representation of the Species Object fields.
   */
  @Override
  public String toString() {
    return String.format(
        "Species: %s, Type: %s, Defining Characteristics: %s",
        speciesName, speciesType, definingCharacteristics);
  }

  @Override
  public String getDefiningCharacteristics() {
    return definingCharacteristics;
  }
}
