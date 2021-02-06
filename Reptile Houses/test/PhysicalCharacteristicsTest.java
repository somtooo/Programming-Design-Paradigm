import static org.junit.Assert.assertEquals;

import animals.PhysicalCharacteristics;
import animals.PhysicalCharacteristicsInterface;
import animals.SizeofSpecies;
import java.time.temporal.ValueRange;
import org.junit.Before;
import org.junit.Test;


/**
 * Test's the methods and implementation of the PhysicalCharacteristics class.
 */
public class PhysicalCharacteristicsTest {
  PhysicalCharacteristicsInterface physicalCharacteristicsInterface;

  /**
   * Sets up object so other function can reuse them.
   */
  @Before
  public void setUp() {
    physicalCharacteristicsInterface =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    new PhysicalCharacteristics(SizeofSpecies.SMALL, ValueRange.of(20, 25), "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    new PhysicalCharacteristics(null, null, "");
  }

  @Test
  public void testConstructor() {
    assertEquals(SizeofSpecies.LARGE, physicalCharacteristicsInterface.getSize());
    ValueRange valueRange = ValueRange.of(30, 33);
    assertEquals(valueRange, physicalCharacteristicsInterface.getTemperature());
    assertEquals("water", physicalCharacteristicsInterface.getNaturalFeature());
  }

  @Test
  public void testToString() {
    assertEquals(
        "Temperature Range: 30 - 33, Preferred Natural Feature: water, Size: LARGE",
        physicalCharacteristicsInterface.toString());
  }
}
