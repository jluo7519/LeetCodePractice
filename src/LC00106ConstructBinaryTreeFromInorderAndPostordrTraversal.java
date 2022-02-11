import java.util.HashMap;
import java.util.Map;

public class LC00106ConstructBinaryTreeFromInorderAndPostordrTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            throw new IllegalArgumentException();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
    private TreeNode build(int[] in, int inStart, int inEnd, int[] post, int poStart, int poEnd, Map<Integer, Integer> map) {
        if (inStart > inEnd || poStart > poEnd) {
            return null;
        }
        TreeNode root = new TreeNode(post[poEnd]);
        int rootIdxIn = map.get(post[poEnd]);
        TreeNode left = build(in, inStart, rootIdxIn - 1, post, poStart, poStart + rootIdxIn - inStart - 1, map);
        TreeNode right = build(in, rootIdxIn + 1, inEnd, post, poStart + rootIdxIn - inStart, poEnd - 1, map);
        root.left = left;
        root.right = right;
        return root;
    }
}
