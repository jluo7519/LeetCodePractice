import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC00187RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null) throw new IllegalArgumentException();
        int len = s.length();
        Map<Integer, Boolean> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        int window = 0;
        for (int i = 0; i < len; i++) {
            // 1. 左移两位
            window <<= 2;
            // 2. 出界两位reset成0
            window &= 0xfffff;
            // 3. 最右边两位0set成新来的char对应bit表示
            window ^= toBit(s.charAt(i));
            if (i < 9) continue;
            Boolean val = map.get(window);
            if (val == null) {
                map.put(window, false);
            } else if (val == false) {
                map.put(window, true);
                res.add(s.substring(i - 9, i + 1));
            } // else do nothing
        }
        return res;
    }
    private int toBit(char c) {
        switch(c) {
            case 'A' : return 0;
            case 'C' : return 1;
            case 'G' : return 2;
            case 'T' : return 3;
            default: throw new IllegalArgumentException();
        }
    }
    public List<String> findRepeatedDnaSequencesBruteForce(String s) {
        if (s == null) throw new IllegalArgumentException();
        Map<String, Boolean> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (i < 9) {
                continue;
            }
            String substr = s.substring(i - 9, i + 1);
            Boolean status = map.get(substr);
            if (status == null) {
                map.put(substr, false);
            } else if (status == false) {
                res.add(substr);
                map.put(substr, true);
            }
        }
        return res;
    }
}
