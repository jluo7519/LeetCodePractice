import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import org.junit.runner.JUnitCore;

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
    //Follow Up: if board length <= 32
    public boolean canWinBits(String currentState) {
        if (currentState == null || currentState.length() == 0) {
            throw new IllegalArgumentException();
        }
        if (currentState.length() > 32) {
            throw new IllegalArgumentException("board exceeded length 32 ");
        }
        //initialize board to int
        int board = 0;
        int len = currentState.length();
        //其实正着反着没区别 镜像对称
        for (int i = 0; i < len; i++) {
            if (currentState.charAt(i) == '-') {
                board |= (1 << i);
            }
        }
        //int mapSize = (1 << len);
        int mapSize = (int)Math.pow(2, len);
        Boolean[] mem = new Boolean[mapSize];
        return dfsBits(board, len, mem);
    }
    private boolean dfsBits(int board, int len, Boolean[] mem) {
    //use 0 to represent +; use 1 to represent -
        if (mem[board] != null) return mem[board];
        for (int i = 0; i < len - 1; i++) {
            int mask = (1 << (i + 1)) + (1 << i);
            //if none of i and i+1 are +, we can flip
            if ((mask & board) == 0) {//找到连续两位是+, 试着翻
                //对方不一定赢，就这么下
                if (!dfsBits(board | mask, len, mem)) {
                    mem[board] = true;
                    return true;
                }
            }
        }
        //after trying all digits either we can't flip or opponent guarantees win in all cases, return false
        mem[board] = false;
        return false;
    }

    public static void main(String args[]) {
        //LC00294FlipGameII test = new LC00294FlipGameII();
        //System.out.println("test case 1 ++++: " + test.canWinBits("++++"));
        //System.out.println("test case 2 +: " + test.canWinBits("+"));
        JUnitCore.main("LC00294FlipGameII");
    }
    @Test
    public void jUnit() {
        LC00294FlipGameII test = new LC00294FlipGameII();
        Assert.assertEquals(test.canWin("+---+++++----+--"), test.canWinBits("+---+++++----+--"));
        Assert.assertEquals(test.canWin("++++++++----+--"), test.canWinBits("++++++++----+--"));
        Assert.assertEquals(test.canWin("+---+++------"), test.canWinBits("+---+++------"));
        Assert.assertEquals(test.canWin("+-+-+-+-+--++-"), test.canWinBits("+-+-+-+-+--++-"));
    }
}
