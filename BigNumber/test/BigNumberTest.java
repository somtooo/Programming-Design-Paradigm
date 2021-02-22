import bignumber.BigNumber;
import bignumber.BigNumberImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BigNumberTest {

    BigNumber bigNumber;
    BigNumber bigNumberWithValue;
    @Before
    public void setUp() throws Exception {
        bigNumber = new BigNumberImpl();
        bigNumberWithValue =new BigNumberImpl("00000");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testIllegalConstructor() {
        new BigNumberImpl("12345.98");
    }

    @Test
    public void length() {
        assertEquals(1,bigNumber.length());
        assertEquals(5,bigNumberWithValue.length());
    }

    @Test
    public void testConstructor() {
        assertEquals("0",bigNumber.toString());
        assertEquals("32411", bigNumberWithValue.toString());
    }

    @Test
    public void shiftLeft() {
        bigNumberWithValue.shiftLeft(-9);
        assertEquals("32411000",bigNumberWithValue.toString());
        bigNumberWithValue.shiftLeft(-4);
        assertEquals("3241",bigNumberWithValue.toString());
    }

    @Test
    public void shiftRight() {
//        bigNumberWithValue.shiftRight(3);
//        assertEquals("32",bigNumberWithValue.toString());
        bigNumberWithValue.shiftRight(-9);
        assertEquals("3",bigNumberWithValue.toString());
    }

    @Test
    public void addDigit() {
    }

    @Test
    public void getDigitAt() {
        assertEquals(3,bigNumberWithValue.getDigitAt(0));
        assertEquals(2,bigNumberWithValue.getDigitAt(1));
        assertEquals(4,bigNumberWithValue.getDigitAt(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalGetNumber () {
        assertEquals(3,bigNumberWithValue.getDigitAt(-1));
    }

    @Test
    public void compareTo() {
        BigNumber bigNumberEqual = new BigNumberImpl("32411");
        BigNumber bigNumberGreater = new BigNumberImpl("324111");
        BigNumber bigNumberSmaller = new BigNumberImpl("32311");
        assertEquals(0,bigNumberWithValue.compareTo(bigNumberEqual));
        assertEquals(-1,bigNumberWithValue.compareTo(bigNumberGreater));
        assertEquals(1,bigNumberWithValue.compareTo(bigNumberSmaller));

    }
    @Test
    public void copy() {
    }

    @Test
    public void add() {
    }
}