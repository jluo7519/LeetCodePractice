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

    public List<List<Integer>> zigzagLevel(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack1 = new Stack<>(); // for even levels
        Stack<TreeNode> stack2 = new Stack<>(); // for odd levels
        stack1.push(root);
        boolean flag = false;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            while (!stack1.isEmpty()) {
                TreeNode cur = stack1.pop();
                level.add(cur.val);
                if (!flag) {
                    if (cur.left != null) {
                        stack2.push(cur.left);
                    }
                    if (cur.right != null) {
                        stack2.push(cur.right);
                    }
                } else {
                    if (cur.right != null) {
                        stack2.push(cur.right);
                    }
                    if (cur.left != null) {
                        stack2.push(cur.left);
                    }
                }
            }
            res.add(level);
            // swap two stacks
            stack1 = stack2;
            stack2 = new Stack<>();
            flag = !flag;
        }
        return res;
    }
}
