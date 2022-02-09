import java.util.Stack;

public class LC00653TwoSumIVBST {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) throw new IllegalArgumentException();
        Stack<TreeNode> lStack = prepLeft(root);
        Stack<TreeNode> rStack = prepRight(root);

        while (!lStack.isEmpty() && !rStack.isEmpty()) {
            TreeNode left = lStack.peek();
            TreeNode right = rStack.peek();
            if (left == right) break;
            int sum = left.val + right.val;
            if (sum == k) {
                return true;
            } else if (sum <= k) {
                incLeft(lStack);
            } else {
                decRight(rStack);
            }
        }
        return false;
    }
    private Stack<TreeNode> prepLeft(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return stack;
    }
    private Stack<TreeNode> prepRight(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return stack;
    }
    private void incLeft(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }
    private void decRight(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
    }

    public static void main(String args[]) {
        LC00653TwoSumIVBST test = new LC00653TwoSumIVBST();
        TreeNode test1 = TreeNode.construct(new Integer[]{5,3,6,2,4,null,7});
        System.out.println(test.findTarget(test1, 9));
        TreeNode test2 = TreeNode.construct(new Integer[]{5,3,6,2,4,null,7});
        System.out.println(test.findTarget(test2, 28));
    }
}
