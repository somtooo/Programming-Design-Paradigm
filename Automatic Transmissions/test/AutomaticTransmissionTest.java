import org.junit.Before;
import org.junit.Test;
import transmission.AutomaticTransmission;
import transmission.Transmission;

import static org.junit.Assert.assertEquals;

public class AutomaticTransmissionTest {
  Transmission transmission;

  @Before
  public void setUp() throws Exception {
    transmission = new AutomaticTransmission(3,4,6,8,10);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testWrongOrderOfArguments() {
    new AutomaticTransmission(3,1,6,8,9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeArgument() {
    new AutomaticTransmission(2,3,6,8,-9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroAsArgument() {
    new AutomaticTransmission(0,3,6,8,9);
  }


  @Test
  public void getSpeed() {
    assertEquals(0,transmission.getSpeed());
  }

  @Test
  public void getGear() {
    assertEquals(0, transmission.getGear());
  }

  @Test
  public void increaseSpeed() {
    Transmission newTransmission = transmission.increaseSpeed();
    assertEquals(2, newTransmission.getSpeed());
    assertEquals(1, newTransmission.getGear());
    Transmission newTransmission1 = newTransmission.increaseSpeed();
    assertEquals(4, newTransmission1.getSpeed());
    assertEquals(3, newTransmission1.getGear());

  }

  @Test
  public void decreaseSpeed() {
    Transmission newTransmission = transmission.increaseSpeed().increaseSpeed().increaseSpeed();
    assertEquals(6,newTransmission.getSpeed());
    assertEquals(4, newTransmission.getGear());
    Transmission decTransmission = newTransmission.decreaseSpeed();
    assertEquals(4,decTransmission.getSpeed());
    assertEquals(3,decTransmission.getGear());
    Transmission decTransmission2 = decTransmission.decreaseSpeed();
    assertEquals(2,decTransmission2.getSpeed());
    assertEquals(1,decTransmission2.getGear());
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalSpeed() {
    transmission.decreaseSpeed();
  }


}



