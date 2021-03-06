import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import wearable.AbstractWearable;
import wearable.AttackWearable;
import wearable.Wearable;
import wearable.footwear.FootWear;
import wearable.footwear.FootWearName;
import wearable.headgear.HeadGear;
import wearable.headgear.HeadGearName;

/** Tests the footwear class for correctness. */
public class FootWearTest {
  Wearable footwear;
  Wearable footWearClassTest;
  Wearable cursedFootWear;

  /** Sets up a footwear object that's to be used by other test functions. */
  @Before
  public void setUp() {
    footwear = new FootWear(FootWearName.BOOTS, " of speed", true, 100);
    cursedFootWear = new FootWear(FootWearName.BOOTS, " of speed", true, -100);
    footWearClassTest = new FootWear(FootWearName.BOOTS, " of speed", false, 120);
  }

  @Test
  public void testFootWearCreation() {
    assertEquals(
        "Name: BOOTS, Description:  of speed, AttackPower: 100, Wears Out: true ",
        footwear.toString());
  }

  @Test
  public void getItemName() {
    assertEquals("BOOTS", footwear.getItemName());
  }

  @Test
  public void wearOut() {
    footwear.wearOut();
    cursedFootWear.wearOut();
    AttackWearable attackFootWear = (AttackWearable) footwear;
    AttackWearable cursedAttackFootWear = (AttackWearable) cursedFootWear;
    assertEquals(70, attackFootWear.getAttackPower());
    assertEquals(-130, cursedAttackFootWear.getAttackPower());
  }

  @Test
  public void isSameClass() {
    Wearable headGear = new HeadGear(HeadGearName.HATS, "of light", false, 40);
    assertTrue(footWearClassTest.isSameClass(footwear));
    assertFalse(footWearClassTest.isSameClass(headGear));
  }

  @Test
  public void compareTo() {
    Wearable footWearSameCompare = new FootWear(FootWearName.BOOTS, " of speed", false, 100);
    Wearable footWearLessCompare = new FootWear(FootWearName.BOOTS, " of speed", false, 10);
    assertEquals(1, footWearClassTest.compareTo(footwear));
    assertEquals(0, footWearSameCompare.compareTo(footwear));
    assertEquals(-1, footWearLessCompare.compareTo(footwear));
  }

  @Test
  public void isFootWear() {
    AbstractWearable footwear = new FootWear(FootWearName.BOOTS, " of speed", false, 100);
    assertTrue(footwear.isFootWear());
  }


  @Test
  public void getAttackPower() {
    AttackWearable attackFootWear = (AttackWearable) footwear;
    assertEquals(100, ((AttackWearable) footwear).getAttackPower());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalFootWearCreation() {
    new FootWear(FootWearName.BOOTS, " ", false, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullName() {
    new FootWear(null, " of speed", false, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullDescription() {
    new FootWear(FootWearName.BOOTS, null, false, 100);
  }
}
