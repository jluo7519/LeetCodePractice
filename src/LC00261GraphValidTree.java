import java.util.ArrayList;
import java.util.List;

public class LC00261GraphValidTree {
    // Solution 1 DFS: O(n) 优点快 缺点只能做静态
    public boolean validTreeS1(int n, int[][] edges) {
        int len = edges.length;
        if (n - 1 != len) {
            return false; // if V - 1 > E 一定有环; if V - 1 < E 一定不能connected
        }
        List<List<Integer>> graph = buildGraph(n, edges);
        boolean[] visited = new boolean[n];
        dfs(0, graph, visited);
        int numVisited = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                numVisited++;
            }
        }
        return numVisited == n;
    }
    private void dfs(int cur, List<List<Integer>> graph, boolean[] visited) {
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        List<Integer> nexts = graph.get(cur);
        for (int next : nexts) {
            dfs(next, graph, visited);
        }
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) { // O(n)
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) { // O(E) = O(V) = O(n)
            int node1 = edge[0];
            int node2 = edge[1];
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        return graph;
    }

    // Solution 2: Union Find 时间O(n log n) 优点可以做动态变化, 缺点复杂度略逊于DFS
    public boolean validTreeS2(int n, int[][] edges) {
        if (edges == null) throw new IllegalArgumentException();
        int len = edges.length;
        if (n - 1 != len) {
            return false;
        }
        GraphValidTreeUF uf = new GraphValidTreeUF(n); // O(n)
        for (int[] edge : edges) { // O(n) = O(V) = O(E)
            int p = edge[0];
            int q = edge[1];
            if (uf.find(p,q)) { // O(log n)
                return false;
            } else {
                uf.union(p,q);
            }
        }
        return true;
    }
    public static void main(String args[]) {
        LC00261GraphValidTree test = new LC00261GraphValidTree();
        System.out.println(test.validTreeS2(5,new int[][]{{0,1},{0,2},{0,3},{1,4}}));
        System.out.println(test.validTreeS2(5,new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}));
        System.out.println(test.validTreeS2(5,new int[][]{{0,1},{0,4},{1,4},{2,3}}));
    }
}
class GraphValidTreeUF {
    private int[] size;
    private int[] parent;

    public GraphValidTreeUF(int n) { // O(n)
        size = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;
            parent[i] = i;
        }
    }

    private int getRoot(int p) { // O(log n)
        int cur = p;
        while (parent[cur] != cur) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        parent[p] = cur;
        return cur;
    }
    public boolean find(int p, int q) { // O(1)
        return getRoot(p) == getRoot(q);
    }
    public void union(int p, int q) { // O(1)
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
