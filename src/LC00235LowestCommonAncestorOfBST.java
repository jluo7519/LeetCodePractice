public class LC00235LowestCommonAncestorOfBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) throw new IllegalArgumentException();
        TreeNode large = p.val >= q.val ? p : q;
        TreeNode small = p == large ? q : p;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val >= small.val && cur.val <= large.val) {
                return cur;
            } else if (cur.val < small.val) {
                cur = cur.right;
            } else if (cur.val > large.val) {
                cur = cur.left;
            }
        }
        throw new RuntimeException("no lca");
    }
}
