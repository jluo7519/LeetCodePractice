import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LC00114FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        dfs(root, prev);
    }
    private void dfs(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (prev[0] != null) {
            prev[0].right = root;
        }
        root.left = null;
        prev[0] = root;
        dfs(left, prev);
        dfs(right, prev);
    }
}
