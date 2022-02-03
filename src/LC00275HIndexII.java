public class LC00275HIndexII {
    public int hIndex(int[] citations) {
        if (citations == null) throw new IllegalArgumentException();
        int len = citations.length;
        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) { // go right
                left = mid + 1;
            } else { // go left
                right = mid - 1;
            }
        }
        //[right, left]
        return len - left;
    }
}
