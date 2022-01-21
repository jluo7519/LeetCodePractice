public class LC00227BasicCalculatorII {
    public static void main(String args[]) {
        LC00227BasicCalculatorII test = new LC00227BasicCalculatorII();
        //System.out.println(test.addAndSubstract("3 - 1+2 + 2 +2 +1+1"));
        System.out.println(test.calculate("3 + 5*2 + 2 *2 +1+1"));
        System.out.println(test.calculate("3+5 / 2"));
        System.out.println(test.calculate("3/2"));
        System.out.println(test.calculate("3+2*2"));
    }
    public int calculate(String s) {
        int sLen = s.length();
        int result = 0;
        int lastTerm = 0;
        char operant = '+';
        int curVal = 0;
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curVal = curVal * 10 + c - '0';
            }
            if (i == sLen - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (operant == '+' || operant == '-') {
                    lastTerm = operant == '+' ? curVal : -curVal;
                    result += lastTerm;
                } else if (operant == '*') {
                    result -= lastTerm;
                    lastTerm = lastTerm * curVal;
                    result += lastTerm;
                } else if (operant == '/') {
                    result -= lastTerm;
                    lastTerm = lastTerm / curVal;
                    result += lastTerm;
                }
                operant = c;
                curVal = 0;
            }
        }
        return result;
    }
    public int addAndSubstract(String s) {
        int sLen = s.length();
        int result = 0;
        char operant = '+';
        int curVal = 0;
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curVal = curVal * 10 + c - '0';
            }
//            if (c == ' ') {
//                continue;
//            } else
            if (i == sLen - 1 || c == '+' || c == '-') {
                result = (operant == '+') ? result + curVal : result - curVal;
                if (c == '+' || c == '-') {
                    operant = c;
                    curVal = 0;
                }
            }
        }
        return result;
    }

    public int addSubstractAndMultply(String s) {
        int sLen = s.length();
        int result = 0;
        int lastTerm = 0;
        char operant = '+';
        int curVal = 0;
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curVal = curVal * 10 + c - '0';
            }
            if (i == sLen - 1 || c == '+' || c == '-' || c == '*') {
                if (operant == '+' || operant == '-') {
                    lastTerm = operant == '+' ? curVal : -curVal;
                    result += lastTerm;
                } else if (operant == '*') {
                    result -= lastTerm;
                    lastTerm = lastTerm * curVal;
                    result += lastTerm;
                }
                operant = c;
                curVal = 0;
            }
        }
        return result;
    }
}
//3 + 1 * 2 + 2 + 2 + 1 + 1
//          i