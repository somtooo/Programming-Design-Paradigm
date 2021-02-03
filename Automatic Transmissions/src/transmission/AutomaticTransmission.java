package transmission;

import java.time.temporal.ValueRange;
import java.util.HashMap;

/** Automatic Transmission Represented as five threshold values. */
public final class AutomaticTransmission implements Transmission {

  private final int speedThreshold1;
  private final int speedThreshold2;
  private final int speedThreshold3;
  private final int speedThreshold4;
  private final int speedThreshold5;
  private int speed;
  private int gear;
  private HashMap<ValueRange, Integer> speedGear;

  /**
   * Constructor assigns gear values to appropriate speed threshold.
   *
   * @param speedThreshold1 in mph.
   * @param speedThreshold2 in mph.
   * @param speedThreshold3 in mph.
   * @param speedThreshold4 in mph.
   * @param speedThreshold5 in mph.
   * @throws IllegalArgumentException if any argument is negative or equals to zero and also if
   *     argument is not in ascending order.
   */
  public AutomaticTransmission(
      int speedThreshold1,
      int speedThreshold2,
      int speedThreshold3,
      int speedThreshold4,
      int speedThreshold5)
      throws IllegalArgumentException {

    if ((speedThreshold1 | speedThreshold2 | speedThreshold3 | speedThreshold4 | speedThreshold5)
        <= 0) {
      throw new IllegalArgumentException("Negative Speed Threshold not allowed");
    } else if (!((speedThreshold1 < speedThreshold2)
        && (speedThreshold2 < speedThreshold3)
        && (speedThreshold3 < speedThreshold4)
        && (speedThreshold4 < speedThreshold5))) {
      throw new IllegalArgumentException("Threshold values must be in ascending order");
    }

    this.speedThreshold1 = speedThreshold1;
    this.speedThreshold2 = speedThreshold2;
    this.speedThreshold3 = speedThreshold3;
    this.speedThreshold4 = speedThreshold4;
    this.speedThreshold5 = speedThreshold5;

    speed = 0;
    gear = 0;
    assignSpeedValueRangeToGearValue();
  }

  /**
   * Gets the current speed in mph.
   *
   * @return speed value in mph.
   */
  @Override
  public int getSpeed() {
    return speed;
  }

  /**
   * Get's the current gear.
   *
   * @return gear value based on speed threshold.
   */
  @Override
  public int getGear() {
    return gear;
  }

  /**
   * Handles the logic to increase the speed of the transmission.
   *
   * @return a new transmission object with a new speed and gear value.
   */
  @Override
  public Transmission increaseSpeed() {
    int newSpeed = speed + 2;
    int newGear = getNewGearValue(newSpeed);
    AutomaticTransmission transmission =
        new AutomaticTransmission(
            speedThreshold1, speedThreshold2, speedThreshold3, speedThreshold4, speedThreshold5);
    transmission.speed = newSpeed;
    transmission.gear = newGear;
    return transmission;
  }

  /**
   * Handles the logic to decrease the speed of the transmission.
   *
   * @return a new transmission object with a new speed and gear value.
   */
  @Override
  public Transmission decreaseSpeed() {
    int newSpeed = speed - 2;
    if (newSpeed < 0) {
      throw new IllegalStateException("Speed is already 0m/s");
    }
    int newGear = getNewGearValue(newSpeed);
    AutomaticTransmission transmission =
        new AutomaticTransmission(
            speedThreshold1, speedThreshold2, speedThreshold3, speedThreshold4, speedThreshold5);
    transmission.speed = newSpeed;
    transmission.gear = newGear;
    return transmission;
  }

  /** Handles the logic to map speed threshold to gear values. */
  private void assignSpeedValueRangeToGearValue() {
    speedGear = new HashMap<>();
    final int speedThreshold0 = 0;
    final int maxSpeed = 250;

    speedGear.put(ValueRange.of(speedThreshold0, (speedThreshold1 - 1)), 1);
    speedGear.put(ValueRange.of(speedThreshold1, (speedThreshold2 - 1)), 2);
    speedGear.put(ValueRange.of(speedThreshold2, (speedThreshold3 - 1)), 3);
    speedGear.put(ValueRange.of(speedThreshold3, (speedThreshold4 - 1)), 4);
    speedGear.put(ValueRange.of(speedThreshold4, (speedThreshold5 - 1)), 5);
    speedGear.put(ValueRange.of(speedThreshold5, maxSpeed), 6);
  }

  /**
   * Handles the logic to find gear value based on new speed value.
   *
   * @param newSpeed in mph.
   * @return new gear value based on new speed.
   */
  private int getNewGearValue(int newSpeed) {
    int gear = 0;
    for (ValueRange index : speedGear.keySet()) {
      if (index.isValidIntValue(newSpeed)) {
        gear = speedGear.get(index);
      }
    }
    return gear;
  }

  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", speed, gear);
  }
}
