import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DedupIterator { // Limitation: cannot handle null in list
    private Integer buffer;
    private Iterator<Integer> it;
    public DedupIterator(List<Integer> list) {
        this.buffer = null;
        it = list.iterator();
    }

    public int next() {
        if (!hasNext()) {
            throw new RuntimeException("no next");
        }
        Integer cur = null;
        if (buffer == null) { // 说明之前的不是重复
            cur = it.next();
        } else {             // 之前是重复, 所以越过了一个
            cur = buffer;    // 把之前越过的assign过来和后面比较 因为buffer是之前重复后的第一个数 有可能后面自己也有重复
            buffer = null;   // 如果buffer里是最后一个element的话 不reset会影响hasNext()判断
        }

        while (it.hasNext()) {
            int val = it.next(); // 如果重复就跳过
            if (val != cur) { // 找到第一个非重复element后break
                buffer = val;
                break;
            }
        }
        return cur;
    }

    public boolean hasNext() {
        return buffer != null || it.hasNext();
    }

    public static void main(String args[]) {
        DedupIterator it1 = new DedupIterator(Arrays.asList(1,2,2,2,3,3,2,2,4));
        while (it1.hasNext()) {
            System.out.print("" + it1.next() + " ");
        }
        System.out.print(it1.hasNext() + "\n");
        DedupIterator it2 = new DedupIterator(Arrays.asList(1,3,5,2,3,6,2,2,4));
        while (it2.hasNext()) {
            System.out.print("" + it2.next() + " ");
        }
        System.out.print(it2.hasNext() + "\n");
        DedupIterator it3 = new DedupIterator(Arrays.asList(1,1,1,1,1,1));
        while (it3.hasNext()) {
            System.out.print("" + it3.next() + " ");
        }
        System.out.print(it3.hasNext() + "\n");
    }
}
