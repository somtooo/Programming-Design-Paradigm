package bignumber;

/**
 * Represents a very big integer value.
 */
public class BigNumberImpl implements BigNumber {
    private ListOfInteger integralNumber;

    /**
     * Constructor initializes the big number as 0.
     */
    public BigNumberImpl() {
        integralNumber = new Node(0,null);
    }

    /**
     * Constructor initializes big number with the required parameters.
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
            for (int index = 0; index < Math.abs(num); index++) {
                integralNumber.removeLastNode();
            }
        }

    }

    @Override
    public void shiftRight(int num) {
        if (num >= 0) {
            for (int index = 0; index < num; index++) {
                integralNumber.removeLastNode();
            }
        } else {
            for (int index = 0; index < Math.abs(num); index++) {
                integralNumber.addDataToEnd(0);
            }
        }
    }

    @Override
    public void addDigit(int digit) throws IllegalArgumentException {
        if (digit < 0) {
            throw new IllegalArgumentException("negative not allowed");
        } if (Integer.toString(digit).length() > 1) {
            throw new IllegalArgumentException("only single digit allowed");
        }
        integralNumber.add(digit);
    }

    @Override
    public int getDigitAt(int position) throws IllegalArgumentException {
        if (position < 0) {
            throw new IllegalArgumentException("Negative not allowed");
        }
        return integralNumber.getData(position);
    }

    @Override
    public BigNumber copy() {
        String copyString = this.toString();
        return new BigNumberImpl(copyString);
    }


    @Override
    public BigNumber add(BigNumber other) {
        String firstString = this.toString();
        String secondString = other.toString();
        if (firstString.length() >= secondString.length())
            return new BigNumberImpl(this.addstr(firstString, secondString));
        return new BigNumberImpl(this.addstr(secondString,firstString));

    }

    private char tochar(byte b) {
        switch (b) {
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            case 0: return '0';
            default: return '0';
        }
    }

    private String addstr(String lrg, String sml) {

        byte[] n1 = new byte[lrg.length()];
        byte[] n2 = new byte[sml.length()];

        for (int i=0; i<lrg.length(); i++) {
            char c = lrg.charAt(i);
            byte in = (byte) Character.getNumericValue(c);
            n1[i] = in;
        }
        for (int i=0; i<sml.length(); i++) {
            char c = sml.charAt(i);
            byte in = (byte) Character.getNumericValue(c);
            n2[i] = in;
        }
        int mx = Math.max(n1.length, n2.length);
        byte[] n3 = new byte[mx+1];
        int r1=n1.length-1, r2=n2.length-1, r3=n3.length-1;
        byte carry=0;

        while (r3 >= 0) {
            byte sum = carry;

            if (r1 >= 0) {
                sum += n1[r1--];
            } if (r2 >= 0) {
                sum += n2[r2--];
            }
            carry = (byte) (sum / 10);
            n3[r3--] = (byte) (sum % 10);
        }

        char[] cc = new char[n3.length];
        for (int b=0; b<n3.length; b++) {
            cc[b] = tochar(n3[b]);
        }

        String ret = new String(cc);
        if (ret.charAt(0) == '0')
            ret = ret.substring(1);
        return ret;
    }

    @Override
    public int compareTo(BigNumber bigNumber) {
        String firstNumber = this.toString();
        String secondNumber = bigNumber.toString();
        return isBigger(firstNumber,secondNumber);
    }


    @Override
    public String toString() {
        return integralNumber.toString();
    }


    /**
     * Stores the string passed in as a big number using a recursive union.
     * @param numberAsString the string value of the number to be stored.
     */
    private void initializeIntegralNumber(String numberAsString) {
        helper(0,numberAsString);
    }

    private void  helper(int index, String num) {
        if (index == num.length()) {
            return;
        }
        if (index == 0) {
            char character = num.charAt(index);
            this.integralNumber = new Node(character - '0',null);
        } else {
            integralNumber.addDataToEnd(num.charAt(index) - '0');
        }
        helper(index + 1,num);

    }

    private int isBigger(String firstNumber, String secondNumber) {
        if (firstNumber.length() > secondNumber.length()) {
            return 1;
        } else if (secondNumber.length() > firstNumber.length()) {
            return -1;
        } else {
            for (int index = 0; index < firstNumber.length(); index++) {
                char firstNumberCharacter = firstNumber.charAt(index);
                char secondNumberCharacter = secondNumber.charAt(index);

                if (Integer.parseInt(String.valueOf(firstNumberCharacter)) > Integer.parseInt(String.valueOf(secondNumberCharacter))) {
                    return 1;
                } else if (Integer.parseInt(String.valueOf(secondNumberCharacter)) > Integer.parseInt(String.valueOf(firstNumberCharacter))) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
