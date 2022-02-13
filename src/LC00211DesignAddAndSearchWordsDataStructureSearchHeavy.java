import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00211DesignAddAndSearchWordsDataStructureSearchHeavy {
    private Set<String> dict;

    public LC00211DesignAddAndSearchWordsDataStructureSearchHeavy() {
        dict = new HashSet<>();
    }
    public void addWord(String word) {
        List<String> toAdd = new ArrayList<>();
        generateWords(word, 0, toAdd, new StringBuilder());
        dict.addAll(toAdd);
    }

    public boolean search(String word) {
        return dict.contains(word);
    }

    private void generateWords(String word, int index, List<String> result, StringBuilder sb) {
        // base case
        if (index == word.length()) {
            result.add(sb.toString());
            return;
        }
        int len = sb.length();
        // no.
        sb.append(word.charAt(index));
        generateWords(word, index + 1, result, sb);
        sb.setLength(len);
        // with .
        sb.append('.');
        generateWords(word, index + 1, result, sb);
        sb.setLength(len);
    }
}
