import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC00301FollowUpReturnAnyValidWithMinRemoval {
    //Any Valid Parentheses Sequence with min removal
    public String anyWithMinRemoval(String s) {
        Set<Integer> rmIndex = new HashSet<>();
        int len = s.length();
        //first pass from left to right to find ) to be removed
        int rmL = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c != '(' && c != ')') {
                continue;
            }
            if (c == '(') {
                rmL++;
            } else if (rmL > 0) {
                rmL--;
            } else {
                //mark to be removed
                rmIndex.add(i);
            }
        }
        //second pass from right to left to find ( to be remved
        int rmR = 0;
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != '(' && c != ')') {
                continue;
            }
            if (c == ')') {
                rmR++;
            } else if (rmR > 0) {
                rmR--;
            } else {
                //mark index to be removed
                rmIndex.add(i);
            }
        }
        //third pass to remove chars at marked index
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (!rmIndex.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public static void main(String args[]) {
        LC00301FollowUpReturnAnyValidWithMinRemoval test = new LC00301FollowUpReturnAnyValidWithMinRemoval();
        String s1 = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";
        String s4 = "(j))(";

        System.out.println("Test Case 1: ");
        System.out.println(test.anyWithMinRemoval(s1));

        System.out.println("Test Case 2: ");
        System.out.println(test.anyWithMinRemoval(s2));

        System.out.println("Test Case 3: ");
        System.out.println(test.anyWithMinRemoval(s3));

        System.out.println("Test Case 4: ");
        System.out.println(test.anyWithMinRemoval(s4));
    }
}
