import java.util.*;

public class LC00692TopKFrequentWords {
    public List<String> topKFrequentMinHeap(String[] words, int k) {
        if (words == null || words.length == 0 || k < 0) {
            throw new IllegalArgumentException();
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        List<String> result = new ArrayList<>();
        Queue<String> minHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (map.get(o1) == map.get(o2)) {
                    return o2.compareTo(o1);
                } else {
                    return map.get(o1) - map.get(o2);
                }
            }
        });
        for (String word: map.keySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(word);
            } else {
                if (map.get(word) >= map.get(minHeap.peek())) {
                    minHeap.offer(word);
                    minHeap.poll();
                }
            }
        }
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }
}
