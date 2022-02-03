import java.util.*;

public class LC00300LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) throw  new IllegalArgumentException();
        if (nums.length <= 1) return nums.length;

        List<Integer> longest = new ArrayList<>();
        //longest.add(nums[0]);
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int replaceIdx = binarySearch(longest, cur);
            if (longest.isEmpty() || longest.get(longest.size() - 1) < cur) {
                longest.add(cur);
            } else {
                longest.set(replaceIdx, cur);
            }
        }
        return longest.size();
    }
    private int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) < target) {
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return left;//因为没找到左右错位，right比target小，left是比target大的第一个
    }
    public int lengthOfLISDP(int[] nums) {
        if (nums == null) throw  new IllegalArgumentException();
        if (nums.length <= 1) return nums.length;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int globalMaxLength = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            globalMaxLength = Math.max(globalMaxLength, dp[i]);
        }
        return globalMaxLength;
    }

    public static void main(String args[]) {
        LC00300LongestIncreasingSubsequence test = new LC00300LongestIncreasingSubsequence();
        //System.out.println(test.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(test.lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(test.lengthOfLIS(new int[]{4,10,4,3,8,9}));
        //System.out.println(test.findSmallestLargerThanTarget(Arrays.asList(1, 2, 4, 5, 7, 7, 8, 101), 9));
        //System.out.println(test.findSmallestLargerThanTarget(Arrays.asList(0,0,1,2,3,3), 3));
        System.out.println(test.lengthOfLIS(new int[]{2, 1, 2, 5, 108, 107, 105, 33, 25, 0, 3, 109, 18}));
    }
}
