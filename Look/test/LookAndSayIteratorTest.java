import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.NoSuchElementException;
import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;
import org.junit.Before;
import org.junit.Test;





/** Tests the look and say iterator for correctness. */
public class LookAndSayIteratorTest {

  private RIterator<BigInteger> lookAndSay;
  private RIterator<BigInteger> lookAndSayDefault;
  private RIterator<BigInteger> lookAndSaySeed;

  /**
   * Sets up different objects to be used repeatedly in other tests.
   */
  @Before
  public void setUp() {
    BigInteger seed = BigInteger.valueOf(123);
    BigInteger endValue = BigInteger.valueOf(122133313);
    lookAndSay = new LookAndSayIterator(seed, endValue);
    lookAndSaySeed = new LookAndSayIterator(seed);
    lookAndSayDefault = new LookAndSayIterator();
  }

  @Test
  public void testConstructor() {
    assertEquals("Seed : 123, End Value Length: 9 End Value: 122133313", lookAndSay.toString());
    assertEquals(
        "Seed : 123, End Value Length: 100 End Value: 999999999999999999999999"
                +
                "99999999999999999999999999999999"
                +
                "99999999999999999999999999999999999999999999",
        lookAndSaySeed.toString());
    assertEquals(
        "Seed : 1, End Value Length: 100 End Value: 999999999999999999999999999999999999999999"
            + "9999999999999999999999999999999999999999999999999999999999",
        lookAndSayDefault.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidZeroFirstConstructor() {
    RIterator<BigInteger> lookAndSay;
    lookAndSay = new LookAndSayIterator(BigInteger.valueOf(102), BigInteger.valueOf(1222));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeFirstConstructor() {
    RIterator<BigInteger> lookAndSay;
    lookAndSay = new LookAndSayIterator(BigInteger.valueOf(-12), BigInteger.valueOf(1222));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeedFirstConstructor() {
    RIterator<BigInteger> lookAndSay;
    lookAndSay = new LookAndSayIterator(BigInteger.valueOf(1222), BigInteger.valueOf(1222));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeedFirstConstructorGreater() {
    RIterator<BigInteger> lookAndSay;
    lookAndSay = new LookAndSayIterator(BigInteger.valueOf(12223), BigInteger.valueOf(1222));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSecondConstructor() {
    RIterator<BigInteger> lookAndSay;
    lookAndSay = new LookAndSayIterator(BigInteger.valueOf(102234));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeSecondConstructor() {
    RIterator<BigInteger> lookAndSay;
    lookAndSay = new LookAndSayIterator(BigInteger.valueOf(-12234));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeedSecondConstructor() {
    RIterator<BigInteger> lookAndSay;
    BigInteger endValue = new BigInteger("999999999999999999999999999999999999999999"
            + "9999999999999999999999999999999999999999999999999999999999");
    lookAndSay = new LookAndSayIterator(endValue);
  }

  @Test
  public void prev() {
    assertEquals(BigInteger.valueOf(1), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(11), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(1211), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayDefault.prev());
    assertEquals(BigInteger.valueOf(11), lookAndSayDefault.prev());
    assertEquals(BigInteger.valueOf(1), lookAndSayDefault.prev());
  }

  @Test
  public void prevFromMiddle() {
    lookAndSaySeed = new LookAndSayIterator(BigInteger.valueOf(1211));
    assertEquals(BigInteger.valueOf(21), lookAndSaySeed.prev());
    assertEquals(BigInteger.valueOf(11), lookAndSaySeed.prev());
    assertEquals(BigInteger.valueOf(1), lookAndSaySeed.prev());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPrevNoElement() {
    lookAndSaySeed = new LookAndSayIterator(BigInteger.valueOf(1321123113));
    lookAndSaySeed.prev();
    lookAndSaySeed.prev();
    lookAndSaySeed.prev();
    lookAndSaySeed.prev();
  }

  @Test(expected = NoSuchElementException.class)
  public void testPrevDefaultNoElement() {
    lookAndSayDefault.prev();
  }



  @Test
  public void hasPrevious() {
    lookAndSaySeed = new LookAndSayIterator(BigInteger.valueOf(123));
    assertFalse(lookAndSaySeed.hasPrevious());
    lookAndSaySeed = new LookAndSayIterator(BigInteger.valueOf(1111));
    assertEquals(BigInteger.valueOf(11), lookAndSaySeed.prev());
  }

  @Test
  public void hasNext() {
    lookAndSay.next();
    assertTrue(lookAndSay.hasNext());
    lookAndSay.next();
    assertTrue(lookAndSay.hasNext());
    lookAndSay.next();
    assertFalse(lookAndSay.hasNext());
  }

  @Test
  public void next() {
    assertEquals(BigInteger.valueOf(1), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(11), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(1211), lookAndSayDefault.next());

    assertEquals(BigInteger.valueOf(123), lookAndSay.next());
    assertEquals(BigInteger.valueOf(111213), lookAndSay.next());
    assertEquals(BigInteger.valueOf(31121113), lookAndSay.next());

    assertEquals(BigInteger.valueOf(123), lookAndSaySeed.next());
    assertEquals(BigInteger.valueOf(111213), lookAndSaySeed.next());
    assertEquals(BigInteger.valueOf(31121113), lookAndSaySeed.next());
  }

  @Test(expected = NoSuchElementException.class)
  public void nextEndValueTest() {
    assertEquals(BigInteger.valueOf(123), lookAndSay.next());
    assertEquals(BigInteger.valueOf(111213), lookAndSay.next());
    assertEquals(BigInteger.valueOf(31121113), lookAndSay.next());
    assertEquals(BigInteger.valueOf(1321123113), lookAndSay.next());
  }

  @Test(expected = NoSuchElementException.class)
  public void noElementTestForDefaultEndValue() {
    RIterator<BigInteger> lookAndSay;
    BigInteger endValue = new BigInteger("1234567891234567891234567891234567891234567891234567"
            +
            "89123456789123456789123456789123456789123456789");
    lookAndSay = new LookAndSayIterator(endValue);
    lookAndSay.next();
    lookAndSay.next();

  }

  @Test
  public void testToString() {
    assertEquals("Seed : 123, End Value Length: 9 End Value: 122133313", lookAndSay.toString());
  }
}
