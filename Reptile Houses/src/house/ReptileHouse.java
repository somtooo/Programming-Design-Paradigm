package house;

import animals.Animals;
import java.util.ArrayList;

/**
 * Creates a ReptileHouse object that manages habitat and determines which habitat a specie goes to.
 */
public class ReptileHouse {
  private  ArrayList<AbstractHabitat> habitats;
  private final int numOfHabitats;
  private ArrayList<Animals> noHome;

  /**
   * Constructs a reptile house object based on the specified parameters.
   *
   * @param habitats list of habitat
   * @param numOfHabitats max number of habitat
   * @throws IllegalArgumentException on negative values, empty list and list greater than max
   *     habitat
   */
  public ReptileHouse(ArrayList<AbstractHabitat> habitats, int numOfHabitats)
      throws IllegalArgumentException {
    if (numOfHabitats < 0){
      throw new IllegalArgumentException("Number of habitat cant be negative");
    } else if(habitats.size() > numOfHabitats){
      throw new IllegalArgumentException("Habitats cannot be more than number of habitats");
    }else if (habitats.size() == 0){
      throw new IllegalArgumentException("Habitats can't be empty");
    }
    this.habitats = habitats;
    this.numOfHabitats = numOfHabitats;
    noHome = new ArrayList<Animals>();
  }
  public ArrayList<AbstractHabitat> getHabitats() {
    return habitats;
  }

  public int getNumOfHabitats() {
    return numOfHabitats;
  }
  /**
   * Handles the logic of adding an animal to its appropriate habitat.
   *
   * @param animals as interface
   */
  public Boolean addAnimalToHabitat(Animals animals) {
    int index = 0;
    boolean addedAnimal = false;
    while(index<habitats.size()){
      if (habitats.get(index).addAnimal(animals)){
        addedAnimal = true;
        break;
      }
      index++;
    }
    if (!addedAnimal){
      noHome.add(animals);
    }

    return addedAnimal;


  }

  /**
   * Reports the natural features currently being used in alphabetical order.
   *
   * @return String
   */
  public String reportNaturalFeatures() {
    StringBuilder combinedReport = new StringBuilder();
    for (AbstractHabitat value: habitats){
      combinedReport.append(value.reportNaturalFeatures());
    }

    return combinedReport.toString();

  }

  /**
   * Looks up which habitat(s) that house a particular species.
   *
   * @param species String object
   * @return String
   */
  public String findSpeciesByHabitat(String species) {
    StringBuilder combinedReport = new StringBuilder();
    boolean found = false;
    String lowerCaseSpecies = species.toLowerCase();
    for (AbstractHabitat value : habitats){
      for (Animals animalValue : value.getAnimals()){
        if (animalValue.getSpecies().getSpeciesName().toLowerCase().equals(lowerCaseSpecies)){
          combinedReport.append(value.getName()).append("\n");
          found = true;
        }
      }
    }
    if (!found){
      for(Animals value: noHome){
        if (value.getSpecies().getSpeciesName().toLowerCase().equals(lowerCaseSpecies)){
          combinedReport.append("Species not found in any habitat but found in Reptile House");
          found = true;
          break;
        }
      }
    }
    if (!found){
      combinedReport.append("Species not found");
    }

    return combinedReport.toString();
  }

  /**
   * Adds an habitat to the reptile house.
   *
   * @param habitat object
   */
  public void addHabitat(AbstractHabitat habitat) {
    if (habitats.size()<numOfHabitats){
      habitats.add(habitat);
    }else {
      throw new IllegalStateException("Habitats can not exceed number of habitats");
    }
  }

  /**
   * Prints a sign for any given habitat that lists the species that it houses along with a
   * description of the species and an indicator of how many of that species is housed in that
   * habitat.
   */
  public boolean printSign() {
    for (AbstractHabitat habitat:habitats){
      System.out.println(habitat.getSignOfHabitat());
    }
    return true;
  }

  /**
   * Prints a “map” that lists all the habitats by location and the natural features in the habitat
   * and species they house.
   */
  public boolean printMap() {
    StringBuilder builder = new StringBuilder();
    for (AbstractHabitat value : habitats){
      builder.append(value.getName()).append(" ").append("in location ").append(habitats.indexOf(value)).append(" ").append(value.getMap()).append("\n");
    }
    System.out.println(builder);
    return true;
  }

  /**
   * Print an index that lists all species in the Reptile House in alphabetical order and their
   * location(s).
   */
  public boolean printIndex() {
    StringBuilder builder = new StringBuilder();
    for (AbstractHabitat value: habitats){
      builder.append(value.getIndex()).append("\n");
    }
    if (noHome.size() > 0){
      for (Animals value: noHome){
        builder.append("List of species not in any habitat ").append(noHome);
      }
    }

    System.out.println(builder);
    return true;
  }

  /**
   * Overrides the toString function to print a modified description of the reptile house.
   *
   * @return String
   */
  @Override
  public String toString() {
    return String.format("There are currently %d Animals not in any Habitat",noHome.size());
  }


}
