package animals;

/**
 * Represents functionalities that must be implemented by any class that signs a contract. This
 * allows all users to be able to get the instance variables.
 */
public interface PersonalFeaturesInterface {
  /**
   * Gets the danger level of the animal.
   *
   * @return the danger level as an enum.
   */
  Danger getDangerState();

  /**
   * Gets if the animal is poisonous.
   *
   * @return a boolean to determine if its true or false.
   */
  Boolean getPoisonous();

  /**
   * Gets if the animal can live with other species.
   *
   * @return a Boolean to determine if its true or false.
   */
  Boolean getCanCohabitate();
}
