package animals;

/**
 * Creates an object that tracks if the animal is poisonous, extinct or endangered, and if they can
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
   * @return the danger level as an enum.
   */
  public Danger getDangerState() {
    return danger;
  }

  /**
   * Gets if the animal is poisonous.
   *
   * @return a boolean to determine if its true or false.
   */
  public Boolean getPoisonous() {
    return isPoisonous;
  }

  /**
   * Gets if the animal can live with other species.
   *
   * @return a Boolean to determine if its true or false.
   */
  public Boolean getCanCohabitate() {
    return canCohabitate;
  }

  /**
   * Gets important details that describe the object properties.
   *
   * @return a String representation of the objects fields.
   */
  @Override
  public String toString() {
    return String.format(
        "Danger State: %s, Poisonous: %s, Cohabitate: %s",
        getDangerState(), getPoisonous(), getCanCohabitate());
  }
}
