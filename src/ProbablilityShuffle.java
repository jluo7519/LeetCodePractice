import java.util.Random;

public class ProbablilityShuffle {
    public void shuffle(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            Random rand = new Random();
            int randIdx = rand.nextInt(i + 1);
            int tmp = nums[randIdx];
            nums[randIdx] = nums[i];
            nums[i] = tmp;
        }
    }
}
