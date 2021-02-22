package bignumber;

public class Node implements ListOfInteger {
    private int data;
    private ListOfInteger next;

    public Node(int data, ListOfInteger rest)  {
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
        ListOfInteger tempNode = new Node(data,null);
        add(this,tempNode);
//        if (next == null) {
//            next = tempNode;
//        }
//        else {
//            ListOfInteger nextNode = next;
//
//            while (nextNode.getNext() != null) {
//                nextNode = nextNode.getNext();
//            }
//            nextNode.setNext(tempNode);
//        }

    }
    public void addHelper(ListOfInteger obj,ListOfInteger toAdd){

        if(obj.getNext()== null)obj.setNext(toAdd);
        else obj.getNext().add()

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


        while (tempNextNode.getNext() != null)  {
            tempNextNode = tempNextNode.getNext();
            tempNode = tempNode.getNext();
        }
        tempNode.setNext(null);

    }




    @Override
    public int getData(int index) {
        ListOfInteger current = this;
        int position = 1;
        while (current != null) {
            if (position == index) {
                return current.getIntData();
            }
            position++;
            current = current.getNext();
        }
        assert (false);
        return 0;
    }

    @Override
    public int getIntData() {
        return data;
    }

    public static void main(String[] args) {
        Node listOfInteger =
                new Node(9, new Node(9, new Node(9, new Node(9, new Node(3, null)))));
        Node listOfIntegerAdd =
                new Node(9, new Node(9, new Node(9, new Node(9, new Node(3, null)))));
        System.out.println(listOfInteger.addList(listOfIntegerAdd).toString());
    }
    @Override
    public ListOfInteger addList(ListOfInteger listToAdd) {
        ListOfInteger reversedNode = reverse(this);
        ListOfInteger reverseSecondNode = reverse(listToAdd);

        ListOfInteger resultantListHead = null;
        ListOfInteger prev = null;
        ListOfInteger temp = null;
        int carry = 0, sum;

        while (reversedNode!=null || reverseSecondNode !=null) {
            sum = carry + (reversedNode != null ? reversedNode.getIntData() : 0)
                    + (reverseSecondNode != null ? reverseSecondNode.getIntData():0);
            carry = (sum >=10) ? 1 : 0;

            sum = sum%10;
            temp = new Node(sum,null);

            if (resultantListHead == null) {
                resultantListHead = temp;
            } else {
                prev.setNext(temp);
            }
            prev = temp;
            if (reversedNode != null) {
                reversedNode = reversedNode.getNext();
            }
            if (reverseSecondNode != null) {
                reverseSecondNode = reverseSecondNode.getNext();
            }
        }
        if (carry > 0) {
            temp.addDataToEnd(carry);
        }
        resultantListHead = reverse(resultantListHead);
        return resultantListHead;
    }

    @Override
    public ListOfInteger add(int number) {
        ListOfInteger reversedNode = reverse(this);
        int carry = number;

        ListOfInteger current = reversedNode;
        while (carry > 0) {
            int sum = current.getData(0) + carry;
            current.setData(sum % 10);
            carry = sum/10;
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
    public int getDataHelp(int accumulator, int index) {
        if (accumulator == index) {
            return data;
        }
        return this.next.getDataHelp(1 + accumulator,index);
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
        if (getStringofData().equals("0")){
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
