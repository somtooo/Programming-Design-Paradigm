package house;

import animals.Animals;
import animals.Danger;
import animals.SizeofSpecies;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Habitat that keeps track of and manages its size, animals within it and natural
 * features it contains. It's also able report important details by implementing the
 * abstract methods from AbstractHabitat.
 */
public class Habitat extends AbstractHabitat {

  private final String name;
  private final List<String> naturalFeatures;
  private final List<Animals> animals;
  private int size;

  /**
   * Constructs a Habitat object with the required parameters.
   *
   * @param naturalFeatures features that the habitat contains
   * @param size in meter square
   * @param name for identifying habitat
   * @throws IllegalArgumentException if any input is null, habitat name is empty,
   *        no natural feature is added more than three natural features are added
   *        and size of habitat is zero or less than zero
   */
  public Habitat(List<String> naturalFeatures, int size, String name)
      throws IllegalArgumentException {
    if (naturalFeatures == null || name == null) {
      throw new IllegalArgumentException("Null not accepted");
    }
    if (findDuplicatesAndThrowException(naturalFeatures)) {
      if (name.trim().isEmpty()) {
        throw new IllegalArgumentException("Habitat Name can't be empty");
      } else if (naturalFeatures.size() < 1) {
        throw new IllegalArgumentException("At least one natural feature must be added");
      } else if (naturalFeatures.size() > 3) {
        throw new IllegalArgumentException("Only a maximum of three natural features allowed");
      } else if (size <= 0) {
        throw new IllegalArgumentException("Size of habitat must be greater than zero");
      }
    }

    this.naturalFeatures = naturalFeatures;
    this.size = size;
    this.name = name.toUpperCase();
    animals = new ArrayList<Animals>();
  }


  @Override
  public List<String> getNaturalFeatures() {
    return naturalFeatures;
  }


  @Override
  public int getSize() {
    return size;
  }


  @Override
  public String getName() {
    return name;
  }


  @Override
  protected Boolean addAnimal(Animals animal) {
    if (animal.getPersonalFeatures().getDangerState() == Danger.EXTINCT) {
      return false;
    } else if (animals.size() == 0) {
      if (naturalFeatures.contains(animal.getPhysicalCharacteristics().getNaturalFeature())) {
        return isThereSpaceToAddAnimal(animal);
      }
      return false;
    } else if (satisfyConditionsIfNotEmpty(animal)) {
      return isThereSpaceToAddAnimal(animal);
    }
    return false;
  }


  @Override
  protected void addNaturalFeature(String feature) {
    if (feature.trim().isEmpty()) {
      throw new IllegalArgumentException("Empty string not allowed");
    }
    if (naturalFeatures.size() < 3) {
      naturalFeatures.add(feature);
    } else {
      throw new IllegalStateException("Habitat is full cant take any more natural feature");
    }
  }

  @Override
  protected String findSpecieInHabitat(String lowerCaseSpecies) {
    if (lowerCaseSpecies.trim().isEmpty()) {
      throw new IllegalArgumentException("Empty string not allowed");
    }

    StringBuilder builder = new StringBuilder();
    boolean foundInHabitat = false;
    for (Animals animalValue : animals) {
      if (animalValue.getSpecies().getSpeciesName().toLowerCase().equals(lowerCaseSpecies)) {
        builder.append("Species found in ").append(name).append(" ");
        foundInHabitat = true;
        break;
      }
    }

    if (!foundInHabitat) {
      builder.append("Species not found in ").append(name).append(" ");
    }
    return builder.toString();
  }

  @Override
  protected String reportNaturalFeatures() {
    naturalFeatures.sort(String.CASE_INSENSITIVE_ORDER);
    return String.format(
        "The natural Features in %s are: %s and the remaining size is %d square meters \n",
        name, naturalFeatures, size);
  }


  @Override
  protected List<Animals> getAnimals() {
    return animals;
  }


  @Override
  protected String getSignOfHabitat() {
    StringBuilder builder = new StringBuilder();
    HashMap<String, Integer> frequency = new HashMap<String, Integer>();
    if (animals.size() > 0) {
      frequency = buildFrequencyCounter();
      for (String species : frequency.keySet()) {
        String value = frequency.get(species).toString();
        builder
            .append("There are currently ")
            .append(value)
            .append(" ")
            .append(species)
            .append(" in")
            .append(" ")
            .append(name)
            .append("\n");
      }

      for (Animals value : animals) {
        builder.append(value.toString());
      }
      return builder.toString();
    }

    return "No Animal in " + name;
  }


  @Override
  protected String getMap() {
    StringBuilder builder = new StringBuilder();
    HashMap<String, Integer> frequency = new HashMap<String, Integer>();
    if (animals.size() > 0) {
      frequency = buildFrequencyCounter();
      builder.append("Natural Features are ").append(naturalFeatures);
      for (String species : frequency.keySet()) {
        String value = frequency.get(species).toString();
        builder
            .append(" There are currently ")
            .append(value)
            .append(" ")
            .append(species)
            .append(" in")
            .append(" ")
            .append(name)
            .append("\n");
      }
      return builder.toString();
    }
    return String.format(
        "There are no species currently in %s. Natural Features contained are %s",
        name, naturalFeatures);
  }

  @Override
  protected String getIndex() {
    ArrayList<String> species = new ArrayList<String>();
    ArrayList<String> speciesWithNoDuplicates = new ArrayList<String>();
    if (animals.size() > 0) {
      for (Animals value : animals) {
        species.add(value.getSpecies().getSpeciesName());
      }

      for (String value : species) {
        if (speciesWithNoDuplicates.contains(value)) {
          continue;
        }
        speciesWithNoDuplicates.add(value);
      }
      speciesWithNoDuplicates.sort(String.CASE_INSENSITIVE_ORDER);
      return String.format("List of Species in %s: %s", name, speciesWithNoDuplicates);
    }

    return String.format("There are no Species in %s", name);
  }


  /**
   * Modifies the toString function to print a description of Habitat.
   *
   * @return a String that contains a summary of the habitat class object.
   */
  @Override
  public String toString() {
    return String.format("Natural Features: %s, Size: %s, Name: %s ", naturalFeatures, size, name);
  }

  /**
   * Checks if a List has any duplicate values. If true then it throws an exception.
   *
   * @param mightHaveDuplicates a List containing values that might have duplicates.
   * @return a Boolean that states weather it had duplicates or not.
   */
  private boolean findDuplicatesAndThrowException(List<String> mightHaveDuplicates) {
    List<String> noDuplicates = new ArrayList<String>();
    for (String value : mightHaveDuplicates) {
      if (noDuplicates.contains(value)) {
        throw new IllegalArgumentException("Can not have duplicates in List");
      }
      noDuplicates.add(value);
    }
    return true;
  }


  /**
   * Assigns a value for a specific size of an animal.
   *
   * @param size the size of the animal can be large small or medium.
   * @return an int that represents the amount of space the animal will occupy in the habitat.
   */
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

  /**
   * Checks to see if theres space in the habitat to add the required animal.
   *
   * @param animal the animal that wants to get added to the habitat.
   * @return a Boolean stating if the animal can be added or not.
   */
  private boolean isThereSpaceToAddAnimal(Animals animal) {
    this.size = size - sizeToInt(animal.getPhysicalCharacteristics().getSize());
    if (this.size >= 0) {
      animals.add(animal);
      return true;
    }
    return false;
  }

  /**
   * Checks an amount of conditions that must pass if the habitat isn't empty
   * to allow the animal to be added into the habitat.
   *
   * @param animal the animal that wants to be added into the habitat.
   * @return a Boolean that states if the animal passed the test's or not.
   */
  private boolean satisfyConditionsIfNotEmpty(Animals animal) {
    long maxTemp = animal.getPhysicalCharacteristics().getTemperature().getMaximum();
    long minTemp = animal.getPhysicalCharacteristics().getTemperature().getMinimum();

    if (animals.contains(animal)) {
      throw new IllegalArgumentException("Can not add duplicates of Animal object");
    }

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

  /**
   * Builds a hashmap that hold the frequency a species in the habitat is found.
   *
   * @return a HashMap that has the species name and the number of times it found
   *       the species in the habitat.
   */
  private HashMap<String, Integer> buildFrequencyCounter() {
    HashMap<String, Integer> frequency = new HashMap<String, Integer>();
    for (Animals value : animals) {
      if (frequency.containsKey(value.getSpecies().getSpeciesName())) {
        frequency.put(
            value.getSpecies().getSpeciesName(),
            frequency.get(value.getSpecies().getSpeciesName()) + 1);
        continue;
      }
      frequency.put(value.getSpecies().getSpeciesName(), 1);
    }
    return frequency;
  }
}
