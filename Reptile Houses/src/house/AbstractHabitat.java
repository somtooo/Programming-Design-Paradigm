package house;

import animals.Animals;

import java.util.ArrayList;

/** Abstract class that contains only abstract methods which is extended by the Habitat class. */
public abstract class AbstractHabitat {

  /**
   * Gets the natural features the habitat has.
   *
   * @return String[]
   */
  public abstract ArrayList<String> getNaturalFeatures();

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
  protected abstract Boolean addAnimal(Animals animal);

  public abstract String reportNaturalFeatures();
  public abstract ArrayList<Animals> getAnimals();
  public abstract String getSignOfHabitat();
  public abstract String getMap();
  public abstract String getIndex();
  protected abstract void addNaturalFeature(String feature);


}
