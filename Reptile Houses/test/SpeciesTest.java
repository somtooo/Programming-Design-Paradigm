import static org.junit.Assert.assertEquals;

import animals.Species;
import animals.TypeOfSpecies;
import org.junit.Before;
import org.junit.Test;


/**
 * Test methods for Species Class.
 */
public class SpeciesTest {
  Species species;

  @Before
  public void setUp() {
    species = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  }

  @Test
  public void testConstructor() {
    Species reptileSpecies = new Species("turtle", TypeOfSpecies.REPTILE, "overweight");
    assertEquals("turtle", reptileSpecies.getSpeciesName());
    assertEquals(TypeOfSpecies.REPTILE, reptileSpecies.getSpeciesType());
    assertEquals("overweight", reptileSpecies.getDefiningCharacteristics());
    Species amphibiansSpecies = new Species("salamander", TypeOfSpecies.AMPHIBIAN, "special skin");
    assertEquals("salamander", amphibiansSpecies.getSpeciesName());
    assertEquals(TypeOfSpecies.AMPHIBIAN, amphibiansSpecies.getSpeciesType());
    assertEquals("special skin", amphibiansSpecies.getDefiningCharacteristics());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSpeciesType() {
    new Species("", TypeOfSpecies.REPTILE, "very fat");
  }

  @Test
  public void testToString() {
    assertEquals(
        "Species: frog, Type: AMPHIBIAN, Defining Characteristics: broken leg", species.toString());
  }
}
