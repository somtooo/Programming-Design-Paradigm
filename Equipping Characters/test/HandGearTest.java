import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import wearable.AbstractWearable;
import wearable.AttackWearable;
import wearable.DefenceWearable;
import wearable.Wearable;
import wearable.handgear.HandGear;
import wearable.handgear.HandGearName;
import wearable.headgear.HeadGear;
import wearable.headgear.HeadGearName;



/** Tests the hand gear class for correctness. */
public class HandGearTest {

  Wearable handGear;
  Wearable cursedHandGear;
  Wearable handGearToCompare;
  AttackWearable attackHandGear;
  DefenceWearable defenseHandGear;
  Wearable sameHandGear;
  Wearable lowerHandGear;

  /** Sets up a jewelry object that's to be used by other test functions. */
  @Before
  public void setUp() throws Exception {
    handGear = new HandGear(HandGearName.GLOVE, "of love", true, 100, 100);
    cursedHandGear = new HandGear(HandGearName.GLOVE, "of love", true, -100, -100);
    handGearToCompare = new HandGear(HandGearName.GLOVE, "of love", false, 110, 120);
    attackHandGear = new HandGear(HandGearName.GLOVE, "of attack", false, 100, 100);
    defenseHandGear = new HandGear(HandGearName.GLOVE, "of defense", false, 100, 100);
    sameHandGear = new HandGear(HandGearName.GLOVE, "of love", false, 100, 100);
    lowerHandGear = new HandGear(HandGearName.GLOVE, "of love", false, 10, 100);
  }

  @Test
  public void testFootWearCreation() {
    assertEquals(
        "Name: GLOVE, Description: of love, AttackPower: 100, DefensePower: 100, Wears Out: true ",
        handGear.toString());
  }

  @Test
  public void getItemName() {
    assertEquals("GLOVE", handGear.getItemName());
  }

  @Test
  public void wearOut() {
    handGear.wearOut();
    cursedHandGear.wearOut();
    AttackWearable attackHandGear = (AttackWearable) handGear;
    DefenceWearable defenseHandGear = (DefenceWearable) handGear;
    AttackWearable cursedAttackHandGear = (AttackWearable) cursedHandGear;
    assertEquals(60, attackHandGear.getAttackPower());
    DefenceWearable cursedDefenseHandGear = (DefenceWearable) cursedHandGear;
    assertEquals(90, defenseHandGear.getDefensePower());
    assertEquals(-140, cursedAttackHandGear.getAttackPower());
    assertEquals(-110, cursedDefenseHandGear.getDefensePower());
  }

  @Test
  public void isSameClass() {
    Wearable headGear = new HeadGear(HeadGearName.HATS, "of light", false, 40);
    assertTrue(handGearToCompare.isSameClass(handGear));
    assertFalse(handGearToCompare.isSameClass(headGear));
  }

  @Test
  public void isJewelry() {
    AbstractWearable handGear = new HandGear(HandGearName.GLOVE, "of love", false, 100, 100);
    assertTrue(handGear.isHandGear());
  }

  @Test
  public void compareTo() {
    assertEquals(1, handGearToCompare.compareTo(handGear));
    assertEquals(0, sameHandGear.compareTo(handGear));
    assertEquals(-1, lowerHandGear.compareTo(handGear));
  }

  @Test
  public void getAttackPower() {
    assertEquals(100, attackHandGear.getAttackPower());
  }

  @Test
  public void getDefensePower() {
    assertEquals(100, defenseHandGear.getDefensePower());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullName() {
    new HandGear(null, " of love", false, 100, 100);
  }

  @Test
  public void testToString() {
    assertEquals(
        "Name: GLOVE, Description: of love, AttackPower: 100, DefensePower: 100, Wears Out: true ",
        handGear.toString());
  }
}
