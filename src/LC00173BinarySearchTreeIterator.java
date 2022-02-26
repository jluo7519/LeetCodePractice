import java.util.Stack;
// overall amortized O(n) since to iterate all nodes in the tree, each node only go in to and go out from stack once
// memory: O(height) cost of stack
public class LC00173BinarySearchTreeIterator {
    private Stack<TreeNode> stack;
    public LC00173BinarySearchTreeIterator(TreeNode root) { // O(log n)
        stack = new Stack<>();
        // all the way to the left from root, push every node into stack
        // logically push the node and its right subtree
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    public int next() { // O(log n)
        if (!hasNext()) throw new RuntimeException("no next");
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return top.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
