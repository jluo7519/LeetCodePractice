import java.util.*;

public class LC00103BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new ArrayDeque<>();
        boolean flag = false;
        deque.offerFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                if (!flag) {
                    TreeNode cur = deque.pollFirst();
                    level.add(cur.val);
                    if (cur.left != null) deque.offerLast(cur.left);
                    if (cur.right != null) deque.offerLast(cur.right);
                } else {
                    TreeNode cur = deque.pollLast();
                    level.add(cur.val);
                    if (cur.right != null) deque.offerFirst(cur.right);
                    if (cur.left != null) deque.offerFirst(cur.left);
                }
            }
            flag = !flag;
            res.add(level);
        }
        return res;
    }
}
