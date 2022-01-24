public class LC00010RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) throw new IllegalArgumentException();
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        //return dfs(s, 0, p, 0, memo);
        return dfsNoPruning(s, 0, p, 0);
    }

    private boolean dfs(String s, int indexS, String p, int indexP, Boolean[][] memo) {
        int pLen = p.length();
        int sLen = s.length();
        //base case
        //结束情况分为三种
        //1. indexS == s.length() && indexP == p.length()  ==> success
        //2. indexS == s.length() && indexP != p.length()  ==> not sure since we don't know the next indexP is * to match ""
        //3. indexS != s.length() && indexP == p.length()  ==> failure since we processed * with its preceding char
        if (memo[indexS][indexP] != null) return memo[indexS][indexP];
        if (indexP == pLen) {
            return indexS == sLen;
        }
        //non * patterns
        //如果indexP走到最后一位了就不是a*结构， 或者不是最后一位但是下一位不是*也就不是a*结构
        if (indexP + 1 == pLen || p.charAt(indexP + 1) != '*') {
            if (indexS < sLen && ((s.charAt(indexS) == p.charAt(indexP)) || p.charAt(indexP) == '.')) {
                memo[indexS][indexP] = dfs(s, indexS + 1, p, indexP + 1, memo);
                //return memo[indexS][indexP];
            } else {
                memo[indexS][indexP] = false;
                //return false;
            }
        } else {//a* patterns
            int i = indexS - 1;
            while (i < sLen && (i < indexS || p.charAt(indexP) == '.' || p.charAt(indexP) == s.charAt(i))) {
                if (dfs(s, i + 1, p, indexP + 2, memo)) {//+2因为consume掉两位a*
                    memo[indexS][indexP] = true;
                    return true;
                }
                i++;
            }
            memo[indexS][indexP] = false;
        }
        return memo[indexS][indexP];
    }

    private boolean dfsNoPruning(String s, int indexS, String p, int indexP) {
        int sLen = s.length(), pLen = p.length();
        if (indexP == pLen) return indexS == sLen;
        //non *
        if (indexP == pLen - 1 || p.charAt(indexP + 1) != '*') {
            if (indexS < sLen && (s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '.')) {
                return dfsNoPruning(s, indexS + 1, p, indexP + 1);
            } else {
                return false;
            }
        } else {//a* pattern
            int i = indexS - 1;
            while (i < sLen && (i < indexS || p.charAt(indexP) == s.charAt(i) || p.charAt(indexP) == '.')) {
                if (dfsNoPruning(s, i + 1, p, indexP + 2)) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }

    public static void main(String args[]) {
        LC00010RegularExpressionMatching test = new LC00010RegularExpressionMatching();
        String s1 = "aa", p1 = "a";
        System.out.println("Test 1: " + test.isMatch(s1, p1));
        String s2 = "aa", p2 = "a*";
        System.out.println("Test 2: " + test.isMatch(s2, p2));
        String s3 = "ab", p3 = ".*";
        System.out.println("Test 3: " + test.isMatch(s3, p3));
        String s4 = "a", p4 = ".*..a*";
        System.out.println("Test 3: " + test.isMatch(s4, p4));
        String s5 = "a", p5 = "ab*";
        System.out.println("Test 3: " + test.isMatch(s5, p5));
    }
}
