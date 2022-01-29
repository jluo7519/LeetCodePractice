import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LC00159LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) throw new IllegalArgumentException();
        int len = s.length();
        int slow = 0;
        int longest = 0;
        char ch1 = '\0';
        char ch2 = '\0';
        int idx1 = -1; //这里给-1的原因是每次赋值完都要把移动到小的index + 1的地方，相当于给0就会跳过0
        int idx2 = -1; //我们要保证第一次赋值完成以后slow是在0的位置
        for (int fast = 0; fast < len; fast++) {
            char ch = s.charAt(fast);
            //if valid extend: valid有两种情况，一种是没到2个distinct，还能多放一个;
            //还有一种是已经有了两个distinct, 但是新来的ch是其中的一个
            if (ch1 == ch) {
                idx1 = fast;
            } else if (ch2 == ch) {
                idx2 = fast;
            } else {//if invalid shrink
                if (idx1 < idx2) {
                    slow = idx1 + 1;
                    ch1 = ch;
                    idx1 = fast;
                } else {
                    slow = idx2 + 1;
                    ch2 = ch;
                    idx2 = fast;
                }
            }
            //最后一定是valid
            longest = Math.max(fast - slow + 1, longest);
        }

        return longest;
    }
/*    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) throw new IllegalArgumentException();
        int len = s.length();
        int slow = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int fast = 0; fast < len; fast++) {
            char ch = s.charAt(fast);
            //if valid extend: valid有两种情况，一种是没到2个distinct，还能多放一个;
            //还有一种是已经有了两个distinct, 但是新来的ch是其中的一个
            if (map.containsKey(ch) || map.size() < 2) {
                map.put(ch, fast);
            } else {//if invalid shrink
                int minIdx = Integer.MAX_VALUE;
                char minCh = '\0';
                for (Character key : map.keySet()) {
                    if (map.get(key) < minIdx) {
                        minIdx = map.get(key);
                        minCh = key;
                    }
                }
                slow = minIdx + 1;
                map.remove(minCh);
                map.put(ch, fast);
            }
            longest = Math.max(fast - slow + 1, longest);
        }
        return longest;
    }*/
/*    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) throw new IllegalArgumentException();
        int len = s.length();
        int slow = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int fast = 0; fast < len; fast++) {
            char ch = s.charAt(fast);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > 2) {
                char sCh = s.charAt(slow);
                map.put(sCh, map.getOrDefault(sCh, 0) - 1);
                if (map.get(sCh) == 0) {
                    map.remove(sCh);
                }
                slow++;
            }
            longest = Math.max(longest, fast - slow + 1);
        }
        return longest;
    }*/
    public static void main(String args[]) {
        LC00159LongestSubstringWithAtMostTwoDistinctCharacters test = new LC00159LongestSubstringWithAtMostTwoDistinctCharacters();
        System.out.println(test.lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(test.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}
