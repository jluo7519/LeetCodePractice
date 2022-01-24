import java.util.*;

public class TwoCups {
    //Q: given two cups of different size, can you measure n units of water? return min steps, if impossible return -1
    public int twoCups(int big, int small, int target) {
        if (target > big + small) return -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        Set<String> visited=  new HashSet<>();
        visited.add(0 + "#" + 0);
        int minSteps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                List<int[]> nexts = convert(cur, big, small);
                for (int[] next : nexts) {
                    //这步如果面试需要clarify怎么样算出结果，是在一个杯子里就算 还是可以装在两个杯子也算
                    if (next[0] == target || next[1] == target || next[0] + next[1] == target) {
                        return minSteps + 1;
                    }
                    if (visited.add(next[0] + "#" + next[1])) {
                        queue.offer(next);
                    }
                }
            }
            minSteps++;
        }
        return -1;
    }
    private List<int[]> convert(int[] cur, int one, int two) {
        List<int[]> nexts = new ArrayList<>();
        //1. fill cup one
        nexts.add(new int[] {one, cur[1]});
        //2. dump cup one
        nexts.add(new int[] {0, cur[1]});
        //3. transfer from one to two
        if (cur[1] == two) { //cup two is full倒不进不倒了
            //nexts.add(new int[] {cur[0], cur[1]});
        } else if (cur[0] + cur[1] < two) { //one can't fill two'
            nexts.add(new int[] {0, cur[0] + cur[1]});
        } else { //fill two with one
            nexts.add(new int[] {Math.max(0, cur[0] - (two - cur[1])), two});
        }
        //4. fill cup two
        nexts.add(new int[] {cur[0], two});
        //5. dump two
        nexts.add(new int[] {cur[0], 0});
        //6. transfer from two to one
        if (cur[0] == one) { //one is full
            //nexts.add(new int[] {cur[0], cur[1]});
        } else if (cur[0] + cur[1] < one) { // can't fill one with two
            nexts.add(new int[] {cur[0] + cur[1], 0});
        } else { // fill one with two
            nexts.add(new int[] {one, Math.max(0, cur[1] - (one - cur[0]))});
        }
        return nexts;
    }
    public static void main(String args[]) {
        TwoCups test = new TwoCups();
        System.out.println(test.twoCups(5,2,4));
    }
}
