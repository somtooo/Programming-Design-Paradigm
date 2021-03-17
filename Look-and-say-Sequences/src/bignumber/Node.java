package bignumber;

/** Represents a node that's part of a linked List. */
public class Node implements ListOfInteger {
  private int data;
  private ListOfInteger next;

  /**
   * Initializes a node with the required parameters.
   *
   * @param data the data the node should hold.
   * @param rest the pointer to the next node.
   */
  public Node(int data, ListOfInteger rest) {
    this.data = data;
    this.next = rest;
  }

  @Override
  public int count() {
    return countHelp(0);
  }

  @Override
  public int countHelp(int accumulator) {
    if (this.next == null) {
      return accumulator + 1;
    }
    return this.next.countHelp(1 + accumulator);
  }

  @Override
  public void addDataToEnd(int data) {
    ListOfInteger tempNode = new Node(data, null);
    addHelper(tempNode);
  }

  @Override
  public ListOfInteger addToHead(int data, ListOfInteger object) {
    ListOfInteger newNode = new Node(data, null);
    newNode.setNext(object);
    return newNode;
  }

  @Override
  public void removeLastNode() {
    if (next == null) {
      this.data = 0;
      return;
    }
    if (next.getNext() == null) {
      next = null;
      return;
    }
    ListOfInteger tempNode = next;
    ListOfInteger tempNextNode = next.getNext();

    while (tempNextNode.getNext() != null) {
      tempNextNode = tempNextNode.getNext();
      tempNode = tempNode.getNext();
    }
    tempNode.setNext(null);
  }

  @Override
  public int getDigitAt(int i) {
    int index = (count() - i) - 1;
    if (index < 0) {
      throw new IllegalArgumentException("wrong");
    }
    ListOfInteger current = this;
    int position = 0;
    while (current != null) {
      if (position == index) {
        return current.getIntData();
      }
      position++;
      current = current.getNext();
    }
    throw new IllegalArgumentException("wrong");
  }

  @Override
  public int getIntData() {
    return data;
  }

  @Override
  public ListOfInteger add(int number) {
    ListOfInteger reversedNode = reverse(this);
    System.out.println(reversedNode.toString());
    int carry = number;

    ListOfInteger current = reversedNode;
    while (carry > 0) {
      int sum = current.getIntData() + carry;
      current.setData(sum % 10);
      carry = sum / 10;
      if (current.getNext() == null) {
        break;
      }
      current = current.getNext();
    }
    if (carry > 0) {
      current.addDataToEnd(carry);
    }
    reversedNode = reverse(reversedNode);
    return reversedNode;
  }

  @Override
  public void setData(int data) {
    this.data = data;
  }

  /**
   * Reverses a linked list.
   *
   * @param head the head of the link list
   * @return the reversed list
   */
  private ListOfInteger reverse(ListOfInteger head) {
    ListOfInteger prev = null;
    ListOfInteger current = head;
    ListOfInteger nextNode;

    while (current != null) {
      nextNode = current.getNext();
      current.setNext(prev);

      prev = current;
      current = nextNode;
    }
    head = prev;
    return head;
  }

  @Override
  public void addHelper(ListOfInteger toAdd) {
    if (this.getNext() == null) {
      this.setNext(toAdd);
    } else {
      this.getNext().addHelper(toAdd);
    }
  }

  @Override
  public ListOfInteger getNext() {
    return next;
  }

  @Override
  public void setNext(ListOfInteger nextNode) {
    next = nextNode;
  }

  @Override
  public String getStringofData() {
    return String.valueOf(data);
  }

  @Override
  public String toString() {
    boolean firstString0 = false;
    StringBuilder builder = new StringBuilder();
    if (next == null) {
      return String.valueOf(data);
    }
    if (getStringofData().equals("0")) {
      firstString0 = true;
    }
    if (!getStringofData().equals("0")) {
      builder.append(getStringofData());
    }
    ListOfInteger nextNode = next;
    while (nextNode.getNext() != null) {
      if (firstString0 && nextNode.getStringofData().equals("0")) {
        nextNode = nextNode.getNext();
        continue;
      }
      builder.append(nextNode.getStringofData());
      nextNode = nextNode.getNext();
    }
    builder.append(nextNode.getStringofData());
    return builder.toString();
  }
}
