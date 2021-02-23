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
        bigNumberWithValue =new BigNumberImpl("3241183828992092929983099404902934029402940294029402940290294029090294024920490249490249029429242049240904290249204920940904902");
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
        bigNumberWithValue.shiftLeft(5);
        assertEquals(41,bigNumberWithValue.toString());

    }

    @Test
    public void shiftRight() {
        bigNumberWithValue.shiftRight(10);
        bigNumberWithValue.shiftRight(-2);
        bigNumberWithValue.shiftRight(4);
        bigNumberWithValue.shiftRight(9);
        bigNumberWithValue.shiftRight(-3);
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