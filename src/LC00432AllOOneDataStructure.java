import java.util.*;

public class LC00432AllOOneDataStructure {
    Bucket head; //head.next is min bucket min/max are freq
    Bucket tail; //tail.prev is max bucket
    Map<String, Bucket> map;

    public LC00432AllOOneDataStructure() {
        head = new Bucket(0);
        tail = new Bucket(0);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    public void inc(String key) {
        //if no such key in it, create new bucket and put in map
        if (!map.containsKey(key)) {
            //check if there is a bucket for freq 1
            if (head.next.freq == 1) {
                head.next.add(key);
            } else {
                //create a new one freq bucket
                Bucket bucket = insertBucket(1, head, head.next);
                bucket.add(key);
            }
            //System.out.println(" " + key + " freq " + head.next.freq);
            map.put(key, head.next);
        } else { //the key existed
            Bucket original = map.get(key);
            int originalFreq = original.freq;
            original.remove(key);

            //just move the element to one freq higher if such bucket existes
            if (original.next.freq == originalFreq + 1) {
                original.next.add(key);
            } else {//if no such bucket
                Bucket bucket = insertBucket(originalFreq + 1, original, original.next);
                bucket.add(key);
            }
            map.put(key, original.next);
            //if after delete the bucket becomes empty, should remove it
            if (original.size() == 0) {
                deleteBucket(original);
            }
        }
    }

    public void dec(String key) {
        if (!map.containsKey(key)) {
            throw new RuntimeException("can't decrement since it didn't exist");
        }
        Bucket original = map.get(key);
        int originalFreq = original.freq;
        original.remove(key);
        if (originalFreq > 1) {
            //if -1 freq exists, just move
            if (original.prev.freq == originalFreq - 1) {
                original.prev.add(key);
            } else {//else create new
                Bucket bucket = insertBucket(originalFreq - 1, original.prev, original);
                bucket.add(key);
            }
            map.put(key, original.prev);
        } else {//original freq is 1, after removal its 0
            map.remove(key);
        }
        if (original.size() == 0) {
            deleteBucket(original);
        }
    }

    public String getMaxKey() {
        if (tail.prev.freq == 0) return "";
        Set<String> maxKeys = tail.prev.strings;
        Iterator<String> it = maxKeys.iterator();
        return it.next();
    }
    public String getMinKey() {
        if (head.next.freq == 0) return "";
        Set<String> minKeys = head.next.strings;
        Iterator<String> it = minKeys.iterator();
        return it.next();
    }
    private Bucket insertBucket(int freq, Bucket left, Bucket right) {
        Bucket bucket = new Bucket(freq);
        left.next = bucket;
        bucket.prev = left;
        right.prev = bucket;
        bucket.next = right;
        return bucket;
    }

    private void deleteBucket(Bucket bucket) {
        if (bucket.size() > 0) {
            throw new RuntimeException("shouldn't be deleting");
        }
        Bucket prev = bucket.prev;
        Bucket next = bucket.next;
        prev.next = next;
        next.prev = prev;
    }

    public static void main(String args[]) {
        LC00432AllOOneDataStructure allOne = new LC00432AllOOneDataStructure();
        allOne.inc("hello");
        allOne.inc("hello");
        Bucket cur = allOne.head;
        allOne.getMaxKey(); // return "hello"
        allOne.getMinKey(); // return "hello"
        allOne.inc("leet");
        allOne.getMaxKey(); // return "hello"
        allOne.getMinKey(); // return "leet"
    }
}

class Bucket {
    Bucket prev;
    Bucket next;
    int freq;
    Set<String> strings;

    Bucket(int freq) {
        this.freq = freq;
        prev = null;
        next = null;
        strings = new HashSet<>();
    }
    boolean add(String string) {
        if (strings.contains(string)) {
            return false;
        }
        strings.add(string);
        return true;
    }

    boolean remove(String string) {
        if (!strings.contains(string)) {
            return false;
        }
        strings.remove(string);
        return true;
    }
    int size() {
        return strings.size();
    }

    boolean containsKey(String key) {
        return strings.contains(key);
    }

    void removeKey(String key) {
        strings.remove(key);
    }

    void addKey(String key) {
        strings.add(key);
    }
}


