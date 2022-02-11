import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC00272ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null || k < 0) throw new IllegalArgumentException();
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        // initialize stacks
        TreeNode cur = root;
        while (cur != null) { // O(log n)
            if (cur.val > target) { // go left
                rightStack.push(cur); // cur and it's right subtree
                cur = cur.left;
            } else { // go right
                leftStack.push(cur); // cur and it's left subtree
                cur = cur.right;
            }
        }
        // get top k closest
        List<Integer> result = new ArrayList<>();
        while (k-- > 0) {  // O(k * log n) ==> amortize to O(k)
            if (!leftStack.isEmpty() && !rightStack.isEmpty()) {
                if (Math.abs(leftStack.peek().val - target) <= Math.abs(rightStack.peek().val - target)) {
                    // left closer
                    result.add(decLeft(leftStack));
                } else {
                    result.add(incRight(rightStack));
                }
            } else if (!leftStack.isEmpty()) {
                result.add(decLeft(leftStack));
            } else if (!rightStack.isEmpty()) {
                result.add(incRight(rightStack));
            } else {
                break;
            }
        }
        return result;
    }
    private int decLeft(Stack<TreeNode> stack) { // O(log n)
        TreeNode top = stack.pop();
        TreeNode cur = top.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return top.val;
    }
    private int incRight(Stack<TreeNode> stack) { // O(log n)
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return top.val;
    }

    public static void main(String args[]) {
        LC00272ClosestBinarySearchTreeValueII test = new LC00272ClosestBinarySearchTreeValueII();
        TreeNode test1 = TreeNode.construct(new Integer[]{4,2,5,1,3});
        List<Integer> res1 = test.closestKValues(test1, 3.714286d, 2);
        for (Integer n : res1) {
            System.out.print(" " + n);
        }
    }
}
