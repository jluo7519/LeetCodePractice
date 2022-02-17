public class MicrosoftOALightingBulbs {
    public int numMoment(int[] array) {
        // 每次打开一个灯亮不亮只要看前一个
        // 但是后面要看n - k个直到某个flag is false
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException();
        }
        int prevMax = 0; // tracks so far the most right bulb turned on
        int moments = 0;
        for (int i = 0; i < array.length; i++) {
            // i + 1是当前打开的总个数
            // 之前打开最大的必须小于等于总个数; 当前打开的编号也小于等于总个数
            if (prevMax <= i + 1 && array[i] <= (i + 1)) {
                moments++;
            }
            prevMax = Math.max(prevMax, array[i]);
        }
        return moments;
    }
    //
    public static void main(String args[]) {
        MicrosoftOALightingBulbs test = new MicrosoftOALightingBulbs();
        int[] input0 = new int[]{2,1,3,5,4}; // should return 3
        int[] input1 = new int[]{2,3,4,1,5}; // should return 2
        int[] input2 = new int[]{1,3,4,2,5}; // should return 3
        System.out.println(test.numMoment(input0));
        System.out.println(test.numMoment(input1));
        System.out.println(test.numMoment(input2));
    }
}
