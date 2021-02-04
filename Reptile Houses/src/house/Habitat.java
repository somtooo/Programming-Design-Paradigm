package house;

import animals.Animals;
import animals.Danger;
import animals.SizeofSpecies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Creates an Habitat object that keeps track of its size and animals within it and implements all
 * abstract methods from AbstractHabitat.
 */
public class Habitat extends AbstractHabitat {

  private final String name;
  private final ArrayList<String> naturalFeatures;
  private final ArrayList<Animals> animals;
  private int size;

  /**
   * Constructs an Habitat object with the required parameters.
   *
   * @param naturalFeatures features that the habitat contains
   * @param size in meter square
   * @param name for identifying habitat
   */
  public Habitat(ArrayList<String> naturalFeatures, int size, String name)
      throws IllegalArgumentException {
    if (name.trim().isEmpty()) {
      throw new IllegalArgumentException("Habitat Name can't be empty");
    } else if (naturalFeatures.size() < 1) {
      throw new IllegalArgumentException("At least one natural feature must be added");
    } else if (naturalFeatures.size() > 3) {
      throw new IllegalArgumentException("Only a maximum of three natural features allowed");
    } else if (size <= 0) {
      throw new IllegalArgumentException("Size of habitat must be greater than zero");
    }
    this.naturalFeatures = naturalFeatures;
    this.size = size;
    this.name = name;
    animals = new ArrayList<Animals>();
  }

  /**
   * Gets the natural features the habitat has.
   *
   * @return String[]
   */
  @Override
  public ArrayList<String> getNaturalFeatures() {
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
  protected Boolean addAnimal(Animals animal) {
    if (animal.getPersonalFeatures().getDangerState() == Danger.EXTINCT) {
      return false;
    } else if (animals.size() == 0) {
      if (naturalFeatures.contains(animal.getPhysicalCharacteristics().getNaturalFeature())) {
        return finalTest(animal);
      }
      return false;
    } else if (satisfyConditionsIfNotEmpty(animal)) {
      return finalTest(animal);
    }
    return false;
  }

  private int sizeToInt(SizeofSpecies size) {
    if (size == SizeofSpecies.SMALL) {
      return 1;
    } else if (size == SizeofSpecies.MEDIUM) {
      return 5;
    } else if (size == SizeofSpecies.LARGE) {
      return 10;
    }
    return 0;
  }

  private boolean finalTest(Animals animal) {
    this.size = size - sizeToInt(animal.getPhysicalCharacteristics().getSize());
    if (this.size >= 0) {
      animals.add(animal);
      return true;
    }
    return false;
  }

  private boolean satisfyConditionsIfNotEmpty(Animals animal) {
    long maxTemp = animal.getPhysicalCharacteristics().getTemperature().getMaximum();
    long minTemp = animal.getPhysicalCharacteristics().getTemperature().getMinimum();
    if (!(animal.getPersonalFeatures().getCanCohabitate())) {
      return false;
    }
    for (Animals value : animals) {
      if (!(value.getPhysicalCharacteristics().getTemperature().isValidValue(minTemp)
          || value.getPhysicalCharacteristics().getTemperature().isValidValue(maxTemp))) {
        return false;
      } else if (value.getSpecies().getSpeciesType() != animal.getSpecies().getSpeciesType()) {
        return false;
      } else if (!(naturalFeatures.contains(
          animal.getPhysicalCharacteristics().getNaturalFeature()))) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected void addNaturalFeature(String feature) {
    if (naturalFeatures.size() < 3) {
      naturalFeatures.add(feature);
    } else {
      throw new IllegalStateException("A habitat can only have three natural features");
    }
  }

  public String reportNaturalFeatures() {
    naturalFeatures.sort(String.CASE_INSENSITIVE_ORDER);
    return String.format(
        "The natural Features in %s are: %s and the remaining size is %d square meters \n",
        name, naturalFeatures, size);
  }

  @Override
  public ArrayList<Animals> getAnimals() {
    return animals;
  }

  @Override
  public String getSignOfHabitat() {
    StringBuilder builder = new StringBuilder();
    HashMap<Animals, String> loadData = new HashMap<Animals, String>();
    ArrayList<String> loadSpecies = new ArrayList<>();
    HashMap<String, Integer> frequency = new HashMap<String, Integer>();
    if (animals.size() > 0){
      for (Animals value : animals) {
        loadData.put(value, value.getSpecies().getSpeciesName().toLowerCase());
      }

      for (Animals animals : loadData.keySet()) {
        loadSpecies.add(loadData.get(animals));
      }

      for (String species : loadSpecies) {
        if (frequency.containsKey(species)) {
          frequency.put(species, frequency.get(species) + 1);
          continue;
        }
        frequency.put(species, 1);
      }

      for (String species : frequency.keySet()) {
        String value = frequency.get(species).toString();
        builder.append("There are currently ").append(value).append(" ").append(species).append(" in").append(" ").append(name).append("\n");
      }
      for (Animals animals : loadData.keySet()) {
        String value = loadData.get(animals);
        String description = animals.toString();
        builder.append(description).append("\n");
      }
      return builder.toString();

    }


    return "No Animal in " + name;
  }

  @Override
  public String getMap() {
    StringBuilder builder = new StringBuilder();
    HashMap<String, Integer> frequency = new HashMap<String, Integer>();
    if (animals.size() > 0){
      for (Animals value:animals){
        if (frequency.containsKey(value.getSpecies().getSpeciesName())){
          frequency.put(value.getSpecies().getSpeciesName(),frequency.get(value.getSpecies().getSpeciesName())+1);
          continue;
        }
        frequency.put(value.getSpecies().getSpeciesName(),1);
      }
      builder.append("Natural Features are ").append(naturalFeatures);
      for (String species : frequency.keySet()) {
        String value = frequency.get(species).toString();
        builder.append(" There are currently ").append(value).append(" ").append(species).append(" in").append(" ").append(name).append("\n");
      }
      return builder.toString();
    }
    return String.format("There are no species currently in %s. Natural Features contained are %s",name,naturalFeatures);

  }

  public String getIndex(){
    ArrayList<String> species = new ArrayList<String>();
    ArrayList<String> speciesWithNoDuplicates = new ArrayList<String>();
    if (animals.size() > 0){
      for (Animals value: animals){
        species.add(value.getSpecies().getSpeciesName());
      }

      for (String value : species){
        if (speciesWithNoDuplicates.contains(value)){
          continue;
        }
        speciesWithNoDuplicates.add(value);
      }
      speciesWithNoDuplicates.sort(String.CASE_INSENSITIVE_ORDER);
      return String.format("List of Species in %s: %s",name,speciesWithNoDuplicates);
    }

    return String.format("There are no Species in %s", name);

  }

  /**
   * Modifies the toString function to print a description of Habitat.
   *
   * @return String
   */
  @Override
  public String toString() {
    return String.format("Natural Features: %s, Size: %s, Name: %s ", naturalFeatures, size, name);
  }
}
