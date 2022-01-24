import java.util.ArrayList;
import java.util.List;

public class LC00320GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        if (word == null || word.length() == 0) throw new IllegalArgumentException();
        List<String> result = new ArrayList<>();
        dfs(result, new StringBuilder(), 0, word, 0);
        return result;
    }
    private void dfs(List<String> result, StringBuilder path, int index, String word, int count) {
        int len = word.length();
        int pathLen = path.length();
        //base case success
        if (index == len) {
            if (count != 0) {
                path.append(count);
            }
            result.add(path.toString());
            path.setLength(pathLen);
            return;
        }
        //no failure
        //no visited
        //1. compress current index
        dfs(result, path, index + 1, word, count + 1);

        //2. not compress
        //depending on current count
        if (count > 0) { //有count先append count
            path.append(count);
        }
        path.append(word.charAt(index));
        dfs(result, path, index + 1, word, 0);
        path.setLength(pathLen);

        //based on whether count is 0, two branches for each
/*        if (count == 0) {
            //1. not compress
            //大胆append
            path.append(word.charAt(index));
            dfs(result, path, index + 1, word, 0);
            path.setLength(pathLen);
            //2. compress
            //不append但是count++
            dfs(result, path, index + 1, word, count + 1);

        } else { //count not zero
            //1. not compress
            //要先把count append上去再append字母
            path.append(count).append(word.charAt(index));
            dfs(result, path, index + 1, word, 0);
            path.setLength(pathLen);

            //2. compress
            //count计数，啥也不添加进新path
            dfs(result, path, index + 1, word, count + 1);
        }*/
    }
    public static void main(String args[]) {
        LC00320GeneralizedAbbreviation test = new LC00320GeneralizedAbbreviation();
        String s1 = "word";
        System.out.println(test.generateAbbreviations(s1).toString());
    }
}
