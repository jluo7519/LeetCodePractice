public class LC00236LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) throw new IllegalArgumentException();
        ReturnLCA result = dfs(root, p, q);
        //都不在tree里的话result.node == null
        return result.isAnc ? result.node : null;
    }
    private ReturnLCA dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new ReturnLCA(false, null);// node null means neither p nor q is found
        if (root.equals(q) && root.equals(p)) return new ReturnLCA(true, root); //scenario  p == q

        //isAnc表示找到了直接往上返
        ReturnLCA left = dfs(root.left, p, q);
        if (left.isAnc) {
            return left;
        }
        ReturnLCA right = dfs(root.right, p, q);
        if (right.isAnc) {
            return right;
        }
        //剩下的是还没找到LCA
        if (left.node != null && right.node != null) {
            //左边右边各自搜索到了p和q所以root是LCA
            return new ReturnLCA(true, root);
        } else if (root.equals(p) || root.equals(q)) {
            //只要左右能找到一个, 并且root等于另一个, 那么root就是LCA, 对应p或者q就是LCA的scenario
            //否则root就是找到的第一个node, 还没有找到LCA, 还得继续往上找
            boolean isAnc = (left.node != null || right.node != null) ? true : false;
            return new ReturnLCA(isAnc, root);
        } else {
            //当前root还不是LCA, 但是左或者右里含有p或者q, 还得继续往上找
            return new ReturnLCA(false, left.node == null ? right.node : left.node);
        }
    }
}
class ReturnLCA{
    boolean isAnc;
    TreeNode node;
    ReturnLCA(boolean isAnc, TreeNode node) {
        this.isAnc = isAnc;
        this.node = node;
    }
}