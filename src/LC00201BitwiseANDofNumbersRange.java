public class LC00201BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd(int left, int right) {
        int shifts = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            shifts++;
        }
        // shift back to get longest common prefix
        return left << shifts;
    }
}
