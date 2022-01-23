import java.util.HashSet;
import java.util.Set;

public class WordBreakNoReuse {
    public static void main(String args[]) {
        WordBreakNoReuse test = new WordBreakNoReuse();
        //test 1
        String s1 = "leetcode";
        Set<String> dict1 = new HashSet<>();
        dict1.add("leet");
        dict1.add("code");
        System.out.println(test.wordBreak(s1, dict1));

        //test 2
        String s2 = "applepenapple";
        Set<String> dict2 = new HashSet<>();
        dict2.add("apple");
        dict2.add("pen");;
        System.out.println(test.wordBreak(s2, dict2));

        String s3 = "catsandog";
        Set<String> dict3 = new HashSet<>();
        dict3.add("cats");
        dict3.add("dog");;
        dict3.add("sand");
        dict3.add("and");
        dict3.add("cat");
        System.out.println(test.wordBreak(s3, dict3));

        //test 4
        String s4 = "catsanddog";
        Set<String> dict4 = new HashSet<>();
        dict4.add("cats");
        dict4.add("dog");;
        dict4.add("sand");
        dict4.add("and");
        dict4.add("cat");
        System.out.println(test.wordBreak(s4, dict4));
    }
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) throw new IllegalArgumentException();
        return dfs(s, 0, dict);
    }
    private boolean dfs(String s, int index, Set<String> dict) {
        int len = s.length();
        if (index == len) {
            return true;
        }
        //visited
        //branching
        for (int i = index; i < len; i++) {
            String substr = s.substring(index, i + 1);
            if (dict.remove(substr)) {
                if (dfs(s, i + 1, dict)) {
                    return true;
                }
                dict.add(substr);
            }
        }
        return false;
    }
}
