package animals;

/**
 * An implementation of the Animal Interface. Implements all Animal Operations to build a
 * Herpetology(study of reptiles and amphibians) Animal object which tracks the animals species
 * physical characteristic and personal features.
 *
 * @author somtoo.
 */
public final class HerpetologyAnimals implements Animals {
  private final Species species;
  private final PhysicalCharacteristics physicalCharacteristics;
  private final PersonalFeatures personalFeatures;

  /**
   * Constructs an Herpetology Animal Class with the associated parameters.
   *
   * @param species can be reptile or amphibians.
   * @param physicalCharacteristics tracks the temperature, natural feature and size.
   * @param personalFeatures tracks if animal is poisonous and in danger.
   */
  public HerpetologyAnimals(
      Species species,
      PhysicalCharacteristics physicalCharacteristics,
      PersonalFeatures personalFeatures) {
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

  /**
   * Overrides the toString function to print a modified description of the object.
   *
   * @return a description of the object fields in String format.
   */
  @Override
  public String toString() {
    return String.format(
        "%s, %s, %s", getSpecies(), getPhysicalCharacteristics(), getPersonalFeatures());
  }
}
