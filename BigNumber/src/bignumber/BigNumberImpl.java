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
        return null;
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
        for (int index = 0; index < numberAsString.length(); index++) {
            char character = numberAsString.charAt(index);
            if (index == 0) {
                this.integralNumber = new Node(character-'0',null);
            } else {
                integralNumber.addDataToEnd(character - '0');
            }
        }
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
