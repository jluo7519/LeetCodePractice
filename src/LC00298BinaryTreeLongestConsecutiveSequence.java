public class LC00298BinaryTreeLongestConsecutiveSequence {
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[]{1};
        dfs(root, max);
        return max[0];
    }

    private int dfs(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, max);
        int right = dfs(root.right, max);
        int res = 1;
        //if left consecutive
        if (root.left != null && root.left.val - 1 == root.val) {
            res += left;
        }
        //if right consecutive
        if (root.right != null && root.right.val - 1 == root.val) {
            res = Math.max(res, right + 1);
        }
        //update globalMax
        max[0] = Math.max(res, max[0]);
        return res;
    }

    public static void main(String args[]) {
        LC00298BinaryTreeLongestConsecutiveSequence test = new LC00298BinaryTreeLongestConsecutiveSequence();
        TreeNode test1 = TreeNode.construct(new Integer[]{1,null,3,2,4,null,null,null,5});
        System.out.println(test.longestConsecutive(test1));
        TreeNode test2 = TreeNode.construct(new Integer[]{2,null,3,2,null,1});
        System.out.println(test.longestConsecutive(test2));
    }
}
