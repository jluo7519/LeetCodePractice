import java.util.*;

public class LC00107BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> thisLevel = new ArrayList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                thisLevel.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(thisLevel);
        }
        Collections.reverse(res);
        return res;
    }

    public List<List<Integer>> levelOrderBottomPre(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        preorder(root, res, 0);
        Collections.reverse(res);
        return res;
    }
    private void preorder(TreeNode root, List<List<Integer>> res, int level) {
/*        if (root == null) {
            return;
        }*/
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);

        if (root.left != null) {
            preorder(root.left, res, level + 1);
        }
        if (root.right != null) {
            preorder(root.right, res, level + 1);
        }
    }
}
