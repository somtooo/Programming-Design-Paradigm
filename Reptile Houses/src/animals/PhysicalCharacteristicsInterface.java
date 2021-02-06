package animals;

import java.time.temporal.ValueRange;

/**
 * Represents functionalities that must be implemented by any class that signs a contract. This
 * allows all users to be able to get specific details about an animals physical characteristics.
 */
public interface PhysicalCharacteristicsInterface {
  /**
   * Get the temperature range for the animal.
   *
   * @return a ValueRange object that holds the temperature range.
   */
  ValueRange getTemperature();

  /**
   * Gets the natural feature preference for the animal.
   *
   * @return a String representation of the animal's preferred natural feature.
   */
  String getNaturalFeature();

  /**
   * Gets the current size of the species.
   *
   * @return the size of species(large, medium or small) as ann enum object.
   */
  SizeofSpecies getSize();
}
