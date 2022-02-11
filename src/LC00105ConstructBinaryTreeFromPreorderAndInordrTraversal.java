import java.util.HashMap;
import java.util.Map;

public class LC00105ConstructBinaryTreeFromPreorderAndInordrTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            throw new IllegalArgumentException();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode res = build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
        return res;
    }
    private TreeNode build(int[] pre, int pStart, int pEnd, int[] in, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (pStart > pEnd || inStart > inEnd) {
            return null;
        }
        // pStart is root, find same value in inorder and divide
        TreeNode root = new TreeNode(pre[pStart]);
        int rootIdxIn = map.get(pre[pStart]);
        // 注意这里preorder的右边界要从inorder里找出来
        // inorder [inStart, rootIdxIn - 1]属于left subtree 对应 preorder里当前root位置数rootIdxIn - 1 - inStart + 1个
        // inorder [rootIdxIn + 1, inEnd]属于right subtree 对应从左子结束以后剩下的全都是
        TreeNode left = build(pre, pStart + 1, pStart + rootIdxIn - inStart, in, inStart, rootIdxIn - 1, map);
        TreeNode right = build(pre, pStart + rootIdxIn - inStart + 1, pEnd, in, rootIdxIn + 1, inEnd, map);
        root.left = left;
        root.right = right;
        return root;
    }
}
