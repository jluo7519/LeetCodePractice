import java.util.ArrayList;
import java.util.List;

public class LC00078Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        // c.c.
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }
    private void dfs(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
    private void dfs2(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // pick
        path.add(nums[index]);
        dfs(nums, index + 1, path, res);
        path.remove(path.size() - 1);
        // not pick
        dfs(nums, index + 1, path, res);
    }
}
