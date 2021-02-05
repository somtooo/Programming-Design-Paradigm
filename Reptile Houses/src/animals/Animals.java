package animals;

/**
 * All Known Implementing Classes: HerpetologyAnimals.
 * This interface represents a set of methods that any animal
 * type class must implement.Therefore any user of this interface must have the ability to
 * require the Species, PhysicalCharacteristics and PersonalFeatures object.
 */
public interface Animals {
  /**
   * Gets a Species Object of the implementing class.
   *
   * @return the Species Object of the implementing class.
   */
  Species getSpecies();

  /**
   * Gets a PhysicalCharacteristics object.
   *
   * @return the PhysicalCharacteristics object of the implementing class.
   */
  PhysicalCharacteristics getPhysicalCharacteristics();

  /**
   * Gets a PersonalFeatures object.
   *
   * @return the PersonalFeatures object of the implementing class.
   */
  PersonalFeatures getPersonalFeatures();
}
