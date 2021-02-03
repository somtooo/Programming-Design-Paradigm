package house;

import animals.Animals;
import java.util.ArrayList;

/**
 * Creates an Habitat object that keeps track of its size and animals within it and implements all
 * abstract methods from AbstractHabitat.
 */
public class Habitat extends AbstractHabitat {

  private final String[] naturalFeatures;
  private final int size;
  private final String name;
  private ArrayList<Animals> animals;

  /**
   * Constructs an Habitat object with the required parameters.
   *
   * @param naturalFeatures features that the habitat contains
   * @param size in meter square
   * @param name for identifying habitat
   */
  public Habitat(String[] naturalFeatures, int size, String name) {
    this.naturalFeatures = naturalFeatures;
    this.size = size;
    this.name = name;
  }

  /**
   * Gets the natural features the habitat has.
   *
   * @return String[]
   */
  @Override
  public String[] getNaturalFeatures() {
    return naturalFeatures;
  }

  /**
   * Gets the size of the habitat in msq.
   *
   * @return int
   */
  @Override
  public int getSize() {
    return size;
  }

  /**
   * Gets the name of the habitat.
   *
   * @return String
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Adds an animal to the habitat.
   *
   * @param animal animal interface
   * @return AbstractHabitat
   */
  @Override
  protected AbstractHabitat addAnimal(Animals animal) {
    throw new IllegalCallerException();
  }

  /**
   * Modifies the toString function to print a description of Habitat.
   *
   * @return String
   */
  @Override
  public String toString() {
    throw new IllegalCallerException();
  }
}
