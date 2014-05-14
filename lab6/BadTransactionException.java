/* BadTransactionException.java */

/**
 *  Implements an exception that should be thrown for bad transaction value.
 **/
public class BadTransactionException extends Exception {

  /**
   *  Creates an exception object for a bad transaction value attempt.
   **/
  public BadTransactionException(int badTransactionValue) {
    super("Invalid transaction value: " + badTransactionValue);
  }
}