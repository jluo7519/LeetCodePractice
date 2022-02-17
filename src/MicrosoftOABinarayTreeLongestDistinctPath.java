import java.util.HashSet;
import java.util.Set;

public class MicrosoftOABinarayTreeLongestDistinctPath {

    public int longest(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        int[] max = new int[]{0};
        Set<Integer> set = new HashSet<>();
        dfs(root, set, max);
        return max[0];
    }
    private void dfs(TreeNode root, Set<Integer> set, int[] max) {
        // DFS从上往下传值, 只能通过input传或者global variable
        if (root == null || set.contains(root.val)) {
            max[0] = Math.max(max[0], set.size()); // 在base case更新更make sense. 中间很多没必要的操作
            return;
        }
        set.add(root.val);
        // max[0] = Math.max(max[0], set.size());
        dfs(root.left, set, max);
        dfs(root.right, set, max);
        set.remove(root.val);

    }

    public static void main(String args[]) {
        MicrosoftOABinarayTreeLongestDistinctPath test = new MicrosoftOABinarayTreeLongestDistinctPath();
        TreeNode root1 = TreeNode.construct(new Integer[]{1, 2, 2, 1, 2, 4, 1});
        System.out.println(test.longest(root1));
        TreeNode root2 = TreeNode.construct(new Integer[]{1, null, 2, 1, 1, null, null, 4});
        System.out.println(test.longest(root2));
    }
}
