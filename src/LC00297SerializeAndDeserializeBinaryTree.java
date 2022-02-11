import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC00297SerializeAndDeserializeBinaryTree {
    // BFS solution
    // pro: save space for null nodes in the end
    public String serialize(TreeNode root) {
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
    public TreeNode deserialize(String data) {
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

    // DFS solution
    // pro: easy to recover subtree
    public String serializeDFS(TreeNode root) {
        if (root == null) {
            return new String("#");
        }
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append("" + root.val  + ",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }
    public TreeNode deserializeDFS(String data) {
        if (data == null) throw new IllegalArgumentException();
        String[] input = data.split(",");
        int[] index= new int[]{0};
        return deserializeHelper(input, index);
    }
    private TreeNode deserializeHelper(String[] input, int[] index) {
        if (index[0] > input.length) {
            return null;
        }
        if (input[index[0]].equals("#")) {
            index[0]++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(input[index[0]++]));
        root.left = deserializeHelper(input, index);
        root.right = deserializeHelper(input, index);
        return root;
    }
    public static void main(String args[]) {
        LC00297SerializeAndDeserializeBinaryTree test = new LC00297SerializeAndDeserializeBinaryTree();
        System.out.println(test.serialize(TreeNode.construct(new Integer[]{1,2,3,null,null,4,5})));
        System.out.println(test.serializeDFS(TreeNode.construct(new Integer[]{1,2,3,null,null,4,5})));
        TreeNode res = test.deserializeDFS("1,2,#,#,3,4,#,#,5,#,#");
        System.out.println(TreeNode.serialize(res));
    }

}
