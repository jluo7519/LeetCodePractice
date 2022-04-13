import java.util.ArrayList;
import java.util.List;

public class LC00257BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(root, sb, res);
        return res;
    }

    private void dfs(TreeNode cur, StringBuilder path, List<String> res) {
        int pathLen = path.length();
        // base case: leaf
        if (cur.left == null && cur.right == null) { // leaf node
            path.append(cur.val);
            res.add(path.toString());
            path.setLength(pathLen);
        }

        path.append(cur.val);
        path.append("->");
        if (cur.left != null) {
            dfs(cur.left, path, res);
        }

        if (cur.right != null) {
            dfs(cur.right, path, res);
        }

        path.setLength(pathLen);
    }
}
