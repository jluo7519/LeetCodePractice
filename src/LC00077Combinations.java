import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00077Combinations {
    public List<List<Integer>> combine(int n, int k) {
        if (k == 0) return new ArrayList<>();
        if (n == 0) throw new IllegalArgumentException();
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, 1, res, new ArrayList<>());
        return res;
    }

    private void dfs(int n, int k, int idx, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i <= n; i++) {
            path.add(i);
            dfs(n, k, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }

    private void dfs2(int n, int k, int idx, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (idx > n) { // 加一层base case防止stackoverflow, idx == n是最后一个数 所以idx > n返回
            return;
        }
        // pick
        path.add(idx);
        dfs2(n, k, idx + 1, res, path);
        path.remove(path.size() - 1);

        // not pick
        dfs2(n, k, idx + 1, res, path);
    }
}
