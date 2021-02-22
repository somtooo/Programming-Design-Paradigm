package driver;

import character.Character;
import character.CharacterInterFace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import wearable.Wearable;
import wearable.footwear.FootWear;
import wearable.footwear.FootWearName;
import wearable.handgear.HandGear;
import wearable.handgear.HandGearName;
import wearable.headgear.HeadGear;
import wearable.headgear.HeadGearName;
import wearable.jewelry.Jewelry;
import wearable.jewelry.JewelryName;



/** Represents a battle ground for characters to battle with gears. */
public class Driver {
  private final int numberOfHeadGear;
  private final int numberOfFootWear;
  private final int numberOfHandGear;
  private final int numberOfJewelry;
  private final List<Wearable> chest;
  private final Wearable handGearDummy;
  private final Wearable headGearDummy;
  private final Wearable footWearDummy;
  private final Wearable jewelryDummy;
  private int numberOfTimesEquipped;

  /**
   * Initializes the class with the required parameters.
   *
   * @param numberOfHeadGear the number of allowed head gear.
   * @param numberOfFootWear the number of allowed footwear.
   * @param numberOfHandGear the number of allowed hand gear.
   * @param numberOfJewelry the number of allowed jewelry.
   */
  public Driver(
      int numberOfHeadGear, int numberOfFootWear, int numberOfHandGear, int numberOfJewelry) {
    this.numberOfHeadGear = numberOfHeadGear;
    this.numberOfFootWear = numberOfFootWear;
    this.numberOfHandGear = numberOfHandGear;
    this.numberOfJewelry = numberOfJewelry;
    footWearDummy = new FootWear(FootWearName.BOOTS, "of height", false, 20);
    headGearDummy = new HeadGear(HeadGearName.HATS, "of love", false, 100);
    jewelryDummy = new Jewelry(JewelryName.AMULET, "of flight", false, 100, 50);
    handGearDummy = new HandGear(HandGearName.GLOVE, "of flight", false, 100, 40);
    chest = new ArrayList<Wearable>();
  }

  /**
   * Simulates a battle between two characters.
   *
   * @param args as input from user.
   */
  public static void main(String[] args) {
    int value;

    Scanner input = new Scanner(System.in);
    System.out.println("Enter 1 to start fight");

    while ((value = input.nextInt()) != 0) {
      Driver driver = new Driver(4, 8, 15, 15);
      driver.buildChest();
      Character character = new Character("John", 50, 50);
      Character secondCharacter = new Character("Abdul", 50, 50);
      System.out.println(character.toString() + "\n");
      System.out.println(secondCharacter.toString() + "\n");
      driver.equipCharacter(character);
      driver.equipCharacter(secondCharacter);
      character.calculateTotalAttackAndDefense();
      secondCharacter.calculateTotalAttackAndDefense();
      System.out.println(character.toString() + "\n");
      System.out.println(secondCharacter.toString() + "\n");
      driver.predict(character, secondCharacter);
      character.wearOutWearableItems();
      secondCharacter.wearOutWearableItems();
      System.out.println("Enter 0 to cancel or 1 to rematch");
    }
  }

  /** Builds a chest with the required number of items. */
  private void buildChest() {
    List<Boolean> wearsOut = new ArrayList<Boolean>();
    wearsOut.add(true);
    wearsOut.add(false);
    List<String> descriptions = new ArrayList<String>();
    descriptions.add("of Flight");
    descriptions.add("of Electricity");
    descriptions.add("of Electrical Shock");
    descriptions.add("of Speed");
    descriptions.add("of Heft");
    descriptions.add("of Swimming");
    descriptions.add("of Drunkenness");
    descriptions.add("of Invisibility");
    descriptions.add("of Sight");
    Random random = new Random();
    List<HeadGearName> headGearNames =
            new ArrayList<HeadGearName>(Arrays.asList(HeadGearName.values()));
    loadFootWear(wearsOut, descriptions, random);
    loadHeadGear(wearsOut, headGearNames, descriptions, random);
    loadJewelry(wearsOut, descriptions, random);
    loadHandGear(wearsOut, descriptions, random);
  }

  /**
   * loads various hand gear's into the chest.
   *
   * @param wearsOut a list of boolean values for if the item wears out.
   * @param descriptions a list of descriptions for various items.
   * @param random random object to generate random numbers.
   */
  private void loadHandGear(List<Boolean> wearsOut, List<String> descriptions, Random random) {
    List<HandGearName> handGearNames =
        new ArrayList<HandGearName>(Arrays.asList(HandGearName.values()));
    for (int i = 0; i < numberOfHandGear; i++) {
      int handGearNamesIndex = random.nextInt((handGearNames.size() - 1) + 1);
      int descriptionsIndex = random.nextInt((descriptions.size() - 1) + 1);
      int wearsOutIndex = random.nextInt((1 + 1));

      Wearable handGearWearable =
          new HandGear(
              handGearNames.get(handGearNamesIndex),
              descriptions.get(descriptionsIndex),
              wearsOut.get(wearsOutIndex),
              random.nextInt((100 - (-20)) + 1) + (-20),
              random.nextInt((100 - (-20)) + 1) + (-20));
      chest.add(handGearWearable);
    }
  }

  /**
   * loads various jewelry into the chest.
   *
   * @param wearsOut a list of boolean values for if the item wears out.
   * @param descriptions a list of descriptions for various items.
   * @param random random object to generate random numbers.
   */
  private void loadJewelry(List<Boolean> wearsOut, List<String> descriptions, Random random) {
    List<JewelryName> jewelryNames =
        new ArrayList<JewelryName>(Arrays.asList(JewelryName.values()));
    for (int i = 0; i < numberOfJewelry; i++) {
      int jewelryNamesIndex = random.nextInt((jewelryNames.size() - 1) + 1);
      int descriptionsIndex = random.nextInt((descriptions.size() - 1) + 1);
      int wearsOutIndex = random.nextInt((1 + 1));

      Wearable jewelryWearable =
          new Jewelry(
              jewelryNames.get(jewelryNamesIndex),
              descriptions.get(descriptionsIndex),
              wearsOut.get(wearsOutIndex),
              random.nextInt((100 - (-20)) + 1) + (-20),
              random.nextInt((100 - (-20)) + 1) + (-20));
      chest.add(jewelryWearable);
    }
  }

  /**
   * loads various head gear into the chest.
   *
   * @param wearsOut a list of boolean values for if the item wears out.
   * @param descriptions a list of descriptions for various items.
   * @param random random object to generate random numbers.
   */
  private void loadHeadGear(
      List<Boolean> wearsOut,
      List<HeadGearName> headGearNames,
      List<String> descriptions,
      Random random) {
    for (int i = 0; i < numberOfHeadGear; i++) {
      int headGearNamesIndex = random.nextInt((headGearNames.size() - 1) + 1);
      int descriptionsIndex = random.nextInt((descriptions.size() - 1) + 1);
      int wearsOutIndex = random.nextInt((1 + 1));
      Wearable headGearWearable =
          new HeadGear(
              headGearNames.get(headGearNamesIndex),
              descriptions.get(descriptionsIndex),
              wearsOut.get(wearsOutIndex),
              random.nextInt((100 - (-20)) + 1) + (-20));
      chest.add(headGearWearable);
    }
  }

  /**
   * loads various foot wear into the chest.
   *
   * @param wearsOut a list of boolean values for if the item wears out.
   * @param descriptions a list of descriptions for various items.
   * @param random random object to generate random numbers.
   */
  private void loadFootWear(List<Boolean> wearsOut, List<String> descriptions, Random random) {
    List<FootWearName> footWearNames =
        new ArrayList<FootWearName>(Arrays.asList(FootWearName.values()));
    for (int i = 0; i < numberOfFootWear; i++) {
      int footWearNamesIndex = random.nextInt((footWearNames.size() - 1) + 1);
      int descriptionsIndex = random.nextInt((descriptions.size() - 1) + 1);
      int wearsOutIndex = random.nextInt((1 + 1));

      Wearable footWearWearable =
          new FootWear(
              footWearNames.get(footWearNamesIndex),
              descriptions.get(descriptionsIndex),
              wearsOut.get(wearsOutIndex),
              random.nextInt((100 - (-20)) + 1) + (-20));
      chest.add(footWearWearable);
    }
  }

  /**
   * Dressed a character with random number of twenty items.
   *
   * @param character the character to be dressed.
   */
  private void equipCharacter(CharacterInterFace character) {
    if (numberOfTimesEquipped > 0) {
      character.wearOutWearableItems();
    }
    Random random = new Random();
    for (int index = 0; index < 20; index++) {
      int randomItemIndex = random.nextInt(((chest.size() - 1)));
      findCorrespondingClassAndAddGiveCharacter(chest.get(randomItemIndex), character);
    }
    numberOfTimesEquipped++;
  }

  /**
   * Determines the classes for each item then tells the character to wear it.
   *
   * @param wearable the item that's to be checked.
   * @param character the character that will wear the item.
   */
  private void findCorrespondingClassAndAddGiveCharacter(
      Wearable wearable, CharacterInterFace character) {
    if (headGearDummy.isSameClass(wearable)) {
      character.addToHeadSlot(wearable);
    } else if (footWearDummy.isSameClass(wearable)) {
      character.addToFeetSlot(wearable);
    } else if (handGearDummy.isSameClass(wearable)) {
      character.addToHandSlot(wearable);
    } else if (jewelryDummy.isSameClass(wearable)) {
      character.addToNeckSlot(wearable);
    }
  }

  /**
   * Pits two characters to battle and predicts the winner and how many rounds it will take.
   *
   * @param firstCharacter the first character to be chosen to battle.
   * @param secondCharacter the second character to be chosen to battle.
   */
  private void predict(CharacterInterFace firstCharacter, CharacterInterFace secondCharacter) {
    int firstCharacterDamage = secondCharacter.getAttackPower() - firstCharacter.getDefensePower();
    int secondCharacterDamage = firstCharacter.getAttackPower() - secondCharacter.getDefensePower();
    if (firstCharacterDamage < 1) {
      firstCharacterDamage = 1;
    } else if (secondCharacterDamage < 1) {
      secondCharacterDamage = 1;
    }
    int firstCharacterRound = firstCharacter.getHitPoints() / firstCharacterDamage;
    int secondCharacterRound = secondCharacter.getHitPoints() / secondCharacterDamage;
    if (firstCharacterRound == secondCharacterRound) {
      System.out.println(" This match is a tie");
    } else if (firstCharacterRound > secondCharacterRound) {
      System.out.printf(
          "%s has won the fight it took %s rounds%n",
          firstCharacter.getName(), firstCharacterRound);
    } else {
      System.out.printf(
          "%s has won the fight it took %s number of rounds%n",
          secondCharacter.getName(), secondCharacterRound);
    }
  }
}
