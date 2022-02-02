import java.util.ArrayList;
import java.util.List;

public class LC00095BinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n == 0) return new ArrayList<>();
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        for (int k = start; k <= end; k++) {
            List<TreeNode> lefts = dfs(start, k - 1);
            List<TreeNode> rights = dfs(k + 1, end);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(k);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static void main(String args[]) {
        LC00095BinarySearchTreesII test = new LC00095BinarySearchTreesII();
        List<TreeNode> res1 = test.generateTrees(3);
        for (TreeNode node : res1) {
            System.out.println("[" + node.toString(node) + "]");
        }
        List<TreeNode> res2 = test.generateTrees(1);
        for (TreeNode node : res2) {
            System.out.println("[" + node.toString(node) + "]");
        }
    }
}
