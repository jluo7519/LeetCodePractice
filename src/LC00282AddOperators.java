import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC00282AddOperators {
    LC00227BasicCalculatorII calculator = new LC00227BasicCalculatorII();

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        dfs(set, new StringBuilder(), target, 0, num, 0, 0);
        //suanfage(set,num,0,0, new StringBuilder(), target);
        for (String sol : set) {
            result.add(sol);
        }
        return result;
    }
    //边DFS边算值
    private void dfs(Set<String> result, StringBuilder path, int target, int index, String num, long cur, long lastTerm) {
        int len = num.length();
        //valid base cases
        if (len == index && cur == target) {
            result.add(path.toString());
            return;
        }
        //invalid base cases
        if (len == index) {
            return;
        }
        //i的位置后面加或者不加运算符
        //加的话可以分三种
        int pathLen = path.length();
        long val = 0;
        for (int i = index; i < len; i++) {
            val = val * 10 + num.charAt(i) - '0';

            if (pathLen == 0) {//得在这里加 否则第一个都不计入了 而且不是i == 0
                //path.length() == 0的时候i不一定是0, 可能我读了2位3位但是还没有加符号没有append
                path.append(val);
                dfs(result, path, target, i + 1, num, val, val);
                path.setLength(pathLen);
                //continue;
            } else { //用else instead of continue就不会跳过如果遇到0break的情况。否则没取完0就直接出去了
                //+
                path.append("+" + val);
                dfs(result, path, target, i + 1, num, cur + val, val);
                path.setLength(pathLen);

                //-
                path.append("-" + val);
                dfs(result, path, target, i + 1, num, cur - val, -val);
                path.setLength(pathLen);

                //*
                path.append("*" + val);
                dfs(result, path, target, i + 1, num, cur - lastTerm + lastTerm * val, lastTerm * val);
                path.setLength(pathLen);

            }
            if (val == 0) break;
        }
    }
    private void suanfage(Set<String> res, String num, long last, long curVal, StringBuilder sb, int target) {
        int len = num.length();
        if (len == 0 && curVal == target) {
            res.add(sb.toString());
            return;
        }
        if (len == 0) return;

        int lenSb = sb.length();
        long val = 0;
        for (int i = 0; i < len; i++) {
            val = 10 * val + num.charAt(i) - '0';
            if (sb.length() != 0) {
                suanfage(res, num.substring(i + 1), val, curVal + val, sb.append("+").append(val), target);
                sb.setLength(lenSb);
                suanfage(res, num.substring(i + 1), -val, curVal - val, sb.append("-").append(val), target);
                sb.setLength(lenSb);
                suanfage(res, num.substring(i + 1), last * val, curVal - last + last * val,
                        sb.append("*").append(val), target);
                sb.setLength(lenSb);
            } else {
                suanfage(res, num.substring(i + 1), val, curVal + val, sb.append(val), target);
                sb.setLength(lenSb);
            }
            if (val == 0) break;
        }
    }
  /*  //借助于calculator最后evaluate
    private void dfs1(Set<String> result, StringBuilder path, int target, int index, String num) {
        int len = num.length();
        //valid base cases
        if (index == len) {
            if (calculator.addSubstractAndMultply(path.toString()) == target) {
                result.add(path.toString());
            }
            return;
        }
        //invalid base cases

        //i的位置后面加或者不加运算符
        //加的话可以分三种
        int pathLen = path.length();
        int val = 0;
        for (int i = index; i < len; i++) {
            //if (i == 0) continue; //0的位置不加
            char c = num.charAt(i);
            val = val * 10 + c - '0';
            if (i == 0) {
                path.append(val);
                dfs1(result, path, target, i + 1, num);
                path.setLength(pathLen);
                continue;
            }
            //+
            path.append("+" + val);
            dfs1(result, path, target, i + 1, num);
            path.setLength(pathLen);

            //-
            path.append("-" + val);
            dfs1(result, path, target, i + 1, num);
            path.setLength(pathLen);

            //*
            path.append("*" + val);
            dfs1(result, path, target, i + 1, num);
            path.setLength(pathLen);

            if (val == 0) break;
        }
    }*/
  public static void main(String args[]) {
      LC00282AddOperators test = new LC00282AddOperators();
      System.out.println(test.addOperators("123", 6));
      System.out.println(test.addOperators("232", 8));
      System.out.println(test.addOperators("3456237490", 9191));
      System.out.println(test.addOperators("105", 5));
      System.out.println(test.addOperators("00", 0));
      System.out.println(test.addOperators("000", 0));
      System.out.println(test.addOperators("2147483648", -2147483648));
  }
}
