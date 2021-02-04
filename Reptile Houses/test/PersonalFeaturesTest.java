import animals.Danger;
import animals.PersonalFeatures;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonalFeaturesTest {
  PersonalFeatures personalFeatures;

  @Before
  public void setUp() {
    personalFeatures = new PersonalFeatures(false, Danger.ENDANGERED, false);
  }

  @Test
  public void testConstructor() {
    assertEquals(false, personalFeatures.getPoisonous());
    assertEquals(Danger.ENDANGERED, personalFeatures.getDangerState());
    assertEquals(false, personalFeatures.getCanCohabitate());
  }

  @Test
  public void testToString() {
    assertEquals(
        "Danger State: ENDANGERED, Poisonous: false, Cohabitate: false",
        personalFeatures.toString());
  }
}
