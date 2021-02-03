package house;

import animals.Animals;
import java.util.ArrayList;

/**
 * Creates a ReptileHouse object that manages habitat and determines which habitat a specie goes to.
 */
public class ReptileHouse {
  private final ArrayList<AbstractHabitat> habitat;
  private final int numOfHabitats;
  private ArrayList<Animals> noHome;

  /**
   * Constructs a reptile house object based on the specified parameters.
   *
   * @param habitat list of habitat
   * @param numOfHabitats max number of habitat
   * @throws IllegalArgumentException on negative values, empty list and list greater than max
   *     habitat
   */
  public ReptileHouse(ArrayList<AbstractHabitat> habitat, int numOfHabitats)
      throws IllegalArgumentException {
    this.habitat = habitat;
    this.numOfHabitats = numOfHabitats;
  }

  /**
   * Handles the logic of adding an animal to its appropriate habitat.
   *
   * @param animals as interface
   */
  public void addAnimalToHabitat(Animals animals) {
    throw new IllegalCallerException();
  }

  /**
   * Reports the natural features currently being used in alphabetical order.
   *
   * @return String
   */
  public String reportNaturalFeatures() {
    throw new IllegalCallerException();
  }

  /**
   * Looks up which habitat(s) that house a particular species.
   *
   * @param species object
   * @return String
   */
  public String findSpeciesByHabitat(String species) {
    throw new IllegalCallerException();
  }

  /**
   * Adds an habitat to the reptile house.
   *
   * @param habitat object
   */
  public void addHabitat(AbstractHabitat habitat) {
    throw new IllegalCallerException();
  }

  /**
   * Prints a sign for any given habitat that lists the species that it houses along with a
   * description of the species and an indicator of how many of that species is housed in that
   * habitat.
   */
  public void printSign() {
    throw new IllegalCallerException();
  }

  /**
   * Prints a “map” that lists all the habitats by location and the natural features in the habitat
   * and species they house.
   */
  public void printMap() {
    throw new IllegalCallerException();
  }

  /**
   * Print an index that lists all species in the Reptile House in alphabetical order and their
   * location(s).
   */
  public void printIndex() {
    throw new IllegalCallerException();
  }

  /**
   * Overrides the toString function to print a modified description of the reptile house.
   *
   * @return String
   */
  @Override
  public String toString() {
    throw new IllegalCallerException();
  }
}
