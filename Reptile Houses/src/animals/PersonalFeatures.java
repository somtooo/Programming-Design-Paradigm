package animals;

/**
 * Creates an object that tracks if the animal is poisonous, extinct or endeangered, and if they can
 * coHabitatie.
 */
public class PersonalFeatures {
  private final Boolean isPoisonous;
  private final Danger danger;
  private final Boolean canCohabitate;

  /**
   * Constructs Personal Features with the specified parameters.
   *
   * @param isPoisonous if the animal is poisonous
   * @param danger the danger level of the animal
   * @param canCohabitate if the animal can live with other species
   */
  public PersonalFeatures(Boolean isPoisonous, Danger danger, Boolean canCohabitate) {
    this.isPoisonous = isPoisonous;
    this.danger = danger;

    this.canCohabitate = canCohabitate;
  }

  /**
   * Gets the danger level of the animal.
   *
   * @return Danger Enum Type
   */
  public Danger getDangerState() {
    return danger;
  }

  /**
   * Gets if the animal is poisonous.
   *
   * @return Boolean
   */
  public Boolean getPoisonous() {
    return isPoisonous;
  }

  /**
   * Gets if the animal can live with other species.
   *
   * @return Boolean
   */
  public Boolean getCanCohabitate() {
    return canCohabitate;
  }
}
