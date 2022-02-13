public class LC00169MajorityElement {
    public int majorityElement(int[] nums) {
        // limitation: only works for single majority elemment
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        // build map
        int[] bitCount = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitCount[i] += (num >> i) & 1;
            }
        }
        // recover
        int len = nums.length;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (bitCount[i] * 2 > len) {
                // majority element has this bit 1
                res += (1 << i);
            }
        }
        return res;
    }
    public static void main(String args[]) {
        LC00169MajorityElement test = new LC00169MajorityElement();
        System.out.println(test.majorityElement(new int[]{3,2,3}));
        System.out.println(test.majorityElement(new int[]{2,2,1,1,1,2,2}));
    }
}
