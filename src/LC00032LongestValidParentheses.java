import java.util.Stack;

public class LC00032LongestValidParentheses {
    public static void main(String args[]) {
        String s = "()(()";
        LC00032LongestValidParentheses test = new LC00032LongestValidParentheses();
        System.out.println(test.longestValidParentheses1(s));

    }
    public int longestValidParentheses1(String s) { //two pass, left to right, right to left
        int len = s.length();
        int l = 0;
        int r = 0;
        int globalMax = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                globalMax = Math.max(l * 2, globalMax);
            } else if (r > l) {
                l = 0;
                r = 0;
            }
        }
        l = 0;
        r = 0;
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                globalMax = Math.max(l * 2, globalMax);
            } else if (l > r) {
                l = 0;
                r = 0;
            }
        }
        return globalMax;
    }
    //stack
    public int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int len = s.length();
        int globalMax = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    globalMax = Math.max(globalMax, i - stack.peek());
                }
            }
        }
        return globalMax;
    }
}
