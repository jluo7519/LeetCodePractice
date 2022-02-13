import java.util.List;

public class LC00211DesignAddAndSearchWordsDataStructureTrie {
    private TrieNode root;

    public LC00211DesignAddAndSearchWordsDataStructureTrie() {
        root = new TrieNode('\0');
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode(word.charAt(i));
            }
            cur = cur.children[idx];
        }
        cur.isLeaf = true;
    }

    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    // TrieNode cur starts from Trie root, so index needs to match cur.children
    private boolean searchHelper(String word, int index, TrieNode cur) {
        // failure
        if (cur == null) {
            return false;
        }
        // failure and success
        if (index == word.length()) {
            return cur.isLeaf;
        }

        char c = word.charAt(index);
        // case of no .
        if (c != '.') {
            return (searchHelper(word, index + 1, cur.children[c - 'a']));
        } else { // case of .
            for (TrieNode child : cur.children) {
                if (searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        }
    }
}
