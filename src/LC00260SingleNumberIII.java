public class LC00260SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        int singlesXOR = 0;
        for (int num : nums) {
            singlesXOR ^= num;
        }
        int theDigit = 0;
        for (int i = 0; i < 32; i++) {
            if ((1 & (singlesXOR >> i)) != 0) {
                theDigit = i;
                break;
            }
        }
        int res0 = 0;
        for (int num : nums) {
            if (((num >> theDigit) & 1) == 0) { // group 0
                res0 ^= num;
            }
        }
        // singlesXOR = res0 ^ res1 ==> singlesXOR ^ res0 = res0 ^ res1 ^ res0 = res1
        return new int[] {res0, res0^singlesXOR};
    }
}
