import java.util.HashMap;
import java.util.Map;

public class LC00340LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinctS1(String s, int k) {
        if (s == null) throw new IllegalArgumentException();
        if (k == 0) return 0;
        int len = s.length();
        MyWindow queue = new MyWindow(k);
        int longest = 0;
        for (int fast = 0; fast < len; fast++) {
            char ch = s.charAt(fast);
            queue.add(ch, fast);
            longest = Math.max(longest, queue.getSize(fast));
        }
        return longest;
    }
    public int lengthOfLongestSubstringKDistinctS2(String s, int k) {
        if (s == null) throw new IllegalArgumentException();
        if (k == 0) return 0;
        int longest = 0;
        int len = s.length();
        int slow = 0;
        int distinct = 0;
        int[] map = new int[256];
        for (int fast = 0; fast < len; fast++) {
            char ch = s.charAt(fast);
            //valid expand
            if (map[ch]++ == 0) {
                distinct++;
            }
            //invalid shrink
            while (distinct > k) {
                if (--map[s.charAt(slow++)] == 0) {
                    distinct--;
                }
            }
            longest = Math.max(longest, fast - slow + 1);
        }
        return longest;
    }
    public static void main(String args[]) {
        LC00340LongestSubstringWithAtMostKDistinctCharacters test = new LC00340LongestSubstringWithAtMostKDistinctCharacters();
        System.out.println(test.lengthOfLongestSubstringKDistinctS2("eceba", 2));
        System.out.println(test.lengthOfLongestSubstringKDistinctS2("aa", 1));
        System.out.println(test.lengthOfLongestSubstringKDistinctS2("ab", 1));
    }
}
class MyWindow {
    QueueNode head, tail;
    int capacity, start;
    Map<Character, QueueNode> map;
    public MyWindow(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new QueueNode('\0', 0);
        tail = new QueueNode('\0', 0);
        head.next = tail;
        tail.prev = head;
        start = 0;
    }
    int getSize(int index) {
        return index - start + 1;
    }
    void add(char ch, int idx) {
        //已存在
        if (map.containsKey(ch)) {
            QueueNode node = map.get(ch);
            node.idx = idx; //不需要put，reference在map里没有改变
            delete(node);
            append(node);
        } else {//新的
            QueueNode node = new QueueNode(ch, idx);
            if (map.size() == capacity) { //满了先删
                map.remove(head.next.ch);
                start = delete(head.next) + 1;
            }
            //删完一定有空间
            append(node);
            map.put(ch, node);
        }
    }
    int delete(QueueNode node) {
        int idx = node.idx;
        QueueNode prev = node.prev;
        QueueNode next = node.next;
        prev.next = next;
        next.prev = prev;
        return idx;
    }
    void append(QueueNode node) {
        QueueNode prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
    }
}
class QueueNode {
    char ch;
    int idx;
    QueueNode prev;
    QueueNode next;
    QueueNode(char ch, int idx) {
        this.ch = ch;
        this.idx = idx;
    }
}