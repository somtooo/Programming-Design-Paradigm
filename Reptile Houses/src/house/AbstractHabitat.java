package house;

import animals.Animals;

/** Abstract class that contains only abstract methods which is extended by the Habitat class. */
public abstract class AbstractHabitat {

  /**
   * Gets the natural features the habitat has.
   *
   * @return String[]
   */
  public abstract String[] getNaturalFeatures();

  /**
   * Gets the size of the habitat in msq.
   *
   * @return int
   */
  public abstract int getSize();

  /**
   * Gets the name of the habitat.
   *
   * @return String
   */
  public abstract String getName();

  /**
   * Adds an animal to the habitat.
   *
   * @param animal animal interface
   * @return AbstractHabitat
   */
  protected abstract AbstractHabitat addAnimal(Animals animal);
}
