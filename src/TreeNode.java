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
    public static String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("#,");
            } else {
                sb.append("" + cur.val + ',');
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        // post process to save space
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) != ',' && sb.charAt(i) != '#') break;
            else if (sb.charAt(i) == ',' || sb.charAt(i) == '#') {
                sb.setLength(sb.length() - 1);
            }
        }
        return sb.toString();
    }
    public static TreeNode deserialize(String data) {
        if (data == null) throw new IllegalArgumentException();
        String[] input = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        if (input.length == 0 || input[0].equals("#") || input[0].equals("")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(input[0]));
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (index < input.length && !input[index].equals("#")) {
                cur.left = new TreeNode(Integer.valueOf(input[index]));
                queue.offer(cur.left);
            }
            index++;
            if (index < input.length && !input[index].equals("#")) {
                cur.right = new TreeNode(Integer.valueOf(input[index]));
                queue.offer(cur.right);
            }
            index++;
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

        System.out.println(TreeNode.serialize(root));

        TreeNode constructRoot = construct(new Integer[]{1,2,3,null,null,4,5});
        System.out.println(TreeNode.serialize(constructRoot));
    }
}
