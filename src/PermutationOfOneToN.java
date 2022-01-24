//given an unsorted array of size n
//find if array is permutation of 1 to n in O(n) time and O(1) space
//n = 5
//array = {1, 2, 3, 4, 5}
//is true
public class PermutationOfOneToN {
    private boolean isPermutation(int[] nums, int n) {
        //每个数异或自己是0
        int result = 1;//出发点需要是1因为里面的数字是从1开始的
        for (int i = 1; i <= n; i++) {
            result ^= nums[i - 1];
        }
        return result == 0;
    }

    public static void main(String args[]) {
        int[] array1 = {2, 5, 1, 4, 3};
        int[] array2 = {1};
        int[] array3 = {3, 3, 3, 5, 4};
        PermutationOfOneToN factorialObject = new PermutationOfOneToN();
        System.out.println(factorialObject.isPermutation(array1, 5));
        //System.out.println(factorialObject.isPermutation(array3, 5));
    }
}

// 00000
// 01010 XOR
// 01010
//