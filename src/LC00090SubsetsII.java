import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC00090SubsetsII {
    public static void main(String args[]) {
        LC00090SubsetsII test = new LC00090SubsetsII();
        int[] nums1 = {1, 2, 2};
        System.out.println(test.subsetsWithDups(nums1).toString());
        int[] nums2 = {0};
        System.out.println(test.subsetsWithDups(nums2).toString());
    }
    public List<List<Integer>> subsetsWithDups(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), 0, nums);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> path, int index, int[] nums) {
        //no success or failure it stops when index >= length
        //no visited
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

}
