import java.util.HashMap;
import java.util.Map;

public class LC00460LFUCache {
    Map<Integer, LFUNode> keyToLFU;
    Map<Integer, LRUNode> keyToLRU;
    LFUNode head;
    LFUNode tail;
    int capacity;

    public LC00460LFUCache(int capacity) {
        this.capacity = capacity;
        keyToLFU = new HashMap<>();
        keyToLRU = new HashMap<>();
        head = new LFUNode(0);
        tail = new LFUNode(0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!keyToLRU.containsKey(key)) {
            return -1;
        }
        LFUNode lfu = keyToLFU.get(key);
        LRUNode lru = keyToLRU.get(key);
        lfu.deleteLRUNode(lru);
        //move up one bucket
        if (lfu.next.freq != lfu.freq + 1) { // if bucket doesn't exist create new
            LFUNode newLFU = insertLFUNode(lfu.freq + 1, lfu, lfu.next);
        }
        lfu.next.addLRUNodeToTail(lru);
        keyToLFU.put(key, lfu.next);
        if (lfu.getSize() == 0) {//empty bucket, delete
            deleteLFUNode(lfu);
        }
        return lru.value;
    }

    public void put(int key, int value) {
        //edge case
        if (capacity == 0) return;
        //check if it exists
        if (keyToLRU.containsKey(key)) {
            //update value and move to one bucket up
            LRUNode lru = keyToLRU.get(key);
            lru.value = value;
            LFUNode lfu = keyToLFU.get(key);
            lfu.deleteLRUNode(lru);
            if (lfu.next.freq != lfu.freq + 1) { //one bucket up doesn't exists
                //create new
                LFUNode newLFU = insertLFUNode(lfu.freq + 1, lfu, lfu.next);
            }
            lfu.next.addLRUNodeToTail(lru);
            keyToLFU.put(key, lfu.next);

            if (lfu.getSize() == 0) {
                deleteLFUNode(lfu);
            }
        } else {//key is new
            LRUNode newLRU = new LRUNode(key, value);
            if (keyToLRU.size() == capacity) { //need to delete LFU ranked by LRU
                int deletedKey = head.next.deleteLeastRecent();
                keyToLRU.remove(deletedKey);
                keyToLFU.remove(deletedKey);
                //if after deletion, bucket of frequency 1 becomes empty, and a new key will be added to it
                //therefore it's not necessary to check empty and delete, it will be size 1 again
            }
            if (head.next.freq != 1) {
                //if freq 1 bucket doesn't exist, need to create it
                LFUNode newLFU = insertLFUNode(1, head, head.next);
            }
            head.next.addLRUNodeToTail(newLRU);
            keyToLFU.put(key, head.next);
            keyToLRU.put(key, newLRU);
        }
    }
    private LFUNode insertLFUNode(int freq, LFUNode left, LFUNode right) {
        LFUNode node = new LFUNode(freq);
        left.next = node;
        node.prev = left;
        right.prev = node;
        node.next = right;
        return node;
    }

    private void deleteLFUNode(LFUNode node) {
        LFUNode prev = node.prev;
        LFUNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public static void main(String args[]) {
        LC00460LFUCache lfu = new LC00460LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));      // return -1 (not found)
        lfu.get(3);      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));      // return -1 (not found)
        lfu.get(3);      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // return 4
        // cache=[4,3], cnt(4)=2, cnt(3)=3
    }
}

class LFUNode {
    Integer freq;
    LFUNode prev;
    LFUNode next;
    LRUNode head;//head: least recent
    LRUNode tail;//tail: most recent
    int size;

    LFUNode(int freq) {
        this.freq = freq;
        head = new LRUNode(0, 0);//dummy head
        tail = new LRUNode(0, 0);//dummy tail
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    int getSize() {
        return size;
    }
    int deleteLRUNode(LRUNode node) {
        LRUNode prevLRU = node.prev;
        LRUNode nextLRU = node.next;
        prevLRU.next = nextLRU;
        nextLRU.prev = prevLRU;
        size--;
        return node.key;
    }

    void addLRUNodeToTail(LRUNode node) {
        LRUNode prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
        size++;
    }

    int deleteLeastRecent() {
        return deleteLRUNode(head.next);
    }
}
class LRUNode {
    Integer key;
    Integer value;
    LRUNode prev;
    LRUNode next;

    LRUNode(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
