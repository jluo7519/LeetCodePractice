import java.util.*;

public class LC00049GroupAnagrams {
    //O(n * k)
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            throw new IllegalArgumentException();
        }
        Map<String, List<String>> map = new HashMap<>();

        // O(n)
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int[] arr = new int[26];
            for (int j = 0; j < str.length(); j++) { // O(k)
                char ch = str.charAt(j);
                int idx = ch - 'a';
                arr[idx]++;
            }
            String key = Arrays.toString(arr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        // O(n)
        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
}
