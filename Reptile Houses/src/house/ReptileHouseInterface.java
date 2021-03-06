package house;

import animals.Animals;
import java.util.List;

/**
 * Represents functionalities that must be implemented by any class that signs a contract. This
 * allows all users to be able to manage a group of habitats and give specific details to the
 * client.
 */
public interface ReptileHouseInterface {
  /**
   * Gets the a list of the Habitats in the Reptile House.
   *
   * @return a List which contains the habitats in the house.
   */
  List<AbstractHabitat> getHabitats();

  /**
   * Gets the number of habitats allowed in the Reptile house.
   *
   * @return an int that states the number of habitats the house can take.
   */
  int getNumOfHabitats();

  /**
   * Handles the logic of adding an animal to its appropriate habitat.
   *
   * @param animals the animal to be added.
   * @return a Boolean to state weather the operation was successful or not.
   */
  Boolean addAnimalToHabitat(Animals animals);

  /**
   * Combines the natural features report generated by all habitats in the reptile house and adds to
   * it their location.
   *
   * @return a String that contains the required report.
   */
  String reportNaturalFeatures();

  /**
   * Looks up which habitat(s) that house a particular species.
   *
   * @param species the name of the animal you want to find,
   * @return a String stating where the animal was found and if it want found.
   */
  String findSpeciesByHabitat(String species);

  /**
   * Adds an habitat to the reptile house.
   *
   * @param habitat the habitat that wants to be added.
   */
  void addHabitat(AbstractHabitat habitat);

  /**
   * Generates a sign for any given habitat that lists the species that it houses along with a
   * description of the species and an indicator of how many of that species is housed in that
   * habitat.
   *
   * @return a String with the sign to print.
   */
  String printSign();

  /**
   * Generates a “map” that lists all the habitats by location and the natural features in the
   * habitat and species they house.
   *
   * @return a String with the map to Print.
   */
  String printMap();

  /**
   * Print an index that lists all species in the Reptile House in alphabetical order and their
   * location(s).
   *
   * @return a String with the Index to Print.
   */
  String printIndex();

  /**
   * Takes a natural feature and tells the required habitat to add it.
   *
   * @param habitat the habitat that you want the natural feature to go too.
   * @param naturalFeature the natural feature that want to be added.
   * @return a Boolean to state weather the operation was successful or not.
   */
  Boolean addNaturalFeatureToHabitat(AbstractHabitat habitat, String naturalFeature);
}
