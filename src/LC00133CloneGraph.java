import java.util.*;

public class LC00133CloneGraph {
    // BFS
    public CloneGraphNode cloneGraph(CloneGraphNode node) {
        if (node == null) return null;
        Queue<CloneGraphNode> queue = new LinkedList<>();
        // deep copy root
        CloneGraphNode copyNode = new CloneGraphNode(node.val);
        queue.offer(node);
        Set<CloneGraphNode> visited = new HashSet<>();
        Map<CloneGraphNode, CloneGraphNode> origToCopy = new HashMap<>();
        origToCopy.put(node, copyNode);
        visited.add(node);
        while (!queue.isEmpty()) {
            CloneGraphNode oriCur = queue.poll();
            CloneGraphNode copyCur = origToCopy.get(oriCur);
            // generate neighbors
            for (CloneGraphNode oriNei : oriCur.neighbors) {
                if (visited.add(oriNei)) { // not visited, copy node and edge, enqueue
                    CloneGraphNode copyNei = new CloneGraphNode(oriNei.val);
                    origToCopy.put(oriNei, copyNei);
                    queue.offer(oriNei);
                }
                // else only copy edge since node has been created
                CloneGraphNode copyNei = origToCopy.get(oriNei);
                copyCur.neighbors.add(copyNei);
            }
        }
        return copyNode;
    }

    // DFS
    public CloneGraphNode cloneGraphDFS(CloneGraphNode node) {
        if (node == null) return null;
        Map<CloneGraphNode, CloneGraphNode> origToCopy = new HashMap<>();
        origToCopy.put(node, new CloneGraphNode(node.val));
        Set<CloneGraphNode> visited = new HashSet<>();
        dfs(node, origToCopy, visited);
        visited.add(node);
        return origToCopy.get(node);
    }
    private void dfs(CloneGraphNode cur, Map<CloneGraphNode, CloneGraphNode> map, Set<CloneGraphNode> visited) {
        if (cur == null) {
            return;
        }
        CloneGraphNode copyCur = map.get(cur);
        for (CloneGraphNode nei : cur.neighbors) {
            if (visited.add(nei)) { // create, go deep, copy edge
                map.put(nei, new CloneGraphNode(nei.val));
                dfs(nei, map, visited);
            }
            // else just copy edge
            copyCur.neighbors.add(map.get(nei));
        }
    }
}
class CloneGraphNode {
    public int val;
    public List<CloneGraphNode> neighbors;
    public CloneGraphNode() {
        val = 0;
        neighbors = new ArrayList<CloneGraphNode>();
    }
    public CloneGraphNode(int _val) {
        val = _val;
        neighbors = new ArrayList<CloneGraphNode>();
    }
    public CloneGraphNode(int _val, ArrayList<CloneGraphNode> neighbors) {
        val = _val;
        neighbors = neighbors;
    }
}