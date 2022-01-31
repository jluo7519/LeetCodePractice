public class LC00424LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        if (s == null || k < 0) throw new IllegalArgumentException();
        int len = s.length();
        if (len == 0) return 0;
        int[] count = new int[26];
        int left = 0;
        int longest = 0;
        char mostFrequent = s.charAt(0);
        for (int right = 0; right < len; right++) {
            //valid expand
            char ch = s.charAt(right);
            count[ch - 'A']++;
            mostFrequent = count[ch - 'A'] > count[mostFrequent - 'A'] ? ch : mostFrequent;
            while (right - left + 1 - count[mostFrequent - 'A'] > k) {//shrink
                ch = s.charAt(left);
                count[ch - 'A']--;
                left++;
                if (ch == mostFrequent) { //need to pick a new most frequent
                    for (char c = 'A'; c <= 'Z'; c++) {
                        if (count[c - 'A'] > count[mostFrequent - 'A']) {
                            mostFrequent = c;
                        }
                    }
                }
            }
            longest = Math.max(right - left + 1, longest);
        }
        return longest;
    }
    public static void main(String args[]) {
        LC00424LongestRepeatingCharacterReplacement test = new LC00424LongestRepeatingCharacterReplacement();
        System.out.println(test.characterReplacement("ABAB", 2));
        System.out.println(test.characterReplacement("AABABBA", 1));
        System.out.println(test.characterReplacement("BAAA", 0));
    }
}
