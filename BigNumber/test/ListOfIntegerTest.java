import static org.junit.Assert.assertEquals;

import bignumber.ListOfInteger;
import bignumber.Node;
import org.junit.Before;
import org.junit.Test;



/** Tests the ListOfInteger Implementation for correctness. */
public class ListOfIntegerTest {
  private ListOfInteger listOfInteger;
  private ListOfInteger node;

  /** Sets up object to be used by other test. */
  @Before
  public void setUp() {
    listOfInteger = new Node(3, new Node(2, new Node(4, new Node(1, new Node(1, null)))));
    node = new Node(1, null);
  }

  @Test
  public void count() {
    assertEquals(5, listOfInteger.count());
  }

  @Test
  public void testAdd() {
    node.addDataToEnd(2);
    node.addDataToEnd(3);
    node.addDataToEnd(4);
    assertEquals("1234", node.toString());
  }

  @Test
  public void testRemoveLastNode() {
    listOfInteger.removeLastNode();
    listOfInteger.removeLastNode();
    node.removeLastNode();
    assertEquals("0", node.toString());
    assertEquals("324", listOfInteger.toString());
  }

  @Test
  public void testGetDataAtIndex() {
    assertEquals(4, listOfInteger.getDigitAt(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetData() {
    assertEquals(0, listOfInteger.getDigitAt(5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetDataNegative() {
    assertEquals(0, listOfInteger.getDigitAt(-1));
  }

  @Test
  public void testAddNumber() {
    listOfInteger.add(9);
    assertEquals("32420", listOfInteger.toString());
  }

  @Test
  public void testToString() {
    ListOfInteger inte = new Node(0, new Node(0, new Node(2, new Node(4, new Node(1, null)))));
    assertEquals("241", inte.toString());
  }

  @Test
  public void getDigitAtIndex() {
    assertEquals(1, listOfInteger.getDigitAt(1));
    assertEquals(3, listOfInteger.getDigitAt(4));
  }
}
