public class LC00268MissingNumber {
    public int missingNumber(int[] nums) {
        int res = 0;
        int expected = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
            expected ^= i;
        }
        if (res == expected) return nums.length;
        return res ^ nums.length ^ expected;
        // 0^1^3^4^5 res
        // 0^1^2^3^4 expected
        // (0^1^2^3^4^5)^(0^1^3^4^5) = 2
    }
    // if input is sorted就可以用binary search
    public int missingNumberSorted(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right - 1) { //
            int mid = left + (right - left) / 2;
            if (nums[mid] - nums[left] == mid - left) { // not in left
                left = mid;
            } else {
                right = mid;
            }
        }
        // [l, r]
        if (nums[0] != 0) return 0;
        return nums[right] - nums[left] == 1 ? nums.length : left + 1;
    }
}

// 0 1 3
//
// 0^1^3
// 0^1^3^2
// 0 1 2 3 4 5 6 7
// 0 1 2 3 5 6 7 8
//       l
//         r
//         m