package LLD.MapJava;


class HashMapLite<K, V>{
	
	class Entry<K, V>{
		K key;
		V value;
		Entry next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
	
	Entry [] hashTable;
	
	private static final int MAX_CAPACITY = 1<<30; // Maximum capacity of hash map
	private static final int INITIAL_SIZE = 1<<4; // 16
	
	public HashMapLite() {
		hashTable = new Entry[INITIAL_SIZE];
	}
	
	public HashMapLite(int capacity) {
		int size = getProperSize(capacity);
		hashTable = new Entry[size];
	}
	
	// Since size of hashTable must be in powers of 2
	// So this function returns the just greater number (which is of power 2) than size if size is not of power 2.
	private int getProperSize(int size) {
		int n = size-1; // it is required :)
		
		// The below steps will make all the bits of n as 1 starting from right most bit up to most significant bit
		n |= n >>> 1; // >>> operator shifts even the bit used for sign
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		
		// in case size is negative
		return (n < 0) ? 1: ( (n+1) >= MAX_CAPACITY ? MAX_CAPACITY: (n+1) ) ;
	}
	
	
	public void put(K key, V value) {
		// get hash of key
		// key.hashCode() returns the integer form of internal address of the object key
		int hashCode = key.hashCode() % this.hashTable.length; 
		// place it in table
		Entry entry = this.hashTable[hashCode];
		
		if( entry == null ) {
			entry = new Entry(key, value);
			this.hashTable[hashCode] = entry;
			return;
		}
		
		Entry prev = entry;
		while( entry != null ) {
			if( entry.key.equals(key) ) {
				entry.value = value;
				return;
			}
			prev = entry;
			entry = entry.next;
		}		
		prev.next = new Entry(key, value);
	}
	
	public V get(K key) {
		// get hash of key
		int hashCode = key.hashCode() % this.hashTable.length;
		// get it from table
		Entry entry = this.hashTable[hashCode];
		
		while( entry != null ) {
			if( entry.key.equals(key) ) // use equals here
				return (V)entry.value;
			entry = entry.next;
		}
		return null;
	}
}


public class Demo {

	public static void main(String[] args) {
		HashMapLite<Integer, String> hml = new HashMapLite<Integer, String>();
		
		hml.put(1, "IKIGAI");
		hml.put(2, "The Lost Symbol");
		hml.put(3, "The Psychology of Money");
		hml.put(4, "Murder on the Orient Express");
		hml.put(5, "The Ride of a Lifetime");

		System.out.println("1 --> "+ hml.get(1));
		
		System.out.println("5 --> "+ hml.get(5));

		hml.put(5, "XYZ");
		
		System.out.println("5 --> "+ hml.get(5));
		
		
		
	}

}
