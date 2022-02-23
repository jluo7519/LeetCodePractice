import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC00039LC00040CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // c.c.
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, 0, target, result, new ArrayList<>());
        return result;
    }
    private void dfs(int[] candidates, int index, int target, List<List<Integer>> result, List<Integer> path) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (index == candidates.length) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int pick = candidates[i];
            path.add(pick);
            dfs(candidates, i, target - pick, result, path);
            path.remove(path.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // cc
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs2(candidates, 0, target, result, new ArrayList<>());
        return result;
    }
    private void dfs2(int[] candidates, int index, int target, List<List<Integer>> result, List<Integer> path) {
        // fail
        if (target < 0) {
            return;
        }
        // success
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        // indices
        if (index == candidates.length) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int pick = candidates[i];
            path.add(pick);
            dfs2(candidates, i + 1, target - pick, result, path);
            path.remove(path.size() - 1);
        }
    }
}
