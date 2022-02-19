import java.util.Random;

public class ProbablilityReservoirSampling {
    public int[] sampling(int[] nums, int k) {
        // c.c.
        int[] sample = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                sample[i] = nums[i];
                continue;
            }
            Random rand = new Random();
            int randIdx = rand.nextInt(i + 1);
            if (randIdx < k) {
                sample[randIdx] = nums[i];
            }
        }
        return sample;
    }
}
