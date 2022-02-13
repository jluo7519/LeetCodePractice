public class LC00136137SingleNumber {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
    public int singleNumberII(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        // create map
        int[] countBit = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                countBit[j] += (num >> j) & 1;
            }
        }
        // recover
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int digit = countBit[i] % 3;
            res += (digit << i);
        }
        return res;
    }

    public static void main(String args[]) {
        LC00136137SingleNumber test = new LC00136137SingleNumber();
        System.out.println(test.singleNumberII(new int[]{2,2,3,2}));
        System.out.println(test.singleNumberII(new int[]{0,1,0,1,0,1,99}));
    }
}
