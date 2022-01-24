import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC00090SubsetsII {
    public List<List<Integer>> subsetsWithDups(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        dfs(result, new ArrayList<Integer>(), 0, nums);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> path, int index, int[] nums) {
        //no success or failure it stops when index >= length
        //no visited
//        if (index == nums.length) {
//            return;
//        }
        for (int i = index; i < nums.length; i++) {
            //only pick first k if duplicate
            if (i > index && nums[i - 1] == nums[i]) continue;
            //pick i
            path.add(nums[i]);
            result.add(new ArrayList<>(path));
            dfs(result, path, i + 1, nums);
            path.remove(path.size() - 1);
            //back tracking to not pick i
        }
    }
    private void dfs2(List<List<Integer>> result, List<Integer> path, int index, int[] nums) {
        if (index == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        //不取 如果都一样就是一个都不取
        int cnt = 1;
        while (cnt + index < nums.length && nums[index] == nums[index + cnt]) {
            cnt++;
        }
        dfs2(result, path, index + cnt, nums);

        //取
        path.add(nums[index]);
        dfs2(result, path, index + 1, nums);
        path.remove(path.size() - 1);
    }
    public static void main(String args[]) {
        LC00090SubsetsII test = new LC00090SubsetsII();
        int[] nums1 = {1, 2, 2};
        System.out.println(test.subsetsWithDups(nums1).toString());
        int[] nums2 = {0};
        System.out.println(test.subsetsWithDups(nums2).toString());
    }
}
