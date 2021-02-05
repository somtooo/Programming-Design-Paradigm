import static org.junit.Assert.assertEquals;

import animals.Danger;
import animals.HerpetologyAnimals;
import animals.PersonalFeatures;
import animals.PhysicalCharacteristics;
import animals.SizeofSpecies;
import animals.Species;
import animals.TypeOfSpecies;
import java.time.temporal.ValueRange;
import org.junit.Before;
import org.junit.Test;

/** Tests the HerpetologyAnimal class methods and implementation for correctness. */
public class HerpetologyAnimalsTest {
  Species species = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  PhysicalCharacteristics physicalCharacteristics =
      new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");
  PersonalFeatures personalFeatures = new PersonalFeatures(false, Danger.ENDANGERED, false);
  HerpetologyAnimals herpetologyAnimals;

  @Before
  public void setUp() {
    herpetologyAnimals = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
  }

  @Test
  public void testConstructor() {
    assertEquals(species, herpetologyAnimals.getSpecies());
    assertEquals(physicalCharacteristics, herpetologyAnimals.getPhysicalCharacteristics());
    assertEquals(personalFeatures, herpetologyAnimals.getPersonalFeatures());
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
