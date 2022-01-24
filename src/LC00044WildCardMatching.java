public class LC00044WildCardMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) throw new IllegalArgumentException();
        if (p.length() == 0) return s.length() == 0;
        Boolean m[][] = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(s, 0, p, 0, m);
    }
    private boolean dfs(String s, int indexS, String p, int indexP, Boolean[][] m) {
        int pLen = p.length();
        int sLen = s.length();
        if (m[indexS][indexP] != null) return m[indexS][indexP];
        if (indexP == pLen) {
            m[indexS][indexP] = indexS == sLen || p.charAt(pLen - 1) == '*';
            return m[indexS][indexP];
        }
        if (indexS == sLen) {
            while (indexP < pLen && p.charAt(indexP) == '*') {
                indexP++;
            }
            if (indexP == pLen) {
                m[indexS][indexP] = true;
            } else {
                m[indexS][indexP] = false;
            }
            return m[indexS][indexP];
        }
        //non *
        if (indexP < pLen && p.charAt(indexP) != '*') {
            if (s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '?') {
                m[indexS][indexP] = dfs(s, indexS + 1, p, indexP + 1, m);
            } else {
                m[indexS][indexP] = false;
            }
        } else {//* 开始分叉
            for (int i = indexS; i < sLen; i++) {
                if (dfs(s, i, p, indexP + 1, m)) {
                    m[indexS][indexP] = true;
                    return true;
                }
            }
            m[indexS][indexP] = false;
        }
        return m[indexS][indexP];
    }

    public static void main(String args[]) {
        LC00044WildCardMatching test = new LC00044WildCardMatching();
        //代码太丑陋跑赢leetcode 7%
    }
}
