import java.util.ArrayList;
import java.util.List;
// Overall时间复杂度 O((M+N)*L), M is number of words in dictionary, N is number of cells on 2d board, L is avg word length
// Overall空间复杂度 O(N*L+L) = O(N*L)   其中DFS调用栈开销O(L), Trie用了O(N*L)
public class LC212WordSearchII {
    WordSearchTrie trie = new WordSearchTrie();
    static final int[][] DIRS = new int[][]{{-1,0}, {0,-1}, {1,0}, {0,1}};
    private void addWords(String[] words) {
        for (String word : words) { // O(M * L)
            trie.addWord(word);
        }
    }
    public List<String> findWords(char[][] board, String[] words) { // O(N * L)
        // c.c.
        addWords(words);
        int rows = board.length;
        int cols = board[0].length;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = board[i][j];
                int idx = ch - 'a';
                if (trie.root.nexts[idx] != null) {
                    dfs(board, i, j, trie.root.nexts[idx], result); // O(L)
                }
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, int i, int j, WordSearchTrieNode cur, List<String> res) {
        int rows = board.length;
        int cols = board[0].length;

        char boardCh = board[i][j];
        // failure
        if (cur == null) {
            return;
        }
        // success
        if (cur.word != null) {
            res.add(cur.word);
            cur.word = null;
            // 不能return 可能后面还有
        }
        board[i][j] = '.'; // mark visited
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= rows || y < 0 || y >= cols) continue;
            char nextCh = board[x][y];
            if (nextCh == '.') continue;
            dfs(board, x, y, cur.nexts[nextCh - 'a'], res);
        }
        board[i][j] = boardCh;
    }
}
class WordSearchTrie {
    WordSearchTrieNode root;
    public WordSearchTrie() {
         root = new WordSearchTrieNode('\0');
    }
    public void addWord(String word) { // O(L)
        int len = word.length();
        WordSearchTrieNode cur = root;
        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (cur.nexts[idx] == null) {
                cur.nexts[idx] = new WordSearchTrieNode(ch);
            }
            cur = cur.nexts[idx];
        }
        cur.word = word;
    }
}
class WordSearchTrieNode {
    char ch;
    WordSearchTrieNode[] nexts;
    String word;
    public WordSearchTrieNode(char ch) {
        this.ch = ch;
        this.nexts = new WordSearchTrieNode[26];
    }
}

