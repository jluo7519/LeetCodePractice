import java.util.Arrays;

public class LC00464CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger <= 0 || desiredTotal < 0) throw new IllegalArgumentException();
        if (desiredTotal == 0) return true;
        //edge case even if we sum all the possible choosable numbers it's still smaller than target
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
/*        boolean[] chosen = new boolean[maxChoosableInteger + 1];//多开一位，0不能选
        return dfs(maxChoosableInteger, desiredTotal, chosen);
*/
        //int chosen = (1 << maxChoosableInteger) - 1;
        Boolean[] mem = new Boolean[1 << maxChoosableInteger];

        boolean ret = dfsPruning(maxChoosableInteger, desiredTotal, 0, mem);
        return ret;
    }

    private boolean dfsPruning(int maxChoosable, int sum, int chosen, Boolean[] mem) {
        //base case other player has reached desired total
        if (sum <= 0) {
            mem[chosen] = false;
            return false;
        }
        if (mem[chosen] != null) {
            return mem[chosen];
        }
        for (int i = 1; i <= maxChoosable; i++) {
            int mask = 1 << (i - 1);
            //i unavailable
            if ((mask & chosen) != 0) {
                continue;
            }
            //i available
            //set corresponding digit to used
            //必须用一个新的变量，否则backtracking回来会出问题。除非下面set back
            int newChosen = chosen + mask; //equivalent to | in this case
            //chosen = chosen + mask;
            //如果对手return true说明这一叉对手赢了，只能继续看后面的叉有没有可能
            //只有对手return false才能就这么选
            boolean ret = dfsPruning(maxChoosable, sum - i, newChosen, mem);
            if (!ret) {
                mem[chosen] = true;
                return true;
            }
        }
        mem[chosen] = false;
        return false;
    }

    private boolean dfs(int maxChoosable, int sum, boolean[] choosen) {
        //base case
        if (sum <= 0) {
            return false;
        }

        for (int i = 1; i <= maxChoosable; i++) {
            if (choosen[i]) {
                continue;
            }
            choosen[i] = true;
            boolean ret = dfs(maxChoosable, sum - i, choosen);
            choosen[i] = false;
            //如果对手return true说明这一叉对手赢了，只能继续看后面的叉有没有可能
            //只有对手return false才能就这么选
            if (ret == false) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        LC00464CanIWin test = new LC00464CanIWin();
        System.out.println("test 1: maxChoosableInteger = 10, desiredTotal = 11, return: "
                + test.canIWin(10, 11));
        System.out.println("test 2: maxChoosableInteger = 10, desiredTotal = 0, return: "
                + test.canIWin(10, 0));
        System.out.println("test 3: maxChoosableInteger = 10, desiredTotal = 1, return: "
                + test.canIWin(10, 1));
        System.out.println("test 4: maxChoosableInteger = 10, desiredTotal = 40, return: "
                + test.canIWin(10, 40));
        System.out.println("test 5: maxChoosableInteger = 10, desiredTotal = 40, return: "
                + test.canIWin(5, 50));
    }
}
