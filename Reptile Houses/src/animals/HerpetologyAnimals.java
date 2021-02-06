package animals;

/**
 * An implementation of the Animal Interface. Implements all Animal Operations to build a
 * Herpetology(study of reptiles and amphibians) Animal object which tracks the animals species
 * physical characteristic and personal features.
 *
 * @author somtoo.
 */
public final class HerpetologyAnimals implements Animals {
  private final SpeciesInterface speciesInterface;
  private final PhysicalCharacteristicsInterface physicalCharacteristicsInterface;
  private final PersonalFeaturesInterface personalFeaturesInterface;

  /**
   * Constructs an Herpetology Animal Class with the associated parameters.
   *
   * @param speciesInterface can be reptile or amphibians.
   * @param physicalCharacteristicsInterface tracks the temperature, natural feature and size.
   * @param personalFeaturesInterface tracks if animal is poisonous and in danger.
   * @throws IllegalArgumentException if the value is null.
   */
  public HerpetologyAnimals(
      SpeciesInterface speciesInterface,
      PhysicalCharacteristicsInterface physicalCharacteristicsInterface,
      PersonalFeaturesInterface personalFeaturesInterface) {
    if (speciesInterface == null
        | physicalCharacteristicsInterface == null
        | personalFeaturesInterface == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
    this.speciesInterface = speciesInterface;
    this.physicalCharacteristicsInterface = physicalCharacteristicsInterface;
    this.personalFeaturesInterface = personalFeaturesInterface;
  }

  @Override
  public SpeciesInterface getSpecies() {
    return speciesInterface;
  }

  @Override
  public PhysicalCharacteristicsInterface getPhysicalCharacteristics() {
    return physicalCharacteristicsInterface;
  }

  @Override
  public PersonalFeaturesInterface getPersonalFeatures() {
    return personalFeaturesInterface;
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
