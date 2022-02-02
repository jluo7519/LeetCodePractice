import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this(val);
        this.left = left;
        this.right = right;
    }
    //TODO: remove nulls after last non-null node
    public String toString(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    sb.append("null, ");
                } else {
                    sb.append("" + cur.val + ", ");
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode rr = new TreeNode(6);
        root.left = left;
        root.right = right;
        right.right = rr;

        System.out.println(root.toString(root));
    }
}
