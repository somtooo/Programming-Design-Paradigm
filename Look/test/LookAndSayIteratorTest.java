import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
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
    BigInteger seed = BigInteger.valueOf(11);
    BigInteger endValue = BigInteger.valueOf(122133313);
    lookAndSay = new LookAndSayIterator(seed, endValue);
    lookAndSaySeed = new LookAndSayIterator(seed);
    lookAndSayDefault = new LookAndSayIterator();
  }

  @Test
  public void testConstructor() {
    assertEquals("Seed : 11, End Value Length: 10 End Value: 1213", lookAndSay.toString());
    assertEquals(
        "Seed : 121, End Value Length: 100 End Value: 999999999999999999999999999999999999"
            + "9999999999999999999999999999999999999999999999999999999999999999",
        lookAndSaySeed.toString());
    assertEquals(
        "Seed : 1, End Value Length: 100 End Value: 999999999999999999999999999999999999999999"
            + "9999999999999999999999999999999999999999999999999999999999",
        lookAndSayDefault.toString());
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
  public void hasPrevious() {
    lookAndSaySeed = new LookAndSayIterator(BigInteger.valueOf(123));
    assertFalse(lookAndSaySeed.hasPrevious());
    lookAndSaySeed = new LookAndSayIterator(BigInteger.valueOf(1001));
    assertEquals(BigInteger.valueOf(2444), lookAndSaySeed.prev());
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
    //    assertEquals(BigInteger.valueOf(2112131211), lookAndSay.next());
    assertEquals(BigInteger.valueOf(1), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(21), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(1211), lookAndSayDefault.next());
    assertEquals(BigInteger.valueOf(111221), lookAndSayDefault.next());
  }

  @Test
  public void testToString() {}
}
