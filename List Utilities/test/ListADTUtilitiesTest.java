import listadt.ListADT;
import listadt.ListADTImpl;
import listadt.ListADTUtilities;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ListADTUtilities for correctness.
 */
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
        Integer[] arr = new Integer[] {1,2,3,4};
        Integer[] emptyArr = new Integer[] {};

        ListADT listADT =  ListADTUtilities.toList(arr);
        assertEquals("(1 2 3 4)", listADT.toString());
        listADT = ListADTUtilities.toList(emptyArr);
        assertEquals("()", listADT.toString());


    }

    @Test
    public void addAll() {
    }

    @Test
    public void frequency() {
    }

    @Test
    public void disjoint() {
    }

    @Test
    public void testEquals() {
    }
}