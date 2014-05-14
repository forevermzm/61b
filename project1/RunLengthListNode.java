/* RunLengthListNode.java */

/**
 *  A RunLengthListNode is a node in a DList (doubly-linked list).
 */

public class RunLengthListNode {

  /**
   *  value references the R, G, B value stored in current node.
   *  repeatedNumbers references the number of consecutive values.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */
  public short redValue;
  public short greenValue;
  public short blueValue;
  public int repeatedNumbers;
  public RunLengthListNode prev;
  public RunLengthListNode next;

  /**
   *  RunLengthListNode() constructor.
   */
  RunLengthListNode() {
    this((short)0, (short) 0, (short) 0, 0);
  }

  RunLengthListNode(short redValue, short greenValue, short blueValue, int repeatedNumbers) {
    this.redValue = redValue;
    this.greenValue = greenValue;
    this.blueValue = blueValue;
    this.repeatedNumbers = repeatedNumbers;
    prev = null;
    next = null;
  }

  public String toString(){
    return String.format("(%d, %d, %d, %d)", redValue, greenValue, blueValue, repeatedNumbers);
  }

}
