import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this(val);
        this.left = left;
        this.right = right;
    }
    //TODO: remove nulls after last non-null node
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    sb.append("null, ");
                } else {
                    sb.append("" + cur.val + ", ");
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    public static TreeNode construct(String data) {
        if (data == null) return null;
        String[] array = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        int index = 0;
        if (array[index] == null || array[index] == "null") return null;
        TreeNode root = new TreeNode(Integer.parseInt(array[index++]));
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (index < array.length) {
                String left = array[index++];
                if (!left.equals("null")) {
                    cur.left = new TreeNode(Integer.parseInt(left));
                    queue.offer(cur.left);
                }
            }

            if (index < array.length) {
                String right = array[index++];
                if (!right.equals("null")) {
                    cur.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }
    public static TreeNode construct(Integer[] array) {
        if (array == null || array.length == 0) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        int index = 0;
        if (array[index] == null) return null;
        TreeNode root = new TreeNode(array[index++]);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (index < array.length) {
                Integer left = array[index++];
                if (left != null) {
                    cur.left = new TreeNode(left);
                    queue.offer(cur.left);
                }
            }

            if (index < array.length) {
                Integer right = array[index++];
                if (right != null) {
                    cur.right = new TreeNode(right);
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode rr = new TreeNode(6);
        root.left = left;
        root.right = right;
        right.right = rr;

        System.out.println(root.toString());

        TreeNode constructRoot = construct(new Integer[]{1,2,3,null,null,4,5});
        System.out.println(constructRoot.toString());
    }
}
