package dungeon;

/** Represent a builder class used to build a level for a game. */
public class MedievalLevelBuilder {
  public final int levelNumber;
  private final Level level;

  private int numberOfRooms;
  private int numberOfMonsters;
  private int numberOfTreasures;
  private int roomsAdded;

  /**
   * Constructs a MedievalLevelBuilder object with the following parameters.
   *
   * @param levelNumber the default level number
   * @param numberOfRooms rooms that are allowed for the specified level.
   * @param numberOfMonsters amount of monsters allowed for the specific level.
   * @param numberOfTreasures amount of treasure allowed for the specific level
   * @throws IllegalArgumentException when any of the parameters are negative.
   */
  public MedievalLevelBuilder(
      int levelNumber, int numberOfRooms, int numberOfMonsters, int numberOfTreasures)
      throws IllegalArgumentException {
    if (levelNumber < 0 | numberOfRooms < 0 | numberOfMonsters < 0 | numberOfTreasures < 0) {
      throw new IllegalArgumentException(" Negative values not allowed");
    }
    this.levelNumber = levelNumber;
    this.numberOfRooms = numberOfRooms;
    this.numberOfMonsters = numberOfMonsters;
    this.numberOfTreasures = numberOfTreasures;
    level = new Level(levelNumber);
    roomsAdded = 0;
  }

  /**
   * Adds a room to the level.
   *
   * @param description the description of the room.
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addRoom(String description) {
    if (numberOfRooms == 0) {
      throw new IllegalStateException("Room size cannot exceed number of rooms");
    }
    level.addRoom(description);
    this.numberOfRooms--;
    roomsAdded++;
    return this;
  }

  /**
   * Adds a goblin monster to a specific room.
   *
   * @param index the index level of the room list.
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addGoblins(int index, int value) {
    testForException(
        index,
        numberOfMonsters,
        " maximum number of monsters reached for this level",
        "Index is out of bounds");
    Monster goblin =
        new Monster(
            "goblin",
            "mischievous and very unpleasant, vengeful, and greedy "
                + "creature whose primary purpose is to cause trouble to humankind",
            7);
    for (int i = 0; i < value; i++) {
      level.addMonster(index, goblin);
      numberOfMonsters--;
    }
    return this;
  }

  /**
   * Adds a orc monster to a specific room.
   *
   * @param index the index level of the room list.
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addOrc(int index) {
    testForException(
        index,
        numberOfMonsters,
        " maximum number of monsters reached for this level",
        "Index is out of bounds");
    Monster orc = new Monster("orc", "brutish, aggressive, malevolent being serving evil", 20);
    level.addMonster(index, orc);
    numberOfMonsters--;
    return this;
  }

  /**
   * Adds a ogre monster to a specific room.
   *
   * @param index the index level of the room list.
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addOgre(int index) {
    testForException(
        index,
        numberOfMonsters,
        " maximum number of monsters reached for this level",
        "Index is out of bounds");
    Monster ogre =
        new Monster("ogre", "large, hideous man-like being that likes to eat humans for lunch", 50);

    level.addMonster(index, ogre);
    numberOfMonsters--;
    return this;
  }

  /**
   * Adds a human monster to a specific room.
   *
   * @param index the index level of the room list.
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addHuman(int index, String name, String description, int value) {
    testForException(
        index,
        numberOfMonsters,
        " maximum number of monsters reached for this level",
        "Index is out of bounds");
    Monster human = new Monster(name, description, value);
    level.addMonster(index, human);
    numberOfMonsters--;
    return this;
  }

  /**
   * Adds a potion treasure to a specific room.
   *
   * @param index the index level of the room list.
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addPotion(int index) {
    testForException(
        index,
        numberOfTreasures,
        "maximum number of treasure reached",
        "Room hasn't been created yet");
    Treasure potion = new Treasure("a healing potion", 1);
    level.addTreasure(index, potion);
    numberOfTreasures--;
    return this;
  }

  /**
   * Adds a gold treasure to a specific room.
   *
   * @param index the index level of the room list.
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addGold(int index, int value) {
    testForException(
        index,
        numberOfTreasures,
        "maximum number of treasure reached",
        "Room hasn't been created yet");
    Treasure gold = new Treasure("pieces of gold", value);
    level.addTreasure(index, gold);
    numberOfTreasures--;
    return this;
  }

  /**
   * Adds a weapon treasure to a specific room.
   *
   * @param index the index level of the room list and the name of the weapon.
   * @param weaponName the name of the weapon
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addWeapon(int index, String weaponName) {
    testForException(
        index,
        numberOfTreasures,
        "maximum number of treasure reached",
        "Room hasn't been created yet");
    Treasure weapon = new Treasure(weaponName, 10);
    level.addTreasure(index, weapon);
    numberOfTreasures--;
    return this;
  }

  /**
   * Adds a special treasure to a specific room.
   *
   * @param index the index level of the room list.
   * @return a MedievalLevelBuilder object with the room modified.
   */
  public MedievalLevelBuilder addSpecial(int index, String description, int value) {
    testForException(
        index,
        numberOfTreasures,
        "maximum number of treasure reached",
        "Room hasn't been created yet");
    Treasure special = new Treasure(description, value);
    level.addTreasure(index, special);
    numberOfTreasures--;
    return this;
  }

  /**
   * Compares parameters to decide weather to throw an exception.
   *
   * @param index position of room in a list.
   * @param number number of monsters or treasure.
   * @param value first exception output.
   * @param secondValue second exception output.
   * @throws IllegalStateException when the room hasn't been created,
   * @throws IllegalArgumentException when the monsters or treasures allowed in maxed out.
   */
  private void testForException(int index, int number, String value, String secondValue) {
    if (number == 0) {
      throw new IllegalStateException(value);
    }
    if (!(index >= 0 && index <= numberOfRooms)) {
      throw new IllegalArgumentException(secondValue);
    }
  }

  /**
   * Builds the object with the specific client input.
   *
   * @return a level object with default or modified values.
   * @throws IllegalStateException if the other functions haven't been called.
   */
  public Level build() {
    if (numberOfRooms > 0 | numberOfMonsters > 0 | numberOfTreasures > 0) {
      throw new IllegalStateException(
          "Finish Creating room monsters and treasure before calling build");
    }
    return level;
  }
}
