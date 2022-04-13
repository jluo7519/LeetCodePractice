import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC00703KthLargestElementInaStream {
    private Queue<Integer> minHeap;
    private int k;
    public LC00703KthLargestElementInaStream(int k, int[] nums) { // O(N log K)
        minHeap = new PriorityQueue<>(k);
        this.k = k;
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else {
                if (num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
        }
    }

    public int add(int val) { // O(log K)
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } else {
            if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(val);
            }
        }
        if (minHeap.size() < k) {
            throw new RuntimeException("less than k elements");
        } else {
            return minHeap.peek();
        }
    }
}
