import org.junit.Before;
import org.junit.Test;
import wearable.AbstractWearable;
import wearable.AttackWearable;
import wearable.DefenceWearable;
import wearable.Wearable;
import wearable.footWear.FootWear;
import wearable.handGear.HandGear;
import wearable.headGear.HeadGear;
import wearable.headGear.HeadGearName;
import wearable.jewelry.Jewelry;
import wearable.jewelry.JewelryName;

import static org.junit.Assert.*;

/**
 * Tests the jewelry class for correctness.
 */
public class JewelryTest {

    Wearable jewelry;
    Wearable cursedJewelry;
    Wearable jewelryToCompare;
    AttackWearable attackJewelry;
    DefenceWearable defenseJewelry;
    Wearable sameJewelry;
    Wearable lowerJewelry;

    /**
     * Sets up a jewelry object that's to be used by other test functions.
     */
    @Before
    public void setUp() throws Exception {
        jewelry =  new Jewelry(JewelryName.AMULET, "of love",true,100,100);
        cursedJewelry =  new Jewelry(JewelryName.AMULET, "of love",true,-100,-100);
        jewelryToCompare= new Jewelry(JewelryName.AMULET, "of love",false,110,120);
        attackJewelry= new Jewelry(JewelryName.AMULET, "of attack",false,100,100);
        defenseJewelry= new Jewelry(JewelryName.AMULET, "of defense",false,100,100);
        sameJewelry= new Jewelry(JewelryName.AMULET, "of love",false,100,100);
        lowerJewelry= new Jewelry(JewelryName.AMULET, "of love",false,10,100);
    }

    @Test
    public void testFootWearCreation() {
        assertEquals("Name: AMULET, Description: of love, AttackPower: 100, DefensePower: 100, Wears Out: true ",jewelry.toString());
    }

    @Test
    public void getItemName() {
        assertEquals("AMULET",jewelry.getItemName());
    }

    @Test
    public void wearOut() {
        jewelry.wearOut();
        cursedJewelry.wearOut();
        AttackWearable attackCursedJewelry = (AttackWearable) cursedJewelry;
        DefenceWearable defenseCursedJewelry = (DefenceWearable) cursedJewelry;
        AttackWearable attackJewelry = (AttackWearable) jewelry;
        DefenceWearable defenseJewelry = (DefenceWearable) jewelry;

        assertEquals(70,attackJewelry.getAttackPower());
        assertEquals(80,defenseJewelry.getDefensePower());
        assertEquals(-130,attackCursedJewelry.getAttackPower());
        assertEquals(-120,defenseCursedJewelry.getDefensePower());
    }

    @Test
    public void isSameClass() {
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of light",false,40);
        assertTrue(jewelryToCompare.isSameClass(jewelry));
        assertFalse(jewelryToCompare.isSameClass(headGear));
    }

    @Test
    public void isJewelry() {
        AbstractWearable jewelry = new Jewelry(JewelryName.AMULET, "of love",false,100,100);
        assertTrue(jewelry.isJewelry());
    }

    @Test
    public void compareTo() {
        assertEquals(1,jewelryToCompare.compareTo(jewelry));
        assertEquals(0,sameJewelry.compareTo(jewelry));
        assertEquals(-1, lowerJewelry.compareTo(jewelry));
    }

    @Test
    public void getAttackPower() {
        assertEquals(100, attackJewelry.getAttackPower());

    }

    @Test
    public void getDefensePower() {
        assertEquals(100,defenseJewelry.getDefensePower());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForNullName() {
        new Jewelry(null, "of flight",false,100,100);
    }



}