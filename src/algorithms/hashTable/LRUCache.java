package algorithms.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Qihao He
 * @date 09/24/2019
 * https://app.laicode.io/app/problem/205
 * https://leetcode.com/problems/lru-cache/
 * LRU:
 *  1. find the corresponding answer / response to a question / request: hashMap
 *  2. adjust the timestamp / order of a particular entry
 *  3. find and delete the oldest entry
 *  4. insert an entry as the newest
 *  data structure: HashMap + Linked List (doubly)
 *  Limit is the max capacity of the cache
 *  each node contains the key, value pair
 *  and it is also a double linked list node.
 */

public class LRUCache<K, V> {
    /**
     *  make it final for the pre-defined size limit of the cache.
     */
    private final int limit;
    /**
     *  record all the time the head and tail of the double linked list.
     */
    private CacheNode<K, V> head;
    private CacheNode<K, V> tail;
    /**
     *  maintains the relationship of key and its corresponding Cachenode
     * in the double linked list.
     */
    private Map<K, CacheNode<K, V>> map;

    public LRUCache(int limit) {
        this.limit = limit;
        this.map = new HashMap<K, CacheNode<K, V>>();
    }

    public void set(K key, V value) {
        CacheNode<K, V> cacheNode = null;
        /**
         *  1. if the key already in the cache, we need to update its value
         *  and move it to head(most recent position).
         */
        if (map.containsKey(key)) {
            cacheNode = map.get(key);
            cacheNode.value = value;
            remove(cacheNode);
        } else if (map.size() < limit) {
            /**
             *  2. if the key is not in the cache and we still have space,
             *  we can add append a new Cachenode to head.
             */
            cacheNode = new CacheNode<K, V>(key, value);
        } else {
            /**
             *  3. if the key is not in the cache and we don't have space,
             *              we need to evict the tail and reuse the Cachenode let it maintain
             *              the new key, value and put it to head.
             */
            cacheNode = tail;
            remove(cacheNode);
            cacheNode.update(key, value);
        }
        append(cacheNode);
    }

    public V get(K key) {
        CacheNode<K, V> Cachenode = map.get(key);
        if (Cachenode == null) {
            return null;
        }
        /**
         *  even it is a read operation, it is still a write operation to
         *  the double linked list, and we need to move the Cachenode to head.
         */
        remove(Cachenode);
        append(Cachenode);
        return Cachenode.value;
    }

    private CacheNode<K, V> remove(CacheNode<K, V> Cachenode) {
        map.remove(Cachenode.key);
        if (Cachenode.prev != null) {
            Cachenode.prev.next = Cachenode.next;
        }
        if (Cachenode.next != null) {
            Cachenode.next.prev = Cachenode.prev;
        }
        if (Cachenode == head) {
            head = head.next;
        }
        if (Cachenode == tail) {
            tail = tail.prev;
        }
        /**
         *  break apart the curr Cachenode
         */
        Cachenode.next = Cachenode.prev = null;
        return Cachenode;
    }

    private CacheNode<K, V> append(CacheNode<K, V> Cachenode) {
        map.put(Cachenode.key, Cachenode);
        if (head == null) {
            head = tail = Cachenode;
        } else {
            Cachenode.next = head;
            head.prev = Cachenode;
            head = Cachenode;
        }
        return Cachenode;
    }

    static class CacheNode<K, V> {
        CacheNode<K, V> next;
        CacheNode<K, V> prev;
        K key;
        V value;
        CacheNode (K key, V value) {
            this.key = key;
            this.value = value;
        }
        void update(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
