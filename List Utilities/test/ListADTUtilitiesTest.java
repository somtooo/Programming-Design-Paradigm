import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import listadt.ListADT;
import listadt.ListADTImpl;
import listadt.ListADTUtilities;
import org.junit.Before;
import org.junit.Test;



/** Tests the ListADTUtilities for correctness. */
public class ListADTUtilitiesTest {

  ListADT<String> list;
  ListADT<Integer> integerList;

  @Before
  public void setUp() throws Exception {
    list = new ListADTImpl<String>();
    integerList = new ListADTImpl<Integer>();
  }

  @Test
  public void toList() {
    Integer[] arr = new Integer[] {1, 2, 3, 4};
    Integer[] emptyArr = new Integer[] {};

    ListADT listADT = ListADTUtilities.toList(arr);
    assertEquals("(1 2 3 4)", listADT.toString());

    listADT = ListADTUtilities.toList(emptyArr);
    assertEquals("()", listADT.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalToList() {
    Integer[] arr = new Integer[] {1, 2, null, 4};
    ListADT listADT = ListADTUtilities.toList(arr);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalMoreToList() {
    Integer[] arr = new Integer[] {1, 2, null, null};
    ListADTUtilities.toList(arr);
  }

  @Test
  public void addAll() {
    list.addBack("goat");
    list.addBack("chicken");
    list.addBack("foul");

    ListADTUtilities.addAll(list, "dest", "abdul");
    assertEquals("(goat chicken foul dest abdul)", list.toString());
    ListADTUtilities.addAll(list, "cow");
    assertEquals("(goat chicken foul dest abdul cow)", list.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAllNull() {
    list.addBack("goat");
    list.addBack("chicken");
    list.addBack("foul");
    ListADTUtilities.addAll(list, "dest", "abdul", null, null);
  }

  @Test
  public void frequency() {
    list.addBack("goat");
    list.addBack("chicken");
    list.addBack("fowl");
    assertEquals(1, ListADTUtilities.frequency(list, "goat"));
    assertEquals(1, ListADTUtilities.frequency(list, "chicken"));
    assertEquals(0, ListADTUtilities.frequency(list, "abdul"));
    assertEquals(0, ListADTUtilities.frequency(list, null));
    list.addBack("goat");
    list.addBack("goat");
    assertEquals(3, ListADTUtilities.frequency(list, "goat"));

    ListADT<String> emptyList = new ListADTImpl<String>();
    assertEquals(0, ListADTUtilities.frequency(emptyList, "goat"));
    emptyList.addBack(null);
    emptyList.addBack("yo yo");
    emptyList.addBack("cow");
    emptyList.addBack("cow");
    emptyList.addBack("feet");
    assertEquals(2, ListADTUtilities.frequency(emptyList, "cow"));
    assertEquals(1, ListADTUtilities.frequency(emptyList, null));
    emptyList.addBack(null);
    emptyList.addBack(null);
    assertEquals(3, ListADTUtilities.frequency(emptyList, null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullFrequency() {
    ListADTUtilities.frequency(null, "cow");
  }

  @Test
  public void disjoint() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("fowl");
    list.addBack("goat");
    list.addBack("chicken");
    assertFalse(ListADTUtilities.disjoint(list, newList));
  }

  @Test
  public void disjointTrue() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("meat");
    newList.addBack("fowl");
    list.addBack("goat");
    list.addBack("chicken");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test
  public void disjointEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test
  public void disjointOneEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    list.addBack("goat");
    list.addBack("fowl");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test
  public void disjointTwoEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("cow");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointIllegal() {
    assertTrue(ListADTUtilities.disjoint(list, null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointIllegalNull() {
    assertTrue(ListADTUtilities.disjoint(null, list));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointNullElement() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("chicken");
    newList.addBack(null);
    list.addBack("fowl");
    list.addBack("beast");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointNullElementReversed() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("chicken");
    newList.addBack(null);
    list.addBack("fowl");
    list.addBack("beast");
    assertTrue(ListADTUtilities.disjoint(newList, list));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointNullElementTwo() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("chicken");
    newList.addBack("fowl");
    list.addBack(null);
    list.addBack("beast");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointNullElementTwoReversed() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("chicken");
    newList.addBack("fowl");
    list.addBack(null);
    list.addBack("beast");
    assertTrue(ListADTUtilities.disjoint(newList, list));
  }

  @Test
  public void testEquals() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("cow");
    list.addBack("goat");
    list.addBack("cow");
    assertTrue(ListADTUtilities.equals(list, newList));
    assertTrue(ListADTUtilities.equals(newList, list));
  }

  @Test
  public void testEqualsDifferentOrder() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("cow");
    newList.addBack("goat");
    list.addBack("goat");
    list.addBack("cow");
    assertFalse(ListADTUtilities.equals(list, newList));
  }

  @Test
  public void testEqualsEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    list.addBack("goat");
    list.addBack("fowl");
    assertFalse(ListADTUtilities.equals(list, newList));
  }

  @Test
  public void testEqualsTwoEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("fowl");
    assertFalse(ListADTUtilities.equals(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualsNull() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("fowl");
    assertFalse(ListADTUtilities.equals(null, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualsNullTwo() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("fowl");
    assertFalse(ListADTUtilities.equals(list, null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualsNullElement() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack("fowl");
    list.addBack("goat");
    list.addBack(null);
    assertFalse(ListADTUtilities.equals(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualsNullElementTwo() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("goat");
    newList.addBack(null);
    list.addBack("goat");
    list.addBack("fowl");
    assertFalse(ListADTUtilities.equals(list, newList));
  }
}
