public class LC00134GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || cost.length != gas.length) throw new IllegalArgumentException();
        int len = cost.length;
        int gasLeft = 0;
        //use a sliding window, if invalid
        //move start pointer backward to see if can get enough gas to complete trip
        //if valid, keep moving end pointer forward until invalid
        int end = 0;
        int start = len;

        while (end < start) {//[end, start)
            if (gasLeft >= 0) {//valid move end forward
                gasLeft += gas[end] - cost[end];
                end++;
            } else { //invalid, move start backward
                start--;
                gasLeft += gas[start] - cost[start];
            }
        }
        return gasLeft >= 0 ? start % len : -1;
    }
    public static void main(String args[]) {
        LC00134GasStation test = new LC00134GasStation();
        System.out.println("gas = [1,2,3,4,5], cost = [3,4,5,1,2]: start at index: "
                + test.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        System.out.println("gas = [2,3,4], cost = [3,4,3]: start at index: "
                + test.canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }
}
