import java.util.*;

public class LC00301 {
    public static void main(String args[]) {
        LC00301 test = new LC00301();
        String s1 = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";
        System.out.println("Test Case 1: ");
        System.out.println(Arrays.toString(test.findRemove(s1)));
        System.out.println(test.removeInvalidParentheses(s1));
        System.out.println("Test Case 2: ");
        System.out.println(Arrays.toString(test.findRemove(s2)));
        System.out.println(test.removeInvalidParentheses(s2));
        System.out.println("Test Case 3: ");
        System.out.println(Arrays.toString(test.findRemove(s3)));
        System.out.println(test.removeInvalidParentheses(s3));
        System.out.println("Test Case 3: ");
        String s4 = "(j))(";
        System.out.println(Arrays.toString(test.findRemove(s4)));
        System.out.println(test.removeInvalidParentheses(s4));

    }
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) throw new IllegalArgumentException();
        Set<String> result = new HashSet<>();
        int[] rm = findRemove(s);
        int rmLeft = rm[0];
        int rmRight = rm[1];
        dfs(result, new StringBuilder(), 0, rmLeft, rmRight, s, 0);
        return new ArrayList<>(result);
    }
    private void dfs(Set<String> result, StringBuilder path, int index, int rmLeft, int rmRight, String s, int delta) {
        int len = s.length();
        //success
        if (index == len && rmLeft == 0 && rmRight == 0 && delta == 0) {
            result.add(path.toString());
            return;
        }
        //failure
        if (index >= len || delta < 0 || rmLeft < 0 || rmRight < 0) {
            return;
        }
        char c = s.charAt(index);
        int pathLen = path.length();
        //other chars
        if (c != '(' && c != ')') {
            path.append(c);
            dfs(result, path, index + 1, rmLeft, rmRight, s, delta);
            path.setLength(pathLen);
        }
        if (c == '(') {
            if (rmLeft > 0) {
                dfs(result, path, index + 1, rmLeft - 1, rmRight, s, delta);
            }
            //not remove
            path.append('(');
            dfs(result, path, index + 1, rmLeft, rmRight, s, delta + 1);
            path.setLength(pathLen);
        } else if (c == ')') {
            if (rmRight > 0) {
                dfs(result, path, index + 1, rmLeft, rmRight - 1, s, delta);
            }
            //not remove
            path.append(')');
            dfs(result, path, index + 1, rmLeft, rmRight, s, delta - 1);
            path.setLength(pathLen);
        }
    }
    private int[] findRemove(String s) {
        int left = 0;
        int right = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')' && left == 0) {
                right++;
            } else if (c == ')') {
                left--;
            }
        }
        return new int[] {left, right};
    }
}
