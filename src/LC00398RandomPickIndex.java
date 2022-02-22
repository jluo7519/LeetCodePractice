import java.util.*;

// O(1) Time pick
public class LC00398RandomPickIndex {
    private int[] nums;
    private Map<Integer, List<Integer>> map;
    private Random rand;
    public LC00398RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.map = new HashMap<>();
        this.rand = new Random();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int key = nums[i];
            List<Integer> values = map.getOrDefault(key, new ArrayList<>());
            values.add(i);
            map.put(key, values);
        }
    }

    public int pick(int target) {
        List<Integer> values = map.get(target);
        if (values == null) throw new RuntimeException("target doesn't exist");
        int idx = rand.nextInt(values.size());
        return values.get(idx);
    }
}
// O(1) Space solution
class RandomPickIdx {
    private int[] nums;
    private Random rand;
    public RandomPickIdx(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    public int pick(int target) {
         int sample = -1;
         int count = 0;
         for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;
            count++;
            int random = rand.nextInt(count);
            if (random == 0) {
                sample = i;
            }
         }
         return sample;
    }
}