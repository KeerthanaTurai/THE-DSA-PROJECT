import java.util.*;

/**
 * Problem 146. LRU Cache
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Intuition:
 *   We need O(1) get and put, plus eviction of the least-recently-used item.
 *   A HashMap gives O(1) key→node lookup, and a doubly-linked list keeps track
 *   of access order so we can move nodes to the front (most recent) and evict
 *   from the back (least recent) in O(1).
 *
 * Approach:
 *   - Maintain:
 *       • dict: Map<key, ListNode> for O(1) access to nodes.
 *       • head, tail: dummy nodes bracketing a doubly-linked list of all cache entries,
 *                     with most-recently-used right after head and least before tail.
 *   - get(key):
 *       1. If key not in dict, return -1.
 *       2. Otherwise, remove the node from its position and move it right after head.
 *       3. Return its value.
 *   - put(key, value):
 *       1. If key exists, update its value, remove it, and move it after head.
 *       2. Else:
 *            a. If at capacity, evict tail.prev (least recently used): remove from dict and list.
 *            b. Create new node, add to dict, and move after head.
 *
 * Time Complexity: O(1) for get and put (all operations on map and linked list are constant time).
 * Space Complexity: O(capacity) for storing up to capacity nodes in the map and list.
 */
public class LRUCache {
    // Doubly-linked list node holding key & value
    static class ListNode {
        int key, val;
        ListNode prev, next;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Map<Integer, ListNode> dict;
    private final ListNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.dict = new HashMap<>();
        // Dummy head and tail
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!dict.containsKey(key)) {
            return -1;
        }
        ListNode node = dict.get(key);
        // Move node to front (most recent)
        removeNode(node);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (dict.containsKey(key)) {
            // Update existing node
            ListNode node = dict.get(key);
            node.val = value;
            removeNode(node);
            moveToHead(node);
        } else {
            // Evict if at capacity
            if (dict.size() == capacity) {
                ListNode lru = tail.prev;
                removeNode(lru);
                dict.remove(lru.key);
            }
            // Insert new node
            ListNode node = new ListNode(key, value);
            dict.put(key, node);
            moveToHead(node);
        }
    }

    // Inserts node right after head
    private void moveToHead(ListNode node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    // Removes node from its current position
    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        System.out.println("Example from prompt:");
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1); // cache: {1=1}
        cache.put(2, 2); // cache: {1=1, 2=2}
        System.out.println("get(1): " + cache.get(1)); // returns 1
        cache.put(3, 3); // evicts key 2, cache: {1=1, 3=3}
        System.out.println("get(2): " + cache.get(2)); // returns -1 (not found)
        cache.put(4, 4); // evicts key 1, cache: {4=4, 3=3}
        System.out.println("get(1): " + cache.get(1)); // returns -1 (not found)
        System.out.println("get(3): " + cache.get(3)); // returns 3
        System.out.println("get(4): " + cache.get(4)); // returns 4

        System.out.println("\nAdditional tests:");

        // Test capacity 1
        LRUCache cache1 = new LRUCache(1);
        cache1.put(10, 10);
        System.out.println("get(10): " + cache1.get(10)); // 10
        cache1.put(20, 20); // evicts 10
        System.out.println("get(10): " + cache1.get(10)); // -1
        System.out.println("get(20): " + cache1.get(20)); // 20

        // Test overwrite existing key
        LRUCache cache2 = new LRUCache(2);
        cache2.put(1, 1);
        cache2.put(2, 2);
        cache2.put(1, 10); // update value of key 1
        System.out.println("get(1): " + cache2.get(1)); // 10
        cache2.put(3, 3); // evicts key 2
        System.out.println("get(2): " + cache2.get(2)); // -1
        System.out.println("get(3): " + cache2.get(3)); // 3

        // Test frequent gets moving items to most-recent
        LRUCache cache3 = new LRUCache(3);
        cache3.put(1, 1);
        cache3.put(2, 2);
        cache3.put(3, 3);
        cache3.get(1);        // now 1 is most recent
        cache3.put(4, 4);     // evicts key 2 (least recent)
        System.out.println("get(2): " + cache3.get(2)); // -1
        System.out.println("get(1): " + cache3.get(1)); // 1
        System.out.println("get(3): " + cache3.get(3)); // 3
        System.out.println("get(4): " + cache3.get(4)); // 4
    }
}