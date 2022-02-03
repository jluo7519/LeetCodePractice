import java.util.ArrayList;
import java.util.List;

public class NBTree {
    public List<TreeNode> allUniqueTrees(int n) {
        if (n < 0) throw new IllegalArgumentException();
        List<TreeNode> result = constructTrees(n);
        return result;
    }
    private List<TreeNode> constructTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n == 1) {
            result.add(new TreeNode(0));
            return result;
        }
        //choose # left leaves and # right leaves, recursively construct tree and connect with a root
        for (int l = 1; l < n; l++) {
            List<TreeNode> lefts = constructTrees(l);
            List<TreeNode> rights = constructTrees(n - l);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(1);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    public int numUniqueTreesDP(int n) {
        if (n < 0) throw new IllegalArgumentException();
        int [] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int l = 1; l < i; l++) {
                dp[i] += dp[l] * dp[i - l];
            }
        }
        return dp[n];
    }
    public int numUniqueTrees(int n) {
        if (n < 0) throw new IllegalArgumentException();
        int [] mem = new int[n + 1];
        return numOfTrees(n, mem);
    }

    private int numOfTrees(int n, int[] mem) {
        if (n == 1) return 1;
        int count = 0;

        for (int l = 1; l < n; l++) {
            int lefts = mem[l] != 0 ? mem[l] : numUniqueTrees(l);
            mem[l] = lefts;

            int rights = mem[n - l] != 0 ? mem[n - l] : numUniqueTrees(n - l);
            mem[n - l] = rights;

            count += lefts * rights;
        }
        mem[n] = count;
        return count;
    }

    public static void main(String args[]) {
        NBTree test = new NBTree();
/*        List<TreeNode> res1 = test.allUniqueTrees(5);
        for (TreeNode node : res1) {
            System.out.println(node.toString(node));
        }*/
        System.out.println(test.numUniqueTrees(5));
    }
}
