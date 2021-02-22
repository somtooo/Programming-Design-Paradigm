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
        bigNumberWithValue =new BigNumberImpl("32411");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testIllegalConstructor() {
        new BigNumberImpl("1234 598");
    }

    @Test
    public void length() {
        assertEquals(1,bigNumber.length());
        assertEquals(5,bigNumberWithValue.length());
    }

    @Test
    public void testConstructor() {
        assertEquals("0",bigNumber.toString());
        assertEquals("3241144", bigNumberWithValue.toString());
    }

    @Test
    public void shiftLeft() {
        bigNumberWithValue.shiftLeft(-9);
//        assertEquals("32411000",bigNumberWithValue.toString());

        assertEquals(41,bigNumberWithValue.length());
        bigNumberWithValue.shiftLeft(-4);
        assertEquals("3241",bigNumberWithValue.toString());
    }

    @Test
    public void shiftRight() {
        bigNumberWithValue.shiftRight(9);
        assertEquals("3",bigNumberWithValue.toString());
    }

    @Test
    public void addDigit() {
        bigNumberWithValue.addDigit(7);
        assertEquals("",bigNumberWithValue.toString());
    }

    @Test
    public void getDigitAt() {

        BigNumber bigNumberEqual = new BigNumberImpl("8439218801");
        assertEquals(0,bigNumberEqual.getDigitAt(4));
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
        BigNumber number = new BigNumberImpl("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        BigNumber number2 = new BigNumberImpl("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        assertEquals("",number.add(number2));

    }
}