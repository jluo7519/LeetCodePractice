import java.util.LinkedList;
import java.util.Queue;

public class LC00513FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        int[] bottomLeft = {0}; // 随便给个值
        int[] maxLevel = {0};
        dfs(root.left, 1, maxLevel, bottomLeft);
        dfs(root.right, 1, maxLevel, bottomLeft);
        return bottomLeft[0];
    }

    private void dfs(TreeNode root, int level, int[] maxLevel, int[] bottomLeft) {
        if (root == null) {
            return;
        }

        if (level > maxLevel[0]) {
            maxLevel[0] = level;
            bottomLeft[0] = root.val;
        }
        dfs(root.left, level + 1, maxLevel, bottomLeft);
        dfs(root.right, level + 1, maxLevel, bottomLeft);
    }

    public int findBottomLeftVal(TreeNode root) {
        if (root == null) throw new IllegalArgumentException();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = queue.peek().val; // 每层开始的地方peek就是bottom left so far
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                // generate
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }
}
