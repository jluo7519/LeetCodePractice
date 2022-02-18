import java.util.*;

public class TopKFrequencyWords {
    public List<String> topKWords(List<String> dict, int k) {
        if (dict == null) throw new IllegalArgumentException();
        int size = dict.size();
        List<String> result = new LinkedList<>();
        Map<String, WordFreq> map = new HashMap<>();
        for (String w : dict) {
            WordFreq word = map.getOrDefault(w, new WordFreq(w));
            map.put(w, word);
            word.freq += 1;
        }
        List<WordFreq> list = new ArrayList<>();
        Queue<WordFreq> minHeap = new PriorityQueue<>(list);
        for (String word : map.keySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(map.get(word));
            } else {
                WordFreq top = minHeap.peek();
                WordFreq cur = map.get(word);
                if (cur.freq > top.freq) {
                    minHeap.poll();
                    minHeap.offer(cur);
                }
            }
        }
        while (k-- > 0 && !minHeap.isEmpty()) {
            result.add(0, minHeap.poll().word);
        }
        return result;
    }
    public static void main(String args[]) {
        TopKFrequencyWords test = new TopKFrequencyWords();
        List<String> input1 = Arrays.asList("S", "S", "S", "A", "b", "A");
        System.out.println(test.topKWords(input1, 5).toString());
    }
}
// 放不下用Trie，更放不下只能分布式consistent hashing算法加强
class WordFreq implements Comparable<WordFreq>{
    String word;
    int freq;
    public WordFreq(String word) {
        this.word = word;
        freq = 0;
    }
    @Override
    public int compareTo(WordFreq that) {
        return this.freq - that.freq;
    }
}