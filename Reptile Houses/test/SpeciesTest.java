import static org.junit.Assert.assertEquals;

import animals.Species;
import animals.SpeciesInterface;
import animals.TypeOfSpecies;
import org.junit.Before;
import org.junit.Test;



/** Test methods for Species Class. */
public class SpeciesTest {
  SpeciesInterface speciesInterface;

  @Before
  public void setUp() {
    speciesInterface = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  }

  @Test
  public void testConstructor() {
    SpeciesInterface reptileSpeciesInterface =
        new Species("turtle", TypeOfSpecies.REPTILE, "overweight");
    assertEquals("turtle", reptileSpeciesInterface.getSpeciesName());
    assertEquals(TypeOfSpecies.REPTILE, reptileSpeciesInterface.getSpeciesType());
    assertEquals("overweight", reptileSpeciesInterface.getDefiningCharacteristics());
    SpeciesInterface amphibiansSpeciesInterface =
        new Species("salamander", TypeOfSpecies.AMPHIBIAN, "special skin");
    assertEquals("salamander", amphibiansSpeciesInterface.getSpeciesName());
    assertEquals(TypeOfSpecies.AMPHIBIAN, amphibiansSpeciesInterface.getSpeciesType());
    assertEquals("special skin", amphibiansSpeciesInterface.getDefiningCharacteristics());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSpeciesType() {
    new Species("", TypeOfSpecies.REPTILE, "very fat");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgument() {
    new Species(null, TypeOfSpecies.REPTILE, null);
  }

  @Test
  public void testToString() {
    assertEquals(
        "Species: frog, Type: AMPHIBIAN, Defining Characteristics: broken leg",
        speciesInterface.toString());
  }
}
