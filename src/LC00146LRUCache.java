import java.util.HashMap;
import java.util.Map;

public class LC00146LRUCache {
    private final int capacity;
    private Map<Integer, GraphNode> map;
    private GraphNode leastRecent;
    private GraphNode mostRecent;

    public LC00146LRUCache(int capacity) {
        this.capacity = capacity;
        leastRecent = new GraphNode(0, 0); //use dummy head and tail
        mostRecent = new GraphNode(0, 0);
        leastRecent.next = mostRecent;
        mostRecent.prev = leastRecent;
        map = new HashMap<>(capacity);
    }
    public int get(int key) {
        GraphNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        delete(node);
        append(node);//move to most recent
        return node.value;
    }
    public void put(int key, int value) {
        //1. exists: update value and move to most recent
        if (map.containsKey(key)) {
            GraphNode node = map.get(key);
            node.value = value;
            delete(node); //move to most recent
            append(node);
        } else {
            //2. new:
            //2.1 LRU full: kick least recent first
            GraphNode node = new GraphNode(key, value);
            if (map.size() == this.capacity) {
                // 注意先后顺序, 如果先remove node再删map会删错key
                map.remove(leastRecent.next.key);
                delete(leastRecent.next);
            }
            map.put(key, node);
            append(node);
        }
    }
    //helper method to move node to most recent end
    public void append(GraphNode node) {
        mostRecent.prev.next = node;
        node.prev = mostRecent.prev;
        node.next = mostRecent;
        mostRecent.prev = node;
    }
    public void delete(GraphNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
/*    public static void main(String args[]) {
        LC00146LRUCache lRUCache = new LC00146LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }*/
}

class GraphNode {
    int key;
    int value;
    GraphNode prev;
    GraphNode next;

    GraphNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
