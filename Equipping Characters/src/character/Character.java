package character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import wearable.AttackWearable;
import wearable.DefenceWearable;
import wearable.Wearable;


/**
 * This class represents a character in a role playing game it has a name and also uses various
 * gears to fight.
 */
public class Character implements CharacterInterFace {
  private final String nameOfCharacter;
  private final Map<CharacterSlots, List<Wearable>> gearDescription;
  private final int defensePower;
  private final int attackPower;
  private int totalAttack;
  private int totalDefense;
  private boolean equipped;

  /**
   * Initializes the character object with the required parameters.
   *
   * @param nameOfCharacter character name.
   * @param attackPower characters base attack power.
   * @param defensePower characters base defense power.
   */
  public Character(String nameOfCharacter, int attackPower, int defensePower)
      throws IllegalArgumentException {
    nullChecker(nameOfCharacter == null, "Null not allowed");
    nullChecker(nameOfCharacter.trim().equals(""), "Name can't be empty");
    nullChecker(
        attackPower <= 0 | defensePower <= 0, " Character attack or defense power must be above 0");
    this.nameOfCharacter = nameOfCharacter;
    gearDescription = new HashMap<>();
    initializeGearDescription();
    this.defensePower = defensePower;
    this.attackPower = attackPower;
    totalAttack = 0;
    totalDefense = 0;
    equipped = false;
  }

  @Override
  public int getHitPoints() {
    return defensePower + (totalDefense + 100);
  }

  @Override
  public int getAttackPower() {
    return totalAttack + attackPower;
  }

  @Override
  public int getDefensePower() {
    return totalDefense + defensePower;
  }

  @Override
  public String getName() {
    return nameOfCharacter;
  }

  @Override
  public void addToHeadSlot(Wearable wearable) {
    nullChecker(wearable == null, "null not allowed");

    List<Wearable> wearables = gearDescription.get(CharacterSlots.HeadSlot);
    if (wearables.size() < 1) {
      wearables.add(wearable);
    } else if (wearables.size() == 1) {
      int index = findIndexToRemove(wearable, wearables);
      if (index >= 0) {
        printDiscardedItem(wearables, index);
        wearables.remove(index);
        wearables.add(wearable);
      }
    }
    equipped = true;
  }

  @Override
  public void addToFeetSlot(Wearable wearable) {
    nullChecker(wearable == null, "null not allowed");

    List<Wearable> wearables = gearDescription.get(CharacterSlots.FeetSlot);
    if (wearables.size() < 2) {
      wearables.add(wearable);
    } else if (wearables.size() == 2) {
      int index = findIndexToRemove(wearable, wearables);
      if (index >= 0) {
        printDiscardedItem(wearables, index);
        wearables.remove(index);
        wearables.add(wearable);
      }
    }
    equipped = true;
  }

  @Override
  public void addToHandSlot(Wearable wearable) {
    nullChecker(wearable == null, "null not allowed");

    List<Wearable> wearables = gearDescription.get(CharacterSlots.HandSlot);
    if (wearables.size() < 10) {
      wearables.add(wearable);
    } else if (wearables.size() == 10) {
      int index = findIndexToRemove(wearable, wearables);
      if (index >= 0) {
        printDiscardedItem(wearables, index);
        wearables.remove(index);
        wearables.add(wearable);
      }
    }
    equipped = true;
  }

  @Override
  public void addToNeckSlot(Wearable wearable) {
    nullChecker(wearable == null, "null not allowed");
    gearDescription.get(CharacterSlots.NeckSlot).add(wearable);
    equipped = true;
  }

  @Override
  public void calculateTotalAttackAndDefense() {
    int temporaryAttack = 0;
    int temporaryDefense = 0;
    List<Wearable> wearablesToSend = new ArrayList<>();
    for (List<Wearable> wearables : gearDescription.values()) {
      for (Wearable item : wearables) {
        if (item instanceof AttackWearable) {
          AttackWearable attackWearable = (AttackWearable) item;
          temporaryAttack = temporaryAttack + attackWearable.getAttackPower();
        }

        if (item instanceof DefenceWearable) {
          DefenceWearable defenseWearable = (DefenceWearable) item;
          temporaryDefense = temporaryDefense + defenseWearable.getDefensePower();
        }
      }
    }
    totalAttack = temporaryAttack;
    totalDefense = temporaryDefense;
  }

  @Override
  public void wearOutWearableItems() {
    for (List<Wearable> wearables : gearDescription.values()) {
      for (Wearable items : wearables) {
        if (items.isWearOut()) {
          items.wearOut();
        }
      }
    }
    calculateTotalAttackAndDefense();
  }

  /**
   * Checks for null or empty strings.
   *
   * @param isTrue a boolean stating if its null or an empty string.
   * @param message String the message to tell the user.
   */
  private void nullChecker(boolean isTrue, String message) {
    if (isTrue) {
      throw new IllegalArgumentException(message);
    }
  }

  /** Initializes a hashMap that represents a characters body parts. */
  private void initializeGearDescription() {
    gearDescription.put(CharacterSlots.HeadSlot, new ArrayList<Wearable>());
    gearDescription.put(CharacterSlots.FeetSlot, new ArrayList<Wearable>());
    gearDescription.put(CharacterSlots.HandSlot, new ArrayList<Wearable>());
    gearDescription.put(CharacterSlots.NeckSlot, new ArrayList<Wearable>());
  }

  /**
   * Compares a new item with the items a character has to determine if its better.
   *
   * @param wearable the new item to compare with.
   * @param compareToWearable a list of the characters item.
   * @return an integer which is the index in the list if the item is to be replaced.
   */
  private int findIndexToRemove(Wearable wearable, List<Wearable> compareToWearable) {
    for (int index = 0; index < compareToWearable.size(); index++) {
      if (wearable.compareTo(compareToWearable.get(index)) > 0) {
        return index;
      }
    }
    return -1;
  }

  /**
   * Prints an item that's about to get discarded to the console.
   *
   * @param wearables a List of the items the character has.
   * @param index the index of the item that's going to get discarded.
   */
  private void printDiscardedItem(List<Wearable> wearables, int index) {
    String discardedItem = wearables.get(index).getItemName() + " Is going to get discarded";
    System.out.println(discardedItem);
  }

  /**
   * Describes what a character is wearing by combining the names of items of the same type.
   *
   * @return a string describing what the character is wearing.
   */
  private String whatAmiWearing() {
    if (!equipped) {
      return "empty because i haven't been dressed yet";
    }
    List<Wearable> headWearables = gearDescription.get(CharacterSlots.HeadSlot);
    List<Wearable> feetWearables = gearDescription.get(CharacterSlots.FeetSlot);
    List<Wearable> handWearables = gearDescription.get(CharacterSlots.HandSlot);
    List<Wearable> neckWearables = gearDescription.get(CharacterSlots.NeckSlot);

    return combineWearableDescription(headWearables)
        + combineWearableDescription(feetWearables)
        + combineWearableDescription(handWearables)
        + combineWearableDescription(neckWearables);
  }

  /**
   * Combines the description of items in a list.
   *
   * @param wearables the list that contains items
   * @return a string that represents the combined description of all the items in the list.
   */
  private String combineWearableDescription(List<Wearable> wearables) {
    StringBuilder builder = new StringBuilder();
    checkIfOnlyOneItem(wearables, builder);
    checkIfMoreThanOneItem(wearables, builder);
    builder.append(" ");
    return builder.toString();
  }

  /**
   * Checks if the list has only one item.
   *
   * @param wearables the list of items.
   * @param builder a string builder used for the combination of strings
   */
  private void checkIfOnlyOneItem(List<Wearable> wearables, StringBuilder builder) {
    if (wearables.size() == 1) {
      builder
          .append(wearables.get(0).getItemName())
          .append(" ")
          .append(wearables.get(0).getItemDescription())
          .append(". ");
    }
  }

  /**
   * Checks if the list has more than one item.
   *
   * @param wearables the list of items.
   * @param builder a string builder used for the combination of strings
   */
  private void checkIfMoreThanOneItem(List<Wearable> wearables, StringBuilder builder) {
    if (wearables.size() > 1) {
      builder.append(wearables.get(0).getItemName());
      for (int index = 0; index < wearables.size(); index++) {
        if (checkIfSecondToLastItem(wearables, builder, index)) {
          break;
        }
        builder.append(" ").append(wearables.get(index).getItemDescription()).append(", ");
      }
    }
  }

  /**
   * Checks if the index is at the second to last item to know when to insert And.
   *
   * @param wearables the list of items.
   * @param builder a string builder used for the combination of strings
   * @param index the index to check.
   */
  private boolean checkIfSecondToLastItem(
      List<Wearable> wearables, StringBuilder builder, int index) {
    if ((index == (wearables.size() - 2)) | (wearables.size() == 1)) {
      builder
          .append(" ")
          .append(wearables.get(index).getItemDescription())
          .append(" and ")
          .append(wearables.get(index + 1).getItemDescription())
          .append(". ");
      return true;
    }
    return false;
  }

  /**
   * Returns a string representation of the object. In general, the {@code toString} method returns
   * a string that "textually represents" this object.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return String.format(
        "I am %s my current gear load out is\n %sI have an Attack Power of %s \n"
            + "and a Defense Power of %s which brings my Hit Points to %s, trust me I am gonna\n"
            + " beat the Java out of you!!!!",
        nameOfCharacter, whatAmiWearing(), getAttackPower(), getDefensePower(), getHitPoints());
  }
}
