import java.util.Stack;
public class LC00230KthSmallestInBST {
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) throw new IllegalArgumentException();
        Stack<TreeNode> stack = new Stack<>();
        addToStack(root, stack);
        TreeNode cur = null;
        while (k-- > 0) {
            cur = increment(stack);
        }
        return cur.val;
    }
    private void addToStack(TreeNode root, Stack<TreeNode> stack) {
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }
    private TreeNode increment(Stack<TreeNode> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("insufficient nodes in tree");
        }
        TreeNode top = stack.pop();
        addToStack(top.right, stack);
        return top;
    }
}
