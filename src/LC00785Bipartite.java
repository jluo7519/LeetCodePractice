import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC00785Bipartite {
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) throw new IllegalArgumentException();
        int[] group = new int[graph.length];
        Arrays.fill(group, -1);
        for (int i = 0; i < graph.length; i++) {
            // only bfs nodes haven't been colored before.
            // otherwise, we already checked that connected component is bipartite
            if (group[i] == -1 && !bfs(i, group, graph)) {
                return false;
            }
        }
        return true;
    }
    private boolean bfs(int v, int[] group, int[][] graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        group[v] = 0; // give any group
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nei : graph[cur]) {
                if (group[nei] == group[cur]) {
                    return false;
                } else if (group[nei] == -1) {
                    group[nei] = 1 - group[cur];
                    queue.offer(nei);
                }
            }
        }
        return true;
    }

    // Overall Time Complexity = O(E * log(V))
    public boolean isBipartiteUF(int[][] graph) {
        BPUnionFind uf = new BPUnionFind(graph);
        for (int p = 0; p < graph.length; p++) { // O(V)
            for (int i = 0; i < graph[p].length; i++) { // O(k)
                int q = graph[p][i];
                if (uf.find(p, q)) { // O(log(V))
                    return false;
                }
                uf.union(graph[p][0], graph[p][i]);
            }
        }
        return true;
    }
}
class BPUnionFind {
    int[] parent;
    int[] size;
    public BPUnionFind(int[][] graph) {
        this.parent = new int[graph.length];
        this.size = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }
    private int getRoot(int p) {
        int cur = p;
        while (parent[cur] != cur) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        parent[p] = cur;
        return cur;
    }
    public boolean find(int p, int q) {
        return getRoot(p) == getRoot(q);
    }
    public void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);
        if (size[rootP] >= size[rootQ]) { // rootQ -> rootP
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            union(q, p);
        }
    }
}