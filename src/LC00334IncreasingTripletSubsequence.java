import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC00334IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        Integer first = null;
        Integer second = null;
        for (int num : nums) {
            if (first != null && second != null) { //subsequence size 2
                if (num > second) {
                    return true;
                } else if (num <= first) {
                    first = num;
                } else { //num > first and num <= second
                    second = num;
                }
            } else if (first != null) { //subsequence size 1
                if (num > first) {
                    second = num;
                } else {
                    first = num;
                }
            } else { // all null, meaning size 0
                first = num;
            }
        }
        return false;
    }
    public boolean increasingTripletConcise(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

    public boolean increasingTripletScratch(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        List<Integer> buffer = new ArrayList<>();
        for (int num : nums) {
            if (buffer.size() == 2) {
                if (num > buffer.get(buffer.size() - 1)) {
                    return true;
                } else if (num <= buffer.get(0)) {
                    buffer.set(0, num);
                } else {
                    buffer.set(1, num);
                }
            } else if (buffer.isEmpty()) { // size 0
                buffer.add(num);
            } else { // size 1
                if (num <= buffer.get(0)) {
                    buffer.set(0, num);
                } else {
                    buffer.add(num);
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        LC00334IncreasingTripletSubsequence test = new LC00334IncreasingTripletSubsequence();
        System.out.println(test.increasingTriplet(new int[]{1,2,3,4,5}));
        System.out.println(test.increasingTriplet(new int[]{5,4,3,2,1}));
        System.out.println(test.increasingTriplet(new int[]{2,1,5,0,4,6}));
    }
}
