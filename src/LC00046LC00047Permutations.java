import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00046LC00047Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        dfs(nums, 0, result, new HashSet<>());
        return result;
    }
    private void dfs(int[] nums, int index, List<List<Integer>> result, Set<List<Integer>> set) {
        if (index == nums.length - 1) {
            List<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            if (set.add(cur)) {
                result.add(cur);
            }
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            dfs(nums, index + 1, result, set);
            swap(nums, index, i);
        }
    }
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
