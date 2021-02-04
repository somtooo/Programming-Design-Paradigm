package house;

import static org.junit.Assert.assertEquals;

import animals.*;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Test's the Habitat class to achieve 100% coverage.
 */
public class HabitatTest {
  ArrayList<String> features = new ArrayList<String>();
  Species species = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  Species illegalSpecies = new Species("frog", TypeOfSpecies.REPTILE, "broken leg");
  PhysicalCharacteristics physicalCharacteristics =
          new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");
  PhysicalCharacteristics invalidTemp =
          new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(40, 43), "water");
  PersonalFeatures personalFeatures = new PersonalFeatures(false, Danger.ENDANGERED, true);
  PersonalFeatures extinctFeatures = new PersonalFeatures(false, Danger.EXTINCT, true);
  AbstractHabitat habitat;


  /**
   * This function runs before any other function so the object is reused.
   *
   */
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
    assertEquals("Habitat A", habitat.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    new Habitat(features, 30, "");
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

  @Test
  public void testAddAnimal() {
    Animals invalidNaturalFeatureAnimal =
            new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(false, habitat.addAnimal(invalidNaturalFeatureAnimal));

    Animals invalidTemperatureRangeAnimal =
            new HerpetologyAnimals(species, invalidTemp, personalFeatures);
    assertEquals(false, habitat.addAnimal(invalidTemperatureRangeAnimal));

    Animals extinctAnimal =
            new HerpetologyAnimals(species, physicalCharacteristics, extinctFeatures);
    assertEquals(false, habitat.addAnimal(extinctAnimal));
  }

  @Test
  public void testIllegalSize() {
    features.add("water");
    Animals sizeToBigToAddToHabitatAnimal =
            new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(true, habitat.addAnimal(sizeToBigToAddToHabitatAnimal));
    assertEquals(true, habitat.addAnimal(sizeToBigToAddToHabitatAnimal));
    assertEquals(false, habitat.addAnimal(sizeToBigToAddToHabitatAnimal));
  }

  @Test
  public void testAddingDifferentSpecies() {
    features.add("water");
    Animals amphibian = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(true, habitat.addAnimal(amphibian));
    Animals reptile =
            new HerpetologyAnimals(illegalSpecies, physicalCharacteristics, personalFeatures);
    assertEquals(false, habitat.addAnimal(reptile));
  }

@Test
public void testGetSignOfHabitat(){
  features.add("water");
  Animals amphibian = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
  habitat.addAnimal(amphibian);
  assertEquals("dddd",habitat.getSignOfHabitat());

}
  @Test
  public void reportNaturalFeaturesTest(){
    assertEquals("The natural Features in Habitat A are: [pond, rocks] and the remaining size is 20 square meters \n",habitat.reportNaturalFeatures());
  }
  @Test
  public void testToString() {
    assertEquals("Natural Features: [rocks, pond], Size: 20, Name: Habitat A", habitat.toString());
  }
}
