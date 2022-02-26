public class LC00124BinaryTreeMaxPathSum {
    public int maxPathSum(TreeNode root) {
        if (root == null) throw new RuntimeException();
        int[] max = new int[]{Integer.MIN_VALUE};
        dfs(root, max);
        return max[0];
    }

    private int dfs(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int leftMax = dfs(root.left, max);
        int rightMax = dfs(root.right, max);
        max[0] = Math.max(max[0], leftMax + rightMax + root.val);
        return Math.max(Math.max(leftMax + root.val, rightMax + root.val), 0);
    }
}
