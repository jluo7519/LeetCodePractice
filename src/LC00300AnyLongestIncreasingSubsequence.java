import java.util.*;

public class LC00300AnyLongestIncreasingSubsequence {
    public List<Integer> anyLIS(int[] nums) {
        if (nums == null) throw new IllegalArgumentException();
        Map<Integer, Integer> edges = new HashMap<>();
        List<Integer> buffer = new ArrayList<>(); //stores indice

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int replaceIdx = binarySearch(buffer, nums, cur);
            if (replaceIdx == buffer.size()) {
                buffer.add(i);
            } else {
                buffer.set(replaceIdx, i);
            }
            if (replaceIdx >= 1) { //if replaceIdx == 0 that means it's the beginning of subsequence, points to nothing
                edges.put(i, buffer.get(replaceIdx - 1));
            }
        }
        return recoverLIS(nums, edges, buffer.get(buffer.size() - 1));
    }
    //find smallest larger or equal to target
    private int binarySearch(List<Integer> buffer, int[] nums, int target) {
        int left = 0;
        int right = buffer.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[buffer.get(mid)] == target) {
                return mid;
            } else if (nums[buffer.get(mid)] < target) { // go right
                left = mid + 1;
            } else { // go left
                right = mid - 1;
            }
        }
        //if target doens't exist, return first larger than target
        //[right, left]
        return left;
    }

    private List<Integer> recoverLIS(int[] nums, Map<Integer, Integer> edges, int end) {
        List<Integer> path = new LinkedList<>();
        int cur = end;
        path.add(nums[cur]);
        while (edges.get(cur) != null) {
            path.add(0, nums[edges.get(cur)]);
            cur = edges.get(cur);
        }
        return path;
    }

    public static void main(String args[]) {
        LC00300AnyLongestIncreasingSubsequence test = new LC00300AnyLongestIncreasingSubsequence();
        List<Integer> res1 = test.anyLIS(new int[]{10,9,2,5,3,7,101,18});
        for (int num : res1) {
            System.out.print(" " + num);
        }
        System.out.println();
        List<Integer> res2 = test.anyLIS(new int[]{0,1,0,3,2,3});
        for (int num : res2) {
            System.out.print(" " + num);
        }
        System.out.println();
        List<Integer> res3 = test.anyLIS(new int[]{7,7,7,7,7,7,7});
        for (int num : res3) {
            System.out.print(" " + num);
        }
        System.out.println();
        List<Integer> res4 = test.anyLIS(new int[]{1,2,3,2,2,2,3,5,6,5,5,7});
        for (int num : res4) {
            System.out.print(" " + num);
        }
        System.out.println();
        List<Integer> res5 = test.anyLIS(new int[]{2, 1, 2, 5, 108, 107, 105, 33, 25, 0, 3, 109, 18});
        for (int num : res5) {
            System.out.print(" " + num);
        }
    }
}
