package animals;

/**
 * Represents functionalities that must be implemented by any class that signs a contract. This
 * allows all users to be able to get specific information about the species of an animal.
 */
public interface SpeciesInterface {
  /**
   * Gets the species name.
   *
   * @return a String that contains the name of the Species object.
   */
  String getSpeciesName();

  /**
   * Gets the type of species.
   *
   * @return a TypeOsSpecies enum that contains the type of the Species object.
   */
  TypeOfSpecies getSpeciesType();

  /**
   * Gets the defining characteristics of the Species object.
   *
   * @return a String that has special feature of the animal.
   */
  String getDefiningCharacteristics();
}
