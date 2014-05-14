/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

    /**
     *  Place any data fields here.
     **/
    SList[] lists;
    private int numberOfEntries;

    /**
     *  Construct a new empty hash table intended to hold roughly sizeEstimate
     *  entries.  (The precise number of buckets is up to you, but we recommend
     *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
     **/

    public HashTableChained(int sizeEstimate) {
        int prime = findMaxPrime(sizeEstimate * 2);
        lists = new SList[prime];
        numberOfEntries = 0;
    }

    /**
     *  Construct a new empty hash table with a default size.  Say, a prime in
     *  the neighborhood of 100.
     **/

    public HashTableChained() {
        this(50);
    }

    /**
     *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of hash table) - 1.
     *
     *  This function should have package protection (so we can test it), and
     *  should be used by insert, find, and remove.
     **/

    int compFunction(int code) {
        // Replace the following line with your solution.
        return code % lists.length;
    }

    /**
     *  Returns the number of entries stored in the dictionary.  Entries with
     *  the same key (or even the same key and value) each still count as
     *  a separate entry.
     *  @return number of entries in the dictionary.
     **/

    public int size() {
        // Replace the following line with your solution.
        return numberOfEntries;
    }

    /**
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/

    public boolean isEmpty() {
        // Replace the following line with your solution.
        return numberOfEntries == 0;
    }

    /**
     *  Create a new Entry object referencing the input key and associated value,
     *  and insert the entry into the dictionary.  Return a reference to the new
     *  entry.  Multiple entries with the same key (or even the same key and
     *  value) can coexist in the dictionary.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/

    public Entry insert(Object key, Object value) {
        // Replace the following line with your solution.
        Entry entry = new Entry();
        entry.key = key;
        entry.value = value;

        int index = compFunction(key.hashCode());
        System.out.println("Index for " + key + " is: " + index);
        SList list;
        if (lists[index] == null)
            list = new SList();
        else {
            list = lists[index];
        }
        list.insertBack(entry);
        lists[index] = list;

        numberOfEntries ++;
        return entry;
    }

    /**
     *  Search for an entry with the specified key.  If such an entry is found,
     *  return it; otherwise return null.  If several entries have the specified
     *  key, choose one arbitrarily and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/

    public Entry find(Object key) {
        // Replace the following line with your solution.
        int index = compFunction(key.hashCode());

        if (lists[index] == null)
            return null;
        else {
            SList list = lists[index];
            ListNode node = list.front();
            if (node.isValidNode()) {
                try {
                    while (true) {
                        Entry entry = (Entry) node.item();
                        if (entry.key.equals(key))
                            return entry;
                        else {
                            node = node.next();
                        }
                    }
                } catch (InvalidNodeException ex) {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     *  Remove an entry with the specified key.  If such an entry is found,
     *  remove it from the table and return it; otherwise return null.
     *  If several entries have the specified key, choose one arbitrarily, then
     *  remove and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */

    public Entry remove(Object key) {
        // Replace the following line with your solution.
        int index = compFunction(key.hashCode());

        if (lists[index] == null)
            return null;
        else {
            SList list = lists[index];
            ListNode node = list.front();
            if (node.isValidNode()) {
                try {
                    while (true) {
                        Entry entry = (Entry) node.item();
                        if (entry.key.equals(key)) {
                            node.remove();
                            numberOfEntries --;
                            return entry;
                        } else {
                            node = node.next();
                        }
                    }
                } catch (InvalidNodeException ex) {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     *  Remove all entries from the dictionary.
     */
    public void makeEmpty() {
        // Your solution here.
        lists = new SList[lists.length];
        numberOfEntries = 0;
    }

    /**
     * Find the max prime smaller than the given n.
     */
    private int findMaxPrime(int n) {
        for (int i = n ; i > 1 ; i -- ) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 2;
    }

    /**
     * Check if the given number is a prime.
     */
    private boolean isPrime(int n) {
        for (int i = 2 ; i < Math.sqrt(n) + 1 ; i ++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HashTableChained hashTable = new HashTableChained();
        System.out.println("Hash table is empty." + hashTable.isEmpty());

        hashTable.insert(3, "Hello");
        hashTable.insert(100, "World");
        System.out.println("Hash table size should be 2. " + (hashTable.size() == 2));

        Entry entry = hashTable.find(3);
        System.out.println("The value for 3 is :" + entry.value);

        entry = hashTable.find(100);
        System.out.println("The value for 100 is :" + entry.value);

        hashTable.remove(100);
        System.out.println("The value for 100 is :" + hashTable.find(100));
        System.out.println("The value for 3 is :" + hashTable.find(3).value);

        hashTable.makeEmpty();
        System.out.println("Hash table size should be 0. " + (hashTable.size() == 0));
        System.out.println("The value for 100 is :" + hashTable.find(100));
        System.out.println("The value for 3 is :" + hashTable.find(3));
    }

}
