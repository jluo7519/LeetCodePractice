import java.util.*;

public class LC00373FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            throw new IllegalArgumentException();
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        List<List<Integer>> result = new ArrayList<>();
        Queue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer pair1, Integer pair2) {
                return nums1[pair1 / len2] + nums2[pair1 % len2] - (nums1[pair2 / len2] + nums2[pair2 % len2]);
            }
        });
        Set<Integer> visited = new HashSet<>();
        minHeap.offer(0);
        while (k-- > 0) {
            Integer cur = minHeap.poll();
            if (cur == null) {
                break;
                //throw new IllegalArgumentException("less than k pairs");
            }
            int idx1 = cur / len2;
            int idx2 = cur % len2;
            result.add(Arrays.asList(nums1[idx1], nums2[idx2]));
            //generate neighbors
            if (idx1 + 1 < len1) {
                int nei1 = (idx1 + 1) * len2 + idx2;
                if (visited.add(nei1)) {
                    minHeap.offer(nei1);
                }
            }
            if (idx2 + 1 < len2) {
                int nei2 = idx1 * len2 + idx2 + 1;
                if (visited.add(nei2)) {
                    minHeap.offer(nei2);
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        LC00373FindKPairsWithSmallestSums test = new LC00373FindKPairsWithSmallestSums();
        List<List<Integer>> res1 = test.kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3);
        for (List<Integer> pair : res1) {
            for (Integer num : pair) {
                System.out.print(" " + num);
            }
            System.out.print(" ");
        }
    }
}
