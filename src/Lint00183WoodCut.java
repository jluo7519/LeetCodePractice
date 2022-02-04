public class Lint00183WoodCut {
    public int woodCut(int[] L, int k) {
        int sum = 0;
        int maxL = 0;
        for (int wood : L) {
            sum += wood;
            maxL = Math.max(wood, maxL);
        }
        int right = Math.min(maxL, sum / L.length + 1);
        int left = 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canCut(L, k, mid)) {//go right try longer
                left = mid + 1;
            } else { //go left try shorter
                right = mid - 1;
            }
        }
        //[right, left]
        //suppose last iteration left == right and wood can cut, reminder is 0, after left = mid + 1 it becomes can't cut
        //so should return right
        return right;
    }

    private boolean canCut(int[] L, int k, int eachLen) {
        int pieces = 0;
        for (int wood : L) {
            pieces += wood / eachLen;
        }
        return pieces >= k;
    }

    public static void main(String args[]) {
        Lint00183WoodCut test = new Lint00183WoodCut();
        System.out.println(test.woodCut(new int[]{232, 124, 456}, 7));
        System.out.println(test.woodCut(new int[]{1, 2, 3}, 7));
    }
}
