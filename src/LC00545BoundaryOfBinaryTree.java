import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LC00545BoundaryOfBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        result.add(root.val);
        addLeftBoundary(result, root.left);
        if (root.left != null || root.right != null) { // to rule out the case root itself is a leaf
            addLeafNodes(result, root);
        }
        int idx = result.size();
        addRightBoundary(result, root.right, idx);
        return result;
    }

    private void addLeftBoundary(List<Integer> res, TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        while (cur.left!= null || cur.right != null) {
            if (cur.left != null) {
                res.add(cur.val);
                cur = cur.left;
            } else if (cur.right != null) {
                res.add(cur.val);
                cur = cur.right;
            }
        }
    }
    private void addRightBoundary(List<Integer> res, TreeNode root, int idx) {
        if (root == null) return;
        TreeNode cur = root;
        while (cur.left!= null || cur.right != null) {
            if (cur.right != null) {
                res.add(idx, cur.val);
                cur = cur.right;
            } else if (cur.left != null) {
                res.add(idx, cur.val);
                cur = cur.left;
            }
        }
    }
    private void addLeafNodes(List<Integer> res, TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        addLeafNodes(res, root.left);
        addLeafNodes(res, root.right);
    }
}
