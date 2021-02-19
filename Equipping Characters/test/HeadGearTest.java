import org.junit.Before;
import org.junit.Test;
import wearable.AbstractWearable;
import wearable.AttackWearable;
import wearable.DefenceWearable;
import wearable.Wearable;
import wearable.footWear.FootWear;
import wearable.footWear.FootWearName;
import wearable.headGear.HeadGear;
import wearable.headGear.HeadGearName;

import static org.junit.Assert.*;

/**
 * Tests the headgear class for correctness.
 */
public class HeadGearTest {
    Wearable headGear;
    Wearable headGearClassTest;

    /**
     * Sets up a headgear object that's to be used by other test functions.
     */
    @Before
    public void setUp() throws Exception {
        headGear = new HeadGear(HeadGearName.HATS," of speed", false,100);
        headGearClassTest = new HeadGear(HeadGearName.HATS," of speed", false,120);
    }

    @Test
    public void testFootWearCreation() {
        assertEquals("Name: HATS, Description:  of speed, DefensePower: 100, Wears Out: false ", headGear.toString());
    }

    @Test
    public void isHeadGear() {
        AbstractWearable headGear =  new HeadGear(HeadGearName.HATS," of speed", false,100);
        assertTrue(headGear.isHeadGear());
    }

    @Test
    public void getItemName() {
        assertEquals("HATS", headGear.getItemName());
    }

    @Test
    public void wearOut() {
        headGear.wearOut();
        DefenceWearable defenseHeadGear = (DefenceWearable) headGear;
        assertEquals(80,defenseHeadGear.getDefensePower());
    }

    @Test
    public void isSameClass() {
        assertTrue(headGearClassTest.isSameClass(headGear));
    }

    @Test
    public void compareTo() {
        Wearable headGearSameCompare = new HeadGear(HeadGearName.HATS," of speed", false,100);
        Wearable headGearLessCompare = new HeadGear(HeadGearName.HATS," of speed", false,10);
        assertEquals(1, headGearClassTest.compareTo(headGear));
        assertEquals(0,headGearSameCompare.compareTo(headGear));
        assertEquals(-1,headGearLessCompare.compareTo(headGear));
    }

    @Test
    public void compareToFootWear() {
    }

    @Test
    public void compareToHandGear() {
    }

    @Test
    public void getDefensePower() {
        DefenceWearable defenseGear = new HeadGear(HeadGearName.HATS," of speed", false,100);
        assertEquals(100,defenseGear.getDefensePower());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForNullName() {
        new HeadGear(null," of speed", false,100);
    }
}