import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import wearable.AbstractWearable;
import wearable.DefenceWearable;
import wearable.Wearable;
import wearable.handgear.HandGear;
import wearable.handgear.HandGearName;
import wearable.headgear.HeadGear;
import wearable.headgear.HeadGearName;


/** Tests the headgear class for correctness. */
public class HeadGearTest {
  Wearable headGear;
  Wearable cursedHeadGear;
  Wearable headGearClassTest;

  /** Sets up a headgear object that's to be used by other test functions. */
  @Before
  public void setUp() throws Exception {
    headGear = new HeadGear(HeadGearName.HATS, " of speed", true, 100);
    cursedHeadGear = new HeadGear(HeadGearName.HATS, " of speed", true, -100);
    headGearClassTest = new HeadGear(HeadGearName.HATS, " of speed", false, 120);
  }

  @Test
  public void testFootWearCreation() {
    assertEquals(
        "Name: HATS, Description:  of speed, DefensePower: 100, Wears Out: true ",
        headGear.toString());
  }

  @Test
  public void isHeadGear() {
    AbstractWearable headGear = new HeadGear(HeadGearName.HATS, " of speed", false, 100);
    assertTrue(headGear.isHeadGear());
  }

  @Test
  public void getItemName() {
    assertEquals("HATS", headGear.getItemName());
  }

  @Test
  public void wearOut() {
    headGear.wearOut();
    cursedHeadGear.wearOut();
    DefenceWearable defenseHeadGear = (DefenceWearable) headGear;
    DefenceWearable cursedDefenseHeadGear = (DefenceWearable) cursedHeadGear;
    assertEquals(80, defenseHeadGear.getDefensePower());
    assertEquals(-120, cursedDefenseHeadGear.getDefensePower());
  }

  @Test
  public void isSameClass() {
    AbstractWearable handGear = new HandGear(HandGearName.GLOVE, "of hate", false, 100, 100);
    assertTrue(headGearClassTest.isSameClass(headGear));
    assertFalse(headGearClassTest.isSameClass(handGear));
  }

  @Test
  public void compareTo() {
    Wearable headGearSameCompare = new HeadGear(HeadGearName.HATS, " of speed", false, 100);
    Wearable headGearLessCompare = new HeadGear(HeadGearName.HATS, " of speed", false, 10);
    assertEquals(1, headGearClassTest.compareTo(headGear));
    assertEquals(0, headGearSameCompare.compareTo(headGear));
    assertEquals(-1, headGearLessCompare.compareTo(headGear));
  }

  @Test
  public void compareToFootWear() {}

  @Test
  public void compareToHandGear() {}

  @Test
  public void getDefensePower() {
    DefenceWearable defenseGear = new HeadGear(HeadGearName.HATS, " of speed", false, 100);
    assertEquals(100, defenseGear.getDefensePower());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullName() {
    new HeadGear(null, " of speed", false, 100);
  }
}
