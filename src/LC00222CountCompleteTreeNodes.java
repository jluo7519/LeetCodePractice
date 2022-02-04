public class LC00222CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        //分叉
        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);

        if (lHeight < rHeight) {
            throw new IllegalArgumentException("not a complete tree");
        } else if (lHeight > rHeight) { // right subtree is full tree
            //return dfs(root.left) + 1 + (int)Math.pow(2, rHeight) - 1;
            return dfs(root.left) + (1 << rHeight);
        } else { //left subtree is full tree
            //return (int)Math.pow(2, lHeight) - 1 + 1 + dfs(root.right);
            return (1 << lHeight) + dfs(root.right);
        }
    }

    private int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }
}
