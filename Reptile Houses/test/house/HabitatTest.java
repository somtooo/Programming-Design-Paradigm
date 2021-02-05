package house;

import static org.junit.Assert.assertEquals;

import animals.Animals;
import animals.Danger;
import animals.HerpetologyAnimals;
import animals.PersonalFeatures;
import animals.PhysicalCharacteristics;
import animals.SizeofSpecies;
import animals.Species;
import animals.TypeOfSpecies;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;






/** Test's the Habitat class to achieve 100% coverage. */
public class HabitatTest {
  List<String> features = new ArrayList<String>();
  Species species = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  PhysicalCharacteristics physicalCharacteristics =
      new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");

  PersonalFeatures personalFeatures = new PersonalFeatures(false, Danger.ENDANGERED, true);

  AbstractHabitat habitat;

  /** This function runs before any other function so the object is reused. */
  @Before
  public void setUp() {
    features.add("rocks");
    features.add("pond");
    habitat = new Habitat(features, 20, "Habitat A");
  }

  @Test
  public void testConstructor() {
    assertEquals(features, habitat.getNaturalFeatures());
    assertEquals(20, habitat.getSize());
    assertEquals("HABITAT A", habitat.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    new Habitat(features, 30, "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgument() {
    new Habitat(null, 30, "water");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalValue() {
    new Habitat(features, -30, "Habitat C");
    new Habitat(features, 0, "Habitat C");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyArray() {
    ArrayList<String> features = new ArrayList<String>();
    new Habitat(features, 10, "Habitat");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArrayLength() {
    ArrayList<String> illegalFeatures = new ArrayList<String>();
    illegalFeatures.add("pond");
    illegalFeatures.add("grass");
    illegalFeatures.add("rock");
    illegalFeatures.add("flower");
    new Habitat(illegalFeatures, 10, "Habitat");
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalNumOfFeatures() {
    habitat.addNaturalFeature("water");
    habitat.addNaturalFeature("lilly");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDuplicates() {
    ArrayList<String> duplicates = new ArrayList<String>();
    duplicates.add("rock");
    duplicates.add("water");
    duplicates.add("rock");
    new Habitat(duplicates, 10, "Duplicate Habitat");
  }

  @Test
  public void testAddAnimalWithInvalidNaturalFeature() {
    Animals invalidNaturalFeatureAnimal =
        new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(false, habitat.addAnimal(invalidNaturalFeatureAnimal));
  }

  @Test
  public void testIllegalCohabitate() {
    features.add("water");
    PersonalFeatures falseCohabitate = new PersonalFeatures(false, Danger.ENDANGERED, false);
    Animals invalidNaturalFeatureAnimal =
        new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals cantHabitate =
        new HerpetologyAnimals(species, physicalCharacteristics, falseCohabitate);
    assertEquals(true, habitat.addAnimal(invalidNaturalFeatureAnimal));
    assertEquals(false, habitat.addAnimal(cantHabitate));
  }

  @Test
  public void testAddHabitat() {
    features.add("water");
    PersonalFeatures falseCohabitate = new PersonalFeatures(false, Danger.ENDANGERED, false);
    Animals invalidNaturalFeatureAnimal =
        new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals cantHabitate =
        new HerpetologyAnimals(species, physicalCharacteristics, falseCohabitate);
    assertEquals(true, habitat.addAnimal(invalidNaturalFeatureAnimal));
    assertEquals(false, habitat.addAnimal(cantHabitate));
  }

  @Test
  public void testExtinctAnimal() {
    PersonalFeatures extinctFeatures = new PersonalFeatures(false, Danger.EXTINCT, true);
    Animals extinctAnimal =
        new HerpetologyAnimals(species, physicalCharacteristics, extinctFeatures);
    assertEquals(false, habitat.addAnimal(extinctAnimal));
  }

  @Test
  public void testInvalidTemperatureRange() {
    PhysicalCharacteristics physicalCharacteristics =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "rocks");
    PhysicalCharacteristics invalidTemp =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(40, 43), "rocks");
    Animals validTemperatureRangeAnimal =
        new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals inValidTemperatureRangeAnimal =
        new HerpetologyAnimals(species, invalidTemp, personalFeatures);

    assertEquals(true, habitat.addAnimal(validTemperatureRangeAnimal));
    assertEquals(false, habitat.addAnimal(inValidTemperatureRangeAnimal));
  }

  @Test
  public void testIllegalSize() {
    features.add("water");
    PhysicalCharacteristics mediumAnimal =
        new PhysicalCharacteristics(SizeofSpecies.MEDIUM, ValueRange.of(30, 33), "water");
    PhysicalCharacteristics smallAnimal =
        new PhysicalCharacteristics(SizeofSpecies.SMALL, ValueRange.of(30, 33), "water");
    Animals sizeToBigToAddToHabitatAnimal =
        new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals mediumSizeToBigToAddToHabitatAnimal =
        new HerpetologyAnimals(species, mediumAnimal, personalFeatures);
    Animals smallSizeToBigToAddToHabitatAnimal =
        new HerpetologyAnimals(species, smallAnimal, personalFeatures);
    assertEquals(true, habitat.addAnimal(sizeToBigToAddToHabitatAnimal));
    assertEquals(true, habitat.addAnimal(mediumSizeToBigToAddToHabitatAnimal));
    assertEquals(true, habitat.addAnimal(smallSizeToBigToAddToHabitatAnimal));
    Animals sizeToBigToAddToHabitatAnimal2 =
            new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(false, habitat.addAnimal(sizeToBigToAddToHabitatAnimal2));
  }

  @Test
  public void testAddingDifferentSpecies() {
    features.add("water");
    Animals amphibian = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(true, habitat.addAnimal(amphibian));
    Species illegalSpecies = new Species("crocodile", TypeOfSpecies.REPTILE, "broken leg");
    Animals reptile =
        new HerpetologyAnimals(illegalSpecies, physicalCharacteristics, personalFeatures);
    assertEquals(false, habitat.addAnimal(reptile));
  }

  @Test
  public void testAddNaturalFeature() {
    habitat.addNaturalFeature("water");
    assertEquals(3, habitat.getNaturalFeatures().size());
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddNaturalFeature() {
    habitat.addNaturalFeature("water");
    habitat.addNaturalFeature("lilly");
  }

  @Test
  public void reportNaturalFeaturesTest() {
    assertEquals(
        "The natural Features in HABITAT A are: [pond, rocks] "
                + "and the remaining size is 20 square meters \n",
        habitat.reportNaturalFeatures());
  }

  @Test
  public void getAnimalTest() {
    features.add("water");
    Animals animalToAdd =
        new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    habitat.addAnimal(animalToAdd);
    assertEquals(1, habitat.getAnimals().size());
  }

  @Test
  public void testGetSignOfHabitat() {
    assertEquals("No Animal in HABITAT A", habitat.getSignOfHabitat());
    features.add("water");
    Animals amphibian = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    habitat.addAnimal(amphibian);
    assertEquals(
        "There are currently 1 frog in HABITAT A\nSpecies: frog, Type: AMPHIBIAN, Defining "
            + "Characteristics: broken leg, Temperature Range: 30 - 33, Preferred Natural Feature: "
            + "water, Size: LARGE, Danger State: ENDANGERED, Poisonous: false, Cohabitate: true",
        habitat.getSignOfHabitat());
  }

  @Test
  public void testGetMapOfHabitat() {
    assertEquals(
        "There are no species currently in HABITAT A. Natural Features contained are [rocks, pond]",
        habitat.getMap());
    features.add("water");
    Animals amphibian = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals dupAmphibian =
        new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    habitat.addAnimal(amphibian);
    assertEquals(
        "Natural Features are [rocks, pond, water] There are currently 1 frog in HABITAT A\n",
        habitat.getMap());
    habitat.addAnimal(dupAmphibian);
    assertEquals(
        "Natural Features are [rocks, pond, water] There are currently 2 frog in HABITAT A\n",
        habitat.getMap());
  }

  @Test
  public void testGetIndexOfHabitat() {
    assertEquals("There are no Species in HABITAT A", habitat.getIndex());
    features.add("water");
    Animals amphibian = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    Animals duplicateAmphibian =
        new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    habitat.addAnimal(amphibian);
    assertEquals("List of Species in HABITAT A: [frog]", habitat.getIndex());
    habitat.addAnimal(duplicateAmphibian);
    assertEquals("List of Species in HABITAT A: [frog]", habitat.getIndex());
  }

  @Test
  public void testToString() {
    assertEquals("Natural Features: [rocks, pond], Size: 20, Name: HABITAT A ", habitat.toString());
  }
}
