public class LC00208ImplementTrie {
    TrieNode root;

    public LC00208ImplementTrie() {
        root = new TrieNode('\0');
    }

    public void insert(String word) {
        TrieNode cur = root; // root is a dummy
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode(c);
            }
            cur = cur.children[idx];
        }
        cur.isLeaf = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                return false;
            }
            cur = cur.children[idx];
        }
        return cur.isLeaf;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                return false;
            }
            cur = cur.children[idx];
        }
        return true;
    }
}
class TrieNode {
    public boolean isLeaf;
    public char c;
    TrieNode[] children;
    public TrieNode() {
        children = new TrieNode[26];
    }
    public TrieNode(char c) {
        this();
        this.c = c;
    }
}