public class LC00098ValidateBinarySearchTree {
    //private TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        //cc
        TreeNode[] prev = new TreeNode[1];
        return inOrderTraverse(root, prev);
    }
    private boolean inOrderTraverse(TreeNode root, TreeNode[] prev) {
        if (root == null) return true;

        if (!inOrderTraverse(root.left, prev)) {
            return false;
        }
        if (prev[0] != null && prev[0].val >= root.val) {
            return false;
        }
        prev[0] = root;
        return inOrderTraverse(root.right, prev);
    }
}
