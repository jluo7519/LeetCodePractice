import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC00294FlipGameII {
    public boolean canWin(String currentState) {
        if (currentState == null || currentState.length() == 0) {
            throw new IllegalArgumentException();
        }
        char[] array = currentState.toCharArray();
        Map<String, Boolean> mem = new HashMap<>();
        return dfs(array, mem);
    }
    private boolean dfs(char[] array, Map<String, Boolean> mem) {
        String s = String.valueOf(array);
        Boolean mRes = mem.get(s);
        if (mRes != null) return mRes;
        //cant flip return false
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            if (array[i] == '+' && array[i + 1] == '+') {
                array[i] = '-';
                array[i + 1] = '-';
                boolean ret = dfs(array, mem);
                array[i] = '+';
                array[i + 1] = '+';
                if (!ret) {
                    mem.put(s, true);
                    return true;
                }
            }
        }
        //every attempt return opponent win || there is nothing can be flipped
        mem.put(s, false);
        return false;
    }

    public static void main(String args[]) {
        LC00294FlipGameII test = new LC00294FlipGameII();
        System.out.println("test case 1 ++++: " + test.canWin("++++"));
        System.out.println("test case 2 +: " + test.canWin("+"));
    }
}
