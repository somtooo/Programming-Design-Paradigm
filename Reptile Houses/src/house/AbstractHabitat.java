package house;

import animals.Animals;
import java.util.List;

/**
 * An abstract class which for now contains only abstract methods that
 * any class that extends it must implement. It implements methods that
 * help to get important details about any class that extends it.
 */
public abstract class AbstractHabitat {

  /**
   * Gets the natural features the sub class has.
   *
   * @return a list which holds the natural features as string.
   */
  public abstract List<String> getNaturalFeatures();

  /**
   * Gets the size of the sub class.
   *
   * @return an int which represents the size in any unit of measurement the sub class chooses.
   */
  public abstract int getSize();

  /**
   * Gets the name of the sub class.
   *
   * @return a String representation of the sub class object name.
   */
  public abstract String getName();

  /**
   * Adds an animal to the sub class habitat.
   *
   * @param animal which holds relevant data like personal features of the animal.
   * @return a Boolean to state if the operation was successful or not.
   */
  protected abstract Boolean addAnimal(Animals animal);

  /**
   * Reports the natural features of the sub class that are currently
   * being used in alphabetical order and the size in square meters.
   *
   * @return A string representation of the report.
   */
  protected abstract String reportNaturalFeatures();

  /**
   * Gets the animal list of the sub class.
   *
   * @return List which has the animals in the habitat.
   */

  protected abstract List<Animals> getAnimals();

  /**
   * Builds a string for the sub class that lists the species that it houses along with a
   * description of the species and an indicator of how many of that species
   * is housed in that sub class.
   *
   * @return String which gives a description of the sign of the sub class.
   */
  protected abstract String getSignOfHabitat();

  /**
   * Gets information on the natural features in the sub class and the species they house.
   *
   * @return String with the required information.
   */
  protected abstract String getMap();

  /**
   * Builds an index that lists all species in the sub class in alphabetical order.
   *
   * @return String with that contains the index.
   */
  protected abstract String getIndex();

  /**
   * Adds a new feature to the sub class.
   *
   * @param feature where a species prefers to live.
   * @throws IllegalArgumentException if feature is empty
   */
  protected abstract void addNaturalFeature(String feature);

  /**
   * Finds if a specie is in Habitat.
   *
   * @param specie the name of animal we are looking to find
   * @return a String that contains if the specie was found and name of Habitat where it was found
   * @throws IllegalArgumentException if string is empty.
   */
  protected abstract String findSpecieInHabitat(String specie);
}
