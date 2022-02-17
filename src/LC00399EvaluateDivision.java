import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC00399EvaluateDivision {
    // Overall Time Complexity = O(M log M + N) M is # equations, N is # queries
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (equations == null || values == null || queries == null || equations.size() != values.length) {
            throw new IllegalArgumentException();
        }
        int len = equations.size();
        EvaluateDivisionUF uf = new EvaluateDivisionUF();
        Map<String, LetterV> map = new HashMap<>();
        for (int i = 0; i < len; i++) { // O(M log M)
            List<String> pair = equations.get(i);
            LetterV p = map.getOrDefault(pair.get(0), new LetterV(pair.get(0)));
            LetterV q = map.getOrDefault(pair.get(1), new LetterV(pair.get(1)));
            map.put(pair.get(0), p);
            map.put(pair.get(1), q);
            // p / q
            if (!uf.find(p, q)) { // O(log M)
                uf.union(p, q, values[i]);
            }
        }
        return evaluate(queries, map, uf); // O(N)
    }
    private double[] evaluate(List<List<String>> queries, Map<String, LetterV> map, EvaluateDivisionUF uf) {
        int size = queries.size();
        double[] res = new double[size];
        for (int i = 0; i < size; i++) { // O(N)
            List<String> query = queries.get(i);
            LetterV p = map.get(query.get(0));
            LetterV q = map.get(query.get(1));
            if (p == null || q == null) {
                res[i] = -1d; // should be null or exception to avoid ambiguity in interviews
                continue;
            }
            // O(1)
            if (uf.find(p, q)) { // root / p = p.val; root / q = q.val => p = root / p.val; q = root / q.val
                                    // p / q = (root/p.val)/(root/q.val) = (root/p.val) * (q.val/root) = q.val/p.val
                res[i] = q.val / p.val;
            } else {
                res[i] = -1d; // should be null or exception to avoid ambiguity in interviews
            }
        }
        return res;
    }
}
class EvaluateDivisionUF {
    EvaluateDivisionUF() {}
    public LetterV getRoot(LetterV p) { // O(log N)
        LetterV cur = p;
        double pVal = 1d;
        while (cur.parent != cur) {
            cur.val *= cur.parent.val; // 先更新value再移动指针否则就更新了他的parent
            cur.parent = cur.parent.parent;
            pVal *= cur.val; // 每次cur向上动，p实际上也在跟着升级
            cur = cur.parent;
        }
        p.val = pVal;
        p.parent = cur; // TODO: 得想想为什么
        return cur;
    }
    public boolean find(LetterV p, LetterV q) { // O(1)
        return getRoot(p) == getRoot(q);
    }
    public void union(LetterV p, LetterV q, double val) { // O(1)
        LetterV rootP = getRoot(p);
        LetterV rootQ = getRoot(q);
        if (rootP.size >= rootQ.size) { // rootQ -> rootP
            rootQ.parent = rootP;
            rootP.size += rootQ.size;
            rootQ.val = val * p.val / q.val; // p = p.val; p / q = val
            // rootP / p = p.val; rootQ / q = q.val
            // rootQ.val = rootP / rootQ = (p * p.val) / (q * q.val) = p/q * p.val/q.val
        } else {
            union(q, p, 1 / val);
        }
    }
}
class LetterV {
    String name;
    double val;
    LetterV parent;
    int size;
    public LetterV(String name) {
        this.name = name;
        this.size = 1;
        this.parent = this;
        this.val = 1d;
    }
}