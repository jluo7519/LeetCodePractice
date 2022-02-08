import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LC00220ContainsDuplicateIII {
    // Q4. abs(nums[i] - nums[j]) <= t and abs(i - j) <= k
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> tree = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            // abs(i - j) <= k means distance is k, # of elements in sliding window is k + 1
            if (i >= k + 1) { //   to delete i - k - 1, so window becomes [i - k, i]    i - k - 1 >= 0
                long left = nums[i - k - 1];
                tree.remove(left); // k elements left in TreeSet, 1 element not yet added
            }
            // check before add to TreeSet
            // abs(cur - x) <= t
            // -t <= cur - x <= t
            // x <= cur + t
            // x >= cur - t
            long upperBound = cur + t;
            long lowerBound = cur - t;
            Long x = tree.floor(upperBound);
            if (x != null && x >= lowerBound) {
                return true;
            }
            tree.add((long)cur);
        }
        return false;
    }
    // Q3. abs(nums[i] - nums[j]) == t and abs(i - j) <= k
    public boolean Q3(int[] nums, int k, int t) {
        // c.c.
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            //remove left first
            // abs(i - j) <= k means window of size k + 1
            if (i >= k + 1) { //current idx is i, window should be [i - k, i], need to remove index i - k - 1 when >= 0
                int left = nums[i - k - 1];
                map.put(left, map.get(left) - 1);
                if (map.get(left) == 0) {
                    map.remove(left);
                }
            }
            // check before put into map
            // abs(nums[i] - nums[j]) == t
            // ==> nums[i] - x == t or x - nums[i] == t
            // ==> x == nums[i] - t or x == t + nums[i]
            if (map.containsKey(cur - t) || map.containsKey(cur + t)) {
                return true;
            }
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        return false;
    }
    // Q2. abs(nums[i] - nums[j]) <= t and abs(i - j) == k
    public boolean Q2(int[] nums, int k, int t) {
        // c.c.
        for (int i = k; i < nums.length; i++) {
            if (nums[i - k] - nums[i] <= t || nums[i] - nums[i - k] <= t) {
                return true;
            }
        }
        return false;
    }
    // Q1. abs(nums[i] - nums[j]) == t and abs(i - j) == k
    public boolean Q1(int[] nums, int k, int t) {
        // c.c.
        for (int i = k; i < nums.length; i++) {
            if (nums[i - k] - nums[i] == t || nums[i] - nums[i - k] == t) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        LC00220ContainsDuplicateIII test = new LC00220ContainsDuplicateIII();
/*        System.out.println(test.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(test.containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
        System.out.println(test.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));*/
        System.out.println(test.Q3(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(test.Q3(new int[]{1, 0, 1, 1}, 1, 2));
        System.out.println(test.Q3(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }
}
