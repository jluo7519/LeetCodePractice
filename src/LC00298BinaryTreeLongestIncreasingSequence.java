public class LC00298BinaryTreeLongestIncreasingSequence {
    public int longestIncreasing(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[]{1};
        dfs(root, max);
        return max[0];
    }

    private int dfs(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, max);
        int right = dfs(root.right, max);
        int res = 1;
        if (root.left != null && root.left.val > root.val) {
            res += left;
        }
        if (root.right != null && root.right.val > root.val) {
            res = Math.max(res, right + 1);
        }
        max[0] = Math.max(max[0], res);
        return res;
    }
    public static void main(String args[]) {
        LC00298BinaryTreeLongestIncreasingSequence test = new LC00298BinaryTreeLongestIncreasingSequence();
        TreeNode test1 = TreeNode.construct(new Integer[]{1,null,3,2,4,null,null,null,5});
        System.out.println(test.longestIncreasing(test1));
        TreeNode test2 = TreeNode.construct(new Integer[]{2,null,3,2,null,1});
        System.out.println(test.longestIncreasing(test2));
    }
}
