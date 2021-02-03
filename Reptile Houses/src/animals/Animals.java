package animals;

/**
 * All Known Implementing Classes: HerpetologyAnimals.
 * The user of this interface has the ability to
 * require the Species, PhysicalCharacteristics and PersonalFeatures object of any implementing
 * CLasses.
 */
public interface Animals {
  /**
   * Gets the Species Object.
   *
   * @return Species
   */
  Species getSpecies();

  /**
   * Gets the PhysicalCharacteristics object.
   *
   * @return PhysicalCharacteristics
   */
  PhysicalCharacteristics getPhysicalCharacteristics();

  /**
   * Gets the PersonalFeatures object.
   *
   * @return PersonalFeatures
   */
  PersonalFeatures getPersonalFeatures();
}
