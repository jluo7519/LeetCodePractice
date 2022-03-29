public class LC00236LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) throw new IllegalArgumentException();
        Ret res = dfs(root, p, q);
        return res.isAnc ? res.node : null;
    }
    private Ret dfs(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null) return new Ret(false, null);
        // p == q
        if (cur.val == p.val && cur.val == q.val) {
            return new Ret(true, cur);
        }
        // one side returns LCA
        Ret left = dfs(cur.left, p, q);
        if (left.isAnc) {
            return left;
        }
        Ret right = dfs(cur.right, p, q);
        if (right.isAnc) {
            return right;
        }
        // cur is LCA, found one node from each side
        if (left.node != null && right.node != null) { //左右都不为null
            return new Ret(true, cur);
        } else if (left.node == null && right.node == null) { //左右都是null
            // nothing from both side
            return new Ret(false, (cur.val == p.val || cur.val == q.val) ? cur : null);
        } else { // 一边为null, 一边不为null
            // cur is LCA, cur is one of the nodes, the other is from left or right
            if (cur.val == p.val || cur.val == q.val) {
                return new Ret(true, cur);
            }
            // only one side returns a node, cur node is not p or q
            return left.node != null ? left : right;
        }
    }
}
class Ret{
    boolean isAnc;
    TreeNode node;
    Ret(boolean isAnc, TreeNode node) {
        this.isAnc = isAnc;
        this.node = node;
    }
}