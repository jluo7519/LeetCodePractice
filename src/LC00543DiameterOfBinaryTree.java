public class LC00543DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) throw new IllegalArgumentException();
        int[] max = new int[]{0};
        dfs(root, max);
        return max[0];
    }
    private int dfs(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, max);
        int right = dfs(root.right, max);
        max[0] = Math.max(left + right, max[0]);
        return Math.max(left, right) + 1;
    }
}
