import static org.junit.Assert.assertEquals;

import animals.Danger;
import animals.PersonalFeatures;
import animals.PersonalFeaturesInterface;
import org.junit.Before;
import org.junit.Test;

/**
 * Test all methods for Personal Features Class.
 */

public class PersonalFeaturesTest {
  PersonalFeaturesInterface personalFeaturesInterface;

  /**
   * Sets up object so other function can reuse them.
   */
  @Before
  public void setUp() {
    personalFeaturesInterface = new PersonalFeatures(false, Danger.ENDANGERED, false);
  }

  @Test
  public void testConstructor() {
    assertEquals(false, personalFeaturesInterface.getPoisonous());
    assertEquals(Danger.ENDANGERED, personalFeaturesInterface.getDangerState());
    assertEquals(false, personalFeaturesInterface.getCanCohabitate());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new PersonalFeatures(false, null, null);
  }

  @Test
  public void testToString() {
    assertEquals(
        "Danger State: ENDANGERED, Poisonous: false, Cohabitate: false",
        personalFeaturesInterface.toString());
  }
}
