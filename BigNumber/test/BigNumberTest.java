import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;
import org.junit.Before;
import org.junit.Test;

/** Tests the big number class for correctness. */
public class BigNumberTest {

  BigNumber bigNumber;
  BigNumber bigNumberWithValue;

  /** Sets up object to be used with other tests. */
  @Before
  public void setUp() {
    bigNumber = new BigNumberImpl();
    bigNumberWithValue = new BigNumberImpl("32411");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructor() {
    new BigNumberImpl("1234.598");
  }

  @Test
  public void length() {
    assertEquals(1, bigNumber.length());
    assertEquals(5, bigNumberWithValue.length());
  }

  @Test
  public void testConstructor() {
    assertEquals("0", bigNumber.toString());
    assertEquals("32411", bigNumberWithValue.toString());
  }

  @Test
  public void shiftLeft() {
    bigNumberWithValue = new BigNumberImpl("3241122847661940294745239839210728878728");
    bigNumberWithValue.shiftLeft(-9);
    bigNumberWithValue.shiftLeft(5);
    bigNumberWithValue.shiftLeft(9);
    bigNumberWithValue.shiftLeft(-5);
    bigNumberWithValue.shiftLeft(0);
    assertEquals("3241122847661940294745239839210000000000", bigNumberWithValue.toString());
  }

  @Test
  public void constructNumber() {
    bigNumberWithValue = new BigNumberImpl();
    bigNumberWithValue.shiftLeft(1);
    assertEquals("0", bigNumberWithValue.toString());
    bigNumberWithValue.addDigit(3);
    assertEquals("3", bigNumberWithValue.toString());
    bigNumberWithValue.shiftLeft(1);
    assertEquals("30", bigNumberWithValue.toString());
    bigNumberWithValue.addDigit(2);
    assertEquals("32", bigNumberWithValue.toString());
    bigNumberWithValue.shiftLeft(1);
    assertEquals("320", bigNumberWithValue.toString());
    bigNumberWithValue.addDigit(4);
    assertEquals("324", bigNumberWithValue.toString());
    bigNumberWithValue.shiftLeft(1);
    assertEquals("3240", bigNumberWithValue.toString());
    bigNumberWithValue.addDigit(1);
    assertEquals("3241", bigNumberWithValue.toString());
    bigNumberWithValue.shiftLeft(1);
    assertEquals("32410", bigNumberWithValue.toString());
    bigNumberWithValue.addDigit(1);
    assertEquals("32411", bigNumberWithValue.toString());
  }

  @Test
  public void shiftRight() {
    bigNumberWithValue = new BigNumberImpl("3241122847661940294745239839210728878728");
    bigNumberWithValue.shiftRight(10);
    bigNumberWithValue.shiftRight(-2);
    bigNumberWithValue.shiftRight(4);
    bigNumberWithValue.shiftRight(9);
    bigNumberWithValue.shiftRight(-3);
    assertEquals("3241122847661940294000", bigNumberWithValue.toString());
    BigNumber bigNumberWithValueTwo = new BigNumberImpl();
    bigNumberWithValueTwo.shiftRight(9);
    assertEquals("0", bigNumberWithValueTwo.toString());
  }

  @Test
  public void addDigit() {
    bigNumberWithValue.addDigit(7);
    assertEquals("32418", bigNumberWithValue.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addDigitIllegal() {
    bigNumberWithValue.addDigit(-7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addDigitIllegalTwo() {
    bigNumberWithValue.addDigit(80);
  }

  @Test
  public void getDigitAt() {
    BigNumber bigNumberEqual = new BigNumberImpl("8439218801");
    assertEquals(1, bigNumberEqual.getDigitAt(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetNumber() {
    assertEquals(3, bigNumberWithValue.getDigitAt(-1));
  }

  @Test
  public void compareTo() {
    BigNumber bigNumberEqual = new BigNumberImpl("32411");
    BigNumber bigNumberGreater = new BigNumberImpl("324111");
    assertEquals(0, bigNumberWithValue.compareTo(bigNumberEqual));
    assertEquals(0, bigNumberEqual.compareTo(bigNumberWithValue));
    assertEquals(-1, bigNumberWithValue.compareTo(bigNumberGreater));
    BigNumber bigNumberSmaller = new BigNumberImpl("32311");
    assertEquals(1, bigNumberGreater.compareTo(bigNumberWithValue));
    assertEquals(1, bigNumberWithValue.compareTo(bigNumberSmaller));
    assertEquals(-1, bigNumberSmaller.compareTo(bigNumberWithValue));
  }

  @Test
  public void copy() {
    BigNumber bigNumber = bigNumberWithValue.copy();
    assertTrue(bigNumberWithValue.toString().equals(bigNumber.toString()));
  }

  @Test
  public void add() {
    BigNumber number =
        new BigNumberImpl(
            "99999999999999999999999999999999999999"
                + "99999999999999999999999999999999999999999999999999999999999999");
    BigNumber number2 =
        new BigNumberImpl(
            "9999999999999999999999999999999999999999999"
                + "999999999999999999999999999999999999999999999999999999999");
    assertEquals(
        "19999999999999999999999999999999999999999999999999999"
            + "999999999999999999999999999999999999999999999998",
        number.add(number2).toString());
  }

  @Test
  public void addSecondTest() {
    BigNumber number = new BigNumberImpl("12345678" + "4683290");
    BigNumber number2 = new BigNumberImpl("7835117883" + "389173899");
    assertEquals("7835241340173857189", number.add(number2).toString());
  }
}
