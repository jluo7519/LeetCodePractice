import java.util.ArrayList;
import java.util.List;

public class LC00300LongestNonDecreasingSubsequence {
    public int longestOfLNDS(int[] nums) {
        if (nums == null) throw new IllegalArgumentException();
        if (nums.length <= 1) return nums.length;
        List<Integer> buffer = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int replaceIdx = binarySearch(buffer, cur);
            if (replaceIdx == buffer.size()) {
                buffer.add(cur);
            } else {
                buffer.set(replaceIdx, cur);
            }
        }
        return buffer.size();
    }

    private int binarySearch(List<Integer> buffer, int target) {
        int left = 0;
        int right = buffer.size() - 1;
        //find smallest larger than target
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (buffer.get(mid) <= target) { // go right
                left = mid + 1;
            } else { // go left
                right = mid - 1;
            }
        }
        //[right, left] if target doesn't exist in buffer, right < target < left
        //if target exists in buffer, left > target, right == target
        return left;
    }

    public static void main(String args[]) {
        LC00300LongestNonDecreasingSubsequence test = new LC00300LongestNonDecreasingSubsequence();
        System.out.println(test.longestOfLNDS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(test.longestOfLNDS(new int[]{10,9,2,5,3,7,101,101}));
        System.out.println(test.longestOfLNDS(new int[]{0,1,0,3,2,3}));
        System.out.println(test.longestOfLNDS(new int[]{0,1,0,3,3,3}));
        System.out.println(test.longestOfLNDS(new int[]{1,2,3,2,2,2,3,5,6,5,5,7}));
    }
}
