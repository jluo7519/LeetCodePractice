import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC00017LetterCombinationsOfPhoneNumber {
    public List<String> letterCombinations(String digits) {
        if (digits == null) throw new IllegalArgumentException();
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) return result;
        List<List<Character>> map = new ArrayList<>();
        map.add(null); //0
        map.add(null); //1
        map.add(Arrays.asList('a','b','c')); //2
        map.add(Arrays.asList('d','e','f')); //3
        map.add(Arrays.asList('g','h','i')); //4
        map.add(Arrays.asList('j','k','l')); //5
        map.add(Arrays.asList('m','n','o')); //6
        map.add(Arrays.asList('p','q','r','s')); //7
        map.add(Arrays.asList('t','u','v')); //8
        map.add(Arrays.asList('w','x','y','z')); //9

        dfs(digits, 0, map, result, new StringBuilder());
        return result;
    }
    // 一共n层，每层3~4个叉
// O(4^n * n)时间
// O(n)空间
    private void dfs(String digits, int idx, List<List<Character>> map, List<String> res, StringBuilder path) {
        int len = digits.length();
        if (idx == len) {
            res.add(path.toString());
            return;
        }
        int num = digits.charAt(idx) - '0';
        List<Character> chars = map.get(num);
        int pathLen = path.length();
        for (int i = 0; i < chars.size(); i++) {
            path.append(chars.get(i));
            dfs(digits, idx + 1, map, res, path);
            path.setLength(pathLen);
        }
    }
}
