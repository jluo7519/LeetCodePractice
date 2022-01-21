import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00140WordBreakII {

    //without memo
    public List<String> wordBreakNoMemo(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add(word);
        }
        dfsNoMemo(s, dict, 0, new StringBuilder(), result);
        return result;
    }
    private void dfsNoMemo(String s, Set<String> dict, int index, StringBuilder sb, List<String> result) {
        int len = s.length();
        if (index == len) {
            sb.setLength(sb.length() - 1);
            result.add(sb.toString());
            return;
        }

        for (int i = index + 1; i <= len; i++) {
            String substr = s.substring(index, i);
            if (dict.contains(substr)) {
                int sbLen = sb.length();
                sb.append(substr + " ");
                dfsNoMemo(s, dict, i, sb, result);
                sb.setLength(sbLen);
            }
        }
    }
}
