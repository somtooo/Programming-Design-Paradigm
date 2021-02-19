import org.junit.Before;
import org.junit.Test;
import wearable.AbstractWearable;
import wearable.footWear.FootWear;
import wearable.footWear.FootWearName;
import wearable.handGear.HandGear;
import wearable.handGear.HandGearName;
import wearable.headGear.HeadGear;
import wearable.headGear.HeadGearName;
import wearable.jewelry.Jewelry;
import wearable.jewelry.JewelryName;

import static org.junit.Assert.*;

/**
 * Tests the abstract wearable class for correctness.
 */
public class AbstractWearableTest {
    AbstractWearable abstractHeadGear;
    AbstractWearable abstractFootWear;
    AbstractWearable abstractHandGear;
    AbstractWearable abstractJewelry;

    /**
     * Sets up object from abstract wearable to be used in other test functions.
     */
    @Before
    public void setUp(){
        abstractHeadGear = new HeadGear(HeadGearName.HATS,"of power",false,100);
        abstractHandGear = new HandGear(HandGearName.GLOVE,"of hope",false,100,100);
        abstractFootWear = new FootWear(FootWearName.BOOTS, "of levitation",true,100);
        abstractJewelry = new Jewelry(JewelryName.AMULET, "of shine",true,100,100);
    }

    @Test
    public void abstractWearableCreationTest() {
        assertEquals("of power",abstractHeadGear.getItemDescription());
        assertFalse(abstractHeadGear.isWearOut());
        assertEquals("of hope",abstractHandGear.getItemDescription());
        assertFalse(abstractHandGear.isWearOut());
        assertEquals("of levitation",abstractFootWear.getItemDescription());
        assertTrue(abstractFootWear.isWearOut());
        assertEquals("of shine", abstractJewelry.getItemDescription());
        assertTrue(abstractJewelry.isWearOut());
    }

    @Test
    public void isHeadGear() {
        assertFalse(abstractHandGear.isHeadGear());
        assertFalse(abstractJewelry.isHeadGear());
        assertFalse(abstractFootWear.isHeadGear());
        assertTrue(abstractHeadGear.isHeadGear());
    }

    @Test
    public void isFootWear() {
        assertFalse(abstractHandGear.isFootWear());
        assertFalse(abstractHeadGear.isFootWear());
        assertFalse(abstractJewelry.isFootWear());
        assertTrue(abstractFootWear.isFootWear());
    }

    @Test
    public void isHandGear() {
        assertFalse(abstractFootWear.isHandGear());
        assertFalse(abstractJewelry.isHandGear());
        assertFalse(abstractHeadGear.isHandGear());
        assertTrue(abstractHandGear.isHandGear());
    }

    @Test
    public void isJewelry() {
        assertTrue(abstractJewelry.isJewelry());
        assertFalse(abstractHandGear.isJewelry());
        assertFalse(abstractHeadGear.isJewelry());
        assertFalse(abstractFootWear.isJewelry());
    }

    @Test
    public void compareToHeadGear() {
        assertEquals(-1,abstractFootWear.compareToHeadGear(abstractFootWear));
        assertEquals(-1,abstractHandGear.compareToHeadGear(abstractHeadGear));
        assertEquals(-1,abstractJewelry.compareToHeadGear(abstractJewelry));
    }

    @Test
    public void compareToFootWear() {
        assertEquals(-1,abstractJewelry.compareToFootWear(abstractJewelry));
        assertEquals(1,abstractHeadGear.compareToFootWear(abstractHandGear));
        assertEquals(-1, abstractHandGear.compareToFootWear(abstractHandGear));
    }

    @Test
    public void compareToHandGear() {
        assertEquals(1,abstractFootWear.compareToHandGear(abstractFootWear));
        assertEquals(-1,abstractJewelry.compareToHandGear(abstractJewelry));
        assertEquals(1,abstractHeadGear.compareToHandGear(abstractHeadGear));
    }

    @Test
    public void compareToJewelry() {
        assertEquals(1,abstractHeadGear.compareToJewelry(abstractHeadGear));
        assertEquals(1,abstractFootWear.compareToJewelry(abstractFootWear));
        assertEquals(1, abstractHandGear.compareToJewelry(abstractHandGear));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForNullDescription() {
        AbstractWearable wearable = new HeadGear(HeadGearName.HATS,null, false,100);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testForEmptyDescription() {
        AbstractWearable wearable = new HeadGear(HeadGearName.HATS," ", false,100);
    }

}