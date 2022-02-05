public class LC00298FollowUpRenZiXing {
    public int longestIncreasing(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[]{1};
        dfsIncreasing(root, max);
        return max[0];
    }
    //ret[0] longest increasing, ret[1] longest decreasing
    private int[] dfsIncreasing(TreeNode root, int[] max) {
        if (root == null) {
            return new int[] {0, 0};
        }
        int[] left = dfsIncreasing(root.left, max);
        int[] right = dfsIncreasing(root.right, max);
        int longestIncreasing = 1;
        int longestDecreasing = 1;
        //increasing from left to root to right
        int res1 = 1;
        //left < root, want longest increasing from left including left
        if (root.left != null && root.left.val < root.val) {
            res1 += left[0];
            longestIncreasing += left[0];
        }
        //right > root, want longest decreasing from right including right
        if (root.right != null && root.right.val > root.val) {
            res1 += right[1];
            longestDecreasing += right[1];
        }

        //increasing from right to root to left
        int res2 = 1;
        //right < root, want longest increasing from right
        if (root.right != null && root.right.val < root.val) {
            res2 += right[0];
            longestIncreasing = Math.max(longestIncreasing, right[0] + 1);
        }
        //left > root, want longest decreasing from left
        if (root.left != null && root.left.val > root.val) {
            res2 += left[1];
            longestDecreasing = Math.max(longestDecreasing, left[1] + 1);
        }
        max[0] = Math.max(max[0], Math.max(res1, res2));

        //return longest increasing, longest decreasing from cur node
        return new int[]{longestIncreasing, longestDecreasing};
    }

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[]{1};
        dfsConsecutive(root, max);
        return max[0];
    }
    private int[] dfsConsecutive(TreeNode root, int[] max) {
        if (root == null) {
            return new int[] {0, 0};
        }
        //idx 0 for longest increasing consecutive
        //idx 1 for longest decreasing consecutive
        int[] left = dfsConsecutive(root.left, max);
        int[] right = dfsConsecutive(root.right, max);

        int longestInc = 1;
        int longestDec = 1;
        //increasing consecutive from right --> root --> left
        int res1 = 1;
        //need longest decreasing consecutive from right including right
        if (root.right != null && root.right.val == root.val - 1) {
            res1 += right[1];
            longestDec += right[1];
        }
        //need longest increasing consecutivfe from left including left
        if (root.left != null && root.left.val == root.val + 1) {
            res1 += left[0];
            longestInc += left[0];
        }
        //increasing consecutive from left --> root --> right
        int res2 = 1;
        //need longest decreasing consective from left including left
        if (root.left != null && root.left.val + 1 == root.val) {
            res2 += left[1];
            longestDec = Math.max(longestDec, left[1] + 1);
        }
        //need longest increasing consecutive from right including right
        if (root.right != null && root.right.val == root.val + 1) {
            res2 += right[0];
            longestInc = Math.max(longestInc, right[0] + 1);
        }

        max[0] = Math.max(max[0], Math.max(res1, res2));
        return new int[] {longestInc, longestDec};
    }
    public static void main(String args[]) {
        LC00298FollowUpRenZiXing test = new LC00298FollowUpRenZiXing();
        TreeNode test1 = TreeNode.construct(new Integer[]{1,null,3,2,4,null,null,null,5});
        System.out.println(test.longestIncreasing(test1));
        System.out.println(test.longestConsecutive(test1));
        TreeNode test2 = TreeNode.construct(new Integer[]{4,null,3,1,4,null,null,null,5});
        System.out.println(test.longestIncreasing(test2));
        System.out.println(test.longestConsecutive(test2));
        TreeNode test3 = TreeNode.construct(new Integer[]{5,null,3,2,null,1});
        System.out.println(test.longestIncreasing(test3));
        System.out.println(test.longestConsecutive(test2));
    }
}
