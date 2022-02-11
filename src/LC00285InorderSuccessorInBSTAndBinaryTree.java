public class LC00285InorderSuccessorInBSTAndBinaryTree {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) throw new IllegalArgumentException();
        boolean found = false;
        TreeNode res = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > p.val) { // go left
                res = cur; // cur可能是p的inorder successor
                cur = cur.left;
            } else if (cur.val < p.val){ // go right
                cur = cur.right;
            } else { // ==
                found = true;
                cur = cur.right;
            }
        }
        // 如果有过某次左拐那就记录过最近右侧parent, 没有过左拐相当于prev一直是null
        // 如果找到p以后有right child, 走到right child以后right.val > p所有还会左拐把right child赋值给res
        // 如果既没有右子, 也没有过左拐赋值，那就刚好是null
        if (!found) throw new RuntimeException("doesn't contain node p");
        return res;
    }
    public TreeNode inorderSuccessorBT(TreeNode root, TreeNode p) {
        if (root == null) throw new IllegalArgumentException();
        TreeNode[] prev = new TreeNode[2];
        TreeNode suc = dfs(root, p, prev);
        // prev[0] to pass prev, prev[1] to record p found
        if (prev[1] == null) throw new RuntimeException("p not contained");
        return suc;
    }
    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode[] prev) {
        if (root == null) return null;
        if (root.val == p.val) {
            prev[1] = root;
        }
        TreeNode left = dfs(root.left, p, prev);
        if (left != null) return left;
        // 要先遍历左数才能得到prev所以这步只能放在中间
        if (prev[0]!= null && prev[0].val == p.val) {
            return root;
        }
        prev[0] = root;
        return dfs(root.right, p, prev);
    }
    public static void main(String args[]) {
        LC00285InorderSuccessorInBSTAndBinaryTree test = new LC00285InorderSuccessorInBSTAndBinaryTree();
        TreeNode test1 = TreeNode.construct(new Integer[]{2,1,3});
        TreeNode p1 = test1.left;
        System.out.println(test.inorderSuccessorBT(test1, p1).val);
        TreeNode test2 = TreeNode.construct(new Integer[]{33, 7, 2, 11, null, 12});
        TreeNode p2 = new TreeNode(35);
        System.out.println(test.inorderSuccessorBT(test2, p2));
    }
}
