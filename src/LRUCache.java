import java.util.LinkedHashMap;
import java.util.Map;

// 146. LRU cache
// LRU last recently used be discard first.
// function: 
// LRUCache(int capacity) : initialize the capacity
// get(key): get the key value, if can not find key return -1
// put(key , value)

// approach: LinkedHashMap
// LinkedHashMap: 
// Hash table and linked list implementation of the Map interface,with predictable iteration order. 
// This implementation differs from HashMap in that it maintains a doubly-linked list running through all of its entries. 
// This linked list defines the iteration ordering,which is normally the order in which keys were inserted into the map(insertion-order). Note that insertion order is not affected if a key is re-inserted into the map. (A key k is reinserted into a map m if m.put(k, v) is invoked when m.containsKey(k) would return true immediately prior to the invocation.)
// A special constructor is provided to create a linked hash map whose order of iteration is the order in which its entries were last accessed, from least-recently accessed to most-recently (access-order). This kind of map is well-suited to building LRU caches.
public class LRUCache extends LinkedHashMap<Integer, Integer>{
	
	private int capaciy;
	// Parameters:
	// initialCapacity:  the initial capacity
	// load Factor:  the load factor
	// accessOrder:  the ordering mode - true for access-order, false for insertion-order
	public LRUCache(int capacity) { // sapce: O(capacity)
		super(capacity, 0.75F, true);
		this.capaciy = capacity;
	}
	
	// time: get O(1)
	public int get(int key) {
		return super.getOrDefault(key, -1);
	}
	
	// time: put O(1)
	public void put(int key, int value) {
		super.put(key, value);
	}
	// boolean removeEldestEntry
	// Returns true if this map should remove its eldest entry.
	// This method is invoked by put and putAll after inserting a new entry into the map. 
	// It provides the implement or with the opportunity to remove the eldest entry each time a new one is added.
	// Parameters: 
	// e The least recently inserted entry in the map, or if this is an access-ordered map, the least recently accessed entry. 
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e) {
		return size() > capaciy;
	} 
	
	

}
