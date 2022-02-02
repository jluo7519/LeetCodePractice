public class ZhongHua {
    public int maxFlower(int[] array) {
        if (array == null) throw new IllegalArgumentException();
        int len = array.length;
        if (len == 0) return 0;
        int res = 0;

        for (int i = 0; i < len; i++) {
            if (array[i] != 0) continue;
            //case 1
            if ((i - 1 < 0 || array[i - 1] != 0) && (i - 2 < 0 || array[i - 2] == 0) && (i + 1 >= len || array[i + 1] == 0)) {
                res++;
                array[i] = 2;
            } else if ((i - 1 < 0 || array[i - 1] == 0) && (i + 1 >= len || array[i + 1] != 0) && (i + 2 >= len || array[i + 2] == 0)) {
                res++;
                array[i] = 2;
            } else if ((i - 1 < 0 || array[i - 1] == 0) && (i + 1 >= len || array[i + 1] == 0)) {
                res++;
                array[i] = 2;
            }
        }
        return res;
    }

    public static void main(String args[]) {
        ZhongHua test = new ZhongHua();
        System.out.println(test.maxFlower(new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 1}));
        System.out.println(test.maxFlower(new int[]{1, 0, 1, 0, 1, 0, 0, 0, 0, 1}));
        System.out.println(test.maxFlower(new int[]{1, 0, 0, 1, 0, 1, 0, 0, 0, 1}));
    }
}
