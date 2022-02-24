import java.util.*;

public class LC00051NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        dfs(n, 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), result, new ArrayList<>());
        return result;
    }
    // Time Complexity: O(n^n))
    // n levels, each level is at most n branches, each branch is O(n)
    private void dfs(int n, int level, Set<Integer> vertical, Set<Integer> diag, Set<Integer> antiDiag, List<List<String>> result, List<String> path) {
        // success
        if (level == n) {
            result.add(new ArrayList<>(path)); // deep copy O(n ^ 2)
            return;
        }
        // failure? merge it with dfs branching, only go deep if no failure
        for (int i = 0; i < n; i++) { // O(n) * O(n) = O(n ^ 2)
            // valid only if no queen on diagonal, vertical, anti-diagonal lines
            if (!vertical.contains(i) && !diag.contains(i - level) && !antiDiag.contains(i + level)) {
                // need to create a new array every level since if we pass down the array, next level will have one more queen
                // but we can only have one queen on each level
                char[] array = new char[n];
                Arrays.fill(array, '.'); // O(n)
                // set
                array[i] = 'Q';
                vertical.add(i);
                diag.add(i - level); // same diagonal if y1 - x1 = y2 - x2
                antiDiag.add(i + level); // same antiDiagonal if y1 + x1 = y2 + x2
                path.add(new String(array)); // O(n)
                dfs(n, level + 1, vertical, diag, antiDiag, result, path);
                // set back
                path.remove(path.size() - 1);
                vertical.remove(i);
                diag.remove(i - level);
                antiDiag.remove(i + level);
            }
        }
    }
}
