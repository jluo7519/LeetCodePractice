public class LC00270ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        if (root == null) throw new IllegalArgumentException();
        int closest = root.val;
        TreeNode cur = root;
        while (cur != null) {
            if (Math.abs(target - cur.val) < Math.abs(target - closest)) {
                closest = cur.val;
            }
            if (Math.abs(cur.val - target) < 0.0001d) {
                return cur.val;
            } else if (target > cur.val) { // go right
                cur = cur.right;
            } else if (target < cur.val) { // go left
                cur = cur.left;
            }
        }
        return closest;
    }
    public static void main(String args[]) {
        LC00270ClosestBinarySearchTreeValue test = new LC00270ClosestBinarySearchTreeValue();
/*        TreeNode test1 = TreeNode.construct(new Integer[]{4,2,5,1,3});
        System.out.println(test.closestValue(test1, 3.714286));
        TreeNode test2 = TreeNode.construct(new Integer[]{1});
        System.out.println(test.closestValue(test2, 4.428571));*/
        TreeNode test3 = TreeNode.construct(new Integer[] {28,12,45,4,24,35,47,2,9,14,25,31,42,46,48,0,3,8,11,13,20,null,26,30,33,41,43,null,null,null,49,null,1,null,null,7,null,10,null,null,null,17,22,null,27,29,null,32,34,36,null,null,44,null,null,null,null,6,null,null,null,16,18,21,23,null,null,null,null,null,null,null,null,null,37,null,null,5,null,15,null,null,19,null,null,null,null,null,40,null,null,null,null,null,null,39,null,38
        });
        System.out.println(test.closestValue(test3, 2.000000));
    }
}
