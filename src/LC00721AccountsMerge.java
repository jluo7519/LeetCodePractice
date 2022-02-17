import java.util.*;
public class LC00721AccountsMerge {
    AccountsMergeUF uf;
    int nAcct;
    // Overall Time Complexity = O(N * K * log N + N * K log K) = O(N *  K log N) if we assume K << N
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null) {
            throw new IllegalArgumentException();
        }
        nAcct = accounts.size();
        uf = new AccountsMergeUF(nAcct);
        Map<String, Integer> emailToAcct = new HashMap<>();
        for (int i = 0; i < nAcct; i++) { // O(N)
            List<String> acct = accounts.get(i);
            int acctLen = acct.size();
            for (int j = 1; j < acctLen; j++) { // O(K)
                String email = acct.get(j);
                Integer id = emailToAcct.get(email);
                if (id == null) { // no prev account for same email
                    emailToAcct.put(email, i);
                } else { // prev account used this email
                    if (!uf.find(i, id)) { // O(log N)
                        uf.union(i, id);
                    }
                }
            }
        }
        Map<Integer, Set<String>> group = group(accounts); // O(N * K)
        return rebuildAccounts(accounts, group); // O(N * K log K)
    }

    // O(N * K)
    private Map<Integer, Set<String>> group(List<List<String>> accounts) {
        Map<Integer, Set<String>> group = new HashMap<>();
        for (int i = 0; i < nAcct; i++) { // O(N)
            int id = uf.getRoot(i);
            Set<String> emailSet = group.getOrDefault(id, new HashSet<>());
            List<String> list = accounts.get(i);
            emailSet.addAll(list.subList(1, list.size())); // O(K)
            group.put(id, emailSet);
        }
        return group;
    }

    // O(N * K log K)
    private List<List<String>> rebuildAccounts(List<List<String>> accounts, Map<Integer, Set<String>> group) {
        List<List<String>> res = new ArrayList<>();
        for (int id : group.keySet()) { // O(N)
            Set<String> emailSet = group.get(id);
            List<String> emails = new ArrayList<>(emailSet); // O(K)
            Collections.sort(emails); // K log K
            emails.add(0, accounts.get(id).get(0)); // O(K)
            res.add(emails);
        }
        return res;
    }
    //[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    public static void main(String args[]) {
        LC00721AccountsMerge test = new LC00721AccountsMerge();
        List<List<String>> res1 = test.accountsMerge(Arrays.asList(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
                Arrays.asList("John","johnsmith@mail.com","john00@mail.com"), Arrays.asList("Mary","mary@mail.com"),
                Arrays.asList("John","johnnybravo@mail.com")));
        for (List<String> l : res1) {
            System.out.println(l.toString());
        }
    }
}
class AccountsMergeUF {
    int[] size;
    int[] parent;

    public AccountsMergeUF(int n) { // O(N)
        this.size = new int[n];
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;
            parent[i] = i;
        }
    }

    public int getRoot(int p) { // O(log N)
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
        if (size[rootP] >= size[rootQ]) { // rootQ --> rootP
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            union(q, p);
        }
    }
}
