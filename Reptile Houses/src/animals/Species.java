package animals;

/** Creates an object that tracks a species name, type and definingCharacteristics. */
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
   * @throws IllegalArgumentException if the speciesName is an empty string.
   */
  public Species(String speciesName, TypeOfSpecies speciesType, String definingCharacteristics)
      throws IllegalArgumentException {
    this.definingCharacteristics = definingCharacteristics;
    if (speciesName.trim().isEmpty()) {
      throw new IllegalArgumentException("Species Name can't be empty");
    }
    this.speciesName = speciesName;
    this.speciesType = speciesType;
  }

  /**
   * Gets the species name.
   *
   * @return a String that contains the name of the Species object.
   */
  public String getSpeciesName() {
    return speciesName;
  }

  /**
   * Gets the type of species.
   *
   * @return a TypeOsSpecies enum that contains the type of the Species object.
   */
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

  /**
   * Gets the defining characteristics of the Species object.
   *
   * @return a String that has special feature of the animal.
   */
  public String getDefiningCharacteristics() {
    return definingCharacteristics;
  }
}
