import java.util.*;

public class LC00140WordBreakII {
    //without memo
    public List<String> wordBreakII(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return null;
        }
        boolean[] mem = new boolean[s.length() + 1];
        Arrays.fill(mem, true);
        List<String> result = new ArrayList<>();
        dfs(s, dict, 0, new StringBuilder(), result, mem);
        return result;
    }
    private void dfs(String s, Set<String> dict, int index, StringBuilder path, List<String> result, boolean[] mem) {
        if (!mem[index]) return;
        int len = s.length();
        int pathLen = path.length();
        if (index == len) {
            path.setLength(pathLen - 1);
            result.add(path.toString());
            //path.setLength(pathLen);
            return;
        }
        int resultSize = result.size();
        for (int i = index; i < len; i ++) {
            String word = s.substring(index, i + 1); //[index, i]
            if (dict.contains(word)) {
                path.append(word).append(' ');
                dfs(s, dict, i + 1, path, result, mem);
                path.setLength(pathLen);
            }
        }
        //if size didn't increase, that means after going deep we know there is no successful cuts
        //therefore it guarantees a failure
        if (result.size() == resultSize) {
            mem[index] = false;
        }
    }
    public static void main(String args[]) {
        //test 1
        LC00140WordBreakII test = new LC00140WordBreakII();
        String s1 = "leetcode";
        Set<String> dict1 = new HashSet<>();
        dict1.add("leet");
        dict1.add("code");

        System.out.println(test.wordBreakII(s1, dict1));

        //test 2
        String s2 = "applepenapple";
        Set<String> dict2 = new HashSet<>();
        dict2.add("apple");
        dict2.add("pen");;
        System.out.println(test.wordBreakII(s2, dict2));

        String s3 = "catsandog";
        Set<String> dict3 = new HashSet<>();
        dict3.add("cats");
        dict3.add("dog");;
        dict3.add("sand");
        dict3.add("and");
        dict3.add("cat");
        System.out.println(test.wordBreakII(s3, dict3));

        //test 4
        String s4 = "catsanddog";
        Set<String> dict4 = new HashSet<>();
        dict4.add("cats");
        dict4.add("dog");;
        dict4.add("sand");
        dict4.add("and");
        dict4.add("cat");
        System.out.println(test.wordBreakII(s4, dict4));
    }
}
