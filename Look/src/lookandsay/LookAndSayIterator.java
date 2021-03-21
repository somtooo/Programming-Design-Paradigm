package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/** Represents an iterator that computes its next and prev based on the look and say algorithm. */
public class LookAndSayIterator implements RIterator<BigInteger> {
  private final BigInteger endValue;
  private BigInteger seed;
  private BigInteger index;

  /**
   * Constructor initializes parameters with the required values.
   *
   * @param seed the starting value.
   * @param endValue the end value.
   * @throws IllegalArgumentException if the seed is equal to or less than zero or if the seed is
   *     greater than the end value.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger endValue) throws IllegalArgumentException {
    if (!(seed.toString().matches("[1-9]+"))) {
      throw new IllegalArgumentException("No zeros");
    }
    if (seed.compareTo(BigInteger.valueOf(0)) <= 0 | seed.compareTo(endValue) >= 0) {
      throw new IllegalArgumentException("Wrong constructor parameters");
    }
    this.seed = seed;
    this.endValue = endValue;
    this.index = seed;
  }

  /**
   * Constructor initializes parameters with the required values.
   *
   * @param seed the starting value.
   * @throws IllegalArgumentException if the seed is equal to or less than zero or if the seed is
   *     greater than the end value.
   */
  public LookAndSayIterator(BigInteger seed) throws IllegalArgumentException {
    if (!(seed.toString().matches("[1-9]+"))) {
      throw new IllegalArgumentException("No zeros");
    }
    this.endValue = buildBigInteger(BigInteger.valueOf(9), 100);
    if (seed.compareTo(BigInteger.valueOf(0)) <= 0 | seed.compareTo(endValue) >= 0) {
      throw new IllegalArgumentException("Wrong constructor parameters");
    }
    this.seed = seed;
    this.index = seed;
  }

  /** Default Constructor. */
  public LookAndSayIterator() {
    this.seed = BigInteger.valueOf(1);
    this.endValue = buildBigInteger(BigInteger.valueOf(9), 100);
    this.index = seed;
  }

  @Override
  public BigInteger prev() throws NoSuchElementException {
    if (!hasPrevious()) {
      throw new NoSuchElementException("no prev");
    }

    StringBuilder stringResult = new StringBuilder();
    char[] number = index.toString().toCharArray();
    for (int i = 0; i < number.length; i = i + 2) {
      String value =
          String.valueOf(number[i + 1]).repeat(Integer.parseInt(String.valueOf(number[i])));
      stringResult.append(value);
    }

    seed = index;
    index = new BigInteger(stringResult.toString());

    return index;
  }

  @Override
  public boolean hasPrevious() {
    int remainder = index.toString().length() % 2;
    return remainder == 0;
  }

  @Override
  public boolean hasNext() {
    boolean comparison = seed.compareTo(endValue) >= 0;
    return !comparison;
  }

  @Override
  public BigInteger next() {
    if (!hasNext()) {
      throw new NoSuchElementException("no next");
    }
    index = seed;
    StringBuilder stringResult = new StringBuilder();

    char repeat = seed.toString().charAt(0);
    String number = seed.toString().substring(1) + " ";
    int times = 1;

    for (char actual : number.toCharArray()) {
      if (actual != repeat) {
        stringResult.append(times).append(repeat);
        times = 1;
        repeat = actual;
      } else {
        times += 1;
      }
    }

    seed = new BigInteger(stringResult.toString());
    return index;
  }

  @Override
  public String toString() {
    return String.format(
        "Seed : %s, End Value Length: %s End Value: %s",
        seed, endValue.toString().length(), endValue);
  }

  /**
   * Builds a big number of repeated numbers. How long it will be depends on the value of length
   * passed.
   *
   * @param number the number that will be repeated in the big number.
   * @param length how many times will the number repeat.
   * @return the built big number.
   */
  private BigInteger buildBigInteger(BigInteger number, int length) {
    BigInteger value = number;

    for (int i = 1; i < length; i++) {
      value = value.multiply(BigInteger.valueOf(10)).add(number);
    }

    return value;
  }
}
