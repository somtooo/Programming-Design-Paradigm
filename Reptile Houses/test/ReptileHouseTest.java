import static org.junit.Assert.assertEquals;

import animals.Animals;
import animals.Danger;
import animals.HerpetologyAnimals;
import animals.PersonalFeatures;
import animals.PhysicalCharacteristics;
import animals.SizeofSpecies;
import animals.Species;
import animals.TypeOfSpecies;
import house.AbstractHabitat;
import house.Habitat;
import house.ReptileHouse;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/** Test Methods and Implementation of ReptileHouse class. */
public class ReptileHouseTest {
  ReptileHouse reptileHouse;

  Species species = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  PhysicalCharacteristics physicalCharacteristics =
      new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");
  PersonalFeatures personalFeatures = new PersonalFeatures(false, Danger.ENDANGERED, true);
  List<String> features = new ArrayList<String>();
  List<AbstractHabitat> habitats = new ArrayList<AbstractHabitat>();
  List<String> featuresWithWater = new ArrayList<String>();
  AbstractHabitat habitat;
  AbstractHabitat secondHabitat;

  /** SetsUp Object so other test function can use it. */
  @Before
  public void setUp() {
    features.add("water");
    features.add("pond");
    featuresWithWater.add("water");
    featuresWithWater.add("lilly");
    habitat = new Habitat(features, 10, "Habitat A");
    secondHabitat = new Habitat(featuresWithWater, 10, "Habitat B");
    habitats.add(habitat);
    habitats.add(secondHabitat);
    reptileHouse = new ReptileHouse(habitats, 3);
  }

  @Test
  public void testConstructor() {
    assertEquals(habitats, reptileHouse.getHabitats());
    assertEquals(3, reptileHouse.getNumOfHabitats());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgument() {
    new ReptileHouse(null, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNegativeValue() {
    new ReptileHouse(habitats, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNumberOfHabitatsInArray() {
    new ReptileHouse(habitats, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArrayLength() {
    ArrayList<AbstractHabitat> emptyHabitats = new ArrayList<AbstractHabitat>();
    new ReptileHouse(emptyHabitats, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDuplicateHabitat() {
    List<AbstractHabitat> habitats = new ArrayList<AbstractHabitat>();
    AbstractHabitat habitat = new Habitat(features, 10, "Habitat A");
    habitats.add(habitat);
    habitats.add(habitat);
    new ReptileHouse(habitats, 3);
  }

  @Test
  public void addAnimalToHabitat() {
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals animal2 = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals animal3 = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(true, reptileHouse.addAnimalToHabitat(animal));
    assertEquals(true, reptileHouse.addAnimalToHabitat(animal2));
    assertEquals(false, reptileHouse.addAnimalToHabitat(animal3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAnimalToHabitatNullTest() {
    reptileHouse.addAnimalToHabitat(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddAnimalToHabitatDuplicate() {
    PhysicalCharacteristics physicalCharacteristicsDup =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rock");
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristicsDup, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal);
    reptileHouse.addAnimalToHabitat(animal);
  }

  @Test
  public void reportNaturalFeatures() {
    assertEquals(
        "The natural Features in HABITAT A are: [pond, water] and the "
            + "remaining size is 10 square meters \n"
            + "The natural Features in HABITAT B are: [lilly, water] "
            + "and the remaining size is 10 square meters",
        reptileHouse.reportNaturalFeatures());
  }

  @Test
  public void findSpeciesByHabitat() {
    PhysicalCharacteristics physicalCharacteristicsDup =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rock");
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals homelessAnimal =
        new HerpetologyAnimals(species, physicalCharacteristicsDup, personalFeatures);
    assertEquals(true, reptileHouse.addAnimalToHabitat(animal));
    assertEquals(
        "Species found in HABITAT A Species "
            + "not found in HABITAT B  and Species not found in Reptile House",
        reptileHouse.findSpeciesByHabitat("frog"));
    assertEquals(false, reptileHouse.addAnimalToHabitat(homelessAnimal));
    assertEquals(
        "Species found in HABITAT A Species not "
            + "found in HABITAT B  and Species found in Reptile House",
        reptileHouse.findSpeciesByHabitat("frog"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullFindSpeciesByHabitat() {
    reptileHouse.findSpeciesByHabitat("");
    reptileHouse.findSpeciesByHabitat(null);
  }

  @Test
  public void findSpeciesByHabitatWhenNotInHabitat() {
    PhysicalCharacteristics physicalCharacteristicsDup =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rock");
    Animals homelessAnimal =
        new HerpetologyAnimals(species, physicalCharacteristicsDup, personalFeatures);
    assertEquals(false, reptileHouse.addAnimalToHabitat(homelessAnimal));
    assertEquals(
        "Species not found in HABITAT A Species not found in "
            + "HABITAT B  and Species found in Reptile House",
        reptileHouse.findSpeciesByHabitat("frog"));
  }

  @Test
  public void findSpeciesByHabitatWhenNotInBoth() {
    assertEquals(
        "Species not found in HABITAT A Species not found in "
            + "HABITAT B  and Species not found in Reptile House",
        reptileHouse.findSpeciesByHabitat("crocodile"));
  }

  @Test
  public void addHabitat() {
    AbstractHabitat habitatToAdd = new Habitat(features, 20, "Habitat C");
    reptileHouse.addHabitat(habitatToAdd);
    assertEquals(3, reptileHouse.getHabitats().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddHabitatDuplicate() {
    AbstractHabitat habitatToAdd = new Habitat(features, 20, "Habitat C");
    reptileHouse.addHabitat(habitatToAdd);
    reptileHouse.addHabitat(habitatToAdd);
  }

  @Test
  public void printSign() {
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal);
    Animals animal2 = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal2);
    assertEquals(
        "There are currently 1 frog in HABITAT A\n"
            + "Species: frog, Type: AMPHIBIAN, Defining Characteristics: broken leg, "
            + "Temperature Range: 30 - 33, Preferred Natural Feature: water, Size: LARGE,"
            + " Danger State: ENDANGERED, Poisonous: false, Cohabitate: true\n"
            + "There are currently 1 frog in HABITAT B\n"
            + "Species: frog, Type: AMPHIBIAN, Defining Characteristics: "
            + "broken leg, Temperature Range: 30 - 33, Preferred Natural "
            + "Feature: water, Size: LARGE, Danger State: ENDANGERED, Poisonous: false, "
            + "Cohabitate: true\n",
        reptileHouse.printSign());
  }

  @Test
  public void printMap() {
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal);
    Animals animal2 = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal2);
    assertEquals(
        "HABITAT A in location 0 Natural Features are [water, pond] "
            + "There are currently 1 frog in HABITAT A\n"
            + "HABITAT B in location 1 Natural Features "
            + "are [water, lilly] There are currently 1 frog in HABITAT B\n",
        reptileHouse.printMap());
  }

  @Test
  public void printIndexTest() {
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal);
    Animals animal2 = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal2);
    assertEquals(
        "List of Species in HABITAT A: [frog]\n" + "List of Species in HABITAT B: [frog]\n",
        reptileHouse.printIndex());
  }

  @Test
  public void printIndexTestWithHomelessAnimal() {
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal);
    PhysicalCharacteristics physicalCharacteristicsHomeless =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rock");
    Animals animal2 =
        new HerpetologyAnimals(species, physicalCharacteristicsHomeless, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal2);
    assertEquals(
        "List of Species in HABITAT A: [frog]\n"
            + "There are no Species in HABITAT B\n"
            + "List of species not in any habitat: frog, ",
        reptileHouse.printIndex());
  }

  @Test
  public void testToString() {
    assertEquals(
        "Reptile House is currently managing 2 Habitats and "
            + "there are currently 0 Animals not in any Habitat",
        reptileHouse.toString());
  }

  @Test
  public void addNaturalFeatureTest() {
    AbstractHabitat habitatInvalid = new Habitat(features, 20, "Habitat B");
    assertEquals(true, reptileHouse.addNaturalFeatureToHabitat(habitat, "water"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNaturalFeatureTestIllegal() {
    AbstractHabitat habitatInvalid = new Habitat(features, 20, "Habitat B");
    reptileHouse.addNaturalFeatureToHabitat(habitatInvalid, "water");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNaturalFeatureTestIllegalNull() {
    reptileHouse.addNaturalFeatureToHabitat(habitat, "");
  }
}
