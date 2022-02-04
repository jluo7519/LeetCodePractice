public class LC00250CountUnivalueSubtrees {
    //private int count = 0;//叶节点 左边Null右边null的时候count++ = 1所以给0
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        int[] count = new int[]{0};
        dfs(root, 0, count);
        return count[0];
    }
    private boolean dfs(TreeNode root, int target, int[] count) {
        if (root == null) {
            return true;
        }
        boolean left = dfs(root.left, root.val, count);
        boolean right = dfs(root.right, root.val, count);

        if (left && right) {
            count[0]++;
            return root.val == target;
        }
        return false;
    }

    public static void main(String args[]) {
        LC00250CountUnivalueSubtrees test = new LC00250CountUnivalueSubtrees();
        TreeNode test1 = TreeNode.construct(new Integer[]{5,1,5,5,5,null,5});
        System.out.println(test1.toString());
        System.out.println(test.countUnivalSubtrees(test1));
        TreeNode test2 = TreeNode.construct(new Integer[]{});
        System.out.println(test.countUnivalSubtrees(test2));
        TreeNode test3 = TreeNode.construct(new Integer[]{5,5,5,5,5,null,5});
        System.out.println(test.countUnivalSubtrees(test3));
    }
}
