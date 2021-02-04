import animals.Species;
import animals.TypeOfSpecies;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpeciesTest {
  Species species;

  @Before
  public void setUp() {
    species = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  }

  @Test
  public void testConstructor() {
    Species reptileSpecies = new Species("turtle", TypeOfSpecies.REPTILE, "overweight");
    Species amphibiansSpecies = new Species("salamander", TypeOfSpecies.AMPHIBIAN, "special skin");
    assertEquals("turtle", reptileSpecies.getSpeciesName());
    assertEquals(TypeOfSpecies.REPTILE, reptileSpecies.getSpeciesType());
    assertEquals("salamander", amphibiansSpecies.getSpeciesName());
    assertEquals(TypeOfSpecies.AMPHIBIAN, amphibiansSpecies.getSpeciesType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSpeciesType() {
    new Species("", TypeOfSpecies.REPTILE, "very fat");
  }

  @Test
  public void testToString() {
    assertEquals("Species: frog, Type: AMPHIBIAN", species.toString());
  }
}
