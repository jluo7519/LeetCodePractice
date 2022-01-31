public class LC00069SquareRootOfX {
    public int mySqrt(int x) {
        if (x < 0) throw new IllegalArgumentException();
        if (x <= 1) return x;

        int left = 1;
        int right = x;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public double mySqrtDouble(int x) {
        if (x < 0) throw new IllegalArgumentException();
        long x10000 = x * 10000;
        long left = 1;
        long right = x10000;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid <= x10000 / mid && (mid + 1) > x10000 / (mid + 1)) {
                return mid / 100d;
            } else if (mid < x10000 / mid){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String args[]) {
        LC00069SquareRootOfX test = new LC00069SquareRootOfX();
        System.out.println(test.mySqrt(4));
        System.out.println(test.mySqrt(8));
        System.out.println(test.mySqrt(3));
        //System.out.println(test.mySqrtDouble(4));
        System.out.println(test.mySqrtDouble(8));
        System.out.println(test.mySqrtDouble(3));
    }
}
