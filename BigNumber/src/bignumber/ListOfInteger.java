package bignumber;

public interface ListOfInteger {
    /**
     * Return the number of books in this list.
     *
     * @return the size of this list
     */
    int count();

    /**
     * Helper for the count method.
     *
     * @param acc the accumulator
     * @return the count from here
     */
    int countHelp(int acc);

    void addDataToEnd(int data);

    public ListOfInteger getNext();

    public void setNext(ListOfInteger  nextNode);

    public String getStringofData();

    public void removeLastNode();

    public int getIntData();
    public int getData(int index) throws IndexOutOfBoundsException,IllegalArgumentException;

    public ListOfInteger addList(ListOfInteger listToAdd);


    public ListOfInteger add(int number);
    public void addHelper(ListOfInteger toAdd);
    public void setData (int data);
    int getDataHelp(int i,int index);
}
