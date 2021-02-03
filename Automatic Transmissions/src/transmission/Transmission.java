package transmission;

/**
 * Interface signs a contract with AutomaticTransmission to implement all the functions listed here.
 */
public interface Transmission {

  Transmission increaseSpeed();

  Transmission decreaseSpeed();

  int getSpeed();

  int getGear();
}
