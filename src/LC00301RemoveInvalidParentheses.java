import java.util.*;

public class LC00301RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) throw new IllegalArgumentException();
        //Set<String> result = new HashSet<>();
        List<String> result = new ArrayList<>();
        int[] rm = findRemove(s);
        int rmLeft = rm[0];
        int rmRight = rm[1];
        dfsWithDedup(result, new StringBuilder(), 0, rmLeft, rmRight, s, 0);
        //return new ArrayList<>(result);
        return result;
    }
    //S2
    private void dfsWithDedup(List<String> result, StringBuilder sb, int index, int rmLeft, int rmRight, String s, int delta) {
        int len = s.length();
        //success
        if (index == len && rmLeft == 0 && rmRight == 0 && delta == 0) {
            result.add(sb.toString());
        }
        //failure
        if (index >= len || rmLeft < 0 || rmRight < 0 || delta < 0) {
            return;
        }
        //no visited it runs on a tree
        char c = s.charAt(index);
        int sbLen = sb.length();
        //3 cases, either ( or ) or neither
        if (c != '(' && c != ')') {
            //need to keep, can only not to remove
            sb.append(c);
            dfsWithDedup(result, sb, index + 1, rmLeft, rmRight, s, delta);
            sb.setLength(sbLen);
        } else if (c == '(') {
            //either remove or not remove
            //1. remove
            dfsWithDedup(result, sb, index + 1, rmLeft - 1, rmRight, s, delta);
            //2. keep
            //if there are k consective (, and we keep them all
            int cntLeft = 1;
            while (index + cntLeft < len && s.charAt(index + cntLeft) == '(') {
                cntLeft++;
            }
            sb.append(s.substring(index, index + cntLeft));
            dfsWithDedup(result, sb, index + cntLeft, rmLeft, rmRight, s,delta + cntLeft);
            sb.setLength(sbLen);
        } else { //)
            //1. remove
            dfsWithDedup(result, sb, index + 1, rmLeft, rmRight - 1, s, delta);
            //2. not remove
            int cntRight = 1;
            while (index + cntRight < len && s.charAt(index + cntRight) == ')') {
                cntRight++;
            }
            sb.append(s.substring(index, index + cntRight));
            dfsWithDedup(result, sb, index + cntRight, rmLeft, rmRight, s,delta - cntRight);
            sb.setLength(sbLen);
        }
    }
    //S1
    private void dfsSetDedup(Set<String> result, StringBuilder path, int index, int rmLeft, int rmRight, String s, int delta) {
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
            dfsSetDedup(result, path, index + 1, rmLeft, rmRight, s, delta);
            path.setLength(pathLen);
        }
        if (c == '(') {
            //remove
            dfsSetDedup(result, path, index + 1, rmLeft - 1, rmRight, s, delta);
            //not remove
            path.append('(');
            dfsSetDedup(result, path, index + 1, rmLeft, rmRight, s, delta + 1);
            path.setLength(pathLen);
        } else if (c == ')') {
            //not remove
            dfsSetDedup(result, path, index + 1, rmLeft, rmRight - 1, s, delta);
            //not remove
            path.append(')');
            dfsSetDedup(result, path, index + 1, rmLeft, rmRight, s, delta - 1);
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
    public static void main(String args[]) {
        LC00301RemoveInvalidParentheses test = new LC00301RemoveInvalidParentheses();
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
}
