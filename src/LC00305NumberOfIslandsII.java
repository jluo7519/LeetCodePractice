import java.util.ArrayList;
import java.util.List;

public class LC00305NumberOfIslandsII {
    private static final int[][] DIRS = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        if (positions == null || m == 0 || n == 0) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<>();
        UnionFind uf = new UnionFind(m, n);
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            int p = getIndex(i, j, m, n);
            List<Integer> neis = convertNeis(i, j, m, n);
            uf.addIsland(p);
            for (int q : neis) {
                if (uf.isIsland(q) && !uf.find(p, q)) {
                    uf.union(p, q);
                }
            }
            res.add(uf.getNumIslands());
        }
        return res;
    }

    private int getIndex(int i, int j, int rows, int cols) {
        return i * cols + j + 1;
    }

    private List<Integer> convertNeis(int i, int j, int rows, int cols) {
        List<Integer> neis = new ArrayList<>();
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols) {
                neis.add(getIndex(x, y, rows, cols));
            }
        }
        return neis;
    }

    public static void main(String args[]) {
        LC00305NumberOfIslandsII test = new LC00305NumberOfIslandsII();
        List<Integer> res1 = test.numIslands2(3,3,new int[][]{{0,0},{0,1},{1,2},{2,1}});
        System.out.println(res1.toString());
        List<Integer> res2 = test.numIslands2(1,1,new int[][]{{0,0}});
        System.out.println(res2.toString());
        List<Integer> res3 = test.numIslands2(3,3,new int[][]{{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}});
        System.out.println(res3.toString());
    }
}

class UnionFind {
    private int numIslands;
    private int[] size;
    private int[] parent;

    public UnionFind(int rows, int cols) {
        this.size = new int[rows * cols + 1];
        this.parent = new int[rows * cols + 1];
        this.numIslands = 0;
    }

    public int getRoot(int p) {
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
        if (size[rootP] >= size[rootQ]) { // rootP is bigger tree
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            this.numIslands--;
        } else {
            union(q, p);
        }
    }

    public void addIsland(int p) {
        if (isIsland(p)) {
            return;
        }
        parent[p] = p;
        size[p] = 1;
        this.numIslands++;
    }

    public boolean isIsland(int p) {
        return parent[p] > 0;
    }

    public int getNumIslands() {
        return numIslands;
    }
}