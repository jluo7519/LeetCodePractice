public class LC00333LargestBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        int[] largest = new int[] {0};
        SizeMinMax res = dfs(root, largest);
        return largest[0];
    }
    private SizeMinMax dfs(TreeNode root, int[] largest) {
        if (root == null) {
            return new SizeMinMax(0, 0, 0);
        }
        SizeMinMax left = dfs(root.left, largest);
        SizeMinMax right = dfs(root.right, largest);
        //null means not a BST
        if (left == null || right == null) {
            return null;
        }
        //if both left and right are BSTs, see if they can combine and form a larger BST
        if ((left.size == 0 || left.max < root.val) && (right.size == 0 || right.min > root.val)) {
            int size = left.size + right.size + 1;
            largest[0] = Math.max(largest[0], size);
            return new SizeMinMax(size,
                    left.size == 0 ? root.val : left.min,
                    right.size == 0 ? root.val : right.max);
        }
        //if can't form a larger BST, that means current root is not a root of BST
        return null;
    }

    public static void main(String args[]) {
        LC00333LargestBSTSubtree test = new LC00333LargestBSTSubtree();
        TreeNode test1 = TreeNode.construct(new Integer[]{10,5,15,1,8,null,7});
        System.out.println(test.largestBSTSubtree(test1));
    }
}
class SizeMinMax {
    int size;
    int min;
    int max;
    public SizeMinMax(int size, int min, int max) {
        this.size = size;
        this.min = min;
        this.max = max;
    }
}
