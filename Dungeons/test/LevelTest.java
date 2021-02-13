import static org.junit.Assert.assertEquals;

import dungeon.Level;
import dungeon.MedievalLevelBuilder;
import org.junit.Before;
import org.junit.Test;



/** Test for thr Level Class. test all methods and implementation for correctness. */
public class LevelTest {

  Level level;

  /** Set's up the level object so other functions can reuse them. */
  @Before
  public void setUp() {
    level =
        new MedievalLevelBuilder(1, 1, 1, 1)
            .addRoom("first room")
            .addGoblins(0, 1)
            .addGold(0, 1)
            .build();
  }

  @Test
  public void testConstructor() {
    assertEquals(1, level.getLevelNumber());
    assertEquals(
        "Level 1 contains 1 rooms:\n"
            + "\n"
            + "Room 0 -- first room\n"
            + "Monsters:\n"
            + "\tgoblin (hitpoints = 7) is a mischievous and very unpleasant, "
            + "vengeful, and greedy creature whose primary purpose is to cause "
            + "trouble to humankind\n"
            + "Treasures:\n"
            + "\tpieces of gold (value = 1)\n",
        level.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNegativeValue() {
    new MedievalLevelBuilder(-1, 2, 2, 2)
        .addRoom("first room")
        .addGoblins(0, 1)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddRoom() {
    new MedievalLevelBuilder(1, 1, 2, 2)
        .addRoom(" first room")
        .addRoom("second room")
        .addGoblins(0, 1)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddGoblin() {
    new MedievalLevelBuilder(0, 1, 5, 1)
        .addRoom("first room")
        .addGoblins(0, 3)
            .addOrc(0)
            .addOrc(0)
            .addOrc(0)
            .addOgre(0)
            .addPotion(0)
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddGoblinWhenNoRoom() {
    new MedievalLevelBuilder(1, 1, 5, 1)
        .addRoom(" first room")
        .addGoblins(0, 4)
        .addGoblins(5, 1)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddOrc() {
    new MedievalLevelBuilder(1, 1, 1, 2)
        .addRoom(" first room")
        .addOrc(0)
        .addOrc(0)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddOrcWhenNoRoom() {
    new MedievalLevelBuilder(1, 1, 2, 2)
        .addRoom(" first room")
        .addOrc(0)
        .addOrc(1)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddOgre() {
    new MedievalLevelBuilder(1, 1, 1, 2)
        .addRoom(" first room")
        .addOgre(0)
        .addOgre(0)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddOgreWhenNoRoom() {
    new MedievalLevelBuilder(1, 1, 2, 2)
        .addRoom(" first room")
        .addOgre(0)
        .addOgre(1)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddHuman() {
    new MedievalLevelBuilder(1, 1, 1, 2)
        .addRoom(" first room")
        .addHuman(0, "abdul", "dog man", 34)
        .addHuman(0, "dog", "ode", 23)
        .addGold(0, 1)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddHumanWhenNoRoom() {
    new MedievalLevelBuilder(1, 1, 2, 2)
        .addRoom(" first room")
        .addHuman(0, "abdulzy", "abdul", 34)
        .addHuman(1, "rahul", "big boy", 33)
        .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddPotion() {
    new MedievalLevelBuilder(1, 1, 1, 1)
        .addRoom(" first room")
        .addOgre(0)
        .addPotion(0)
        .addPotion(0)
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddPotionWhenNoRoom() {
    new MedievalLevelBuilder(1, 1, 2, 2).addRoom(" first room").addOgre(0).addPotion(1).build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddGold() {
    new MedievalLevelBuilder(1, 1, 1, 4)
        .addRoom(" first room")
        .addOgre(0)
        .addGold(0, 1)
        .addGold(0, 1)
        .addGold(0, 1)
        .addGold(0, 1)
            .addGold(0, 1)
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddGoldWhenNoRoom() {
    new MedievalLevelBuilder(1, 1, 2, 2)
            .addRoom(" first room").addOgre(0).addGold(1, 1).build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddWeapon() {
    new MedievalLevelBuilder(1, 1, 1, 1)
        .addRoom(" first room")
        .addOgre(0)
        .addWeapon(0, "sword")
        .addWeapon(0, "sword")
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddWeaponWhenNoRoom() {
    new MedievalLevelBuilder(1, 1, 2, 2)
        .addRoom(" first room")
        .addOgre(0)
        .addWeapon(1, "sword")
        .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalAddSpecial() {
    new MedievalLevelBuilder(1, 1, 1, 1)
        .addRoom(" first room")
        .addOgre(0)
        .addSpecial(0, "special", 20)
        .addSpecial(0, "special2", 45)
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddSpecialWhenNoRoom() {
    new MedievalLevelBuilder(1, 1, 2, 1)
        .addRoom(" first room")
        .addOgre(0)
        .addSpecial(1, "sword special", 50)
        .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalCallToBuild() {
    new MedievalLevelBuilder(1, 2, 2, 2)
        .addRoom(" first room")
        .addOgre(0)
        .addSpecial(0, "big gun", 100)
        .build();
  }
}
