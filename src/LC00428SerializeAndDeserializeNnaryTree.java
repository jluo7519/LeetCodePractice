import java.util.ArrayList;
import java.util.List;

public class LC00428SerializeAndDeserializeNnaryTree {
    private int i = 0;
    public String serialize(NNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return ".";
        }
        sb.append(root.val + "-" + root.children.size() + ",");
        for (NNode child : root.children) {
            sb.append(serialize(child));
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public NNode deserialize(String data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (data.equals(".")) return null;
        return deserializeHelper(data.split(","));
    }

    private NNode deserializeHelper(String[] data) {
        String[] array = data[i++].split("-");
        int len = Integer.valueOf(array[1]);
        List<NNode> children = new ArrayList<>();
        for (int idx = 0; idx < len; idx++) {
            children.add(deserializeHelper(data));
        }
        return new NNode(Integer.valueOf(array[0]), children);
    }
}

class NNode {
    public int val;
    public List<NNode> children;

    public NNode() {}

    public NNode(int _val) {
        val = _val;
    }

    public NNode(int _val, List<NNode> _children) {
        val = _val;
        children = _children;
    }
}