import static org.junit.Assert.assertEquals;

import animals.PhysicalCharacteristics;
import animals.SizeofSpecies;
import java.time.temporal.ValueRange;
import org.junit.Before;
import org.junit.Test;


/**
 * Test's the methods and implementation of the PhysicalCharacteristics class.
 */
public class PhysicalCharacteristicsTest {
  PhysicalCharacteristics physicalCharacteristics;

  @Before
  public void setUp() {
    physicalCharacteristics =
        new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    new PhysicalCharacteristics(SizeofSpecies.SMALL, ValueRange.of(20, 25), "");
  }

  @Test
  public void testConstructor() {
    assertEquals(SizeofSpecies.LARGE, physicalCharacteristics.getSize());
    ValueRange valueRange = ValueRange.of(30, 33);
    assertEquals(valueRange, physicalCharacteristics.getTemperature());
    assertEquals("water", physicalCharacteristics.getNaturalFeature());
  }

  @Test
  public void testToString() {
    assertEquals(
        "Temperature Range: 30 - 33, Preferred Natural Feature: water, Size: LARGE",
        physicalCharacteristics.toString());
  }
}
