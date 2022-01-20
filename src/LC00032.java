public class LC00032 {
    public static void main(String args[]) {
        String s = "(()";
        LC00032 test = new LC00032();
        //System.out.println(test.longestValidParentheses(s));

    }
/*    public int longestValidParentheses(String s) {
        int len = s.length();
        int max = 0;
        int[] m = new int[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                m[i] = 0;
            } else if (s.charAt(i) == '(') {
                int j = i + m[i + 1] + 1;

            }

        }
    }*/
}
