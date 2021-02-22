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
        if (next == null) {
            next = tempNode;
        }
        else {
            ListOfInteger nextNode = next;

            while (nextNode.getNext() != null) {
                nextNode = nextNode.getNext();
            }
            nextNode.setNext(tempNode);
        }

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
        if (index < 0) {
            throw new IllegalArgumentException("negative numbers not allowed");
        }
        if (index >= count()) {
            throw new IndexOutOfBoundsException(" index is out of bounds");
        }
        return getDataHelp(0,index);
    }

    @Override
    public ListOfInteger add(ListOfInteger node,int number) {
        ListOfInteger reversedNode = reverse(node);
        int carry = number;

        return null;
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

    public static void main(String[] args) {
        Node listOfInteger =
                new Node(3, new Node(2, new Node(4, new Node(1, new Node(1, null)))));
        System.out.println(listOfInteger.reverse(listOfInteger).toString());
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
