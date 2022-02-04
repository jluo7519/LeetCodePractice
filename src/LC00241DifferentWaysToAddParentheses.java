import java.util.ArrayList;
import java.util.List;

public class LC00241DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        if (expression == null || expression.length() == 0) {
            throw new IllegalArgumentException();
        }
        return dfs(expression, 0, expression.length() - 1);
    }
    private List<Integer> dfs(String expression, int start, int end) {
        List<Integer> result = new ArrayList<>();
        boolean isNum = true;
        for (int i = start; i <= end; i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') { //(left) operator (right)
                isNum = false;
                List<Integer> lefts = dfs(expression, start, i - 1);
                List<Integer> rights = dfs(expression, i + 1, end);

                for (int left : lefts) {
                    for (int right : rights) {
                        result.add(c == '+' ? left + right : c == '-' ? left - right : left * right);
                    }
                }
            }
        }
        if (isNum) { // base case
            result.add(Integer.valueOf(expression.substring(start, end + 1)));
        }
        return result;
    }

    public static void main(String args[]) {
        LC00241DifferentWaysToAddParentheses test = new LC00241DifferentWaysToAddParentheses();
        List<Integer> res1 = test.diffWaysToCompute("2-1-1");
        for (int num : res1) {
            System.out.print(" " + num);
        }
        List<Integer> res2 = test.diffWaysToCompute("2*3-4*5");
        for (int num : res2) {
            System.out.print(" " + num);
        }
    }

}
