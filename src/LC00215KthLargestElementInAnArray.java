import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class LC00215KthLargestElementInAnArray {
    public int findKthLargestMinHeap(int[] nums, int k) {
        if (nums == null || k <= 0 || k > nums.length) throw new IllegalArgumentException();
        Queue<Integer> minHeap = new PriorityQueue<>(k, (Integer o1, Integer o2) -> o1 - o2);
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (minHeap.size() < k) {
                minHeap.offer(cur);
            } else {
                // larger than top, need to enqueue
                if (cur > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(cur);
                } // if smaller or equal, not doing anything
            }
        }
        return minHeap.peek();
    }
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || k <= 0 || k > nums.length) throw new IllegalArgumentException();
        return quickSelect(nums, k, 0, nums.length - 1);
    }
    private int quickSelect(int[] nums, int k, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int pivotIdx = partition(nums, start, end);
        int rank = pivotIdx - start + 1; // rank in current subarray
        if (rank == k) { // we found target
            return nums[pivotIdx];
        } else if (rank < k) { // go right
            return quickSelect(nums, k - rank, pivotIdx + 1, end);
        } else { // go left
            return quickSelect(nums, k, start, pivotIdx - 1);
        }
    }

    private int partition(int[] nums, int start, int end) {
        Random rand = new Random();
        int pivotIdx = start + rand.nextInt(end - start + 1); //select a random pivot
        int pivot = nums[pivotIdx];
        swap(nums, pivotIdx, end);

        int left = start - 1; //[start, left] is empty at start
        //[start, left] > pivot, [left + 1, end] < pivot
        for (int i = start; i < end; i++) {
            if (nums[i] > pivot) {
                swap(nums, ++left, i);
            }
        }
        swap(nums, ++left, end);
        return left;
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

    public static void main(String args[]) {
        LC00215KthLargestElementInAnArray test = new LC00215KthLargestElementInAnArray();
        System.out.println(test.findKthLargest(new int[] {3,2,1,5,6,4}, 2));
        System.out.println(test.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));
    }
}
