import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00472ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dict = new HashSet<>();
        for (String word : words) {
            dict.add(word);
        }
        List<String> result = new ArrayList<>();
        for (String word: words) { // O(n)
            dict.remove(word);
            Boolean[] mem = new Boolean[word.length()];
            if (dfs(word, 0, 0, dict, mem)) { // O(2^k * k) ⇒ pruning ⇒ O(k^3)
                result.add(word);
            }
            dict.add(word);
        }
        return result;
    }
    private boolean dfs(String word, int idx, int cut, Set<String> dict, Boolean[] mem) {
        int len = word.length();
        if (idx == len) {
            return cut > 0;
        }
        if (mem[idx] != null) {
            return mem[idx];
        }
        // 一共要填k个mem, 每个填值是O(k^2), 总体复杂度从O(2^k * k)下降到O(k^3)
        for (int i = idx + 1; i <= len; i++) { //[idx, i + 1) [idx, i]
            String str = word.substring(idx, i);
            if (dict.contains(str) && dfs(word, i, cut + 1, dict, mem)) {
                mem[idx] = true;
                return true;
            }
        }
        mem[idx] = false;
        return false;
    }
}
