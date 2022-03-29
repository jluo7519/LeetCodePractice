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
    // S2 checks if both node p q exists
    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) throw new IllegalArgumentException();
        TreeNode cur = root;
        TreeNode larger = p.val >= q.val ? p : q;
        TreeNode smaller = larger == p ? q : p;
        boolean foundLarger = false;
        boolean foundSmaller = false;
        TreeNode lca = null;
        // find smaller
        while (cur != null) {
            if (lca == null && cur.val <= larger.val && cur.val >= smaller.val) {
                lca = cur;
            }
            if (cur.val == smaller.val) {
                foundSmaller = true;
                break;
            } else if (cur.val > smaller.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        cur = root;
        while (cur != null) {
            if (lca == null && cur.val <= larger.val && cur.val >= smaller.val) {
                lca = cur;
            }
            if (cur.val == larger.val) {
                foundLarger = true;
                break;
            } else if (cur.val > larger.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (lca == null) throw new RuntimeException("no lca");
        return lca;
    }
}
