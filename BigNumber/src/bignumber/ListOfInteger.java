package bignumber;

/** Represents functionality of a node that's part of a linked list. */
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

  /**
   * Adds data to the end of a list.
   *
   * @param data the data to be added
   */
  void addDataToEnd(int data);

  /**
   * Gets the next list from a node.
   *
   * @return the next node that the node is pointing too.
   */
  ListOfInteger getNext();

  /**
   * Sets the value for the next node.
   *
   * @param nextNode the node to be set next
   */
  void setNext(ListOfInteger nextNode);

  /**
   * Gets the string value of tht data.
   *
   * @return the data as a string.
   */
  String getStringofData();

  /** Removes the last node from the list. */
  void removeLastNode();

  /**
   * Gets the int value of data.
   *
   * @return the int value of data
   */
  int getIntData();

  /**
   * Adds a node to the head of the list.
   *
   * @param data the data to instantiate the node with.
   * @param list the head of the list.
   * @return the new list head.
   */
  ListOfInteger addToHead(int data, ListOfInteger list);

  /**
   * Gates the data at the specified index.
   *
   * @param index the index to get the data at.
   * @return the data.
   */
  int getDigitAt(int index);

  /**
   * Adds a number to the link list.
   *
   * @param number number to add.
   * @return the head of the new list.
   */
  ListOfInteger add(int number);

  /**
   * Helper method to add a node to the end of a list.
   *
   * @param toAdd the node to add.
   */
  void addHelper(ListOfInteger toAdd);

  /**
   * Sets the data of the node.
   *
   * @param data the data to set the node with.
   */
  void setData(int data);
}
