import java.util.ArrayList;
import java.util.List;

public class LC00216CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k == 0 || n < 0) {
            return result;
        }
        dfs(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    private void dfs(int k, int target, int idx, List<Integer> cur, List<List<Integer>> res) {
        if (k == 0) {
            if (target == 0) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = idx; i <= 9; i++) {
            cur.add(i);
            dfs(k - 1, target - i, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}
