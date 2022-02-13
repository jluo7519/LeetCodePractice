import java.util.HashSet;
import java.util.Set;

public class LC00211DesignAddAndSearchWordsDataStructureAddHeavy {
    private Set<String> dict;

    public LC00211DesignAddAndSearchWordsDataStructureAddHeavy() {
        dict = new HashSet<>();
    }

    public void addWord(String word) {
        dict.add(word);
    }

    public boolean search(String word) {
        for (String w : dict) {
            int i = 0;
            for (i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (word.length() != w.length() || (c != '.' && c != w.charAt(i))) {
                    break;
                }
            }
            if (i == word.length()) {
                return true;
            }
        }
        return false;
    }
}
