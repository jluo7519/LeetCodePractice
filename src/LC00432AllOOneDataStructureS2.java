import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC00432AllOOneDataStructureS2 {
    Map<Integer, Set<String>> buckets;
    Map<String, Integer> map;
    int max;
    int min;

    public LC00432AllOOneDataStructureS2() {
        buckets = new HashMap<>();
        map = new HashMap<>();
        max = 0;
        min = 0;
    }

    public void inc(String key) {
        if (map.containsKey(key)) {//if key existed
            int originalFreq = map.get(key);
            removeFromBucket(key, originalFreq);
            addToBucket(key, originalFreq + 1);
            map.put(key, originalFreq + 1);
            //update max
            if (originalFreq == max) {
                max++;
            }
            //update min
            if (originalFreq == min) {
                min = buckets.containsKey(min) ? min : originalFreq + 1;
            }
        } else {
            addToBucket(key, 1);
            map.put(key, 1);
            //update min
            min = 1;
            //update max
            if (max == 0) {
                max = 1;
            }
        }

    }
    public void dec(String key) {
        if (!map.containsKey(key) || map.get(key) <= 0) {
            throw new RuntimeException("frequency <= 0 or didn't exist!");
        }
        int originalFreq = map.get(key);
        removeFromBucket(key, originalFreq);
        addToBucket(key, originalFreq + 1);
        //update max
        if (originalFreq == max) {
            max = map.containsKey(originalFreq) ? originalFreq : originalFreq - 1;
        }
        //update min
        if (originalFreq == min) {
            if (originalFreq > 1) {
                min = originalFreq - 1;
            } else if (!buckets.containsKey(originalFreq)) {
                //TODO: 比如三个bucket count1有1个element， count99有n个，count100有m个，如果count1没有了，怎么得到新的min?
            }
        }
    }
    private void removeFromBucket(String key, int freq) {
        buckets.get(freq).remove(key);
        if (buckets.get(freq).size() == 0) {
            buckets.remove(freq);
        }
    }
    private void addToBucket(String key, int freq) {
        if (buckets.containsKey(freq)) {
            buckets.get(freq).add(key);
        } else {
            Set<String> set = new HashSet<>();
            set.add(key);
            buckets.put(freq, set);
        }
    }
}
