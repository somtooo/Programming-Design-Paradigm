import character.Character;
import org.junit.Before;
import org.junit.Test;
import wearable.Wearable;
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
 * Tests the character class for correctness.
 */
public class CharacterTest {
    Character character;

    /**
     * Sets up character Objects to be used by other test functions
     */
    @Before
    public void setUp() {
        character = new Character("John",50,50);
    }

    @Test
    public void testCreateCharacter() {
    assertEquals(
        "I am John my current gear load out is\n"
            + " empty because i haven't been dressed yetI have an Attack Power of 50 \n"
            + "and a Defense Power of 50 which brings my Hit Points to 100, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }
    @Test
    public void getHitPoints() {
        assertEquals(50,character.getHitPoints());
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of flight",false,100);
        character.addToHeadSlot(headGear);
        character.calculateTotalAttackAndDefense();
        assertEquals(150,character.getHitPoints());
    }

    @Test
    public void getAttackPower() {
        assertEquals(50,character.getAttackPower());
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of flight",false,100);
        character.addToHeadSlot(headGear);
        character.calculateTotalAttackAndDefense();
        assertEquals(50,character.getAttackPower());
        Wearable handGear = new HandGear(HandGearName.GLOVE,"of flight",false,100,100);
        character.addToHeadSlot(handGear);
        character.calculateTotalAttackAndDefense();
        assertEquals(150,character.getAttackPower());
    }

    @Test
    public void getDefensePower() {
        assertEquals(50,character.getDefensePower());
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of flight",false,100);
        character.addToHeadSlot(headGear);
        character.calculateTotalAttackAndDefense();
        assertEquals(150,character.getDefensePower());
    }

    @Test
    public void getName() {
        assertEquals("John",character.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCharacterNullTest() {
        new Character(null,50,50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCharacterEmptyString() {
        new Character("",50,50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCharacterNegativeValue() {
        new Character("John",-50,50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCharacterNegativeValueSecondValue() {
        new Character("John",50,-50);
    }



    @Test
    public void addToHeadSlot() {
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of flight",false,100);
        character.addToHeadSlot(headGear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + " HATS of flight.     I have an Attack Power of 50 \n"
            + "and a Defense Power of 150 which brings my Hit Points to 200, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void testAddBetterGearToHeadSlot() {
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of flight",false,100);
        Wearable betterHeadGear = new HeadGear(HeadGearName.HATS,"of flight",false,120);
        character.addToHeadSlot(headGear);
        character.addToHeadSlot(betterHeadGear);
        character.calculateTotalAttackAndDefense();
        assertEquals(
                "I am John my current gear load out is\n"
                        + " HATS of flight.     I have an Attack Power of 50 \n"
                        + "and a Defense Power of 170 which brings my Hit Points to 220, trust me I am gonna\n"
                        + " beat the Java out of you!!!!",
                character.toString());
    }

    @Test
    public void testAddWorseGearToHeadSlot() {
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of flight",false,100);
        Wearable betterHeadGear = new HeadGear(HeadGearName.HATS,"of flight",false,20);
        character.addToHeadSlot(headGear);
        character.addToHeadSlot(betterHeadGear);
        character.calculateTotalAttackAndDefense();
        assertEquals(
                "I am John my current gear load out is\n"
                        + " HATS of flight.     I have an Attack Power of 50 \n"
                        + "and a Defense Power of 150 which brings my Hit Points to 200, trust me I am gonna\n"
                        + " beat the Java out of you!!!!",
                character.toString());
    }


    @Test
    public void addToFeetSlot() {
        Wearable footWear = new FootWear(FootWearName.BOOTS,"of flight",false,100);
        character.addToFeetSlot(footWear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + "  BOOTS of flight.    I have an Attack Power of 150 \n"
            + "and a Defense Power of 50 which brings my Hit Points to 100, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void addBetterGearToFeetSlot() {
        Wearable footWear = new FootWear(FootWearName.BOOTS,"of flight",false,100);
        Wearable betterFootWear = new FootWear(FootWearName.HOVERBOARD,"of heft",false,120);
        character.addToFeetSlot(footWear);
        character.addToFeetSlot(footWear);
        character.addToFeetSlot(betterFootWear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + "  BOOTS of flight and of heft.    I have an Attack Power of 270 \n"
            + "and a Defense Power of 50 which brings my Hit Points to 100, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void addWorseGearToFeetSlot() {
        Wearable footWear = new FootWear(FootWearName.BOOTS,"of flight",false,100);
        Wearable worseFootWear = new FootWear(FootWearName.HOVERBOARD,"of heft",false,20);
        character.addToFeetSlot(footWear);
        character.addToFeetSlot(footWear);
        character.addToFeetSlot(worseFootWear);
        character.calculateTotalAttackAndDefense();
        assertEquals(
                "I am John my current gear load out is\n"
                        + "  BOOTS of flight and of flight.    I have an Attack Power of 250 \n"
                        + "and a Defense Power of 50 which brings my Hit Points to 100, trust me I am gonna\n"
                        + " beat the Java out of you!!!!",
                character.toString());
    }


    @Test
    public void addToHandSlot() {
        Wearable handGear = new HandGear(HandGearName.GLOVE,"of love",false,100,100);
        character.addToHandSlot(handGear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + "   GLOVE of love.   I have an Attack Power of 150 \n"
            + "and a Defense Power of 150 which brings my Hit Points to 200, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void addBetterGearToHandSlot() {
        Wearable handGear = new HandGear(HandGearName.GLOVE,"of love",false,100,100);
        Wearable betterHandGear = new HandGear(HandGearName.RING,"of strength",false,120,100);
        for (int index = 0; index<10;index++) {
            character.addToHandSlot(handGear);
        }
       character.addToHandSlot(betterHandGear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + "   GLOVE of love,  of love,  of love,  of love,  of love,  of love,  of love,  of love,  of love and of strength.   I have an Attack Power of 1070 \n"
            + "and a Defense Power of 1050 which brings my Hit Points to 1100, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void addWorseGearToHandSlot() {
        Wearable handGear = new HandGear(HandGearName.GLOVE,"of love",false,100,100);
        Wearable worseHandGear = new HandGear(HandGearName.RING,"of strength",false,20,100);
        for (int index = 0; index<10;index++) {
            character.addToHandSlot(handGear);
        }
        character.addToHandSlot(worseHandGear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + "   GLOVE of love,  of love,  of love,  of love,  of love,  of love,  of love,  of love,  of love and of love.   I have an Attack Power of 1050 \n"
            + "and a Defense Power of 1050 which brings my Hit Points to 1100, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void calculateTotalAttackAndDefense() {
        Wearable handGear = new HandGear(HandGearName.GLOVE,"of love",false,100,100);
        character.addToHandSlot(handGear);
        character.calculateTotalAttackAndDefense();
        assertEquals(150,character.getDefensePower());
        assertEquals(150,character.getAttackPower());
        Wearable footWear = new FootWear(FootWearName.BOOTS,"of flight",false,100);
        character.addToFeetSlot(footWear);
        character.calculateTotalAttackAndDefense();
        assertEquals(250,character.getAttackPower());
        assertEquals(150,character.getDefensePower());
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of flight",false,100);
        character.addToHeadSlot(headGear);
        character.calculateTotalAttackAndDefense();
        assertEquals(250,character.getAttackPower());
        assertEquals(250,character.getDefensePower());
    }

    @Test
    public void addToNeckSlot() {
        Wearable Jewelry = new Jewelry(JewelryName.AMULET,"of love",false,100,100);
        character.addToNeckSlot(Jewelry);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + "    AMULET of love.  I have an Attack Power of 150 \n"
            + "and a Defense Power of 150 which brings my Hit Points to 150, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void wearOutWearableItems() {
        Wearable Jewelry = new Jewelry(JewelryName.AMULET,"of love",true,100,100);
        character.addToNeckSlot(Jewelry);
        character.calculateTotalAttackAndDefense();
        assertEquals(
                "I am John my current gear load out is\n"
                        + "    AMULET of love.  I have an Attack Power of 150 \n"
                        + "and a Defense Power of 150 which brings my Hit Points to 150, trust me I am gonna\n"
                        + " beat the Java out of you!!!!",
                character.toString());
        character.wearOutWearableItems();
    assertEquals(
        "I am John my current gear load out is\n"
            + "    AMULET of love.  I have an Attack Power of 120 \n"
            + "and a Defense Power of 130 which brings my Hit Points to 130, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void testToString() {
        Wearable jewelry = new Jewelry(JewelryName.AMULET,"of love",true,100,100);
        Wearable handGear = new HandGear(HandGearName.GLOVE,"of hate",false,100,100);
        Wearable footWear = new FootWear(FootWearName.BOOTS,"of flight",false,100);
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of total defense",false,100);
        character.addToHeadSlot(headGear);
        character.addToFeetSlot(footWear);
        character.addToNeckSlot(jewelry);
        character.addToHandSlot(handGear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + " HATS of total defense.  BOOTS of flight.  GLOVE of hate.  AMULET of love.  I have an Attack Power of 350 \n"
            + "and a Defense Power of 350 which brings my Hit Points to 350, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void describeWearable() {
        Wearable jewelry = new Jewelry(JewelryName.AMULET,"of love",true,100,100);
        Wearable handGear = new HandGear(HandGearName.GLOVE,"of hate",false,100,100);
        Wearable footWear = new FootWear(FootWearName.BOOTS,"of flight",false,100);
        Wearable secondFootWear = new FootWear(FootWearName.HOVERBOARD,"of flight",false,-30);
        Wearable headGear = new HeadGear(HeadGearName.HATS,"of total defense",false,100);
        character.addToHeadSlot(headGear);
        character.addToFeetSlot(footWear);
        character.addToNeckSlot(jewelry);
        character.addToHandSlot(handGear);
        character.addToFeetSlot(secondFootWear);
        character.calculateTotalAttackAndDefense();

    assertEquals(
        "I am John my current gear load out is\n"
            + " HATS of total defense.  BOOTS of flight and of flight.  GLOVE of hate.  AMULET of love.  I have an Attack Power of 320 \n"
            + "and a Defense Power of 350 which brings my Hit Points to 350, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }

    @Test
    public void testCursedItemReducesStrength() {
        Wearable footWear = new FootWear(FootWearName.BOOTS,"of flight",false,100);
        Wearable handGear = new HandGear(HandGearName.GLOVE,"of hate",false,100,100);
        character.addToFeetSlot(footWear);
        character.addToHandSlot(handGear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + "  BOOTS of flight.  GLOVE of hate.   I have an Attack Power of 250 \n"
            + "and a Defense Power of 150 which brings my Hit Points to 250, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
        Wearable cursedFootWear = new FootWear(FootWearName.HOVERBOARD,"of flight",false,-130);
        character.addToFeetSlot(cursedFootWear);
        character.calculateTotalAttackAndDefense();
    assertEquals(
        "I am John my current gear load out is\n"
            + "  BOOTS of flight and of flight.  GLOVE of hate.   I have an Attack Power of 120 \n"
            + "and a Defense Power of 150 which brings my Hit Points to 250, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        character.toString());
    }
}