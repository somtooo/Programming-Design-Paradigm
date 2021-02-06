import static org.junit.Assert.assertEquals;

import animals.Animals;
import animals.Danger;
import animals.HerpetologyAnimals;
import animals.PersonalFeatures;
import animals.PersonalFeaturesInterface;
import animals.PhysicalCharacteristics;
import animals.PhysicalCharacteristicsInterface;
import animals.SizeofSpecies;
import animals.Species;
import animals.SpeciesInterface;
import animals.TypeOfSpecies;
import house.AbstractHabitat;
import house.Habitat;
import house.ReptileHouse;
import house.ReptileHouseInterface;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;





/** Test Methods and Implementation of ReptileHouse class. */
public class ReptileHouseTest {
  ReptileHouseInterface reptileHouseInterface;
  SpeciesInterface speciesInterface = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  PhysicalCharacteristicsInterface physicalCharacteristicsInterface =
      new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");
  PersonalFeaturesInterface personalFeaturesInterface =
      new PersonalFeatures(false, Danger.ENDANGERED, true);
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
    reptileHouseInterface = new ReptileHouse(habitats, 3);
  }

  @Test
  public void testConstructor() {
    assertEquals(habitats, reptileHouseInterface.getHabitats());
    assertEquals(3, reptileHouseInterface.getNumOfHabitats());
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
    Animals animal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    Animals animal2 =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    Animals animal3 =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    assertEquals(true, reptileHouseInterface.addAnimalToHabitat(animal));
    assertEquals(true, reptileHouseInterface.addAnimalToHabitat(animal2));
    assertEquals(false, reptileHouseInterface.addAnimalToHabitat(animal3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAnimalToHabitatNullTest() {
    reptileHouseInterface.addAnimalToHabitat(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddAnimalToHabitatDuplicate() {
    PhysicalCharacteristicsInterface physicalCharacteristicsInterfaceDup =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rock");
    Animals animal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterfaceDup, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal);
    reptileHouseInterface.addAnimalToHabitat(animal);
  }

  @Test
  public void reportNaturalFeatures() {
    assertEquals(
        "The natural Features in HABITAT A are: [pond, water] and the "
            + "remaining size is 10 square meters \n"
            + "The natural Features in HABITAT B are: [lilly, water] "
            + "and the remaining size is 10 square meters",
        reptileHouseInterface.reportNaturalFeatures());
  }

  @Test
  public void findSpeciesByHabitat() {
    PhysicalCharacteristicsInterface physicalCharacteristicsInterfaceDup =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rock");
    Animals animal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    Animals homelessAnimal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterfaceDup, personalFeaturesInterface);
    assertEquals(true, reptileHouseInterface.addAnimalToHabitat(animal));
    assertEquals(
        "Species found in HABITAT A Species "
            + "not found in HABITAT B  and Species not found in Reptile House",
        reptileHouseInterface.findSpeciesByHabitat("frog"));
    assertEquals(false, reptileHouseInterface.addAnimalToHabitat(homelessAnimal));
    assertEquals(
        "Species found in HABITAT A Species not "
            + "found in HABITAT B  and Species found in Reptile House",
        reptileHouseInterface.findSpeciesByHabitat("frog"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullFindSpeciesByHabitat() {
    reptileHouseInterface.findSpeciesByHabitat("");
    reptileHouseInterface.findSpeciesByHabitat(null);
  }

  @Test
  public void findSpeciesByHabitatWhenNotInHabitat() {
    PhysicalCharacteristicsInterface physicalCharacteristicsInterfaceDup =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rock");
    Animals homelessAnimal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterfaceDup, personalFeaturesInterface);
    assertEquals(false, reptileHouseInterface.addAnimalToHabitat(homelessAnimal));
    assertEquals(
        "Species not found in HABITAT A Species not found in "
            + "HABITAT B  and Species found in Reptile House",
        reptileHouseInterface.findSpeciesByHabitat("frog"));
  }

  @Test
  public void findSpeciesByHabitatWhenNotInBoth() {
    assertEquals(
        "Species not found in HABITAT A Species not found in "
            + "HABITAT B  and Species not found in Reptile House",
        reptileHouseInterface.findSpeciesByHabitat("crocodile"));
  }

  @Test
  public void addHabitat() {
    AbstractHabitat habitatToAdd = new Habitat(features, 20, "Habitat C");
    reptileHouseInterface.addHabitat(habitatToAdd);
    assertEquals(3, reptileHouseInterface.getHabitats().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddHabitatDuplicate() {
    AbstractHabitat habitatToAdd = new Habitat(features, 20, "Habitat C");
    reptileHouseInterface.addHabitat(habitatToAdd);
    reptileHouseInterface.addHabitat(habitatToAdd);
  }

  @Test
  public void printSign() {
    Animals animal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal);
    Animals animal2 =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal2);
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
        reptileHouseInterface.printSign());
  }

  @Test
  public void printMap() {
    Animals animal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal);
    Animals animal2 =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal2);
    assertEquals(
        "HABITAT A in location 0 Natural Features are [water, pond] "
            + "There are currently 1 frog in HABITAT A\n"
            + "HABITAT B in location 1 Natural Features "
            + "are [water, lilly] There are currently 1 frog in HABITAT B\n",
        reptileHouseInterface.printMap());
  }

  @Test
  public void printIndexTest() {
    Animals animal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal);
    Animals animal2 =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal2);
    assertEquals(
        "List of Species in HABITAT A: [frog]\n" + "List of Species in HABITAT B: [frog]\n",
        reptileHouseInterface.printIndex());
  }

  @Test
  public void printIndexTestWithHomelessAnimal() {
    Animals animal =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal);
    PhysicalCharacteristicsInterface physicalCharacteristicsInterfaceHomeless =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rock");
    Animals animal2 =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterfaceHomeless, personalFeaturesInterface);
    reptileHouseInterface.addAnimalToHabitat(animal2);
    assertEquals(
        "List of Species in HABITAT A: [frog]\n"
            + "There are no Species in HABITAT B\n"
            + "List of species not in any habitat: frog, ",
        reptileHouseInterface.printIndex());
  }

  @Test
  public void testToString() {
    assertEquals(
        "Reptile House is currently managing 2 Habitats and "
            + "there are currently 0 Animals not in any Habitat",
        reptileHouseInterface.toString());
  }

  @Test
  public void addNaturalFeatureTest() {
    AbstractHabitat habitatInvalid = new Habitat(features, 20, "Habitat B");
    assertEquals(true, reptileHouseInterface.addNaturalFeatureToHabitat(habitat, "water"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNaturalFeatureTestIllegal() {
    AbstractHabitat habitatInvalid = new Habitat(features, 20, "Habitat B");
    reptileHouseInterface.addNaturalFeatureToHabitat(habitatInvalid, "water");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNaturalFeatureTestIllegalNull() {
    reptileHouseInterface.addNaturalFeatureToHabitat(habitat, "");
  }
}
