package animals;

import java.time.temporal.ValueRange;

/**
 * Creates an object that tracks the temperature, preferred natural feature and size of an animal.
 */
public class PhysicalCharacteristics {
  private final ValueRange temperature;
  private final String naturalFeature;
  private final SizeofSpecies size;

  /**
   * Constructs a physical characteristics object with the specified parameters.
   *
   * @param size in medium, small or large.
   * @param temperature in celsius.
   * @param naturalFeature preferred natural feature of the animal
   * @throws IllegalArgumentException if the naturalFeature is an empty string.
   */
  public PhysicalCharacteristics(SizeofSpecies size, ValueRange temperature, String naturalFeature)
      throws IllegalArgumentException {
    if (naturalFeature.trim().isEmpty()) {
      throw new IllegalArgumentException("Natural Feature can't be empty");
    }

    this.temperature = temperature;
    this.naturalFeature = naturalFeature;
    this.size = size;
  }

  /**
   * Get the temperature range for the animal.
   *
   * @return a ValueRange object that holds the temperature range.
   */
  public ValueRange getTemperature() {
    return temperature;
  }

  /**
   * Gets the natural feature preference for the animal.
   *
   * @return a String representation of the animal's preferred natural feature.
   */
  public String getNaturalFeature() {
    return naturalFeature;
  }

  /**
   * Gets the current size of the species.
   *
   * @return the size of species(large, medium or small) as ann enum object.
   */
  public SizeofSpecies getSize() {
    return size;
  }

  /**
   * Gets important details needed to describe the class.
   *
   * @return a String representation of the data in the object fields.
   */
  @Override
  public String toString() {
    return String.format(
        "Temperature Range: %s, Preferred Natural Feature: %s, Size: %s",
        getTemperature(), getNaturalFeature(), getSize());
  }
}
