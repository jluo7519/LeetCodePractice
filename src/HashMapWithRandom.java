import java.util.*;

public class HashMapWithRandom<K, V> {
    private Map<K, V> map;
    private Map<K, Integer> idxMap; //stores index in arraylist
    private List<K> list;
    private Random rand;

    public HashMapWithRandom(){
        list = new ArrayList<>();
        map = new HashMap<>();
        idxMap = new HashMap<>();
        rand = new Random();
    }

    //put
    public V put(K key, V value) {
        V ret = null;
        if (map.containsKey(key)) { // no need to add to list
            ret = map.get(key);
        } else { //if it's new, need to add to list
            list.add(key);
            idxMap.put(key, list.size() - 1);
        }
        map.put(key, value);
        return ret;
    }

    //get: same as hashmap, nothing new
    public V get(K key) {
        return map.get(key);
    }

    //remove
    public boolean remove(K key) {
        if (!map.containsKey(key)) {
            return false;
        }
        //need to swap in arraylist before removal
        map.remove(key);
        //replace removal slot with last element in array
        K replaceKey = list.get(list.size() - 1);
        list.set(idxMap.get(key), replaceKey);
        //remove last one from list
        list.remove(list.size() - 1);
        //remove from index map
        idxMap.remove(key);
        return true;
    }

    //size
    public int size() {
        if (list.size() != map.size()) {
            throw new RuntimeException("should equal");
        }
        return list.size();
    }
    //random
    public V random() {
        int randomIdx = rand.nextInt(list.size());
        K randomKey = list.get(randomIdx);
        return map.get(randomKey);
    }
    public static void main(String args[]) {
        HashMapWithRandom map = new HashMapWithRandom();
        System.out.println(map.put(2, 3));
        System.out.println(map.put(3, 3));
        System.out.println(map.put('a', 5));
        System.out.println(map.put('c', 5));
        System.out.println(map.put('b', 6));
        System.out.println(map.put(12, 22));
        System.out.println(map.remove(12));

        System.out.println("random pick: ");
        System.out.println(map.random());
        System.out.println(map.random());
        System.out.println(map.random());
        System.out.println(map.random());
    }
}
