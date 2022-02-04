import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class LC00894AllPossibleFullBinaryTrees {
    public List<TreeNode> allPossibleFBT(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n % 2 == 0) return new ArrayList<>();
        return dfs(n);
    }

    private List<TreeNode> dfs(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n == 1) {
            result.add(new TreeNode(0));
            return result;
        }

        for (int l = 1; l < n; l += 2) {
            List<TreeNode> lefts = dfs(l);
            List<TreeNode> rights = dfs(n - l - 1);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        LC00894AllPossibleFullBinaryTrees test = new LC00894AllPossibleFullBinaryTrees();
        List<TreeNode> res1 = test.allPossibleFBT(7);
        List<TreeNode> res2 = test.allPossibleFBT(3);
        for (TreeNode node : res2) {
            System.out.println(node.toString());
        }

    }
}
