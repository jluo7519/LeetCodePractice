import java.util.Arrays;

public class LC00259ThreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null) throw new IllegalArgumentException();
        int len = nums.length;
        if (len < 3) return 0;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            //first we fix a first number, then use two pointers to find a two sum
            int tar = target - nums[i];
            int start = i + 1;
            int end = len - 1;
            while (start < end) {
                //valid increase sum
                if (nums[start] + nums[end] < tar) {
                    count += end - start;
                    start++;
                } else {
                    //invalid decrease sum
                    end--;
                }
            }
        }
        return count;
    }
    public static void main(String args[]) {
        LC00259ThreeSumSmaller test = new LC00259ThreeSumSmaller();
        System.out.println("test [-2,0,1,3]: " + test.threeSumSmaller(new int[]{-2,0,1,3}, 2));
        System.out.println("test []: " + test.threeSumSmaller(new int[]{}, 0));
        System.out.println("test [0]: " + test.threeSumSmaller(new int[]{0}, 0));
    }
}
//-2 0 1 3   tar = 2 - -2 = 4
//i
//     s
//     e