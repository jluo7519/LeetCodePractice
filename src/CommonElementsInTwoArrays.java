import java.sql.Array;
import java.util.*;

public class CommonElementsInTwoArrays {
    // unsorted, HashSet O(m+n) Time and O(min(m,n)) Space
    public List<Integer> commonElementsS1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) throw new IllegalArgumentException();
        if (nums1.length > nums2.length) {
            return commonElementsS1(nums2, nums1);
        }
        Set<Integer> res = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
            }
        }
        return new ArrayList<>(res);
    }
    // two sorted arrays of similar size O(nlogn)
    public List<Integer> commonElementsS2(int[] nums1, int[] nums2) {
        // c.c.
        Set<Integer> res = new HashSet<>();
        // binary search nums1 in nums2
        for (int num : nums1) {
            int idx = binarySearch(nums2, num);
            if (idx != -1) {
                res.add(nums2[idx]);
            }
        }
        return new ArrayList<Integer>(res);
    }
    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) { // go left
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    // two sorted arrays one much longer than the other 在长的里搜短的

    public static void main(String args[]) {
        CommonElementsInTwoArrays test = new CommonElementsInTwoArrays();
        int[] nums1 = new int[]{0,0,1,3,3,5};
        int[] nums2 = new int[]{0,0,3};
        System.out.println(test.commonElementsS1(nums1, nums2).toString());
        System.out.println(test.commonElementsS2(nums1, nums2).toString());
    }
}
