import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.junit.Before;

import transmission.AutomaticTransmission;
import transmission.Transmission;

/** Test all functions in the AutomaticTransmission Class. */
public class AutomaticTransmissionTest {
  Transmission transmission;

  /**
   * Tests for error free object creation and also uses the object for other tests.
   *
   * @throws Exception if any errors is encountered.
   */
  @Before
  public void setUp()  {
    transmission = new AutomaticTransmission(3, 4, 6, 8, 10);
  }

  /** Test if the arguments arent in increasing order. */
  @Test(expected = IllegalArgumentException.class)
  public void testWrongOrderOfArguments() {
    new AutomaticTransmission(3, 1, 6, 8, 9);
  }

  /** Test if any argument is negative. */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeArgument() {
    new AutomaticTransmission(2, 3, 6, 8, -9);
  }

  /** Test if any argument is zero. */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroAsArgument() {
    new AutomaticTransmission(0, 3, 6, 8, 9);
  }

  /** Tests the get speed function. */
  @Test
  public void getSpeed() {
    assertEquals(0, transmission.getSpeed());
  }
  /** Tests the get gear function. */

  @Test public void getGear() {
    assertEquals(0, transmission.getGear());
  }

  /** Tests the increase speed function. */
  @Test
  public void increaseSpeed() {
    Transmission newTransmission = transmission.increaseSpeed();
    assertEquals(2, newTransmission.getSpeed());
    assertEquals(1, newTransmission.getGear());
    Transmission newTransmission1 = newTransmission.increaseSpeed();
    assertEquals(4, newTransmission1.getSpeed());
    assertEquals(3, newTransmission1.getGear());
  }
  /** Tests the decrease speed function. */

  @Test public void decreaseSpeed() {
    Transmission newTransmission = transmission.increaseSpeed().increaseSpeed().increaseSpeed();
    assertEquals(6, newTransmission.getSpeed());
    assertEquals(4, newTransmission.getGear());
    Transmission decTransmission = newTransmission.decreaseSpeed();
    assertEquals(4, decTransmission.getSpeed());
    assertEquals(3, decTransmission.getGear());
    Transmission decTransmission2 = decTransmission.decreaseSpeed();
    assertEquals(2, decTransmission2.getSpeed());
    assertEquals(1, decTransmission2.getGear());
  }

  /** Tests if speed can fall below 0. */
  @Test(expected = IllegalStateException.class)
  public void testIllegalSpeed() {
    transmission.decreaseSpeed();
  }

  /** Tests the toString function. */
  @Test
  public void toStringTest() {
    Transmission newTransmission = transmission.increaseSpeed();
    assertEquals("Transmission (speed = 2, gear = 1)", newTransmission.toString());
  }
}
