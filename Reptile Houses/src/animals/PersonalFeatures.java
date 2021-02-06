package animals;

/**
 * Creates an object that tracks if the animal is poisonous, extinct or endangered, and if they can
 * coHabitatie.
 */
public class PersonalFeatures implements PersonalFeaturesInterface {
  private final Boolean isPoisonous;
  private final Danger danger;
  private final Boolean canCohabitate;

  /**
   * Constructs Personal Features with the specified parameters.
   *
   * @param isPoisonous if the animal is poisonous
   * @param danger the danger level of the animal
   * @param canCohabitate if the animal can live with other species
   * @throws IllegalArgumentException if any input is null
   */
  public PersonalFeatures(Boolean isPoisonous, Danger danger, Boolean canCohabitate) {
    if (isPoisonous == null || danger == null || canCohabitate == null) {
      throw new IllegalArgumentException("Null not allowed");

    }
    this.isPoisonous = isPoisonous;
    this.danger = danger;

    this.canCohabitate = canCohabitate;
  }

  @Override
  public Danger getDangerState() {
    return danger;
  }

  @Override
  public Boolean getPoisonous() {
    return isPoisonous;
  }

  @Override
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
