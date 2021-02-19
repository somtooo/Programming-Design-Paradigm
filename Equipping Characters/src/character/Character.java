package character;

import wearable.AttackWearable;
import wearable.DefenceWearable;
import wearable.Wearable;

import java.util.*;

/**
 * This class represents a character in a role playing game it has a name and also uses various gears to fight.
 */
public class Character implements CharacterInterFace {
  private final String nameOfCharacter;
  private final Map<CharacterSlots, List<Wearable>> gearDescription;
  private final int defensePower;
  private int totalAttack;
  private int totalDefense;


  /**
   * Initializes the character object with the required parameters.
   * @param nameOfCharacter character name.
   * @param attackPower characters base attack power.
   * @param defensePower characters base defense power.
   */
  public Character(String nameOfCharacter, int attackPower, int defensePower) throws IllegalArgumentException{
    if (nameOfCharacter == null) {throw new IllegalArgumentException("Null not allowed");}
    if (nameOfCharacter.trim().equals("")) {
      throw new IllegalArgumentException("Name can't be empty");
    }
    if (attackPower <=0 | defensePower <=0) {
      throw new IllegalArgumentException(" Chracter attack or defense power must be above 0");
    }
    this.nameOfCharacter = nameOfCharacter;
    gearDescription = new HashMap<>();
    initializeGearDescription();
    this.defensePower = defensePower;
    totalAttack = attackPower;
    totalDefense = defensePower;
  }

  @Override
  public int getHitPoints() {
    return defensePower + totalDefense;
  }

  @Override
  public int getAttackPower() {
    return totalAttack;
  }

  @Override
  public int getDefensePower() {
    return totalDefense;
  }

  @Override
  public String getName() {
    return nameOfCharacter;
  }

  @Override
  public void addToHeadSlot(Wearable wearable) {
    List<Wearable> wearables = gearDescription.get(CharacterSlots.HeadSlot);
    if (wearables.size() < 1) {
      wearables.add(wearable);
    } else if (wearables.size() == 1) {
      int index = findIndexToRemove(wearable, wearables);
      if (index > 0) {
        printDiscardedItem(wearables, index);
        wearables.remove(index);
        wearables.add(wearable);
      }
    }
  }

  @Override
  public void addToFeetSlot(Wearable wearable) {
    List<Wearable> wearables = gearDescription.get(CharacterSlots.FeetSlot);
    if (wearables.size() < 2) {
      wearables.add(wearable);
    } else if (wearables.size() == 2) {
      int index = findIndexToRemove(wearable, wearables);
      if (index > 0) {
        printDiscardedItem(wearables, index);
        wearables.remove(index);
        wearables.add(wearable);
      }
    }
  }

  @Override
  public void addToHandSlot(Wearable wearable) {
    List<Wearable> wearables = gearDescription.get(CharacterSlots.HandSlot);
    if (wearables.size() < 10) {
      wearables.add(wearable);
    } else if (wearables.size() == 10) {
      int index = findIndexToRemove(wearable, wearables);
      if (index > 0) {
        printDiscardedItem(wearables, index);
        wearables.remove(index);
        wearables.add(wearable);
      }
    }
  }


  @Override
  public void calculateTotalAttackAndDefense() {
    List<Wearable> wearablesToSend = new ArrayList<>();
    for (List<Wearable> wearables : gearDescription.values()) {
      for (Wearable item : wearables) {
        if (item instanceof AttackWearable) {
          AttackWearable attackWearable = (AttackWearable) item;
          totalAttack = totalAttack + attackWearable.getAttackPower();
        }  if (item instanceof DefenceWearable) {
          DefenceWearable defenseWearable = (DefenceWearable) item;
          totalDefense = totalDefense + defenseWearable.getDefensePower();
        }
      }
    }
  }



  @Override
  public void addToNeckSlot(Wearable wearable) {
    gearDescription.get(CharacterSlots.NeckSlot).add(wearable);
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
  }

  private void initializeGearDescription() {
    gearDescription.put(CharacterSlots.HeadSlot, new ArrayList<Wearable>());
    gearDescription.put(CharacterSlots.FeetSlot, new ArrayList<Wearable>());
    gearDescription.put(CharacterSlots.HandSlot, new ArrayList<Wearable>());
    gearDescription.put(CharacterSlots.NeckSlot, new ArrayList<Wearable>());
  }

  private int findIndexToRemove(Wearable wearable, List<Wearable> compareToWearable) {
    for (int index = 0; index < compareToWearable.size(); index++) {
      if (wearable.compareTo(compareToWearable.get(index)) > 0) {
        return index;
      }
    }
    return 0;
  }


  private void printDiscardedItem(List<Wearable> wearables, int index) {
    String discardedItem = wearables.get(index).getItemName() + "Is going to get discarded";
    System.out.println(discardedItem);
  }



  private String whatAmIWearing() {
    List<Wearable> headWearables = gearDescription.get(CharacterSlots.HeadSlot);
    List<Wearable> feetWearables = gearDescription.get(CharacterSlots.FeetSlot);
    List<Wearable> handWearables = gearDescription.get(CharacterSlots.HandSlot);
    List<Wearable> neckWearables = gearDescription.get(CharacterSlots.NeckSlot);

    return combineWearableDescription(headWearables)
        + combineWearableDescription(feetWearables)
        + combineWearableDescription(handWearables)
        + combineWearableDescription(neckWearables);
  }

  private String combineWearableDescription(List<Wearable> Wearables) {
    StringBuilder builder = new StringBuilder();
    checkIfOnlyOneItem(Wearables, builder);
    checkIfMoreThanOneItem(Wearables, builder);
    builder.append(" ");
    return builder.toString();
  }

  private void checkIfOnlyOneItem(List<Wearable> Wearables, StringBuilder builder) {
    if (Wearables.size() == 1) {
      builder
          .append(Wearables.get(0).getItemName())
          .append(" ")
          .append(Wearables.get(0).getItemDescription())
          .append(". ");
    }
  }

  private void checkIfMoreThanOneItem(List<Wearable> Wearables, StringBuilder builder) {
    if (Wearables.size() > 1) {
      builder.append(Wearables.get(0).getItemName());
      for (int index = 0; index < Wearables.size(); index++) {
        if (checkIfSecondToLastItem(Wearables, builder, index)) break;
        builder.append(" ").append(Wearables.get(index).getItemDescription()).append(", ");
      }
    }
  }

  private boolean checkIfSecondToLastItem(
      List<Wearable> Wearables, StringBuilder builder, int index) {
    if ((index == (Wearables.size() - 2)) | (Wearables.size() == 1)) {
      if (Wearables.size() == 2) {
        if (Wearables.get(index).getItemName().equals(Wearables.get(index + 1).getItemName())) {
          builder.append(" ").append(Wearables.get(index).getItemDescription()).append(". ");
          return true;
        }
      }
      builder.append(" and ").append(Wearables.get(index).getItemDescription()).append(". ");
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
        nameOfCharacter, whatAmIWearing(), totalAttack, totalDefense, defensePower + totalDefense);
  }
}
