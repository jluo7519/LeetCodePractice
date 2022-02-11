import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC01008ConstructBSTFromPreorderTraversal {
    public TreeNode buildBST(int[] preorder) {
        if (preorder == null) throw new IllegalArgumentException();
        int[] index = new int[]{0};
        return buildBST(preorder, Long.MIN_VALUE, Long.MAX_VALUE, index);
    }
    private TreeNode buildBST(int[] pre, long min, long max, int[] index) {
        if (index[0] >= pre.length) {
            return null;
        }
        int rootVal = pre[index[0]];
        if (rootVal >= max || rootVal <= min) {
            return null;
        }
        // need to make sure not to increment in base cases!!!
        index[0]++;
        TreeNode left = buildBST(pre, min, rootVal, index);
        TreeNode right = buildBST(pre, rootVal, max, index);
        TreeNode root = new TreeNode(rootVal);
        root.left = left;
        root.right = right;
        return root;
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null) throw new IllegalArgumentException();
        int[] pre = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(preorder);
        int[] in = preorder;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return build(in, 0, in.length - 1, pre, 0, pre.length - 1, map);
    }
    private TreeNode build(int[] in, int inStart, int inEnd, int[] pre, int preStart, int preEnd, Map<Integer, Integer> map) {
        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int rootIdxIn = map.get(pre[preStart]);
        TreeNode left = build(in, inStart, rootIdxIn - 1, pre, preStart + 1, rootIdxIn - inStart + preStart, map);
        TreeNode right = build(in, rootIdxIn + 1, inEnd, pre, rootIdxIn - inStart + preStart + 1, preEnd, map);
        root.left = left;
        root.right = right;
        return root;
    }
}
// pre = 8 5 1 7 10 12
// in  = 1 5 7 8 10 12