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
    

  }

  @Test
  public void decreaseSpeed() {
    Transmission newTransmission = transmission.decreaseSpeed();
    assertEquals(0,transmission.getSpeed());
    assertEquals(0, transmission.getGear());
    assertEquals(0, newTransmission.getGear());
    assertEquals(0, newTransmission.getSpeed());

  }
}



