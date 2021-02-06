import static org.junit.Assert.assertEquals;

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
import java.time.temporal.ValueRange;
import org.junit.Before;
import org.junit.Test;





/** Tests the HerpetologyAnimal class methods and implementation for correctness. */
public class HerpetologyAnimalsTest {
  SpeciesInterface speciesInterface = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  PhysicalCharacteristicsInterface physicalCharacteristicsInterface =
      new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");
  PersonalFeaturesInterface personalFeaturesInterface =
      new PersonalFeatures(false, Danger.ENDANGERED, false);
  HerpetologyAnimals herpetologyAnimals;

  /**
   * Sets up object that other functions will use.
   */
  @Before
  public void setUp() {
    herpetologyAnimals =
        new HerpetologyAnimals(
            speciesInterface, physicalCharacteristicsInterface, personalFeaturesInterface);
  }

  @Test
  public void testConstructor() {
    assertEquals(speciesInterface, herpetologyAnimals.getSpecies());
    assertEquals(physicalCharacteristicsInterface, herpetologyAnimals.getPhysicalCharacteristics());
    assertEquals(personalFeaturesInterface, herpetologyAnimals.getPersonalFeatures());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new HerpetologyAnimals(null, physicalCharacteristicsInterface, personalFeaturesInterface);
  }

  @Test
  public void testToString() {
    assertEquals(
        "Species: frog, Type: AMPHIBIAN, Defining Characteristics: broken leg, Temperature Range: "
            + "30 - 33, Preferred Natural Feature: water, "
            + "Size: LARGE, Danger State: ENDANGERED, Poisonous: false, Cohabitate: false",
        herpetologyAnimals.toString());
  }
}
