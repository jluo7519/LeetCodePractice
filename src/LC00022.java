import java.util.ArrayList;
import java.util.List;

public class LC00022 {
    public static void main(String args[]) {
        LC00022 test = new LC00022();
        System.out.println(test.generateParenthesis(3).toString());
    }

    public List<String> generateParenthesis(int n) {
        if (n <= 0) return null;
        List<String> result = new ArrayList<>();
        dfs(n, 0, 0, 0, new StringBuilder(), result);
        return result;
    }
    private void dfs(int n, int index, int l, int r, StringBuilder sb, List<String> result) {
        //success
        if (index == 2 * n) {
            result.add(sb.toString());
            return;
        }
        //invalid cases
        if (r > l || l > n) {
            return;
        }
        //(
        if (l < n) {
            sb.append('(');
            dfs(n, index + 1, l + 1, r, sb, result);
            sb.setLength(sb.length() - 1);
        }
        //)
        if (r < l) {
            sb.append(')');
            dfs(n, index + 1, l, r + 1, sb, result);
            sb.setLength(sb.length() - 1);
        }
    }
}
