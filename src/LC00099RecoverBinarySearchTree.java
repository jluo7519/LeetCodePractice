import java.util.ArrayList;
import java.util.List;

public class LC00099RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        TreeNode[] mistakes = new TreeNode[2];
        TreeNode[] prev = new TreeNode[1];
        inOrderTraverse(prev, root, mistakes);
        int tmp = mistakes[0].val;
        mistakes[0].val = mistakes[1].val;
        mistakes[1].val = tmp;
    }
    private void inOrderTraverse(TreeNode[] prev, TreeNode cur, TreeNode[] mistakes) {
        if (cur == null) return;
        //left
        inOrderTraverse(prev, cur.left, mistakes);
        //check cur pair
        if (prev[0] != null && prev[0].val >= cur.val) {
            if (mistakes[0] == null) {
                mistakes[0] = prev[0];
                mistakes[1] = cur;
            } else {
                mistakes[1] = cur;
            }
        }
        prev[0] = cur;
        inOrderTraverse(prev, cur.right, mistakes);
    }
}
