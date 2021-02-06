package house;

import animals.Animals;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ReptileHouse that manages a specified number of habitats by its ability to
 * add animal or features to the habitats it manages and by keeping the client up to date
 * with required information about the Reptile house.
 */
public class ReptileHouse implements ReptileHouseInterface {
  private final int numOfHabitats;
  private final List<AbstractHabitat> habitats;
  private final List<Animals> noHome;

  /**
   * Constructs a reptile house object based on the specified parameters.
   *
   * @param habitats list of habitat
   * @param numOfHabitats max number of habitat
   * @throws IllegalArgumentException on negative values, empty list and list greater than max
   *     habitat
   */
  public ReptileHouse(List<AbstractHabitat> habitats, int numOfHabitats)
      throws IllegalArgumentException {
    if (habitats == null) {
      throw new IllegalArgumentException("Null not allowed");
    }

    if (findDuplicatesAndThrowException(habitats)) {
      if (numOfHabitats < 0) {
        throw new IllegalArgumentException("Number of habitat cant be negative");
      } else if (habitats.size() > numOfHabitats) {
        throw new IllegalArgumentException("Habitats cannot be more than number of habitats");
      } else if (habitats.size() == 0) {
        throw new IllegalArgumentException("Habitats can't be empty");
      }
    }

    this.habitats = habitats;
    this.numOfHabitats = numOfHabitats;
    noHome = new ArrayList<Animals>();
  }

  @Override
  public List<AbstractHabitat> getHabitats() {
    return habitats;
  }

  @Override
  public int getNumOfHabitats() {
    return numOfHabitats;
  }

  @Override
  public Boolean addAnimalToHabitat(Animals animals) {
    if (animals == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
    int index = 0;
    boolean addedAnimal = false;
    while (index < habitats.size()) {
      if (habitats.get(index).addAnimal(animals)) {
        addedAnimal = true;
        break;
      }
      index++;
    }
    if (!addedAnimal) {
      if (noHome.contains(animals)) {
        throw new IllegalArgumentException("Can not add duplicates of Animal object");
      }
      noHome.add(animals);
    }

    return addedAnimal;
  }

  @Override
  public String reportNaturalFeatures() {
    StringBuilder combinedReport = new StringBuilder();
    for (AbstractHabitat value : habitats) {
      combinedReport.append(value.reportNaturalFeatures());
    }

    return combinedReport.toString().trim();
  }

  @Override
  public String findSpeciesByHabitat(String species) {
    if (species == null) {
      throw new IllegalArgumentException("Null not allowed");
    } else if (species.trim().isEmpty()) {
      throw new IllegalArgumentException(" Species cant be an empty string");
    }

    StringBuilder combinedReport = new StringBuilder();
    String lowerCaseSpecies = species.toLowerCase();
    combinedReport.append(checkForSpeciesInHabitat(lowerCaseSpecies));
    combinedReport.append(checkForSpeciesInHomeless(lowerCaseSpecies));

    return combinedReport.toString().trim();
  }



  @Override
  public void addHabitat(AbstractHabitat habitat) {
    if (habitat == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
    if (!habitats.contains(habitat)) {
      if (habitats.size() < numOfHabitats) {
        habitats.add(habitat);
      }
      return;
    }
    throw new IllegalArgumentException("Can not add duplicate habitat to house");
  }

  @Override
  public String printSign() {
    StringBuilder builder = new StringBuilder();
    for (AbstractHabitat habitat : habitats) {
      builder.append(habitat.getSignOfHabitat()).append("\n");
    }
    return builder.toString();
  }

  @Override
  public String printMap() {
    StringBuilder builder = new StringBuilder();
    for (AbstractHabitat value : habitats) {
      builder
          .append(value.getName())
          .append(" ")
          .append("in location ")
          .append(habitats.indexOf(value))
          .append(" ")
          .append(value.getMap());
    }
    return builder.toString();
  }

  @Override
  public String printIndex() {
    StringBuilder builder = new StringBuilder();
    for (AbstractHabitat value : habitats) {
      builder.append(value.getIndex()).append("\n");
    }
    if (noHome.size() > 0) {
      for (Animals value : noHome) {
        builder.append("List of species not in any "
                + "habitat: ").append(value.getSpecies().getSpeciesName()).append(", ");
      }
    }

    return builder.toString();
  }

  /**
   * Checks if a List has any duplicate values. If true then it throws an exception.
   *
   * @param mightHaveDuplicates a List containing values that might have duplicates.
   * @return a Boolean that states weather it had duplicates or not.
   */
  private boolean findDuplicatesAndThrowException(List<AbstractHabitat> mightHaveDuplicates) {
    List<AbstractHabitat> noDuplicates = new ArrayList<AbstractHabitat>();
    for (AbstractHabitat value : mightHaveDuplicates) {
      if (noDuplicates.contains(value)) {
        throw new IllegalArgumentException("Can not have duplicates in List");
      }
      noDuplicates.add(value);
    }
    return true;
  }


  @Override
  public Boolean addNaturalFeatureToHabitat(AbstractHabitat habitat, String naturalFeature) {
    boolean addedToHabitat = false;
    if (!habitats.contains(habitat)) {
      throw new IllegalArgumentException("Habitat not in Reptile House");
    } else if (naturalFeature.trim().isEmpty()) {
      throw new IllegalArgumentException("Empty string for natural feature detected");
    }

    for (AbstractHabitat value : habitats) {
      if (value.equals(habitat)) {
        value.addNaturalFeature(naturalFeature);
        addedToHabitat = true;
        break;
      }
    }
    return addedToHabitat;
  }

  /**
   * Overrides the toString function to print a modified description of the reptile house.
   *
   * @return String
   */
  @Override
  public String toString() {
    return String.format("Reptile House is currently managing %d Habitats and "
            + "there are currently %d Animals not in any Habitat", habitats.size(), noHome.size());
  }

  /**
   * Goes through the habitats the Reptile house manages and looks for a specie.
   *
   * @param lowerCaseSpecies the name of the species in lower case.
   * @return a String that says where it was found and if it want found.
   */
  private String checkForSpeciesInHabitat(String lowerCaseSpecies) {
    StringBuilder builder = new StringBuilder();
    for (AbstractHabitat value : habitats) {
      builder.append(value.findSpecieInHabitat(lowerCaseSpecies));
    }
    return builder.toString();
  }

  /**
   * Goes through the homeless List the Reptile house manages and looks for a specie.
   *
   * @param lowerCaseSpecies the name of the species in lower case.
   * @return a String that says where it was found and if it want found.
   */
  private String checkForSpeciesInHomeless(String lowerCaseSpecies) {
    StringBuilder report = new StringBuilder();
    boolean foundHomeless = false;
    for (Animals value : noHome) {
      if (value.getSpecies().getSpeciesName().toLowerCase().equals(lowerCaseSpecies)) {
        report.append(" and Species found in Reptile House");
        foundHomeless = true;
        break;
      }
    }
    if (!foundHomeless) {
      report.append(" and Species not found in Reptile House");
    }
    return report.toString();
  }
}
