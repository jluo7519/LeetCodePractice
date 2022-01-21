import java.util.ArrayList;
import java.util.List;

public class LC00093RestoreIpAddresses {
    public static void main(String args[]) {
        LC00093RestoreIpAddresses test = new LC00093RestoreIpAddresses();
        String s1 = "25525511135";
        System.out.println(test.restoreIpAddresses(s1).toString());
        String s2 = "0000";
        System.out.println(test.restoreIpAddresses(s2).toString());
        String s3 = "101023";
        System.out.println(test.restoreIpAddresses(s3).toString());
    }

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4) {//throw new IllegalArgumentException("not valid input");
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        dfs(result, new StringBuilder(), 0, 0, s);
        return result;
    }
    //TODO: 看下可以改成每次往后走1到3位添在里面
    private void dfs(List<String> result, StringBuilder path, int index, int nPieces, String s) {
        int len = s.length();
        //success
        if (index == len && nPieces == 4) {
            path.setLength(path.length() - 1);
            result.add(path.toString());
            return;
        }
        //failure 需要吗？
//        if (index > len || nPieces > 4 ) {
//            return;
//        }
        //visited? 不需要因为跑在树上
        //分叉: 加点与不加点
        int val = 0;
        for (int i = index; i < len; i++) {
            val = val * 10 + (s.charAt(i) - '0');
            if (val > 255) break;//if greater than 255, shouldn't keep doing or next loop will still be greather than 255
            int pathLen = path.length();
            path.append(val + ".");
            dfs(result, path, i + 1, nPieces + 1, s);
            path.setLength(pathLen);
            if (val == 0) break; //if value is zero, need to prevent leading zero
        }
    }
}
