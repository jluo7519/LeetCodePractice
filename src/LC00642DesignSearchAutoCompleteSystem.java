import java.util.*;

public class LC00642DesignSearchAutoCompleteSystem {
    private AutoCompleteNode root;
    private Map<String, Integer> countBook;
    private AutoCompleteNode cur;
    private StringBuilder path;

    public LC00642DesignSearchAutoCompleteSystem(String[] sentences, int[] times) {
        root = new AutoCompleteNode('\0');
        cur = root;
        countBook = new HashMap<>();
        path = new StringBuilder();
        int len = sentences.length;
        for (int i = 0; i < len; i++) { // O(n * k)
            String s = sentences[i];
            int count = times[i];
            addSentence(s, count); // O(k)
        }
    }

    public List<String> input(char c) { // O(k)
        // #
        if (c == '#') {
            String sentence = path.toString();
            int count = countBook.getOrDefault(sentence, 0) + 1;
            addSentence(sentence, count); // O(k)
            path = new StringBuilder();
            cur = root;
            return new ArrayList<>();
        }
        // non #
        path.append(c);
        if (cur == null) {
            return new ArrayList<>();
        }
        cur = cur.nexts[getIdx(c)];
        if (cur == null) {
            return new ArrayList<>();
        }
        return cur.getTop3();
    }

    private void addSentence(String sentence, int count) { // O(k)
        AutoCompleteNode cur = root;
        int len = sentence.length();
        for (int i = 0; i < len; i++) {
            int idx = getIdx(sentence.charAt(i));
            if (cur.nexts[idx] == null) {
                cur.nexts[idx] = new AutoCompleteNode(sentence.charAt(i));
            }
            cur = cur.nexts[idx];
            cur.updateTop3(sentence, count);
        }
        countBook.put(sentence, count);
        cur.isSentence = true;
    }

    private int getIdx(char c) {
        return c == ' ' ? 26 : c - 'a';
    }
}

class AutoCompleteNode {
    boolean isSentence;
    AutoCompleteNode[] nexts;
    char c;
    List<StrCount> top3;
    public AutoCompleteNode(char c) {
        this.c = c;
        this.nexts = new AutoCompleteNode[27];
        top3 = new ArrayList<>();
        this.isSentence = false;
    }
    public List<String> getTop3() { // O(1)
        List<String> res = new ArrayList<>();
        for (StrCount p : top3) {
            res.add(p.sentence);
        }
        return res;
    }
    public void updateTop3(String sentence, int count) { // O(1)
        insertOrUpdateTop3(sentence, count);
        Collections.sort(top3, (p1, p2) -> {
            if (p1.count == p2.count) {
                return p1.sentence.compareTo(p2.sentence);
            }
            return p2.count - p1.count;
        });
        top3 = top3.subList(0, Math.min(3, top3.size()));
    }
    public void insertOrUpdateTop3(String sentence, int count) { // O(1)
        for (StrCount p : top3) {
            if (p.sentence.equals(sentence)) {
                p.count = count;
                return;
            }
        }
        top3.add(new StrCount(sentence, count));
    }
}

class StrCount {
    String sentence;
    int count;
    public StrCount(String sentence, int count) {
        this.sentence = sentence;
        this.count = count;
    }
}