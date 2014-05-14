/* RunLengthList.java */

/**
 *  A RunLengthList is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 */

public class RunLengthList {

    /**
     *  head references the sentinel node.
     *
     *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */

    protected RunLengthListNode head;
    protected long size;

    /* RunLengthList invariants:
     *  1)  head != null.
     *  2)  For any RunLengthListNode x in a RunLengthList, x.next != null.
     *  3)  For any RunLengthListNode x in a RunLengthList, x.prev != null.
     *  4)  For any RunLengthListNode x in a RunLengthList, if x.next == y, then y.prev == x.
     *  5)  For any RunLengthListNode x in a RunLengthList, if x.prev == y, then y.next == x.
     *  6)  size is the number of RunLengthListNodes, NOT COUNTING the sentinel
     *      (denoted by "head"), that can be accessed from the sentinel by
     *      a sequence of "next" references.
     */

    /**
     *  RunLengthList() constructor for an empty RunLengthList.
     */
    public RunLengthList() {
        head = new RunLengthListNode();
        head.redValue = Short.MIN_VALUE;
        head.greenValue = Short.MIN_VALUE;
        head.blueValue = Short.MIN_VALUE;
        head.repeatedNumbers = Integer.MIN_VALUE;
        head.next = head;
        head.prev = head;
        size = 0;
    }

    /**
     *  RunLengthList() constructor for a one-node RunLengthList.
     */
    public RunLengthList(short redValue, short greenValue, short blueValue, int repeatedNumbers) {
        head = new RunLengthListNode();
        head.redValue = Short.MIN_VALUE;
        head.greenValue = Short.MIN_VALUE;
        head.blueValue = Short.MIN_VALUE;
        head.repeatedNumbers = Integer.MIN_VALUE;
        head.next = new RunLengthListNode();
        head.next.redValue = redValue;
        head.next.greenValue = greenValue;
        head.next.blueValue = blueValue;
        head.next.repeatedNumbers = repeatedNumbers;
        head.prev = head.next;
        head.next.prev = head;
        head.prev.next = head;
        size = 1;
    }

    /**
     *  insertFront() inserts an value at the front of a RunLengthList.
     */
    public void insertFront(short redValue, short greenValue, short blueValue, int repeatedNumbers) {
        RunLengthListNode newNode = new RunLengthListNode(redValue, greenValue, blueValue, repeatedNumbers);
        newNode.next = head.next;
        newNode.next.prev = newNode;
        newNode.prev = head;
        head.next = newNode;

        if (size == 0) {
            head.prev = newNode;
        }

        size ++;
    }

    /**
     *  removeFront() removes the first value (and first non-sentinel node) from
     *  a RunLengthList.  If the list is empty, do nothing.
     */
    public void removeFront() {
        if ( size == 0 ) return;

        head.next = head.next.next;
        head.next.prev = head;

        size --;
    }

    public void insertBack(short redValue, short greenValue, short blueValue, int repeatedNumbers) {
        RunLengthListNode newNode = new RunLengthListNode(redValue, greenValue, blueValue, repeatedNumbers);

        newNode.next = head;
        newNode.prev = head.prev;
        newNode.next.prev = newNode;
        newNode.prev.next = newNode;

        if (size == 0) {
            head.next = newNode;
        }

        size ++;
    }

    public void removeBack() {
        if (size == 0) return;

        head.prev = head.prev.prev;
        head.prev.next = head;

        size --;
    }

    /**
     *  toString() returns a String representation of this RunLengthList.
     *
     *  DO NOT CHANGE THIS METHOD.
     *
     *  @return a String representation of this RunLengthList.
     */
    public String toString() {
        String result = "[  ";
        RunLengthListNode current = head.next;
        while (current != head) {
            result = result + String.format("(%d, %d, %d, %d)", current.redValue, current.greenValue, current.blueValue);
            current = current.next;
        }
        return result + "]";
    }

}
