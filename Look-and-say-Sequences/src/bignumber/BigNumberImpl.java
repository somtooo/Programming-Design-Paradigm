package bignumber;

/** Represents a very big integer value. */
public class BigNumberImpl implements BigNumber {
  private ListOfInteger integralNumber;

  /** Constructor initializes the big number as 0. */
  public BigNumberImpl() {
    integralNumber = new Node(0, null);
  }

  /**
   * Constructor initializes big number with the required parameters.
   *
   * @param numberAsString the number to initialize big number as.
   */
  public BigNumberImpl(String numberAsString) {
    if (!numberAsString.trim().matches("\\d+")) {
      throw new IllegalArgumentException("Only numbers allowed");
    }
    initializeIntegralNumber(numberAsString);
  }

  @Override
  public int length() {
    return integralNumber.count();
  }

  @Override
  public void shiftLeft(int num) {
    if (num >= 0) {
      for (int index = 0; index < num; index++) {
        integralNumber.addDataToEnd(0);
      }
    } else {
      shiftRight(Math.abs(num));
    }
  }

  @Override
  public void shiftRight(int num) {
    if (num >= length()) {
      integralNumber = new Node(0, null);
    } else if (num < 0) {
      shiftLeft(Math.abs(num));
    } else {
      for (int index = 0; index < num; index++) {
        this.integralNumber.removeLastNode();
      }
    }
  }

  @Override
  public void addDigit(int digit) throws IllegalArgumentException {
    if (digit < 0) {
      throw new IllegalArgumentException("negative not allowed");
    }
    if (Integer.toString(digit).length() > 1) {
      throw new IllegalArgumentException("only single digit allowed");
    }
    integralNumber = integralNumber.add(digit);
  }

  @Override
  public int getDigitAt(int position) throws IllegalArgumentException {
    if (position < 0) {
      throw new IllegalArgumentException("Negative not allowed");
    }
    if (position > length()) {
      throw new IllegalArgumentException("out of boubds");
    }
    return integralNumber.getDigitAt(position);
  }

  @Override
  public BigNumber copy() {
    String copyString = this.toString().trim();
    return new BigNumberImpl(copyString);
  }

  @Override
  public BigNumber add(BigNumber other) {
    String firstString = this.toString();
    String secondString = other.toString();
    if (firstString.length() >= secondString.length()) {
      return new BigNumberImpl(this.addstr(firstString, secondString));
    }
    return new BigNumberImpl(this.addstr(secondString, firstString));
  }

  /**
   * Takes in a byte value to return the character representation.
   *
   * @param b the byte value.
   * @return the character representation.
   */
  private char tochar(byte b) {
    switch (b) {
      case 1:
        return '1';
      case 2:
        return '2';
      case 3:
        return '3';
      case 4:
        return '4';
      case 5:
        return '5';
      case 6:
        return '6';
      case 7:
        return '7';
      case 8:
        return '8';
      case 9:
        return '9';
      case 0:
        return '0';
      default:
        return '0';
    }
  }

  /**
   * Adds two strings together to represent an Big number.
   *
   * @param lrg the larger of the two strings.
   * @param sml the smaller of the two strings
   * @return a string that represents the addition.
   */
  private String addstr(String lrg, String sml) {

    byte[] number1 = new byte[lrg.length()];
    byte[] number2 = new byte[sml.length()];

    for (int i = 0; i < lrg.length(); i++) {
      char character = lrg.charAt(i);
      byte integer = (byte) Character.getNumericValue(character);
      number1[i] = integer;
    }
    for (int i = 0; i < sml.length(); i++) {
      char character = sml.charAt(i);
      byte integer = (byte) Character.getNumericValue(character);
      number2[i] = integer;
    }
    int max = Math.max(number1.length, number2.length);
    byte[] number3 = new byte[max + 1];
    int remainderOne = number1.length - 1;
    int remainderTwo = number2.length - 1;
    int remainderThree = number3.length - 1;
    byte carry = 0;

    while (remainderThree >= 0) {
      byte sum = carry;

      if (remainderOne >= 0) {
        sum += number1[remainderOne--];
      }
      if (remainderTwo >= 0) {
        sum += number2[remainderTwo--];
      }
      carry = (byte) (sum / 10);
      number3[remainderThree--] = (byte) (sum % 10);
    }

    char[] finalCharacter = new char[number3.length];
    for (int b = 0; b < number3.length; b++) {
      finalCharacter[b] = tochar(number3[b]);
    }

    String result = new String(finalCharacter);
    if (result.charAt(0) == '0') {
      result = result.substring(1);
    }
    return result;
  }

  @Override
  public int compareTo(BigNumber bigNumber) {
    String firstNumber = this.toString();
    String secondNumber = bigNumber.toString();
    return isBigger(firstNumber, secondNumber);
  }

  @Override
  public String toString() {
    return integralNumber.toString();
  }

  /**
   * Stores the string passed in as a big number using a recursive union.
   *
   * @param numberAsString the string value of the number to be stored.
   */
  private void initializeIntegralNumber(String numberAsString) {
    for (int i = numberAsString.length() - 1; i >= 0; i--) {
      char character = numberAsString.charAt(i);
      if (i == numberAsString.length() - 1) {
        this.integralNumber = new Node(character - '0', null);
      } else {
        integralNumber = integralNumber.addToHead(numberAsString.charAt(i) - '0', integralNumber);
      }
    }
  }

  /**
   * Compares two strings to find which is bigger.
   *
   * @param firstNumber the string representation to the first number.
   * @param secondNumber the string representation to the second number.
   * @return an int specifying which string is bigger.
   */
  private int isBigger(String firstNumber, String secondNumber) {
    if (firstNumber.length() > secondNumber.length()) {
      return 1;
    } else if (secondNumber.length() > firstNumber.length()) {
      return -1;
    } else {
      for (int index = 0; index < firstNumber.length(); index++) {
        char firstNumberCharacter = firstNumber.charAt(index);
        char secondNumberCharacter = secondNumber.charAt(index);

        if (Integer.parseInt(String.valueOf(firstNumberCharacter))
            > Integer.parseInt(String.valueOf(secondNumberCharacter))) {
          return 1;
        } else if (Integer.parseInt(String.valueOf(secondNumberCharacter))
            > Integer.parseInt(String.valueOf(firstNumberCharacter))) {
          return -1;
        }
      }
    }
    return 0;
  }
}
