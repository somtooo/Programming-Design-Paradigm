package animals;

import java.time.temporal.ValueRange;

/**
 * Creates an object that tracks the temperature, preferred natural feature and size of an animal.
 */
public class PhysicalCharacteristics implements PhysicalCharacteristicsInterface {
  private final ValueRange temperature;
  private final String naturalFeature;
  private final SizeofSpecies size;

  /**
   * Constructs a physical characteristics object with the specified parameters.
   *
   * @param size in medium, small or large.
   * @param temperature in celsius.
   * @param naturalFeature preferred natural feature of the animal.
   * @throws IllegalArgumentException if the naturalFeature is an empty string or any input is null.
   */
  public PhysicalCharacteristics(SizeofSpecies size, ValueRange temperature, String naturalFeature)
      throws IllegalArgumentException {
    if (size == null || temperature == null || naturalFeature == null) {
      throw new IllegalArgumentException("null not allowed");
    }
    if (naturalFeature.trim().isEmpty()) {
      throw new IllegalArgumentException("Natural Feature can't be empty");
    }

    this.temperature = temperature;
    this.naturalFeature = naturalFeature;
    this.size = size;
  }

  @Override
  public ValueRange getTemperature() {
    return temperature;
  }

  @Override
  public String getNaturalFeature() {
    return naturalFeature;
  }

  @Override
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
