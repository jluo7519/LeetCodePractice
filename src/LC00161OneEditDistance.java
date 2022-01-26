public class LC00161OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) return false;
        int len1 = s.length();
        int len2 = t.length();
        if (Math.abs(len1 - len2) > 1) return false;
        int len = Math.min(len1, len2);
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == t.charAt(i)) {
                continue;
            }
            //if not equal, then we try to one edit it
            //depends on the length of two strings
            if (len1 > len2) {
                //longer try delete
                return s.substring(i + 1, len1).equals(t.substring(i, len2));
            } else if (len2 > len1) {
                //shorter try insert
                return s.substring(i, len1).equals(t.substring(i + 1, len2));
            } else {
                //same length try replace
                return s.substring(i + 1, len1).equals(t.substring(i + 1, len2));
            }
        }
        //from 0 to len - 1 all matches
        return len1 != len2;
    }
    public static void main(String args[]) {
        LC00161OneEditDistance test = new LC00161OneEditDistance();
        System.out.println(test.isOneEditDistance("c", "c"));
        System.out.println(test.isOneEditDistance("c", ""));
        System.out.println(test.isOneEditDistance("c", "cccccc"));
        System.out.println(test.isOneEditDistance("", ""));
        System.out.println(test.isOneEditDistance("abcdefg", "abdefg"));
    }
}
